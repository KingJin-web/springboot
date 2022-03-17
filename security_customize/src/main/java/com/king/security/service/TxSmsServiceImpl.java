package com.king.security.service;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @program: springboot
 * @description: 发送短信
 * @author: King
 * @create: 2022-03-17 15:12
 */
@Service
public class TxSmsServiceImpl {
    @Value(value = "${sendSms.secretId}")
    private String secretId; //
    @Value(value = "${sendSms.secretKey}")
    private String secretKey;
    @Value(value = "${sendSms.SmsSdkAppId}")
    private String SmsSdkAppId;
    @Value(value = "${sendSms.TemplateId}")
    private String TemplateId;

    //更改短信模板id
    public void setTemplateId(String templateId) {
        TemplateId = templateId;
    }

    /**
     * 发送短信
     *
     * @param phoneNumber 电话号码
     * @param smsCode     验证码
     */
    public void sendSmsCode(String phoneNumber, String smsCode) {
        try {
            Credential cred = new Credential(secretId, secretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
//            String[] phoneNumbers = {"+8618711673843"};
            req.setPhoneNumberSet(new String[]{phoneNumber});
            req.setSmsSdkAppId(SmsSdkAppId);
            req.setSignName("花草信息个人网");
            req.setTemplateId(TemplateId);

            String[] templateParamSet1 = {smsCode, "30"};
            req.setTemplateParamSet(templateParamSet1);

            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}