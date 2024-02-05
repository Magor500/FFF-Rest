package FFFRest.Rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import storage.Breed;
import storage.BreedDao;

public class BreedController {
	private final BreedDao breedDao;
	
	public BreedController(BreedDao breedDao) {
		this.breedDao = breedDao;
	}

	@GetMapping("/breeds")
	public List<Breed> getAll() {
		return breedDao.getAll(); 
	}
	
	@PostMapping("/breeds")
	public Breed save(@RequestBody Breed breed) {
		return breedDao.save(breed);
	}
	
	@DeleteMapping("/breeds/{id}")
	public void delete(@PathVariable long id) {
		breedDao.delete(id);
	}

}
