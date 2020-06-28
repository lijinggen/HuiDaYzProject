package com.huida.controller;

import com.huida.bean.Renzheng;
import com.huida.service.RenzhengService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/renzheng")
public class RenzhengController {

    @Autowired
    private RenzhengService renzhengService;
    @RequestMapping("/uploadImg")
    public String uploadImg(MultipartFile image) throws Exception {
        String Url = "http://192.168.0.118:8080/RenzhengImg/" + image.getOriginalFilename();
        String filePath = "D:/YzImages/RenzhengImg/" + image.getOriginalFilename();
        FileOutputStream fileOutputStream = null;
        File outFile = new File(filePath);
        InputStream inputStream = null;
        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
            outFile.getParentFile().mkdirs();
        }
        fileOutputStream = new FileOutputStream(outFile);
        fileOutputStream = new FileOutputStream(outFile);
        inputStream = image.getInputStream();
        IOUtils.copy(inputStream, fileOutputStream);
        if (fileOutputStream != null && inputStream != null) {
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
        }
        return Url;
    }

    @RequestMapping("/insert")
    public void insert(@RequestBody Renzheng renzheng){
        renzhengService.insert(renzheng);
    }

    @RequestMapping("/loading")
    public Renzheng loading(String openID){
        return renzhengService.loading(openID);
    }
}
