package com.artemis.lottery.common;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 短信工具类
 *
 * @author zhengenshen
 * @date 2018-05-14 10:28
 */
@Slf4j
@Component
public class SMSTools {

    private static final Map<String, Integer> SMS = new ConcurrentHashMap<>();

    private static final String BUSINESS_LIMIT_CONTROL = "isv.BUSINESS_LIMIT_CONTROL";
    private static final String OK = "OK";

    @Value("${access-key-id}")
    private String accessKeyId;

    @Value("${access-key-secret}")
    private String accessKeySecret;

    @Value("${sign-name}")
    private String signName;

    @Value("${template-code}")
    private String templateCode;

    @Value("${template-param}")
    private String templateParam;

    public void verify(String phoneNumber, int sms) throws Exception {
        if (SMS.containsKey(phoneNumber)) {
            if (sms == SMS.get(phoneNumber)) {
                SMS.remove(phoneNumber);
                return;
            }
        }
        throw new Exception("验证码错误");
    }


    /**
     * 向短信平台发送验证码
     *
     * @param phoneNumber 手机号
     * @return 验证码
     */
    public int sendSms(String phoneNumber) throws Exception {


        int code = (int) (Math.random() * 900000) + 100000;
        log.debug("验证码：" + code);

        String newTemplateParam = templateParam.replace("codeValue", code + "");

        sendSms(phoneNumber, templateCode, newTemplateParam);

        SMS.put(phoneNumber, code);
        return code;
    }

    private void sendSms(String phoneNumber, String templateCode, String templateParam) throws Exception {

        //发短信
        // 调试的时候不开启这个功能
        SendSmsResponse response;
        try {
            response = sendSms(accessKeyId, accessKeySecret, phoneNumber, signName, templateCode, templateParam);
            log.debug("短信接口返回的数据----------------");
            log.debug("Code=" + response.getCode());
            log.debug("Message=" + response.getMessage());
            log.debug("RequestId=" + response.getRequestId());
            log.debug("BizId=" + response.getBizId());
            if (!OK.equals(response.getCode())) {
                if (BUSINESS_LIMIT_CONTROL.equals(response.getCode())) {
                    log.error("{}短信发送失败,业务限流", phoneNumber);
                    throw new Exception(phoneNumber + "短信发送失败,业务限流");
                }
            }
        } catch (Exception e) {
            log.error("捕获短信发送失败异常,{}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }


    //产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";


    private static SendSmsResponse sendSms(String accessKeyId, String accessKeySecret, String phoneNumber, String signName, String templateCode, String templateParam) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateParam);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch

        return acsClient.getAcsResponse(request);
    }


    public static QuerySendDetailsResponse querySendDetails(String accessKeyId, String accessKeySecret, String bizId) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber("17191081795");
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch

        return acsClient.getAcsResponse(request);
    }
}
