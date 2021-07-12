package com.zyp.restdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.zyp.restdemo")
@EnableJpaRepositories("com.zyp.restdemo.dao") // JPA扫描该包路径下的Repositorie
@EntityScan("com.zyp.restdemo.entity") // 扫描Entity实体类
public class RestapidemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapidemoApplication.class, args);
	}

}
