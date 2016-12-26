package com.absurd.mapper;

import com.absurd.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20.
 */
public interface UserMapper {
    User selectById(@Param("id") long id);
     User selectByName(@Param("username") String username);
      List<User> selectAll();
      int selectCount();
     int insert(User u);
     int delete(Long id);
  boolean update(User u);
}
