package pl.majchrzw.mvcseleniumtests;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeerService {
	
	private final BeerRepository repository;
	
	public List<Beer> getBeers(){
		return repository.findAll();
	}
	
	public void addBeer(Beer beer){
		repository.save(beer);
	}
	
	public void deleteBeer(Integer id){
		repository.deleteById(id);
	}
}
