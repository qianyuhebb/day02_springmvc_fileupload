package cn.itcast.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.UUID;

/**
 * 时间：  2020/1/25
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
@Controller
@RequestMapping("/user")
public class UserController {


    /**
     * 文件上传
     * @return
     */
    @RequestMapping("fileUpload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");
//        String path = request.getSession().getServletContext().getRealPath("/uploads/");
       String path ="H:\\uploads\\";
        System.out.println(path);
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            //判断上传的是表单项还是普通文件项
            if (fileItem.isFormField()){
                //说明是普通表单项
            }else {
                //说明上传的是文件项 ,后去文件名称
                String name = fileItem.getName();
                System.out.println(name);
                //完成上传
                fileItem.write(new File( path,name));
                //删除临时文件
                fileItem.delete();
            }
        }

        return  "success";
    }

    /**
     * spring mvc  文件上传
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("fileUpload2")
    public String fileUpload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("spring mvc  文件上传");
//        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        String path ="H:\\uploads\\";
        System.out.println(path);
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }

       /* DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            //判断上传的是表单项还是普通文件项
            if (fileItem.isFormField()){
                //说明是普通表单项
            }else {*/
        //说明上传的是文件项 ,后去文件名称
//                String name = fileItem.getName();
        String name = upload.getOriginalFilename();
        System.out.println(name);
        //完成上传
//                fileItem.write(new File( path,name));
        upload.transferTo(new File(path,name));
        //删除临时文件
//                fileItem.delete();
       /*     }
        }*/

        return  "success";
    }

    /**
     * 跨服务 文件上传
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("fileUpload3")
    public String fileUpload3( MultipartFile upload) throws Exception {
        System.out.println("跨服务  文件上传");
        String path ="http://localhost:9090/uploads/";
        String name = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        name = uuid +"_" + name;

        //创建客户端对象，与文件服务器连接6
        Client client = Client.create();
        //没有斜杠注意加斜杠
        WebResource webResource = client.resource(path+name);
        webResource.put(upload.getBytes());

        return  "success";
    }
}
