package com.tritern.authlogin.service;

import com.tritern.authlogin.entity.AuthEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {

//    List<AuthEntity> getAuthUserModel();

//    List<AuthEntity> getFiltered(String keyword,Integer pageNo);

    List<AuthEntity> getFilter(String keyword, Integer pageNo);

    List<AuthEntity> getList(Integer pageNo);

    Integer getSize(String keyword);

    Integer getSizeNor();

    String  signUp(AuthEntity authEntity);

    String removeUser(String email);

    String loginUser(String username, String password);
}
