package com.bc.rm.server.entity.econtract;

/**
 * 电子合同个人账号
 *
 * @author zhou
 */
public class EcontractAccount {
    private String id;
    private String thirdPartyUserId;
    private String name;
    private String idType;
    private String idNumber;
    private String mobile;
    private String mail;

    private String apiResultCode;
    private String apiResultMessage;

    public EcontractAccount() {

    }

    public EcontractAccount(String thirdPartyUserId,
                            String name,
                            String idType,
                            String idNumber,
                            String mobile,
                            String mail) {
        this.thirdPartyUserId = thirdPartyUserId;
        this.name = name;
        this.idType = idType;
        this.idNumber = idNumber;
        this.mobile = mobile;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThirdPartyUserId() {
        return thirdPartyUserId;
    }

    public void setThirdPartyUserId(String thirdPartyUserId) {
        this.thirdPartyUserId = thirdPartyUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
}
