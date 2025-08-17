package org.lucas;

import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/** 应用入口类 */
@SpringBootApplication
@EnableScheduling
@EnableFileStorage
public class RAGAgentPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(RAGAgentPlatformApplication.class, args);
    }
}
