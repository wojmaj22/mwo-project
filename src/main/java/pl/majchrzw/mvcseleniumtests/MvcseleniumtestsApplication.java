package pl.majchrzw.mvcseleniumtests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.crypto.Data;

@SpringBootApplication
public class MvcseleniumtestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcseleniumtestsApplication.class, args).getBean(DataInitializer.class).initData();
	}
	
}
