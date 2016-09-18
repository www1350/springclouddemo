package com.absurd.controller;

import com.absurd.model.User;
import com.absurd.service.UserService;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2016/5/21.
 */
@Api(value = "user",tags="user")
@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /***
     * http://localhost:8080/user/users?size=2&page=10
     * @param page
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public Page<User> getUsers(@PageableDefault(page = 0,value = 20) Pageable page){
      return   userService.getList(page);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserByParam(@RequestParam("id") Long id){
        User u = userService.getUser(id);
        if(u==null){
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<User>(u,HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        User u = userService.getUser(id);
        if(u==null){
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<User>(u,HttpStatus.OK);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public boolean addUser(@RequestBody User u){
        return userService.register(u);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateUser(@RequestBody User u){
        return userService.updateUser(u);
    }


}
