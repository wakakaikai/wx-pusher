package com.kevin.strategy.template;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.kevin.common.WxConstants;
import com.kevin.common.WxTemplateConstants;
import com.kevin.dto.IdentityInfo;
import com.kevin.dto.baidu.BaiduWeatherParam;
import com.kevin.dto.tianxing.TianXingParam;
import com.kevin.remote.BaiduRemoteClient;
import com.kevin.remote.TianXingDataRemoteClient;
import com.kevin.strategy.WxTemplateStrategy;
import com.kevin.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Kevin
 * @Date: 2022/9/13 19:31
 * @Description: 特殊早安推送策略
 */
@Slf4j
@Service(WxTemplateConstants.SPECIAL_MORNING)
public class SpecialMorningStrategy implements WxTemplateStrategy {
    @Autowired
    private BaiduRemoteClient baiduRemoteClient;
    @Autowired
    private TianXingDataRemoteClient tianXingDataRemoteClient;

//    @Override
//    public void execute(WxMpTemplateMessage wxMpTemplateMessage, IdentityInfo identityInfo) {
//        Integer districtCode = WxOpUtils.getDistrictCode(identityInfo);
//        // 获取天气的url
//        String weatherUrl = "https://api.map.baidu.com/weather/v1/?district_id=" + districtCode + "&data_type=all&ak=" + WxConstants.BAI_DU_AK;
//        // 天气信息json格式
//        String weatherStr = HttpUtil.get(weatherUrl);
//        JSONObject result = JSONObject.parseObject(JSONObject.parseObject(weatherStr).get("result").toString());
//        // 实时天气
//        JSONObject now = JSONObject.parseObject(result.get("now").toString());
//        // 今日天气
//        JSONObject today = JSONArray.parseArray(result.get("forecasts").toString()).getJSONObject(0);
//        // 明日天气
//        JSONObject tomorrow = JSONArray.parseArray(result.get("forecasts").toString()).getJSONObject(1);
//        // 每日英语
//        String dailyEnglishUrl = "http://api.tianapi.com/everyday/index?key=" + WxConstants.TX_AK;
//        String dailyEnglishStr = HttpUtil.get(dailyEnglishUrl);
//        JSONObject dailyEnglishObject = JSONArray.parseArray(JSONObject.parseObject(dailyEnglishStr).get("newslist").toString()).getJSONObject(0);
//        // 英文句子
//        String english = dailyEnglishObject.get("content").toString();
//        // 20230505更新，wx平台最新规范[https://developers.weixin.qq.com/community/develop/doc/000a2ae286cdc0f41a8face4c51801]
//        // 每个模板块最多只能填充20个字符，需要对超长内容切割
//        String english1 = english.substring(0, Math.min(english.length(), 20));
//        String english2 = null;
//        if (english.length() > 20) {
//            english2 = english.substring(20);
//        }
//        // 中文翻译
//        String chinese = dailyEnglishObject.get("note").toString();
//        String chinese1 = chinese.substring(0, Math.min(chinese.length(), 20));
//        String chinese2 = null;
//        if (chinese.length() > 20) {
//            chinese2 = chinese.substring(20);
//        }
//        wxMpTemplateMessage.addData(new WxMpTemplateData("location", identityInfo.getAddress(), "#9370DB"));
//        wxMpTemplateMessage.addData(new WxMpTemplateData("now_temp", now.get("temp").toString(), "#87CEFA"));
//        wxMpTemplateMessage.addData(new WxMpTemplateData("now_weather", now.get("text").toString(), "#87CEEB"));
//        wxMpTemplateMessage.addData(new WxMpTemplateData("now_wind_dir", now.get("wind_dir").toString(), "#708090"));
//        wxMpTemplateMessage.addData(new WxMpTemplateData("now_wind_class", now.get("wind_class").toString(), "#708090"));
//        wxMpTemplateMessage.addData(new WxMpTemplateData("now_rh", now.get("rh").toString(), "#778899"));
//        String todayWeatherDay = today.get("text_day").toString();
//        String todayWeatherNight = today.get("text_night").toString();
//        if (todayWeatherDay.equals(todayWeatherNight)) {
//            wxMpTemplateMessage.addData(new WxMpTemplateData("today_weather", todayWeatherDay, "#FFC1C1"));
//        } else {
//            wxMpTemplateMessage.addData(new WxMpTemplateData("today_weather", todayWeatherDay + "转" + todayWeatherNight, "#FFC1C1"));
//        }
//        wxMpTemplateMessage.addData(new WxMpTemplateData("today_high", today.get("high").toString(), "#CD9B9B"));
//        wxMpTemplateMessage.addData(new WxMpTemplateData("today_low", today.get("low").toString(), "#CD9B9B"));
//        String tomorrowWeatherDay = tomorrow.get("text_day").toString();
//        String tomorrowWeatherNight = tomorrow.get("text_night").toString();
//        if (tomorrowWeatherDay.equals(tomorrowWeatherNight)) {
//            wxMpTemplateMessage.addData(new WxMpTemplateData("tomorrow_weather", tomorrowWeatherDay, "#DDA0DD"));
//        } else {
//            wxMpTemplateMessage.addData(new WxMpTemplateData("tomorrow_weather", tomorrowWeatherDay + "转" + tomorrowWeatherNight, "#DDA0DD"));
//        }
//        wxMpTemplateMessage.addData(new WxMpTemplateData("tomorrow_high", tomorrow.get("high").toString(), "#EE82EE"));
//        wxMpTemplateMessage.addData(new WxMpTemplateData("tomorrow_low", tomorrow.get("low").toString(), "#EE82EE"));
//        // 相识天数，可以修改为恋爱天数，或者其他纪念意义天数
//        Long meetDays = WxOpUtils.countDays(WxConstants.MEET_DATE, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//        wxMpTemplateMessage.addData(new WxMpTemplateData("meet_days", String.valueOf(meetDays), "#C000C0"));
//        wxMpTemplateMessage.addData(new WxMpTemplateData("daily_english_en1", english1, "#FFCCFF"));
//        wxMpTemplateMessage.addData(new WxMpTemplateData("daily_english_en2", english2, "#FFCCFF"));
//        wxMpTemplateMessage.addData(new WxMpTemplateData("daily_english_cn1", chinese1, "#CCCCFF"));
//        wxMpTemplateMessage.addData(new WxMpTemplateData("daily_english_cn2", chinese2, "#CCCCFF"));
//    }


