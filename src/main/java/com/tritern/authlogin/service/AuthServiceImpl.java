package com.tritern.authlogin.service;

import com.tritern.authlogin.PasswordSalt;
import com.tritern.authlogin.entity.AuthEntity;
import com.tritern.authlogin.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AuthServiceImpl implements AuthService{

    @Autowired
    AuthRepository authRepository;

    @Autowired
    PasswordSalt passwordSalt;

    public AuthServiceImpl(AuthRepository authRepository,PasswordSalt passwordSalt) {
        this.authRepository = authRepository;
        this.passwordSalt=passwordSalt;
    }

//    public List<AuthEntity> getAuthUserModel(){
//
//        try {
////            if (keyword != null){
////                List<AuthEntity> users = authRepository.search(keyword);
////                return users;
////            }else {
//                List<AuthEntity> users = authRepository.findAll();
//                //  List<Auth> customUser = new ArrayList<>();
////            users.stream().forEach(e -> {
////                AuthUserModel user = new AuthUserModel();
////                BeanUtils.copyProperties(e, user);
////                customUser.add(user);
////            });
//
//                return users;
////            }
//        }catch(Exception e) {
//            throw e;
//        }
//    }

//    public List<EmployeeEntity> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
//    {
//        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//
//        Page<EmployeeEntity> pagedResult = repository.findAll(paging);
//
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<EmployeeEntity>();
//        }
//    }

//    public List<AuthEntity> getFiltered(String keyword,Integer pageNo) {
//        try {
//            Pageable paging = PageRequest.of(pageNo, 5);
//
//            if (keyword != null) {
//                List<AuthEntity> sl = authRepository.search(keyword, paging);
//                System.out.println(sl);
//                return sl;
//            } else {
//                Page<AuthEntity> pagedResult = authRepository.findAll(paging);
//                return pagedResult.getContent();
//            }
//        }catch (Exception e){
//            throw e;
//        }
//    }

    public List<AuthEntity> getList(Integer pageNo){
        try {
            Pageable paging = PageRequest.of(pageNo-1, 5);

            Page<AuthEntity> pagedResult = authRepository.findAll(paging);
            return pagedResult.getContent();
        }catch (Exception e){
            throw e;
        }
    }

    public List<AuthEntity> getFilter(String keyword, Integer pageNo){
        try{
            Pageable paging = PageRequest.of(pageNo-1, 5);

            if(keyword != null){
                List<AuthEntity> sl = authRepository.search(keyword, paging);
                System.out.println(sl);
                return sl;
            }
            else{
                return null;
            }
        } catch (Exception e){
            throw e;
        }

    }

    public Integer getSize(String keyword){
        try{

        List<AuthEntity> listSize = authRepository.searchLength(keyword);

        return listSize.size();

        }catch (Exception e){
            throw e;
        }
    }


    public Integer getSizeNor() {

        try {
            List<AuthEntity> listofSize = authRepository.findAll();

            return listofSize.size();
        } catch (Exception e){
            throw e;
        }
    }

    public String signUp(AuthEntity users) {
        try {

            if (!authRepository.existsByEmail(users.getEmail())){
                AuthEntity authEntity =new AuthEntity();
                authEntity.setFirstName(users.getFirstName());
                authEntity.setLastName(users.getLastName());
                authEntity.setEmail(users.getEmail());
                authEntity.setUsername(users.getUsername());
                String salt = passwordSalt.getSalt(30);
                String securePassword = passwordSalt.generateSecurePassword(users.getPassword(),salt);
                authEntity.setPassword(securePassword);
                authEntity.setSalt(salt);
                authRepository.save(authEntity);
                return "User added successfully";
            }else {
                return "This User already exists in the database.";
            }

        }catch (Exception e) {
            throw e;
        }
    }

    public String removeUser(String user) {
        try {

                AuthEntity authEntity = authRepository.findByEmail(user);
                System.out.println(authEntity);
                if(authEntity!=null){
                authRepository.delete(authEntity);
                return "User deleted successfully.";
            } else {
                return "User does not exist.";
            }
        }catch (Exception e) {
            throw e;
        }
    }

    public String loginUser(String username, String password) {
        try {
            AuthEntity authEntity = authRepository.findbyemail(username);
            System.out.println(authEntity);
            if(authEntity!=null) {
                String salt = authEntity.getSalt();
                String securePassword = passwordSalt.generateSecurePassword(password,salt);
                if (authEntity.getPassword().equals(securePassword)) {
                    System.out.println("User logined");
                    return "User Login Successfully";
                } else {
                    System.out.println("password incorrect");
                    return "password is incorrect";
                }
            }else{
                System.out.println("Id does not exist");
                return "Id does not exist";
            }
//            if (authRepository.existsById(users.getId())) {
//                authRepository.save(users);
//                return "User updated successfully.";
//            } else {
//                return "User does not exist.";
//            }exist
        }catch(Exception e) {
            throw e;
        }
    }

}
