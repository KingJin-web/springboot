package com.king.security.vo;

import lombok.Data;

/**
 * @program: mooc
 * @description: 支付宝异步通知返回的结果，需要注意 notify_time是String类型，不是Date类型，如果需要需要自己手动转换
 * @author: King
 * @create: 2021-10-16 14:52
 */
@Data
public class AlipayVo {
    private String gmt_create;
    private String charset;
    private String gmt_payment;
    private String notify_time;
    private String subject;
    private String sign;
    /** 支付者的id */
    private String buyer_id;
    /** 订单的信息 */
    private String body;
    /** 支付金额 */
    private String invoice_amount;
    private String version;
    /** 通知id */
    private String notify_id;
    private String fund_bill_list;
    /** 通知类型； trade_status_sync */
    private String notify_type;
    /** 订单号 */
    private String out_trade_no;
    /** 支付的总额 */
    private String total_amount;
    /** 交易状态  TRADE_SUCCESS */
    private String trade_status;
    /** 流水号 */
    private String trade_no;
    private String auth_app_id;
    /** 商家收到的款 */
    private String receipt_amount;
    private String point_amount;
    /** 应用id */
    private String app_id;
    /** 最终支付的金额 */
    private String buyer_pay_amount;
    /** 签名类型 */
    private String sign_type;
    /** 商家的id */
    private  String seller_id;

  

}
