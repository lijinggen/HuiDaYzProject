package com.huida.controller;

import com.huida.bean.Wenda;
import com.huida.bean.WendaComment;
import com.huida.service.WendaService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/wenda")
public class WendaController {

    @Autowired
    WendaService wendaService;
    @RequestMapping("/uploadImg")
    public String uploadImg(MultipartFile image) throws Exception {
        String Url="http://192.168.0.118:8080/WendaImages/"+image.getOriginalFilename();
        String filePath="D:/YzImages/WendaImages/"+image.getOriginalFilename();
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
        if(fileOutputStream!=null&&inputStream!=null){
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
        }
        return Url;
    }

    @RequestMapping("/issueWenda")
    public void issueWenda(@RequestBody Wenda wenda){
        wendaService.issueWenda(wenda);
    }

    @RequestMapping("/loading")
    public List<Wenda> loding(int page){
        return wendaService.loading(page);
    }

    @RequestMapping("/issueComment")
    public void issueComment(@RequestBody WendaComment wendaComment){
        wendaService.issueComment(wendaComment);
    }
}
