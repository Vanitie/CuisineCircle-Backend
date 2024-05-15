package com.ccb.controllers;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import com.ccb.model.domain.ChatRequest;
import com.ccb.model.domain.ChatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
//还需要引入下面两个domain类

@RestController
public class ChatController {
    @PostMapping("/chat")//文档要求使用post请求
    public ChatResponse chat(String q){
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";//这里看官方文档
        String ApiKey = "sk-c3a72a1cfd52491fbdc288b24b5908c1";//ApiKey

        ChatRequest chatRequest = new ChatRequest(q);
        String json = JSONUtil.toJsonStr(chatRequest);
        //System.out.println(json);//正式发送给api前,查看请求的主要数据情况
        String result = HttpRequest.post(url)
                .header("Authorization","Bearer "+ ApiKey)
                .header("Content-Type","application/json")
                .body(json)
                .execute().body();
        System.out.println(result);
        return JSONUtil.toBean(result, ChatResponse.class);
    }
}

