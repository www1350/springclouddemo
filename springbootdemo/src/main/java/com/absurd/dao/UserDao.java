package com.absurd.dao;

import com.absurd.model.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/19.
 */

public interface UserDao{

    User queryUser(String username);

    List<User> select(PageInfo page);


}
