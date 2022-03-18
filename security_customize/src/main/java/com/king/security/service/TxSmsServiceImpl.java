package com.king.security.service;

import com.king.security.util.MyException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Value(value = "${sendSms.SecretId}")
    private String secretId; //
    @Value(value = "${sendSms.SecretKey}")
    private String secretKey;
    @Value(value = "${sendSms.SmsSdkAppId}")
    private String SmsSdkAppId;
    @Value(value = "${sendSms.TemplateId}")
    private String TemplateId;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //更改短信模板id
    public void setTemplateId(String templateId) {
        TemplateId = templateId;
    }

    /**
     * 发送短信
     *
     * @param phoneNumber 电话号码
     * @param smsCode     验证码
     * @return
     */
    public String sendSmsCode(String phoneNumber, String smsCode) throws MyException {
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

            req.setPhoneNumberSet(new String[]{phoneNumber});
            req.setSmsSdkAppId(SmsSdkAppId);
            req.setTemplateId(TemplateId);
            //SignName – 短信签名内容，使用 UTF-8 编码，必须填写已审核通过的签名，例如：腾讯云，
            // 签名信息可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-sign)
            // 的签名管理查看。  发送国内短信该参数必填。
            req.setSignName("花草信息个人网");

            //对应短信模板的内容 测试只支持数字字符串
            //您正在申请手机注册，验证码为：{1}，{2}分钟内有效！
            String[] templateParamSet1 = {smsCode, "30"};
            req.setTemplateParamSet(templateParamSet1);

            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            logger.info(SendSmsResponse.toJsonString(resp));

            return resp.getSendStatusSet()[0].getMessage();
        } catch (TencentCloudSDKException e) {
            logger.error(e.toString());
            throw new MyException(e.getMessage());
        }

    }
}