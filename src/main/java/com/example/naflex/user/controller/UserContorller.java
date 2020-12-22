package com.example.naflex.user.controller;

import com.example.naflex.user.mapper.UserMapper;
import com.example.naflex.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserContorller {

    @Autowired
    UserMapper userMapper;

    @GetMapping
    public List<UserVO> userList(){
        return userMapper.userList();
    }

    @PostMapping
    public void insertUser(@RequestBody UserVO user){
        userMapper.insertUser(user);
    }

    @GetMapping("/{user_id}")
    public UserVO fetchUserByID(@PathVariable int user_id){
        UserVO fetchUser = userMapper.fetchUserByID(user_id);
        return fetchUser;
    }

    @PutMapping("/{user_id}")
    public void updateUser(@PathVariable int user_id, @RequestBody UserVO user){

        UserVO updateUser = user;

        updateUser.setFirst_name(user.getFirst_name());
        updateUser.setLast_name(user.getLast_name());
        updateUser.setAge(user.getAge());
        updateUser.setSalary(user.getSalary());

        userMapper.updateUser(user);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable int user_id){
        userMapper.deletUser(user_id);
    }
}
