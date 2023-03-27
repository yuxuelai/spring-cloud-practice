package com.lavender.controller;

import com.alibaba.fastjson.JSONObject;
import com.lavender.pojo.ProductModel;
import com.lavender.pojo.ResultModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;


@Api(tags = "the arrangement of FileUp")
@RestController
//@RequestMapping(value = "/file",method = RequestMethod.POST)
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class FileupController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

//    @RequestMapping(value = "file/upload",method = RequestMethod.POST)
//    @ResponseBody
//    public String upload(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws FileNotFoundException {
//        //获取前台上传人
//        String name = request.getParameter("name");
//        //获取上传文件的原始名称
//        String filename = file.getOriginalFilename();
//        System.out.println("上传文件名："+filename);
//        String suffixName = filename.substring(filename.lastIndexOf("."));
//        System.out.println("截取的后缀名："+suffixName);
//        //产生随机文件名称
//        filename= UUID.randomUUID()+suffixName;
//
//        //创建文件流
//        String filepath="D:\\JavaWebProject\\back-spring\\web\\upload\\temp\\";
//        File img=new File(filepath+filename);
//        try {//开始上传文件
//            file.transferTo(img);
//            return "success";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "success";
//    }


//428350332926427136
//428350332926427136


    @RequestMapping(value = "/file/upload")
    @ResponseBody
    public ResultModel upload(@RequestParam("file") MultipartFile file){

        String s = saveFile (file, request, response);
        ProductModel productModel = new ProductModel ();
        productModel.setImgHref (s);

        ResultModel res = ResultModel.getResult (productModel);
        return res;

    }

    /**
     * 把单个的上传提取成一个方法
     * @param file
     *
     */
    private String saveFile(MultipartFile file , HttpServletRequest req, HttpServletResponse resp) {

        // 设置UUID
//        Long time = System.currentTimeMillis ();
        UUID id = UUID.randomUUID ();

        // 获得上传的文件名
        String fileName = file.getOriginalFilename ();

        System.out.println (fileName);
        // 客户端可能不选择文件就上传，这时需要判断一下，只有文件名不为null或不是""时才保存文件。思考一下为什么要trim？
        if (fileName != null && !fileName.trim ().equals ("")) {

            resp.setHeader ("Content-Type", "multipart/form-data");
//            resp.setHeader ("ContentType","multipart/form-data");


            // 在当前项目下创建一个upload目录，用来存放上传的文件
            String realPath = req.getSession ().getServletContext ().getRealPath ("/upload/");
            System.out.println (realPath);
            File desFile = new File (realPath);

            if (desFile.exists () == false) {
                //如果这个目录不存在，就创建
                desFile.mkdirs ();
            }
            // 需要保存的文件对象
            desFile = new File (desFile.getAbsoluteFile () + "/" + fileName);
            try {
                //保存文件
                file.transferTo (desFile);
//                File fileSource = desFile.getAbsoluteFile ();
                String filepath = "D:\\第七阶段-SpringBoot\\back-web\\upload\\" + id+fileName;
                File file1 = new File (filepath);
                copyFileUsingStream (desFile, file1);
                return "/upload/"+id + fileName;
            } catch (IOException e) {
                throw new RuntimeException (e);
            }
        }
//        return "/img/" +System.currentTimeMillis ()+ fileName;

        return "/upload/"+id + fileName;

    }

    // 复制文件到
    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {

            is = new FileInputStream (source);
            os = new FileOutputStream (dest);
//            以1024字节读出
            byte[] buffer = new byte[1024];
            int length;
//            写入到os中
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }


}
