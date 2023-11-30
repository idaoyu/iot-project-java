package com.bbkk.project.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * jwt 工具类
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-30 18:01
 **/
@Slf4j
public class JwtUtil {

    /**
     * 加解密时使用的密钥，用来生成 key
     */
    private static final String SECURITY_KEY_STRING = "zvIBhWGUxrsO2mxa5/EndJD9amdBJHia";

    private static SecretKey generateSecurityKey() {
        byte[] bytes = SECURITY_KEY_STRING.getBytes(StandardCharsets.UTF_8);
        return Keys.builder(new SecretKeySpec(bytes, 0, bytes.length, "HmacSHA256")).build();
    }

    /**
     * 创建 jwt token
     *
     * @param claims          负载数据
     * @param effectiveSecond 有效时间（单位：秒）
     * @return jwt token
     */
    public static String createJwt(Map<String, Object> claims, int effectiveSecond) {
        JwtBuilder builder = Jwts.builder();
        Date current = new Date();
        // 计算 jwt token 的过期时间
        DateTime expiration = DateUtil.offset(current, DateField.SECOND, effectiveSecond);
        // jwt 负载
        builder.claims(claims);
        // jwt 签发时间
        builder.issuedAt(current);
        // jwt 生效时间
        builder.notBefore(current);
        // jwt 过期时间
        builder.expiration(expiration);
        // jwt 的 id
        builder.id(IdUtil.simpleUUID());
        // 对 jwt 签名
        builder.signWith(generateSecurityKey(), Jwts.SIG.HS256);
        return builder.compact();
    }

    /**
     * 校验并解析 jwt token
     *
     * @param jwt jwt token
     * @return payload
     */
    public static Claims parseJwtToken(String jwt) {
        JwtParser jwtParser = Jwts.parser()
                // 对 jwt 签名校验
                .verifyWith(generateSecurityKey())
                .build();
        return jwtParser.parseSignedClaims(jwt).getPayload();
    }

}
