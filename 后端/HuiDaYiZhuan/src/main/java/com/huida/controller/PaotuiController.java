package com.huida.controller;

import com.huida.bean.Paotui;
import com.huida.service.PaotuiService;
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
@RequestMapping("/paotui")
public class PaotuiController {
    @Autowired
    PaotuiService paotuiService;

    @RequestMapping("/uploadImg")
    public String uploadImg(MultipartFile image) throws Exception {
        String Url = "http://192.168.0.118:8080/PaotuiImages/" + image.getOriginalFilename();
        String filePath = "D:/YzImages/ScstateImages/" + image.getOriginalFilename();
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

    @RequestMapping("/issuePaotui")
    public void issuePaotui(@RequestBody Paotui paotui){
        paotuiService.issuePaotui(paotui);
    }

    @RequestMapping("/getMaxPage")
    public Integer getMaxPage() {
        if (paotuiService.paotuiList == null || paotuiService.paotuiList .size() == 0)
            return 0;
        if (paotuiService.paotuiList .size() > 1)
            return (paotuiService.paotuiList .size() - 1) / 10;
        else
            return 0;
    }

    @RequestMapping("/loading")
    public List<Paotui> loading(Integer page){
        return paotuiService.loading(page);
    }
}
