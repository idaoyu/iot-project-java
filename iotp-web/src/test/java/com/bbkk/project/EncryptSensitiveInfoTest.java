package com.bbkk.project;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 加密敏感信息
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-28 21:15
 **/
@SpringBootTest
public class EncryptSensitiveInfoTest {

	@Autowired
	private StringEncryptor stringEncryptor;

	@Test
	public void getPass() {
		String text = "";
		System.out.println(stringEncryptor.encrypt(text));

	}

}
