package com.absurd.dao.impl;

import com.absurd.core.dao.BaseDao;
import com.absurd.dao.UserDao;
import com.absurd.model.User;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
@Repository
public class UserDaoImpl  extends BaseDao implements UserDao {
    static final String NAMESPACE_INFOUSER = "com.absurd.mapper.UserMapper.";

    @Override
    public User queryUser(String username){
        return  getJdbcTemplate().queryForObject("select uid as id,username,password from t_user where username=?", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User u = new User();
                u.setId(resultSet.getLong("id"));
                u.setUsername(resultSet.getString("username"));
                u.setPassword(resultSet.getString("password"));
                return u;
            }
        },username);
    }

    @Override
    public List<User> select(PageInfo page) {
        return super.getSqlSessionTemplate().selectList( NAMESPACE_INFOUSER + "selectAll" , null,new RowBounds(page.getPageNum(), page.getPageSize()));
    }
}
