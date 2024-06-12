package com.damitha.task.manager.task.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaskManagerTaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerTaskServiceApplication.class, args);
	}

}
