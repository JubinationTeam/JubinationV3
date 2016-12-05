package com.jubination.backend.service.security;

import java.util.Date;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class IpAwarePersistentTokenRepository implements PersistentTokenRepository {
    private final PersistentTokenRepository delegateRepository;

    public IpAwarePersistentTokenRepository(PersistentTokenRepository delegateRepository) {
        if (delegateRepository == null) {
            throw new IllegalArgumentException("delegateRepository cannot be null");
        }
        this.delegateRepository = delegateRepository;
    }

   
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        String ipSeries = ipSeries(token.getSeries());
        System.out.println("logged from "+ipSeries);
        PersistentRememberMeToken ipToken = tokenWithSeries(token, ipSeries);
        this.delegateRepository.createNewToken(ipToken);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        String ipSeries = ipSeries(series);
        this.delegateRepository.updateToken(ipSeries, tokenValue, lastUsed);
    }


    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        String ipSeries = ipSeries(seriesId);
        PersistentRememberMeToken ipToken = delegateRepository.getTokenForSeries(ipSeries);
        return tokenWithSeries(ipToken, seriesId);
    }

 
    @Override
    public void removeUserTokens(String username) {
        delegateRepository.removeUserTokens(username);
    }

    
    private PersistentRememberMeToken tokenWithSeries(PersistentRememberMeToken token, String series) {
        if (token == null) {
            return null;
        }
        return new PersistentRememberMeToken(token.getUsername(), series, token.getTokenValue(), token.getDate());
    }

 
    private String ipSeries(String series) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new IllegalStateException("RequestContextHolder.getRequestAttributes() cannot be null");
        }
        return series + attributes.getRequest().getRemoteAddr();
    }
}