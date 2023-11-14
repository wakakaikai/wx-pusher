package com.kevin.dto.baidu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaiduWeatherParam {
    private String district_id;

    private String data_type;

    private String ak;
}
