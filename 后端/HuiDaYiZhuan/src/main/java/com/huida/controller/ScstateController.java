package com.huida.controller;

import com.huida.bean.Scstate;
import com.huida.bean.ScstateComment;
import com.huida.service.ScstateService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/scstate")
public class ScstateController {

    @Autowired
    private ScstateService scstateService;

    @RequestMapping("/uploadImg")
    public String uploadImg(MultipartFile image) throws Exception {
        String Url = "http://192.168.0.118:8080/ScstateImages/" + image.getOriginalFilename();
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

    @RequestMapping("/issueScstate")
    public void issueScstate(@RequestBody Scstate scstate) {
        scstateService.issueScstate(scstate);
    }

    @RequestMapping("/loading")
    public List<Scstate> loading(int page) {
        return scstateService.loading(page);
    }

    @RequestMapping("/issueComment")
    public void issueComment(@RequestBody ScstateComment scstateComment) {
        scstateService.issueComment(scstateComment);
    }

    @RequestMapping("/getMaxPage")
    public Integer getMaxPage() {

        if (scstateService.scstateList == null || scstateService.scstateList.size() == 0)
            return 0;
        if (scstateService.scstateList.size() > 1)
            return (scstateService.scstateList.size() - 1) / 10;
        else
            return 0;
    }
}
