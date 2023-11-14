package com.kevin.dto.baidu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaiduGeocodingParam {
    private String address;

    private String output;

    private String ak;
}
