package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: leyou
 * @Description: 上传东西(文件 , 照片)启动类
 * @Author: jiaxiong
 * @Version: 1.8.0_131
 * @Date: 2019-12-11 21:37
 **/
@SpringBootApplication
@EnableEurekaClient
public class LeyouUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouUploadApplication.class);
    }
}
