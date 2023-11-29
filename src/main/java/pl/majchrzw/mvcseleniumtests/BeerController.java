package pl.majchrzw.mvcseleniumtests;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class BeerController {
	
	private final BeerService service;
	
	@GetMapping("/list")
	public String getBeers(Model model){
		model.addAttribute("list", service.getBeers());
		return "list";
	}
	
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("beer", new Beer());
		return "form";
	}
	
	@PostMapping("/add")
	public String addBeer(@ModelAttribute Beer beer) {
		service.addBeer(beer);
		return "redirect:/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBeer(@PathVariable Integer id) {
		service.deleteBeer(id);
		return "redirect:/list";
	}
	
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		// Pobierz piwo do aktualizacji z serwisu
		Beer beerToUpdate = service.getBeerById(id);
		
		// Przekaż piwo do formularza Thymeleaf
		model.addAttribute("beer", beerToUpdate);
		
		return "edit_form";
	}
	
	@PostMapping("/update/{id}")
	public String updateBeer(@PathVariable Integer id, @ModelAttribute Beer updatedBeer) {
		// Zaktualizuj piwo w serwisie
		service.updateBeer(id, updatedBeer);
		
		return "redirect:/list";
	}
	
	// change
}
