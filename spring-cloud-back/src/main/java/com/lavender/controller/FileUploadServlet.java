package com.lavender.controller;//package com.lavender.controller;
//
//import com.alibaba.fastjson2.JSONObject;
//import com.lavender.pojo.ImgModel;
//import com.lavender.pojo.ResultModel;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//
//import javax.servlet.ServletException;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.List;
//
//@WebServlet("/file/upload")
//public class FileUploadServlet extends HttpServlet {
//    // 上传文件存储目录
//    private static final String UPLOAD_DIRECTORY = "upload";
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("uploadServlet");
//        // 检测是否为多媒体上传
//        if (!ServletFileUpload.isMultipartContent(req)) {
//            // 如果不是则停止
//            PrintWriter writer = resp.getWriter();
//            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
//            writer.flush();
//            return;
//        }
//
//        // 配置上传参数
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
//
//        // 设置临时存储目录
////        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//
////        解析文件
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//        // 中文处理
//        upload.setHeaderEncoding("UTF-8");
//
//        // 构造临时路径来存储上传的文件
//        // 这个路径相对当前应用的目录
//        String uploadPath = req.getServletContext().getRealPath("./") + "/" + UPLOAD_DIRECTORY;
////        ResultDTO resultDTO = null;
//
//        createDirectory(uploadPath);
//
//        try {
////            Integer.parseInt("aa");
//            // 解析请求的内容提取文件数据
//            @SuppressWarnings("unchecked")
//            List<FileItem> formItems = upload.parseRequest(req);
//
//            if (formItems != null && formItems.size() > 0) {
//                // 迭代表单数据
//                for (FileItem item : formItems) {
//                    // 处理不在表单中的字段
//                    if (!item.isFormField()) {
////                        文件名称
//                        String fileName = new File(item.getName()).getName();
//                        fileName = System.currentTimeMillis() + "--" + fileName;
////                        文件保存的绝对路径
//                        String filePath = uploadPath + "/" + fileName;
////                        c://a/b     1566949548--a.jpg
//                        File storeFile = new File(filePath);
//                        // 在控制台输出文件的上传路径
//                        System.out.println("filePath:" + filePath);
//                        // 保存文件到硬盘
//                        item.write(storeFile);
////                        拷贝一份文件到web目录下
////                        createDirectory(path);
//                        copyFile(storeFile, fileName);
//
//                        ImgModel imgModel = new ImgModel();
//                        imgModel.setSrc("/" + UPLOAD_DIRECTORY + "/" + fileName);
//                        System.out.println("ImgResultModel:" + ResultModel.getResult(imgModel));
//                        resp.getWriter().println(JSONObject.toJSONString(ResultModel.getResult(imgModel)));
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            req.setAttribute("message",
//                    "错误信息: " + ex.getMessage());
//        }
//    }
//
//
//    private void createDirectory(String uploadPath) {
//        // 如果目录不存在则创建
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
//    }
//
//    private void copyFile(File storeFile, String fileName) {
//        String path = "D:\\JavaWebProject\\back-spring-2\\web\\upload\\" + UPLOAD_DIRECTORY;
//
//        createDirectory(path);
//        File file = new File(path, fileName);
//        try (InputStream inputStream = new FileInputStream(storeFile);
//             OutputStream outputStream = new FileOutputStream(file);) {
//            int read = 0;
//            while ((read = inputStream.read()) != -1) {
//                outputStream.write(read);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
