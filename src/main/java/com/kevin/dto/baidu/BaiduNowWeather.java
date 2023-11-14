package com.kevin.dto.baidu;

import com.alibaba.fastjson2.annotation.JSONField;

public class BaiduNowWeather {
    @JSONField(name = "text")
    private String text;
    @JSONField(
            name = "temp"
    )
    private Integer temp;
    @JSONField(
            name = "feels_like"
    )
    private Integer feelsLike;
    @JSONField(
            name = "rh"
    )
    private String rh;
    @JSONField(
            name = "wind_class"
    )
    private String windClass;
    @JSONField(
            name = "wind_dir"
    )
    private String windDir;
}
