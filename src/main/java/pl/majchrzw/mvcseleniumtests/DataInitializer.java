package pl.majchrzw.mvcseleniumtests;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DataInitializer {
	
	private final BeerRepository repository;
	
	public void initData(){
		Beer beer = Beer.builder()
				.name("Żubr")
				.price(new BigDecimal("3.99"))
				.build();
		repository.save(beer);
		beer = Beer.builder()
				.name("Tatra")
				.price(new BigDecimal("2.89"))
				.build();
		repository.save(beer);
	}
}
