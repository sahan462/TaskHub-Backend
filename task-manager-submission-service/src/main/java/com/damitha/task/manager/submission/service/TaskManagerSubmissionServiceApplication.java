package com.damitha.task.manager.submission.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaskManagerSubmissionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerSubmissionServiceApplication.class, args);
	}

}
