package com.chinaunicom.myiot.control;

import com.chinaunicom.myiot.utils.DateUtils;
import com.chinaunicom.myiot.utils.SimUtils;
import com.chinaunicom.myiot.vo.Sim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@RestController
public class IccidConfigControl {
    private static final Logger logger = LoggerFactory.getLogger(IccidConfigControl.class);

    @Value("${filePath}")
    private String filePath;

    @RequestMapping(value = "/singleConfig",method = RequestMethod.POST)
    public String singleConfig(Sim sim) {
        String ICCID = sim.getIccid();
        //String customerfield = sim.getCustomerfield();
        String starttime = sim.getStarttime();
        String endtime = sim.getEndtime();

        if (ICCID == null || "".equals(ICCID)){
            return "请输入正确的ICCID！";
        }
        if (starttime == null || "".equals(starttime)){
            return "请输入正确的启用时间！";
        }
        if (endtime == null || "".equals(endtime)){
            return "请输入正确的停用时间！";
        }

        SimUtils.addSim(sim, filePath+"result/sims.csv");

        return "ICCID:"+ ICCID + " 配置成功！";
    }

    //文件上传
    @PostMapping(value = "/batchConfigUpload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "文件上传失败，文件为空!";
        }
        // 获取文件名
        String originalFilename = file.getOriginalFilename();
        logger.info("上传文件的文件名是：" + originalFilename);

        // 获取文件后缀名
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        logger.info("上传文件的后缀名是：" + suffixName);

        if(!".txt".equals(suffixName) && !".TXT".equals(suffixName)){
            return "请上传txt格式的文本文件！";
        }

        //String filePath = request.getSession().getServletContext().getRealPath("/uploadFiles/");
        // 获取UUID名称
        //String fileName = UUID.randomUUID() + suffixName;
        String fileName = DateUtils.getYYYYMMDDHHMMSS(new Date()) + suffixName;

        // 获取上传文件的File对象
        File dest = new File(filePath + "upload/" + fileName);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);
            logger.info("上传成功！"+ filePath + "upload/" + fileName);

            //解析文件
            BufferedReader br = new BufferedReader(new FileReader(filePath + "upload/" + fileName));
            String ch;
            while((ch = br.readLine())!=null){
                String[] simData = ch.split(",");

                if(simData==null || simData.length!=4){
                    logger.warn("数据格式错误！"+ch);
                    continue;
                }

                String ICCID = simData[0];
                if("ICCID".equals(ICCID)){
                    continue;
                }
                String customerfield = simData[1];
                String starttime = simData[2];
                String endtime = simData[3];

                Sim sim = new Sim();
                sim.setIccid(ICCID);
                sim.setCustomerfield(customerfield);
                sim.setStarttime(starttime);
                sim.setEndtime(endtime);

                SimUtils.addSim(sim, filePath+"result/sims.csv");

            }

            br.close();

            return originalFilename + "上传成功！";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "上传失败！";
    }

    @GetMapping(value = "/downloadTemplate")
    public String downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        // 获取指定目录下的第一个文件
        File scFileDir = new File(filePath + "template/");
        File TrxFiles[] = scFileDir.listFiles();

        String fileName = TrxFiles[0].getName(); //下载的文件名

        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            File file = new File(filePath + "template/", fileName);

            // 如果文件名存在，则进行下载
            if (file.exists()) {
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    logger.info("Download file successfully!");
                }
                catch (Exception e) {
                    logger.info("Download file failed!");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    @GetMapping(value = "/downloadResultFile")
    public String downloadResultFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        // 获取指定目录下的第一个文件
        File scFileDir = new File(filePath+"result/");
        File TrxFiles[] = scFileDir.listFiles();

        String fileName = TrxFiles[0].getName(); //下载的文件名

        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            File file = new File(filePath+"result/sims.csv");

            // 如果文件名存在，则进行下载
            if (file.exists()) {
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    logger.info("Download file successfully!");
                }
                catch (Exception e) {
                    logger.info("Download file failed!");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

}
