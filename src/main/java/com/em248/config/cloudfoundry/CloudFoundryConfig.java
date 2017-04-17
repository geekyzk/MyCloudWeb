package com.em248.config.cloudfoundry;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by tian on 2017/4/17.
 */
@ConfigurationProperties(prefix = "cloudfoundry")
@PropertySource("classpath:cloudfoundry.properties")
@Component
public class CloudFoundryConfig {

    private String host;

    private String username;

    private String password;

    private String defaultCreateUserOrgGuid;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDefaultCreateUserOrgGuid() {
        return defaultCreateUserOrgGuid;
    }

    public void setDefaultCreateUserOrgGuid(String defaultCreateUserOrgGuid) {
        this.defaultCreateUserOrgGuid = defaultCreateUserOrgGuid;
    }
}
