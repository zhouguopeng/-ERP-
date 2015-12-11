package com.dao.implement;

import java.util.List;

import org.crazycake.jdbcTemplateTool.JdbcTemplateTool;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.model.User;
import com.util.SpringContextUtil;


@Repository
public class UserDaoImpl implements UserDao {
	
	public User findByUserId(String id) {

	 	JdbcTemplateTool jtt = SpringContextUtil.getApplicationContext().getBean("jdbcTemplateTool",JdbcTemplateTool.class);
	 	String sql = "select * from sys_user where USERID=?";
	 	
	 	List<User> userList = jtt.list(sql, new Object[]{id}, User.class);
	 	User user = new User();
	 	user.setUserId(id);
	 	if(null!=userList && userList.size()!=0){
	 		user = userList.get(0);
	 	}
	 	return user;
	 	/*String sql = "select * from sys_user where USERID='"+id+"'";  
        
		User user=(User)jdbcTemplate.queryForObject(sql, new RowMapper(){  
        	  
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {  
            // TODO Auto-generated method stub  
            User user=new User();  
            user.setUserId(rs.getString("USERID"));
            user.setUserName(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            return user;  
            }
        });  
       */ 
	}
}
