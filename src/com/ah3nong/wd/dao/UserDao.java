package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.User;

public interface UserDao {

	public int insertUser(User u);

	public User findUserByPrimaryKey(int id);

	public User findUserByUsername(String username);

	public int findCountOfReply(int id);

	public int findCountOfReplyAccepted(int id);

	public List<User> findAllUser();

	public List<User> findExperts(int id);

	public int countAllUser(int id);

	public List<User> queryForPager(Map map);

	public int countForPager(Map map);

	public int updateUserIsExpert(User user);

	public int updateUserExperience(User user);
	
	public int updateUserByPrimarykey(User user);
}
