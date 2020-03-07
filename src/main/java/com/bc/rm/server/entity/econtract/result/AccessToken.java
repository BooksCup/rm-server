package com.bc.rm.server.entity.econtract.result;

/**
 * token
 *
 * @author zhou
 */
public class AccessToken {
    private Long expiresIn;
    private String token;
    private String refreshToken;

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
