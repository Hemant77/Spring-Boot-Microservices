package com.spring.jpa;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.spring.jpa.config.Config;
import com.spring.jpa.model.Customer;
import com.spring.jpa.model.Product;
import com.spring.jpa.service.CustomerService;
import com.spring.jpa.service.ProductService;

import jakarta.transaction.Transactional;

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

		@Autowired
		ProductService productService;

//		@Autowired
//		CustomerService customerService;

		public QueryAccountCountRunner(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}

		@Override
		public void run(String... args) throws Exception {
			long productCount = this.jdbcTemplate.queryForObject(QUERY, Long.class);
			logger.info("Number of products: {}", productCount);

			Product newProduct = new Product((long) 4, "Sugar", (float) 22.5);
			productService.saveProductDeatils(newProduct);
			System.out.println("Product saved!");

			Random random = new Random();
			int id = random.nextInt((4 - 1) + 1) + 1;
			logger.info("Get product by randomly generated Id");
			Product product = productService.fetchProductById(id);
			logger.info("Id : {}, Product : {}", id, product.toString());
//			this.transaction();

			System.out.println("Product with id - 4 : " + productService.fetchProductById(4).toString());
		}

//		@Transactional
//		public String transaction() {
//			Product prod = new Product((long) 4, "Sugar", (float) 22.5);
//			productService.saveProductDeatils(prod);
//			System.out.println("Product saved!");
//
//			Customer customer = new Customer(1, "Thomas");
//			customerService.saveCustomerDetails(customer);
//			System.out.println("Customer saved!");
//			
//			return null;
//		}
	}

}
