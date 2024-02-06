package FFFRest.Rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import storage.User;
import storage.UserDao;

@RestController
public class UserController{

	private final UserDao userDao;

	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@GetMapping("/users/getAll")
	public List<User> getAll() {
		return userDao.getAll();
	}

	@PostMapping("/users/save")
	public User save(@RequestBody User user) {
		return userDao.save(user);
	}
	
	@PostMapping("/users/update")
	public User update(@RequestBody User user) {
		return userDao.update(user);
	}
	
	@GetMapping("/users/{id}")
	public void getById(@PathVariable long id) {
		userDao.getById(id);
	}

}
