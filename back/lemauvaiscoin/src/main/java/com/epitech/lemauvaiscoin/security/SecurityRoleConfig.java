package com.epitech.lemauvaiscoin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.core.GrantedAuthorityDefaults;

@Configuration
public class SecurityRoleConfig {

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(SecurityConstants.ROLE_PREFIX);
    }

}
