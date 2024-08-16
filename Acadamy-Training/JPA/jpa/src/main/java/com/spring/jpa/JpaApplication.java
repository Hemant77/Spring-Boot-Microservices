package com.spring.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.spring.jpa.config.Config;

@SpringBootApplication
@Import(Config.class)
public class JpaApplication {
	private final Logger logger = LoggerFactory.getLogger(JpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);

	}

	private static final String QUERY = "SELECT count(*) FROM product";

	@Component
	public final class QueryAccountCountRunner implements CommandLineRunner {

		private JdbcTemplate jdbcTemplate;

		public QueryAccountCountRunner(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}

		@Override
		public void run(String... args) throws Exception {
			System.out.println("Query : " + QUERY);
			long productCount = this.jdbcTemplate.queryForObject(QUERY, Long.class);
			logger.info("Number of products: {}", productCount);

//			logger.info("Get product by Id");
//			productRepository.findById(2);

		}
	}

}
