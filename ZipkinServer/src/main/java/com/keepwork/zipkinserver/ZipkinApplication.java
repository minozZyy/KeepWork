package com.keepwork.zipkinserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import zipkin.server.EnableZipkinServer;
import zipkin2.storage.mysql.v1.MySQLStorage;

import javax.sql.DataSource;

@EnableZipkinServer
@SpringBootApplication

public class ZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinApplication.class, args);
	}

	@Bean
	public MySQLStorage mySQLStorage(DataSource datasource) {
		return MySQLStorage.newBuilder().datasource(datasource).executor(Runnable::run).build();
	}
}
