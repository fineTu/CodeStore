package dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import po.User;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	static Logger logger = LoggerFactory.getLogger(UserDao.class.getName());
	public User login(String userName,String pass){
		logger.debug("=====================login!!=====================");
		String sql = "select * from user where name = ? and password = ?";
		//返回键值对的形式值得学习
		List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql, new Object[]{userName,pass});
		if(list.size()==1){
			Map<String,Object> map = list.get(0);
			User user = new User();
			user.setId(((Long) map.get("id")).intValue());
			user.setName((String) map.get("name"));
			user.setPassword((String) map.get("password"));
			user.setDepartId(((Long) map.get("deptid")).intValue());
			return user;
		}
		return null;
	}
	
	public boolean register(User user){
		logger.debug("=====================login!!=====================");
		String sql = "insert into user(name,password) values(?,?)";
		int i = jdbcTemplate.update(sql, new Object[]{user.getName(),user.getPassword(),1});
		return i==1?true:false;
	}
}
