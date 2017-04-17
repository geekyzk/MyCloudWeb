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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * Created by tian on 2017/4/17.
 */
@Repository
public class AdminClient {

    CloudFoundryConfig config;

    @Autowired
    public AdminClient(CloudFoundryConfig cloudFoundryConfig) {
        this.config = cloudFoundryConfig;
    }

    private ConnectionContext connectionContext;

    private TokenProvider tokenProvider;

    private CloudFoundryClient cloudFoundryClient;

    private UaaClient uaaClient;

    private DopplerClient dopplerClient;

    public CloudFoundryClient getCloudFoundryClient() {
        return cloudFoundryClient;
    }

    public UaaClient getUaaClient() {
        return uaaClient;
    }

    public DopplerClient getDopplerClient() {
        return dopplerClient;
    }

    @PostConstruct
    public void init(){
        this.connectionContext = DefaultConnectionContext.builder()
                .apiHost(config.getHost())
                .skipSslValidation(true)
                .build();
        this.tokenProvider = PasswordGrantTokenProvider.builder()
                .username(config.getUsername())
                .password(config.getPassword())
                .build();
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

    }

}
