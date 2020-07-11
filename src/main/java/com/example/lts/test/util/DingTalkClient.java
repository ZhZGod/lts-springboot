package com.example.lts.test.util;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.chatbot.SendResult;
import com.dingtalk.chatbot.message.Message;
import com.dingtalk.chatbot.message.TextMessage;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: Wang Wei
 * @create: 2019-05-06 10:32
 **/
public class DingTalkClient {
    private static Logger logger = LoggerFactory.getLogger(DingTalkClient.class);

    private static final OkHttpClient CLIENT = new OkHttpClient().newBuilder().readTimeout(2, TimeUnit.SECONDS).build();

    public static void send(String content, List<String> atMobiles, String webHook) {
        TextMessage textMessage = new TextMessage(content);
        textMessage.setAtMobiles(atMobiles);
        send(textMessage, webHook);
    }

    public static void send(String content, String webHook) {
        send(new TextMessage(content), webHook);
    }

    public static void send(Message message, String webHook) {
        SendResult result = null;
        try {
            result = doSend(message, webHook);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("message: {}, result: {}, webHook: {}", message.toJsonString(), result, webHook);
        }
    }

    private static SendResult doSend(Message message, String webHook) throws IOException {
        Request request = new Request.Builder()
                .url(webHook)
                .post(RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), message.toJsonString()))
                .build();
        SendResult sendResult = new SendResult();
        try (okhttp3.Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                JSONObject obj     = JSONObject.parseObject(response.body().string());
                Integer    errcode = obj.getInteger("errcode");
                sendResult.setErrorCode(errcode);
                sendResult.setErrorMsg(obj.getString("errmsg"));
                sendResult.setIsSuccess(errcode.equals(0));
            }
        }
        return sendResult;
    }

    public static void main(String[] args) throws IOException {
        Message message = new TextMessage("测试内容");
        SendResult sendResult = doSend(message, "https://oapi.dingtalk.com/robot/send?access_token=28ce00b540bea67e981a24ae47166c13bdfaba0046b935d8571ace81bc06af14");
        System.out.println(JSONObject.toJSON(sendResult));
    }
}
