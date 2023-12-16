package com.bbkb.project;

import com.bbkb.project.config.NettyMqttConfig;
import com.bbkb.project.service.NettyMqttService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/16 12:10
 **/
@SpringBootApplication
@RequiredArgsConstructor
public class IotpDeviceGatewayApplication implements CommandLineRunner {

    private final NettyMqttConfig nettyMqttConfig;

    public static void main(String[] args) {
        SpringApplication.run(IotpDeviceGatewayApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        NettyMqttService nettyMqttService = new NettyMqttService();
        nettyMqttService.startUp(nettyMqttConfig.getPort());
    }
}
