package com.bc.rm.server.entity.econtract;

/**
 * 电子合同机构
 *
 * @author zhou
 */
public class EcontractOrg {
    private String orgId;
    private String thirdPartyUserId;
    private String creator;
    private String name;
    private String idType;
    private String idNumber;
    private String legalIdNumber;
    private String legalName;
    private String createTime;
    private String modifyTime;

    private String apiResultCode;
    private String apiResultMessage;

    public EcontractOrg() {

    }

    public EcontractOrg(
            String thirdPartyUserId,
            String creator,
            String name,
            String idType,
            String idNumber,
            String legalIdNumber,
            String legalName) {
        this.thirdPartyUserId = thirdPartyUserId;
        this.creator = creator;
        this.name = name;
        this.idType = idType;
        this.idNumber = idNumber;
        this.legalIdNumber = legalIdNumber;
        this.legalName = legalName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getThirdPartyUserId() {
        return thirdPartyUserId;
    }

    public void setThirdPartyUserId(String thirdPartyUserId) {
        this.thirdPartyUserId = thirdPartyUserId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public String getLegalIdNumber() {
        return legalIdNumber;
    }

    public void setLegalIdNumber(String legalIdNumber) {
        this.legalIdNumber = legalIdNumber;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
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
