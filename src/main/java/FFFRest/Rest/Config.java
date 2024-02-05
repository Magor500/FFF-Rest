package FFFRest.Rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import storage.BreedDao;
import storage.MysqlBreedDao;
import storage.MysqlTypeActivityDao;
import storage.MysqlUserDao;
import storage.TypeActivityDao;
import storage.UserDao;

@Configuration
public class Config {
	private final JdbcTemplate jdbcTemplate;
	
	public Config(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Bean
	public BreedDao getBreedDao(){
			return new MysqlBreedDao(jdbcTemplate);
	}
	
	@Bean
	public TypeActivityDao getTypeActivityDao(TypeActivityDao typeActivityDao){
		return new MysqlTypeActivityDao(jdbcTemplate, typeActivityDao);
	}
	
	@Bean
	public UserDao getUseDao(UserDao userDao){	
		return new MysqlUserDao(jdbcTemplate, userDao);
	}
}