    public void execute(WxMpTemplateMessage wxMpTemplateMessage, IdentityInfo userInfo) {
        String everyDayEnglishStr;
        JSONObject dailyEnglishObject;
        try {
            BaiduWeatherParam baiduWeatherParam = BaiduWeatherParam.builder().ak(WxConstants.BAI_DU_AK).district_id(userInfo.getAdcode()).build();
            everyDayEnglishStr = this.baiduRemoteClient.queryWeather(baiduWeatherParam);
            dailyEnglishObject = JSONObject.parseObject(JSONObject.parseObject(everyDayEnglishStr).getString("result"));
            JSONObject now = JSONObject.parseObject(dailyEnglishObject.getString("now"));
            JSONObject today = JSONArray.parseArray(dailyEnglishObject.get("forecasts").toString(), new JSONReader.Feature[0]).getJSONObject(0);
            JSONObject tomorrow = JSONArray.parseArray(dailyEnglishObject.get("forecasts").toString(), new JSONReader.Feature[0]).getJSONObject(1);
            JSONObject afterTomorrow = JSONArray.parseArray(dailyEnglishObject.get("forecasts").toString(), new JSONReader.Feature[0]).getJSONObject(2);
            String dateAndWeek = today.getString("date") + " " + today.getString("week");
            String todayWeatherDay = today.getString("text_day");
            String todayWeatherNight = today.getString("text_night");
            String todayWeather = StringUtils.endsWithIgnoreCase(todayWeatherDay, todayWeatherNight) ? todayWeatherDay : todayWeatherDay + "转" + todayWeatherNight;
            String tomorrowWeatherDay = tomorrow.getString("text_day");
            String tomorrowWeatherNight = tomorrow.getString("text_night");
            String tomorrowWeather = StringUtils.endsWithIgnoreCase(tomorrowWeatherDay, tomorrowWeatherNight) ? tomorrowWeatherDay : tomorrowWeatherDay + "转" + tomorrowWeatherNight;
            String afterTomorrowWeatherDay = afterTomorrow.getString("text_day");
            String afterTomorrowWeatherNight = afterTomorrow.getString("text_night");
            String afterTomorrowWeather = StringUtils.endsWithIgnoreCase(afterTomorrowWeatherDay, afterTomorrowWeatherNight) ? afterTomorrowWeatherDay : afterTomorrowWeatherDay + "转" + afterTomorrowWeatherNight;
            wxMpTemplateMessage.addData(new WxMpTemplateData("dateAndWeek", dateAndWeek, "#EED016"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("city", userInfo.getCity(), "#60AEF2"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("weather", now.getString("text"), "#b28d0a"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("today_low", today.getString("low") + "°", "#0ace3c"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("today_high", today.getString("high") + "°", "#dc1010"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("now_weather", now.getString("text"), "#87CEEB"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("now_temp", now.getString("temp") + "°", "#87CEFA"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("now_wind_dir", now.getString("wind_dir"), "#6e6e6e"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("now_wind_class", now.getString("wind_class"), "#6e6e6e"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("now_rh", now.getString("rh") + "%", "#1f95c5"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("today_weather", todayWeather, "#DDA0DD"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("tomorrow_weather", tomorrowWeather, "#DDA0DD"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("after_tomorrow_weather", afterTomorrowWeather, "#DDA0DD"));
        } catch (Exception var23) {
            log.error(">>> 获取百度天气错误", var23);
        }

        String date = DateUtils.formatDate(new Date(), "yyyy-MM-dd");

        try {
            wxMpTemplateMessage.addData(new WxMpTemplateData("meet_days", this.getTogetherDay(date), "#FEABB5"));
            log.info("togetherDate:{}", this.getTogetherDay(date));
        } catch (Exception var22) {
            log.error(">>> 相识天数处理失败", var22);
        }

        try {
            wxMpTemplateMessage.addData(new WxMpTemplateData("girl_birthday", this.getBirthday(WxConstants.GIRL_BIRTHDAY, date), "#6EEDE2"));
            log.info("girl_birthday:{}", this.getBirthday(WxConstants.GIRL_BIRTHDAY, date));
            wxMpTemplateMessage.addData(new WxMpTemplateData("boy_birthday", this.getBirthday(WxConstants.BOY_BIRTHDAY, date), "#6EEDE2"));
        } catch (Exception var21) {
            log.error(">>> 生日处理失败", var21);
        }

        try {
            everyDayEnglishStr = this.tianXingDataRemoteClient.queryMorningData(TianXingParam.builder().key(WxConstants.TX_AK).build());
            dailyEnglishObject = JSONArray.parseArray(JSONObject.parseObject(everyDayEnglishStr).get("newslist").toString(), new JSONReader.Feature[0]).getJSONObject(0);
            String chineseContent = dailyEnglishObject.getString("content");
            String englishContent = this.translateToEnglish(chineseContent);
            wxMpTemplateMessage.addData(new WxMpTemplateData("daily_en", englishContent, "#879191"));
            wxMpTemplateMessage.addData(new WxMpTemplateData("daily_cn", chineseContent, "#879191"));
        } catch (Exception var20) {
            log.error(">>> 获取天行数据早安心语错误", var20);
        }

    }

    public String translateToEnglish(String sentence) {
        String result = null;

        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder().url("https://fanyi.youdao.com/translate?&doctype=json&type=AUTO&i=" + sentence).get().addHeader("Content-Type", "").build();
            Response response = client.newCall(request).execute();
            result = response.body().string();
            JSONObject jsonObject = JSONObject.parseObject(result);
            result = jsonObject.getJSONArray("translateResult").getJSONArray(0).getJSONObject(0).getString("tgt");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(">>> 有道云翻译接口报错", e);
        }

        return result;
    }

    private String getBirthday(String birthday, String date) {
        String birthDay = "无法识别";

        try {
            Calendar calendar = Calendar.getInstance();
            String newD = calendar.get(1) + "-" + birthday;
            birthDay = DateUtils.daysBetween(date, newD);
            if (Integer.parseInt(birthDay) < 0) {
                Integer newBirthDay = Integer.parseInt(birthDay) + 365;
                birthDay = newBirthDay + "天";
            } else {
                birthDay = birthDay + "天";
            }
        } catch (ParseException var7) {
            log.error("togetherDate获取失败" + var7.getMessage());
        }

        return birthDay;
    }

    private String getTogetherDay(String date) {
        String togetherDay = "";

        try {
            togetherDay = "第" + DateUtils.daysBetween(WxConstants.MEET_DATE, date) + "天";
        } catch (ParseException var4) {
            log.error("togetherDate获取失败" + var4.getMessage());
        }

        return togetherDay;
    }
}
