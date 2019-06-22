package com.chinaunicom.myiot.utils;

import com.chinaunicom.myiot.vo.Sim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;

public class SimUtils {
    private static final Logger logger = LoggerFactory.getLogger(SimUtils.class);

    public static void addSim(Sim sim, String filename) {
        ArrayList<Sim> sims = Reader(filename);

        boolean isExist = false;
        for(int index=0; index<sims.size(); index++){
            Sim s = sims.get(index);
            if(sim.getIccid().equals(s.getIccid())){
                sim.setState(s.getState());
                sims.set(index, sim);
                isExist = true;
                break;
            }
        }

        if(!isExist){
            sim.setState("9"); // 默认存"9"
            sims.add(sim);
        }

        Writer(sims, filename);
    }

    public static ArrayList<Sim> Reader(String filename){
        ArrayList<Sim> sims = new ArrayList<Sim>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String ch;
            while((ch = br.readLine())!=null){
                String[] simData = ch.split(",");

                if(simData==null || simData.length!=5){
                    logger.warn("数据格式错误！"+ch);
                    continue;
                }

                Sim s = new Sim();
                s.setIccid(simData[0]);
                s.setCustomerfield(simData[1]);
                s.setStarttime(simData[2]);
                s.setEndtime(simData[3]);
                s.setState(simData[4]);

                sims.add(s);
            }
            br.close();

        }catch(Exception e){

        }

        return sims;
    }

    public static void Writer(ArrayList<Sim> sims, String filename){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

            for (int i = 0; i < sims.size(); i++) {
                Sim s = sims.get(i);

                StringBuilder sb = new StringBuilder();
                sb.append(s.getIccid().trim()).append(",")
                        .append(s.getCustomerfield().trim()).append(",")
                        .append(s.getStarttime().trim()).append(",")
                        .append(s.getEndtime().trim()).append(",")
                        .append(s.getState().trim());

                bw.write(sb.toString());
                bw.newLine();
                bw.flush();
            }
            bw.close();
        }catch(Exception e){

        }

    }

}
