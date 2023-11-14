package com.kevin.remote;

import com.kevin.dto.tianxing.TianXingParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(value = "TianDataRemoteClient", url = "${tianxin.server}")
public interface TianXingDataRemoteClient {
    @GetMapping(value = {"/caihongpi/index"}, consumes = {"application/json"}, produces = {"application/json"})
    String queryRainbow(@SpringQueryMap TianXingParam tianXinParam);

    @GetMapping(value = {"/zaoan/index"}, consumes = {"application/json"}, produces = {"application/json"})
    String queryMorningData(@SpringQueryMap TianXingParam tianXinParam);

    @GetMapping(value = {"/zaoan/index"}, consumes = {"application/json"}, produces = {"application/json"})
    String queryNightData(@SpringQueryMap TianXingParam tianXinParam);

    @GetMapping(value = {"/topnews/index"}, consumes = {"application/json"}, produces = {"application/json"})
    String queryTopNews(@SpringQueryMap TianXingParam tianXinParam);

    @GetMapping(value = {"/one/index"}, consumes = {"application/json"}, produces = {"application/json"})
    String queryOneNote(@SpringQueryMap TianXingParam tianXinParam);
}
