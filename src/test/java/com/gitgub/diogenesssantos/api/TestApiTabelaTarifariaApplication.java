package com.gitgub.diogenesssantos.api;

import org.springframework.boot.SpringApplication;

public class TestApiTabelaTarifariaApplication {

	public static void main(String[] args) {
		SpringApplication.from(start::main).with(TestcontainersConfiguration.class).run(args);
	}

}
