package com.kevin.common;

import org.springframework.context.annotation.Configuration;

/**
 * @author Kevin
 * @Date: 2022/8/23 18:46
 * @Description: 常量类
 */
@Configuration
public class WxConstants {
    public WxConstants() {
    }

    /**
     * appId
     */
    public static final String APP_ID = "wx444abc5146f473ef";

    /**
     * appSecret
     */
    public static final String APP_SECRET = "c34d16d9a532a641f0d73e34602b99ea";

    /**
     * 公众号
     */
    public static final String PUBLIC_ID = "gh_3fc6c189d6a4";

    /**
     * token
     */
    public static final String TOKEN = "wakakaikai";

    /**
     * 请求api需要的token，开启定时任务，每一个小时获取一次
     */
    public static String accessToken = "";

    /**
     * 请求百度地图相关服务的AK
     */
    public static final String BAI_DU_AK = "fffcqc9cXkxjl58PqwuzKUo9iWdkcwxz";

    /**
     * 天行API AK
     */
    public static final String TX_AK = "5e6a347b4fdb32236916aee33cb703da";

    /**
     * 相识日期
     */
    public static final String MEET_DATE = "2018-12-01";

    public static final String GIRL_BIRTHDAY = "04-20";

    public static final String BOY_BIRTHDAY = "01-26";
    /**
     * 客服
     */
    public static String CUSTOMER_SERVICE = "ChatGPT@ChatGPT";
}
