package com.kevin.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.kevin.common.WxConstants;
import com.kevin.dto.baidu.BaiduGeocodingParam;
import com.kevin.enums.WxEventType;
import com.kevin.enums.WxMessageType;
import com.kevin.remote.BaiduRemoteClient;
import com.kevin.service.SubscribeService;
import com.kevin.strategy.WxEventContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: Kevin
 * @Date: 2022/8/24 19:28
 * @Description: 订阅相关实现类
 */
@Service
@Slf4j
public class SubscribeServiceImpl implements SubscribeService {
    @Autowired
    private WxEventContext wxEventContext;

    @Autowired
    private BaiduRemoteClient baiduRemoteClient;

    /**
     * 检查令牌
     *
     * @param request  请求
     * @param response 响应
     */
    @Override
    public void checkToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            log.info(">>> 开始解析收到的信息");
            // 解析请求数据
            Map<String, String> requestMap = parseRequest(request);
            // 微信信息类型，event or text
            String msgType = requestMap.get("MsgType");
            log.info(">>> 微信消息类型：{}", msgType);
            // 发送方账号
            String openId = requestMap.get("FromUserName");
            log.info(">>> openId：{}", openId);
            // 消息类型
            if (WxMessageType.TEXT.getType().equals(msgType)) {
                wxEventContext.execute("text", requestMap, response);
            } else {
                String geocodingResult;
                if (WxMessageType.EVENT.getType().equals(msgType)) {
                    geocodingResult = requestMap.get("Event");
                    this.wxEventContext.execute(geocodingResult, requestMap, response);
                } else if (StringUtils.endsWithIgnoreCase(msgType, WxEventType.LOCATION.getType())) {
                    geocodingResult = baiduRemoteClient.queryGeocoding(BaiduGeocodingParam.builder().address(requestMap.get("Label")).ak(WxConstants.BAI_DU_AK).build());
                    log.info(">>> 根据详细地址获取经纬度：{}", geocodingResult);
                    JSONObject locationObject = JSONObject.parseObject(geocodingResult).getJSONObject("result").getJSONObject("location");
                    requestMap.put("Latitude", locationObject.getString("lat"));
                    requestMap.put("Longitude", locationObject.getString("lng"));
                    requestMap.put("precise", JSONObject.parseObject(geocodingResult).getString("precise"));
                    this.wxEventContext.execute(msgType, requestMap, response);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    /**
     * 解析请求参数
     *
     * @param request 请求参数
     * @return 存储解析完的参数的Map
     */
    private Map<String, String> parseRequest(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> requestMap = new HashMap<>(16);
        try (ServletInputStream sis = request.getInputStream()) {
            // 读取输入流
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(sis);
            // 获取根节点
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            // 遍历所有节点，记录节点name和内容
            for (Element element : elements) {
                requestMap.put(element.getName(), element.getText());
            }
        }
        return requestMap;
    }
}
