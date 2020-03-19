package com.bc.rm.server.entity.econtract;

/**
 * 印章
 *
 * @author zhou
 */
public class EcontractSeal {

    private String sealId;
    /**
     * 0: 个人印章
     * 1: 机构印章
     */
    private String accountType;

    /**
     * 创建类型
     * 0: 通过模板创建
     * 1: 通过图片创建
     */
    private String createType;

    private String accountId;
    private String alias;
    private String color;
    private Integer height;
    private Integer width;
    private String type;

    private String orgId;
    private String htext;
    private String qtext;
    private String central;

    private String data;
    private Boolean transparentFlag;

    private String fileKey;
    private String url;

    private String createTime;

    private String apiResultCode;
    private String apiResultMessage;

    /**
     * return field
     */
    private String createDate;
    private Boolean defaultFlag;
    private Integer sealType;
    private String sealBizType;


    public EcontractSeal() {

    }

    public EcontractSeal(String accountId,
                         String alias,
                         String color,
                         Integer height,
                         Integer width,
                         String type) {
        this.accountId = accountId;
        this.alias = alias;
        this.color = color;
        this.height = height;
        this.width = width;
        this.type = type;
    }

    public EcontractSeal(String orgId,
                         String alias,
                         String color,
                         Integer height,
                         Integer width,
                         String htext,
                         String qtext,
                         String type,
                         String central) {
        this.orgId = orgId;
        this.alias = alias;
        this.color = color;
        this.height = height;
        this.width = width;
        this.htext = htext;
        this.qtext = qtext;
        this.type = type;
        this.central = central;
    }

    public String getSealId() {
        return sealId;
    }

    public void setSealId(String sealId) {
        this.sealId = sealId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getHtext() {
        return htext;
    }

    public void setHtext(String htext) {
        this.htext = htext;
    }

    public String getQtext() {
        return qtext;
    }

    public void setQtext(String qtext) {
        this.qtext = qtext;
    }

    public String getCentral() {
        return central;
    }

    public void setCentral(String central) {
        this.central = central;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getTransparentFlag() {
        return transparentFlag;
    }

    public void setTransparentFlag(Boolean transparentFlag) {
        this.transparentFlag = transparentFlag;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getApiResultCode() {
        return apiResultCode;
    }

    public void setApiResultCode(String apiResultCode) {
        this.apiResultCode = apiResultCode;
    }

    public String getApiResultMessage() {
        return apiResultMessage;
    }

    public void setApiResultMessage(String apiResultMessage) {
        this.apiResultMessage = apiResultMessage;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public Integer getSealType() {
        return sealType;
    }

    public void setSealType(Integer sealType) {
        this.sealType = sealType;
    }

    public String getSealBizType() {
        return sealBizType;
    }

    public void setSealBizType(String sealBizType) {
        this.sealBizType = sealBizType;
    }
}
