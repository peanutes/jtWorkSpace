package tedu.jt.manage.controller;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tedu.jt.common.vo.PicUploadResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 图片下载
 *
 * @author Administrator
 * @create 2017-12-10 13:48
 */
@Controller //文件上传
public class PicUploadController {
    private Logger logger = Logger.getLogger(PicUploadController.class);
    //pic/upload
    @RequestMapping("/pic/upload")
    @ResponseBody
    public PicUploadResult upload(MultipartFile uploadFile){
        PicUploadResult result = new PicUploadResult();
        String fileName = uploadFile.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf("."));
        if(extName.matches("^*.(jpg|gif|jpeg|png)$")){
            try {
                BufferedImage image = ImageIO.read(uploadFile.getInputStream());
                result.setWidth(image.getWidth()+"");
                result.setHeight(image.getHeight()+"");
                //设置路径 一个绝对路径一个相对路径
                String dir = "c:\\jt-upload/";
                String path = "images/"+new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"/";
                //文件名加后缀
                String newFileName = System.currentTimeMillis()+""+ RandomUtils.nextInt(100,999)+extName;
                //相对路劲
                result.setUrl("http://image.jt.com/"+path+newFileName);
                //产生目录
                File _dir = new File(dir+path);
                if(!_dir.exists()){
                    _dir.mkdirs();
                }

                //绝对路径
                uploadFile.transferTo(new File(dir+path+newFileName));
                //返回结果
                return  result;

            }catch (IOException e){
                logger.error(e.getMessage());
                result.setError(1);
            }
        }else {
            result.setError(1);
        }
        return result;
    }
}
