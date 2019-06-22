package com.chinaunicom.myiot.service;

import com.chinaunicom.myiot.utils.SimUtils;
import com.chinaunicom.myiot.vo.Sim;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimService {

    public List<Sim> querySims(String filename){
        ArrayList<Sim> simList = SimUtils.Reader(filename);
        return simList;
    }

}
