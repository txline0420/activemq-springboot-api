package com.txl.activemq.activemq.bbms;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by TangXiangLin on 2022-09-05 10:55
 */
public class BbmsResult {
    @JSONField(name = "AllEqpStat")
    private List<BbmsDevice> bbmsDevices;

    //1. Constructor
    public BbmsResult() {

    }

    protected BbmsResult(Builder builder) {
        this.bbmsDevices = builder.bbmsDevices;
    }

    //2. Builder
    public static Builder newBuilder(){
        return new Builder();
    }

    public static class Builder{
        private List<BbmsDevice> bbmsDevices;

        public Builder bbmsDevices(List<BbmsDevice> v){
            this.bbmsDevices = v;
            return this;
        }

        public BbmsResult build(){
            return new BbmsResult(this);
        }
    }

    //3. Getter
    public List<BbmsDevice> getBbmsDevices() {
        return bbmsDevices;
    }

    //4. Setter
    public void setBbmsDevices(List<BbmsDevice> bbmsDevices) {
        this.bbmsDevices = bbmsDevices;
    }

    //5. ToString
    @Override
    public String toString() {
        return "BbmsResult{" +
                "bbmsDevices=" + bbmsDevices +
                '}';
    }

}
