package com.bbkk.project.utils;

import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class JwtUtilTest {

    @Test
    void createJwt() {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("name", "xiaoming");
        String jwt = JwtUtil.createJwt(map, 1);
        System.out.println(jwt);
    }

    @Test
    void parseJwtToken() {
        Claims claims = JwtUtil.parseJwtToken("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoieGlhb21pbmciLCJpYXQiOjE3MDEzNDE2NDUsIm5iZiI6MTcwMTM0MTY0NSwiZXhwIjoxNzAxMzQxNjQ2LCJqdGkiOiJkYWE4MjA1ODk3NzY0ZGNiYjc4ZTRjMGFjMjg3MjI2ZiJ9.4jMgBCw8qyb4VMngDFN7O3s8dPq3b3OxOs6MfzGUvb4");
        System.out.println(JSONObject.toJSONString(claims));
    }
}