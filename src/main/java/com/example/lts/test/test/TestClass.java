package com.example.lts.test.test;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.dingtalk.chatbot.message.MarkdownMessage;
import com.taobao.api.ApiException;

import java.util.Arrays;

public class TestClass {
    private static final String ORDER_MESSAGE_TEMPLATE = "用户：%s\n\n开通：%s\n\n订购金额：%s\n\n订购时间：%s\n\n服务结束时间：%s\n\n类型：%s\n\n";

    public static void main(String[] args) throws ApiException {
//        robotMessage();

        MarkdownMessage markdownMessage = new MarkdownMessage();
        markdownMessage.setTitle("系统检测");



        markdownMessage.add(ORDER_MESSAGE_TEMPLATE);
        com.example.lts.test.util.DingTalkClient.send(markdownMessage, "https://oapi.dingtalk.com/robot/send?access_token=3171bbc140cffbd8e3edda9e46af6127d45abc01c08d86e172c28e12ca5692fe");
    }

    public static void robotMessage() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=3171bbc140cffbd8e3edda9e46af6127d45abc01c08d86e172c28e12ca5692fe");
        OapiRobotSendRequest request = new OapiRobotSendRequest();

//        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
//        text.setContent("【系统检测】赵昊同志勤勤恳恳，特此奖励大红花");
//        request.setText(text);

        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("系统检测");
        markdown.setText("标题：今日作业\n\n内容：三字经抄写100遍\n\n执行人：赵昊\n\n");
        request.setMarkdown(markdown);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        at.setAtMobiles(Arrays.asList("16692167380"));
// isAtAll类型如果不为Boolean，请升级至最新SDK
        at.setIsAtAll(Boolean.TRUE);
        request.setAt(at);

        OapiRobotSendResponse response = client.execute(request);
        int i = 1;
    }
}
