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
import storage.TypeActivity;
import storage.TypeActivityDao;

@RestController
public class TypeActivityController {

private final TypeActivityDao typeActivityDao;
	
	public TypeActivityController(TypeActivityDao typeActivityDao) {
		this.typeActivityDao = typeActivityDao;
	}

	@GetMapping("/typeActivities/getAll")
	public List<TypeActivity> getAll() {
		return typeActivityDao.getAll(); 
	}
	
	@GetMapping("/typeActivities/{id}")
	public void getById(@PathVariable long id) {
		typeActivityDao.getById(id);
	}

}
