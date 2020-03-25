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
    private String orgLegalIdNumber;
    private String orgLegalName;
    private String createTime;
    private String modifyTime;

    private String apiResultCode;
    private String apiResultMessage;

    public EcontractOrg() {

    }

    public EcontractOrg(String orgId,
                        String name,
                        String idType,
                        String idNumber,
                        String orgLegalIdNumber,
                        String orgLegalName) {
        this.orgId = orgId;
        this.name = name;
        this.idType = idType;
        this.idNumber = idNumber;
        this.orgLegalIdNumber = orgLegalIdNumber;
        this.orgLegalName = orgLegalName;

    }

    public EcontractOrg(String thirdPartyUserId,
                        String creator,
                        String name,
                        String idType,
                        String idNumber,
                        String orgLegalIdNumber,
                        String orgLegalName) {
        this.thirdPartyUserId = thirdPartyUserId;
        this.creator = creator;
        this.name = name;
        this.idType = idType;
        this.idNumber = idNumber;
        this.orgLegalIdNumber = orgLegalIdNumber;
        this.orgLegalName = orgLegalName;
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

    public String getOrgLegalIdNumber() {
        return orgLegalIdNumber;
    }

    public void setOrgLegalIdNumber(String orgLegalIdNumber) {
        this.orgLegalIdNumber = orgLegalIdNumber;
    }

    public String getOrgLegalName() {
        return orgLegalName;
    }

    public void setOrgLegalName(String orgLegalName) {
        this.orgLegalName = orgLegalName;
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

    @Override
    public String toString() {
        return "EcontractOrg{" +
                "orgId='" + orgId + '\'' +
                ", thirdPartyUserId='" + thirdPartyUserId + '\'' +
                ", creator='" + creator + '\'' +
                ", name='" + name + '\'' +
                ", idType='" + idType + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", orgLegalIdNumber='" + orgLegalIdNumber + '\'' +
                ", orgLegalName='" + orgLegalName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", apiResultCode='" + apiResultCode + '\'' +
                ", apiResultMessage='" + apiResultMessage + '\'' +
                '}';
    }
}
