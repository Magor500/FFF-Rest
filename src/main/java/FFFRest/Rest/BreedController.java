package FFFRest.Rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import storage.Breed;
import storage.BreedDao;

@RestController
public class BreedController {
	
	private final BreedDao breedDao;
	
	public BreedController(BreedDao breedDao) {
		this.breedDao = breedDao;
	}

	@GetMapping("/breeds/getAll")
	public List<Breed> getAll() {
		return breedDao.getAll(); 
	}
	
	@PostMapping("/breeds/save")
	public Breed save(@RequestBody Breed breed) {
		return breedDao.save(breed);
	}
	
	@DeleteMapping("/breeds/{id}")
	public void delete(@PathVariable long id) {
		breedDao.delete(id);
	}

}
