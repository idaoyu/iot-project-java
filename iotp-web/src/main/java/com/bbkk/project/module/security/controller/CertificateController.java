package com.bbkk.project.module.security.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 证书接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-28 21:53
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cert")
public class CertificateController {

    @GetMapping
    public String getCert() {
        return null;
    }

}
