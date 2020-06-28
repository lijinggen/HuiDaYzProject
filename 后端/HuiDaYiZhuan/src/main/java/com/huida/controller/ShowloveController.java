package com.huida.controller;

import com.huida.bean.Comment;
import com.huida.bean.Showlove;
import com.huida.service.ShowloveService;
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
@RequestMapping("/showlove")
public class ShowloveController {

    @Autowired
    private ShowloveService showloveService;

    @RequestMapping("/issueShowlove")
    public void issueShowlove(@RequestBody Showlove showlove){
        showlove.setFormatTime(FormatTimeUtils.fomatTime(showlove.getIssueTime()));
        showloveService.insert(showlove);
    }

    @RequestMapping("/loading")
    public List<Showlove> loading(){
        return showloveService.loading();
    }
        
    @RequestMapping("/uploadImg")
    public String uploadImg(MultipartFile image) throws Exception {
        String Url="http://192.168.0.118:8080/ShowloveImages/"+image.getOriginalFilename();
        String filePath="D:/YzImages/ShowloveImages/"+image.getOriginalFilename();
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

    @RequestMapping("/detailShowlove")
    public Showlove detailShowlove(Integer showloveID){
        for(int i=0;i<showloveService.showlovesList.size();i++){
            if(showloveService.showlovesList.get(i).getShowloveID()==showloveID){
                return showloveService.showlovesList.get(i);
            }
        }
        return null;
    }

    @RequestMapping("/issueComment")
    public void issueComment(@RequestBody Comment comment){
        showloveService.insertComment(comment);
    }

    @RequestMapping("/getComment")
    public List<Comment> getComment(Integer showloveID){
        return  showloveService.getComment(showloveID);
    }
}
