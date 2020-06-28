package com.huida.controller;

import java.util.Map;

import com.huida.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/openID")
public class GetOpenIDController {
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;
    @Value("${wx.grantType}")
    private String grantType;
    @Value("${wx.requestUrl}")
    private String requestUrl;

    @RequestMapping("/session")
    public Map<String, Object> getSession(@RequestParam(required = true) String code) {
        return this.getSessionByCode(code);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getSessionByCode(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session"
                + "?appid=wx833db212e4fd8f3e"
                + "&secret=9e3fa34aaf8377f6e7f87a900f0f812b"
                + "&js_code=" + code
                + "&grant_type=authorization_code";
        String data = HttpUtils.get(url);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = null;
        try {
            json = mapper.readValue(data, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}