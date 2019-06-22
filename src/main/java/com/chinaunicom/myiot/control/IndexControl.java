package com.chinaunicom.myiot.control;


import com.chinaunicom.myiot.service.SimService;
import com.chinaunicom.myiot.utils.SimUtils;
import com.chinaunicom.myiot.vo.Sim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexControl {

    private static final Logger logger = LoggerFactory.getLogger(IndexControl.class);

    @Value("${filePath}")
    private String filePath;

    @Autowired
    SimService simService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/singleConfigIndex",method = RequestMethod.GET)
    public String singleConfigIndex() {
        return "singleConfigIndex";
    }

    @RequestMapping(value = "/batchConfigIndex",method = RequestMethod.GET)
    public String batchConfigIndex() {
        return "batchConfigIndex";
    }

    @RequestMapping(value = "/showDetailIndex",method = RequestMethod.GET)
    public String showDetailIndex(Model model) {

        logger.info("filePath:" + filePath);

        List<Sim> simList = simService.querySims(filePath+"result/sims.csv");
        model.addAttribute("simList",simList);
        return "iccidList";
    }

}
