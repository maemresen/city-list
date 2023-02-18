package com.maemresen.city.list.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;

@RequiredArgsConstructor
@Service
public class Listener implements CommandLineRunner {

	private final DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {
		Connection connection = dataSource.getConnection();
		connection.close();
	}
}
