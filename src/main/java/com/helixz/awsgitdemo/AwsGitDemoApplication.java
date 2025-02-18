package com.helixz.awsgitdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AwsGitDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsGitDemoApplication.class, args);
    }

}
