package com.txl.activemq.activemq.bbms;

import java.util.List;

/**
 * Created by TangXiangLin on 2022-09-05 10:36
 * 登机桥设备
 */
public class BbmsDevice {
    /** 设备编号 */
    private String equipmentCode;
    /** 机位编号 */
    private String standCode;
    /** 是否在线 */
    private Boolean isOnline;
    /** 是否在使用 */
    private Boolean isInUse;
    /** 是否有故障 */
    private Boolean hasError;
    /** 设备使用时, 关键状态 */
    private List<String> eqpRunningStatuses;
    /** 设备有故障时, 故障的描述 */
    private List<String> eqpErrorStatuses;
    /** 上报时间 */
    private String reportTime;

    //1. Constructor
    protected BbmsDevice() {
    }

    protected BbmsDevice(Builder builder){
        this.equipmentCode = builder.equipmentCode;
        this.standCode = builder.standCode;
        this.isOnline = builder.isOnline;
        this.isInUse = builder.isInUse;
        this.hasError = builder.hasError;
        this.eqpRunningStatuses = builder.eqpRunningStatuses;
        this.eqpErrorStatuses = builder.eqpErrorStatuses;
        this.reportTime = builder.reportTime;
    }

    //2. Builder
    public static Builder create(){
        return new Builder();
    }

    public static class Builder{
        private String equipmentCode;
        private String standCode;
        private Boolean isOnline;
        private Boolean isInUse;
        private Boolean hasError;
        private List<String> eqpRunningStatuses;
        private List<String> eqpErrorStatuses;
        private String reportTime;

        public Builder reportTime(String v){
            this.reportTime = v;
            return this;
        }

        public Builder eqpErrorStatuses(List<String> l){
            this.eqpErrorStatuses = l;
            return this;
        }

        public Builder eqpRunningStatuses(List<String> l){
            this.eqpRunningStatuses = l;
            return this;
        }

        public Builder hasError(Boolean v){
            this.hasError = v;
            return this;
        }

        public Builder isInUse(Boolean v){
            this.isInUse = v;
            return this;
        }

        public Builder isOnline(Boolean v){
            this.isOnline = v;
            return this;
        }

        public Builder standCode(String v){
            this.standCode = v;
            return this;
        }

        public Builder equipmentCode(String v){
            this.equipmentCode = v;
            return this;
        }

        public BbmsDevice build(){
            return new BbmsDevice(this);
        }

    }

    // 3. Setter
    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public void setStandCode(String standCode) {
        this.standCode = standCode;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public void setInUse(Boolean inUse) {
        isInUse = inUse;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public void setEqpRunningStatuses(List<String> eqpRunningStatuses) {
        this.eqpRunningStatuses = eqpRunningStatuses;
    }

    public void setEqpErrorStatuses(List<String> eqpErrorStatuses) {
        this.eqpErrorStatuses = eqpErrorStatuses;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    // 4. Getter
    public String getEquipmentCode() {
        return equipmentCode;
    }

    public String getStandCode() {
        return standCode;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public Boolean getInUse() {
        return isInUse;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public List<String> getEqpRunningStatuses() {
        return eqpRunningStatuses;
    }

    public List<String> getEqpErrorStatuses() {
        return eqpErrorStatuses;
    }

    public String getReportTime() {
        return reportTime;
    }


    // 5. ToString
    @Override
    public String toString() {
        return "BbmsDevice{" +
                "equipmentCode='" + equipmentCode + '\'' +
                ", standCode='" + standCode + '\'' +
                ", isOnline=" + isOnline +
                ", isInUse=" + isInUse +
                ", hasError=" + hasError +
                ", eqpRunningStatuses=" + eqpRunningStatuses +
                ", eqpErrorStatuses=" + eqpErrorStatuses +
                ", reportTime='" + reportTime + '\'' +
                '}';
    }
}
