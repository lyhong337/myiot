package com.chinaunicom.myiot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinaunicom.myiot.utils.HttpUtils;
import com.chinaunicom.myiot.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyiotApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    private String getFileStorePath(String courtId, String seesionId){
        String fileStorePath = null;
        //请求参数
        String data = "{\"courtId\":\"" + courtId + "\",\"sessionId\":\"" + seesionId + "\"}";
        String fileServiceUrl="http://111.11.11.11:8086";
        //发送请求，获取结果
        String result = HttpUtils.doPost(fileServiceUrl + "/ms-service/voice/search", data);
        if(StringUtils.isNotBlank(result)){
            JSONObject jsonobject = JSON.parseObject(result);
            fileStorePath = jsonobject.getString("path");

        }
        return fileStorePath;
    }

}
