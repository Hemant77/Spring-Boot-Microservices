package com.spring.jpa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Autowired
	DataSource dataSource;

//	@Bean
//	public RewardNetwork rewardNetwork(
//		AccountRepository accountRepository, 
//		RestaurantRepository restaurantRepository, 
//		RewardRepository rewardRepository ) {
//		return new RewardNetworkImpl(
//			accountRepository, 
//			restaurantRepository, 
//			rewardRepository);
//	}

//	@Bean
//	public ProductRepository productRepository() {
//		ProductRepositoryImpl repository = new ProductRepositoryImpl();
//		repository.setDataSource(dataSource);
//		return repository;
//	}
}
