package org.springboot.security.service;

import com.nimbusds.jose.JOSEException;

public interface AuthService {

    String login(String username, String password) throws JOSEException;
}
