package com.wy.test;

import com.wy.config.WeixinPayConfig;
import com.wy.util.HttpClientUtil;
import com.wy.util.StringUtil;
import com.wy.util.WeiXinUtil;
import com.wy.util.XmlUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther Seay
 * @date 2019/4/3 10:00
 * 订单查询测试  订单的所有集合开发
 * 微信接口文档 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
 */
public class TestOrderQuery {
    private static final String url = "https://api.mch.weixin.qq.com/pay/orderquery";

    public static void main(String[] args) throws IOException {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("appid", WeixinPayConfig.appid); // 公众账号ID
        map.put("mch_id", WeixinPayConfig.mch_id); // 商户号
        map.put("transaction_id", "4200000087201804105653326283"); // 微信订单号 /*这里两个订单号选其一*/
        // map.put("out_trade_no", "20180405055656553"); // 商户订单号
        map.put("mch_id", WeixinPayConfig.mch_id); // 商户号
        map.put("nonce_str", StringUtil.getRandomString(30)); // 随机字符串
        map.put("sign", WeiXinUtil.getSign(map)); // 签名
        String xml= XmlUtil.genXml(map);
        System.out.println(xml);
        InputStream in= HttpClientUtil.sendXMLDataByPost(url, xml).getEntity().getContent(); // 发现xml消息
        WeiXinUtil.getElementValue(in);
    }
}