package com.dao.implement;

import java.util.List;

import org.crazycake.jdbcTemplateTool.JdbcTemplateTool;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.model.User;
import com.util.SpringContextUtil;


@Repository
public class UserDaoImpl implements UserDao {
	
	JdbcTemplateTool jtt = SpringContextUtil.getApplicationContext().getBean("jdbcTemplateTool",JdbcTemplateTool.class);
	
	public User findByUserId(String id) {

	 	String sql = "select * from sys_user where USERID=?";
	 	
	 	List<User> userList = jtt.list(sql, new Object[]{id}, User.class);
	 	User user = new User();
	 	user.setUserId(id);
	 	if(null!=userList && userList.size()!=0){
	 		user = userList.get(0);
	 	}
	 	return user;
	}
	
	public Long count(){
		String sql = "select * from sys_user";
		int count = jtt.count(sql, null);
		return Long.valueOf(count);
	}
	
	public void save() throws Exception{
		User user = new User();
		user.setUserId("111111");
		user.setUserName("aaaa");
		jtt.save(user);
	}
	
	public void update(Integer sid) throws Exception{
		User user = jtt.get(User.class, sid);
		user.setUserName("bbb");
		jtt.update(user);
	} 
	
	public void delete(Integer sid)throws Exception{
		User user = new User();
		user.setSid(Long.valueOf(sid));
		jtt.delete(user);
	}
}
