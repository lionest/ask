package com.ah3nong.wd.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.UserDao;

public class UserDaoImpl extends BaseDao<UserDaoImpl> implements UserDao ,Serializable {
	private static final long serialVersionUID = 8846311345801486154L;

	public int countAllUser(int id) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject(
				"wd_user.countAll", id);
		return rows;
	}

	public int countForPager(Map map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject(
				"wd_user.countPaginationByPageNum", map);
		return rows;
	}

	public List<User> findAllUser() {
		List<User> list = getSqlMapClientTemplate().queryForList(
				"wd_user.selectAll");
		return list;
	}

	public List<User> findExperts(int id) {
		List<User> list = getSqlMapClientTemplate().queryForList(
				"wd_user.selectExpert", id);
		return list;
	}

	public User findUserByPrimaryKey(int id) {
		User key = new User();
		key.setId(id);
		User user = (User) getSqlMapClientTemplate().queryForObject(
				"wd_user.selectByPrimaryKey", key);
		return user;
	}

	public List<User> queryForPager(Map map) {
		List<User> list = getSqlMapClientTemplate().queryForList(
				"wd_user.selectPaginationByPageNum", map);
		return list;
	}

	public User findUserByUsername(String username) {
		User key = new User();
		key.setUsername(username);
		User user = (User) getSqlMapClientTemplate().queryForObject(
				"wd_user.selectByUserId", key);
		return user;
	}

	public int findCountOfReply(int id) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject(
				"wd_user.selectReplyCount", id);
		return rows;
	}

	public int findCountOfReplyAccepted(int id) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject(
				"wd_user.selectReplyCountAccepted", id);
		return rows;
	}

	public int insertUser(User u) {
		int id = (Integer) getSqlMapClientTemplate()
				.insert("wd_user.insert", u);
		return id;
	}

	@Override
	public int updateUserIsExpert(User user) {
		int rows = (Integer) getSqlMapClientTemplate().update(
				"wd_user.updateUser", user);
		return rows;
	}

	@Override
	public int updateUserExperience(User user) {
		int rows = (Integer) getSqlMapClientTemplate().update(
				"wd_user.updateUserExperience", user);
		return rows;
	}

	@Override
	public int updateUserByPrimarykey(User user) {
		int rows = (Integer) getSqlMapClientTemplate().update(
				"wd_user.updateByPrimaryKey", user);
		return rows;
	}

}
