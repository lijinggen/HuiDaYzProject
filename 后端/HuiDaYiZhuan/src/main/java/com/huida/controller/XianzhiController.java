package com.huida.controller;

import com.huida.bean.Comment;
import com.huida.bean.Xianzhi;
import com.huida.service.XianzhiService;
import com.huida.utils.FormatTimeUtils;
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
@RequestMapping("/xianzhi")
public class XianzhiController {

    @Autowired
    XianzhiService xianzhiService;

    @RequestMapping("/search")
    public List<Xianzhi> search(String target) {
        return xianzhiService.search(target);
    }

    @RequestMapping("/loading")
    public List<Xianzhi> loading(int page) {
        return xianzhiService.loading(page);
    }

    @RequestMapping("/uploadImg")
    public String uploadImg(MultipartFile image) throws Exception {
        String Url = "http://192.168.0.118:8080/XianzhiImages/" + image.getOriginalFilename();
        String filePath = "D:/YzImages/XianzhiImages/" + image.getOriginalFilename();
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

    @RequestMapping("/issueXianzhi")
    public void issueXianzhi(@RequestBody Xianzhi xianzhi) {
        xianzhi.setFormatTime(FormatTimeUtils.fomatTime(xianzhi.getIssueTime()));
        xianzhiService.insert(xianzhi);
    }

    @RequestMapping("/getMaxPageNumber")
    public Integer getMaxPageNumber() {
        if(xianzhiService.xianzhiList.size()>0)
            return (xianzhiService.xianzhiList.size() -1)/ 10;
        else
            return xianzhiService.xianzhiList.size()/10;
    }

    @RequestMapping("/detailXianzhi")
    public Xianzhi detailXianzhi(Integer xianzhiID) {
        for (int i = 0; i < xianzhiService.xianzhiList.size(); i++) {
            if (xianzhiService.xianzhiList.get(i).getXianzhiID() == xianzhiID) {
                Xianzhi xianzhi = xianzhiService.xianzhiList.get(i);
                xianzhiService.setWatchTimes(xianzhiID);
                return xianzhi;
            }
        }
        return null;
    }

    @RequestMapping("/issueComment")
    public void insertComment(@RequestBody Comment comment) {
        xianzhiService.insertComment(comment);
    }

    @RequestMapping("/getComment")
    public List<Comment> getComment(Integer xianzhiID) {
        return xianzhiService.getComment(xianzhiID);
    }

    @RequestMapping("/getTempMaxPageNumber")
    public Integer getTempMaxPageNumber() {
        if(xianzhiService.searchList.size()==0)
            return 0;
        if(xianzhiService.searchList.size()>1)
            return (xianzhiService.searchList.size() -1)/ 10;
        else
            return xianzhiService.xianzhiList.size()/10;
    }

    @RequestMapping("/loadTempList")
    public List<Xianzhi> loadTempList(Integer page) {
        return xianzhiService.loadTempList(page);
    }
}
