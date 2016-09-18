package com.absurd.dao;

import com.absurd.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2016/5/19.
 */

public interface UserDao{

    User queryUser(String username);

    List<User> select(Pageable page);


}
