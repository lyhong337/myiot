package com.chinaunicom.myiot.timer;

import com.chinaunicom.myiot.utils.DateUtils;
import com.chinaunicom.myiot.utils.HttpUtils;
import com.chinaunicom.myiot.utils.SimUtils;
import com.chinaunicom.myiot.vo.Sim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AutoConfigTimer {

    private static Logger logger = LoggerFactory.getLogger(AutoConfigTimer.class);

    @Value("${filePath}")
    private String filePath;

    @Value("${url}")
    private String url;

    @Value("${authorization}")
    private String authorization;

    @Value("${autoSwtich}")
    private String autoSwtich;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void scheduled(){
        if("1".equals(autoSwtich)) {
            logger.info("=====>>>>>定时任务Start>>>>>", System.currentTimeMillis());

            ArrayList<Sim> sims = SimUtils.Reader(filePath + "result/sims.csv");

            for (Sim sim : sims) {
                String iccid = sim.getIccid();
                //String customerfield = sim.getCustomerfield();
                String starttime = sim.getStarttime().replace(":", "");
                String endtime = sim.getEndtime().replace(":", "");
                String state = sim.getState();

                String currentTime = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss").substring(11, 16).replace(":", "");

                if (currentTime.compareToIgnoreCase(starttime) < 0) {
                    if (!"0".equals(state)) { // 不是关闭
                        String status = "DEACTIVATED";
                        String stringJson = "{\"status\": \"" + status + "\"}";
                        String content = HttpUtils.httpPut(url + iccid, authorization, stringJson);
                        logger.info(iccid + " 调用" + status + "返回：" + content);

                        sim.setState("0");
                    }
                }

                if (currentTime.compareToIgnoreCase(starttime) > 0 && currentTime.compareToIgnoreCase(endtime) < 0) {
                    if (!"1".equals(state)) { // 不是开启
                        String status = "ACTIVATED";
                        String stringJson = "{\"status\": \"" + status + "\"}";
                        String content = HttpUtils.httpPut(url + iccid, authorization, stringJson);
                        logger.info(iccid + " 调用" + status + "返回：" + content);

                        sim.setState("1");
                    }

                }

                if (currentTime.compareToIgnoreCase(endtime) > 0) {
                    if (!"0".equals(state)) { // 不是关闭
                        String status = "DEACTIVATED";
                        String stringJson = "{\"status\": \"" + status + "\"}";
                        String content = HttpUtils.httpPut(url + iccid, authorization, stringJson);
                        logger.info(iccid + " 调用" + status + "返回：" + content);

                        sim.setState("0");
                    }
                }

            }

            SimUtils.Writer(sims, filePath + "result/sims.csv");

            logger.info("=====>>>>>定时任务End>>>>>", System.currentTimeMillis());
        }
    }
}
