package com.lym;

import com.lym.core.config.properties.Fundf10Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by liuyanmin on 2021/2/13.
 */
@SpringBootApplication(scanBasePackages = "com.lym")
@EnableTransactionManagement
@EnableConfigurationProperties({Fundf10Properties.class})
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
