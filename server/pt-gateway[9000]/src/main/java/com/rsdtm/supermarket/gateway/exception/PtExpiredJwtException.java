package com.rsdtm.supermarket.gateway.exception;

import org.springframework.security.core.AuthenticationException;



public class PtExpiredJwtException extends AuthenticationException {
    public PtExpiredJwtException(String msg, Throwable t) {
        super(msg, t);
    }

    public PtExpiredJwtException(String msg) {
        super(msg);
    }

}
