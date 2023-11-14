package com.kevin.dto.baidu;

import com.alibaba.fastjson2.annotation.JSONField;

public class BaiduForecastsWeather {
    @JSONField(name = "text_day")
    private String textDay;

    @JSONField(name = "text_night")
    private String textNight;

    @JSONField(name = "high")
    private String high;
    @JSONField(name = "low")
    private String low;

    @JSONField(name = "wc_day")
    private String wcDay;

    @JSONField(name = "wd_day")
    private String wdDay;

    @JSONField(name = "wc_night")
    private String wcNight;

    @JSONField(name = "wd_night")
    private String wdNight;

    @JSONField(name = "date")
    private String date;

    @JSONField(name = "week")
    private String week;
}
