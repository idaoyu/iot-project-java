package com.bbkk.project.api;

import com.bbkk.project.data.GetProductInfoDTO;
import com.bbkk.project.data.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * /api/productInfo api
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-13 18:23
 **/
@FeignClient(contextId = "remoteProductInfoService", value = "iotp-device")
public interface RemoteProductInfoService {

    /**
     * 通过产品id 查询产品信息
     *
     * @param id 产品id
     * @return 产品信息
     */
    @GetMapping(value = "/api/productInfo/get")
    R<GetProductInfoDTO> getProductInfo(@RequestParam("id") Long id);


}
