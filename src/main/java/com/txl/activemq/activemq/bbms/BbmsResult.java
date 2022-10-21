package com.txl.activemq.activemq.bbms;

import java.util.List;

/**
 * Created by TangXiangLin on 2022-09-05 10:55
 */
public class BbmsResult {
    private List<BbmsDevice> bbmsDevices;

    public BbmsResult() {

    }

    public List<BbmsDevice> getBbmsDevices() {
        return bbmsDevices;
    }

    public void setBbmsDevices(List<BbmsDevice> bbmsDevices) {
        this.bbmsDevices = bbmsDevices;
    }

    @Override
    public String toString() {
        return "BbmsResult{" +
                "bbmsDevices=" + bbmsDevices +
                '}';
    }

}
