package com.em248.config.cloudfoundry;

import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.doppler.DopplerClient;
import org.cloudfoundry.operations.CloudFoundryOperations;
import org.cloudfoundry.operations.DefaultCloudFoundryOperations;
import org.cloudfoundry.reactor.ConnectionContext;
import org.cloudfoundry.reactor.DefaultConnectionContext;
import org.cloudfoundry.reactor.TokenProvider;
import org.cloudfoundry.reactor.client.ReactorCloudFoundryClient;
import org.cloudfoundry.reactor.doppler.ReactorDopplerClient;
import org.cloudfoundry.reactor.tokenprovider.PasswordGrantTokenProvider;
import org.cloudfoundry.reactor.uaa.ReactorUaaClient;
import org.cloudfoundry.uaa.UaaClient;
import org.cloudfoundry.uaa.UaaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Created by tian on 2017/4/17.
 */
@Repository
@SessionScope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class UserClient {


    private ConnectionContext connectionContext;

    private TokenProvider tokenProvider;

    private CloudFoundryClient cloudFoundryClient;

    private UaaClient uaaClient;

    private DopplerClient dopplerClient;

    private CloudFoundryOperations cloudFoundryOperations;

    CloudFoundryConfig config;

    @Autowired
    public UserClient(CloudFoundryConfig config) {
        this.config = config;
    }

    public ConnectionContext getConnectionContext() {
        return connectionContext;
    }


    public TokenProvider getTokenProvider() {
        return tokenProvider;
    }



    public CloudFoundryClient getCloudFoundryClient() {
        return cloudFoundryClient;
    }


    public UaaClient getUaaClient() {
        return uaaClient;
    }


    public DopplerClient getDopplerClient() {
        return dopplerClient;
    }



    public CloudFoundryOperations getCloudFoundryOperations() {
        return cloudFoundryOperations;
    }



    public Boolean init(String username, String password){
        this.connectionContext = DefaultConnectionContext
                .builder()
                .connectionPoolSize(10)
                .skipSslValidation(true)
                .apiHost(config.getHost())
                .build();
        this.tokenProvider = PasswordGrantTokenProvider
                .builder()
                .username(username)
                .password(password)
                .build();

        try {
            this.tokenProvider.getToken(this.connectionContext).block();
            this.cloudFoundryClient = ReactorCloudFoundryClient
                    .builder()
                    .connectionContext(this.connectionContext)
                    .tokenProvider(this.tokenProvider)
                    .build();
            this.uaaClient = ReactorUaaClient
                    .builder()
                    .connectionContext(this.connectionContext)
                    .tokenProvider(this.tokenProvider)
                    .build();
            this.dopplerClient = ReactorDopplerClient
                    .builder()
                    .connectionContext(this.connectionContext)
                    .tokenProvider(this.tokenProvider)
                    .build();
            this.cloudFoundryOperations = DefaultCloudFoundryOperations.builder()
                    .cloudFoundryClient(this.cloudFoundryClient)
                    .dopplerClient(this.dopplerClient)
                    .uaaClient(this.uaaClient)
                    .build();
            return true;
        } catch (UaaException e) {
            e.printStackTrace();
            return false;
        }

    }

}
