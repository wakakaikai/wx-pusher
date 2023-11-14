package com.kevin.remote;

import com.kevin.dto.baidu.BaiduGeocodingParam;
import com.kevin.dto.baidu.BaiduReverseGeocodingParam;
import com.kevin.dto.baidu.BaiduWeatherParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "BaiduWeatherRemoteClient", url = "${baidu.server}")
public interface BaiduRemoteClient {
    @GetMapping(value = {"/geocoding/v3/"})
    String queryGeocoding(@SpringQueryMap BaiduGeocodingParam baiduReverseGeocodingParam);

    @GetMapping(value = {"/reverse_geocoding/v3/"})
    String queryReverseGeocoding(@SpringQueryMap BaiduReverseGeocodingParam baiduReverseGeocodingParam);

    @GetMapping(value = {"/weather/v1/"})
    String queryWeather(@SpringQueryMap BaiduWeatherParam baiduWeatherParam);
}
