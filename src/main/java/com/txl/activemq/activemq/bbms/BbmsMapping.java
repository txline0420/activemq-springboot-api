package com.txl.activemq.activemq.bbms;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created By TangXiangLin on 2022-10-21 11:19
 * 映射关系
 */
public class BbmsMapping {

    private String deviceId;
    private String cId;

    //1. Constructor
    protected BbmsMapping() {
    }

    protected BbmsMapping(Builder builder) {
        this.deviceId = builder.deviceId;
        this.cId = builder.cId;
    }

    //2. Builder
    public static Builder create(){
        return new Builder();
    }

    public static class Builder{
        private String deviceId;
        private String cId;

        public Builder deviceId(String v){
            this.deviceId = v;
            return this;
        }

        public Builder cId(String v){
            this.cId = v;
            return this;
        }

        public BbmsMapping build(){
            return new BbmsMapping(this);
        }
    }

    //3. Setter
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    //4.Getter
    public String getDeviceId() {
        return deviceId;
    }

    public String getcId() {
        return cId;
    }

    //5. CSV
    public List<BbmsMapping> parseFromCSV(){
        List<BbmsMapping> list = new CopyOnWriteArrayList<>();
        try {
            CsvReader reader = CsvUtil.getReader();
            ClassPathResource classPathResource = new ClassPathResource("/db/BoardingBridgeMappingEntry.csv");
            File file = classPathResource.getFile();
            CsvData csvData = reader.read(file);
            List<CsvRow> rows = csvData.getRows();
            for (CsvRow row : rows) {
                BbmsMapping bbmsMapping = BbmsMapping.create()
                        .deviceId(row.get(1))
                        .cId(row.get(2))
                        .build();
                list.add(bbmsMapping);
            }
        } catch (IORuntimeException | NumberFormatException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {

    }

}
