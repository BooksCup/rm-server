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
    private String email;
    private String createTime;
    private String modifyTime;

    /**
     * 是否修改成功
     */
    private boolean successFlag = false;

    private String apiResultCode;
    private String apiResultMessage;

    public EcontractAccount() {

    }

    public EcontractAccount(String thirdPartyUserId,
                            String name,
                            String idType,
                            String idNumber,
                            String mobile,
                            String email) {
        this.thirdPartyUserId = thirdPartyUserId;
        this.name = name;
        this.idType = idType;
        this.idNumber = idNumber;
        this.mobile = mobile;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(boolean successFlag) {
        this.successFlag = successFlag;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "EcontractAccount{" +
                "id='" + id + '\'' +
                ", thirdPartyUserId='" + thirdPartyUserId + '\'' +
                ", name='" + name + '\'' +
                ", idType='" + idType + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", successFlag=" + successFlag +
                ", apiResultCode='" + apiResultCode + '\'' +
                ", apiResultMessage='" + apiResultMessage + '\'' +
                '}';
    }
}
