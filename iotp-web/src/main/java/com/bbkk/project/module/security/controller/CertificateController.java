package com.bbkk.project.module.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "证书接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cert")
public class CertificateController {

	@GetMapping
	@Operation(summary = "获取证书")
	public String getCert() {
		return null;
	}

}
