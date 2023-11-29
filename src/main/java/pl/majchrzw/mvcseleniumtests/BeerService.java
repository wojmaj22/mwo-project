package pl.majchrzw.mvcseleniumtests;

import jakarta.persistence.EntityNotFoundException;
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
	
	public Beer getBeerById(Integer id){
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No beer found"));
	}
	
	public void updateBeer(Integer id, Beer beer){
		if( repository.existsById(id)){
			repository.save(beer);
		} else {
			throw new RuntimeException("No beer found");
		}
	}
}
