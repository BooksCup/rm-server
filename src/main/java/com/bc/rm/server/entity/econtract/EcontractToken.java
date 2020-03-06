package com.bc.rm.server.entity.econtract;

import com.bc.rm.server.util.CommonUtil;

/**
 * 电子合同token
 *
 * @author zhou
 */
public class EcontractToken {
    private String id;
    private String appId;
    private String secret;
    private String grantType;
    private String content;
    private Long expiresIn;
    private String expiryTime;
    private String createTime;

    public EcontractToken() {

    }

    public EcontractToken(String appId, String secret, String grantType) {
        this.id = CommonUtil.generateId();
        this.appId = appId;
        this.secret = secret;
        this.grantType = grantType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
