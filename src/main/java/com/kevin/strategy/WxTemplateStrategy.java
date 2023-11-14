package com.kevin.strategy;

import com.kevin.dto.IdentityInfo;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * @Author: Kevin
 * @Date: 2022/9/6 19:05
 * @Description: 微信模板策略
 */
public interface WxTemplateStrategy {
    /**
     * 执行
     *
     * @param wxMpTemplateMessage 微信模板信息
     * @param identityInfo        身份信息
     */
    void execute(WxMpTemplateMessage wxMpTemplateMessage, IdentityInfo identityInfo);
}
