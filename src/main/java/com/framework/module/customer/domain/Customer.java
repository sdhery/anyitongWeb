package com.framework.module.customer.domain;

import com.framework.common.domain.BaseDomain;


public class Customer extends BaseDomain {
    private Integer customerId;
    private String realName;
    private Integer sex;//性别
    private String idCard;//身份证
    private String address;//住所地址
    private String purchaseType;//购买类型
    private String homeSituation;//居家状况
    private String mobilePhone;//移动电话
    private String phone;//固定电话
    private String roadTraffic;//交通道路
    private Integer sysUserId;//录入者
    private String pagerId;//呼叫器ID
    private String mapLongitude;//地图经度
    private String mapLatitude;//地图纬度
    private String mapPicPath;
    private String recordPath;
    private Integer approvalStatus;
    private String approvalDes;
    private Integer approvalSysUserId;
    private String certPicPath;
    private String backCertPicPath;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getHomeSituation() {
        return homeSituation;
    }

    public void setHomeSituation(String homeSituation) {
        this.homeSituation = homeSituation;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoadTraffic() {
        return roadTraffic;
    }

    public void setRoadTraffic(String roadTraffic) {
        this.roadTraffic = roadTraffic;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getPagerId() {
        return pagerId;
    }

    public void setPagerId(String pagerId) {
        this.pagerId = pagerId;
    }

    public String getMapLongitude() {
        return mapLongitude;
    }

    public void setMapLongitude(String mapLongitude) {
        this.mapLongitude = mapLongitude;
    }

    public String getMapLatitude() {
        return mapLatitude;
    }

    public void setMapLatitude(String mapLatitude) {
        this.mapLatitude = mapLatitude;
    }

    public String getMapPicPath() {
        return mapPicPath;
    }

    public void setMapPicPath(String mapPicPath) {
        this.mapPicPath = mapPicPath;
    }

    public String getRecordPath() {
        return recordPath;
    }

    public void setRecordPath(String recordPath) {
        this.recordPath = recordPath;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalDes() {
        return approvalDes;
    }

    public void setApprovalDes(String approvalDes) {
        this.approvalDes = approvalDes;
    }

    public Integer getApprovalSysUserId() {
        return approvalSysUserId;
    }

    public void setApprovalSysUserId(Integer approvalSysUserId) {
        this.approvalSysUserId = approvalSysUserId;
    }

    public String getCertPicPath() {
        return certPicPath;
    }

    public void setCertPicPath(String certPicPath) {
        this.certPicPath = certPicPath;
    }

    public String getBackCertPicPath() {
        return backCertPicPath;
    }

    public void setBackCertPicPath(String backCertPicPath) {
        this.backCertPicPath = backCertPicPath;
    }
}
