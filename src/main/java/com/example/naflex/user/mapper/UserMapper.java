package com.example.naflex.user.mapper;

import com.example.naflex.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserVO> userList();
    UserVO fetchUserByID(int user_id);
    void updateUser(UserVO user);
    void insertUser(UserVO user);;
    void deletUser(int user_id);
}
