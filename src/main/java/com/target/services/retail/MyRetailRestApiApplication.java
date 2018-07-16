package com.target.services.retail;

import com.target.services.retail.model.PriceDocument;
import com.target.services.retail.repository.PriceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;


@EnableCaching
@SpringBootApplication
@ComponentScan(basePackageClasses = ProductsController.class)
public class MyRetailRestApiApplication {


	public static void main(String[] args) {

		SpringApplication.run(MyRetailRestApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PriceRepository priceRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				priceRepository.save(new PriceDocument("13860500","USD",new BigDecimal(12)));
				priceRepository.save(new PriceDocument("13860428","USD",new BigDecimal(14)));
			}

		};

	}
}