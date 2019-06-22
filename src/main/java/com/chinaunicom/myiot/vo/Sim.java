package com.chinaunicom.myiot.vo;

import lombok.Data;

@Data
public class Sim {
    private String iccid;
    private String customerfield;
    private String starttime;
    private String endtime;
    private String state; //0 关 1 开 9 未知

}
