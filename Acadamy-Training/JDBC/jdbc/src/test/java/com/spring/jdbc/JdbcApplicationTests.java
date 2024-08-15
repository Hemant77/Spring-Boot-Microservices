package com.spring.jdbc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class JdbcApplicationTests {

	public static final String query = "select count(*) from product";

	@Test
	void contextLoads() {
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	private void testProductCount() {
		long productCount = jdbcTemplate.queryForObject(query, Long.class);
		assertThat(productCount).isEqualTo(3);

	}

}
