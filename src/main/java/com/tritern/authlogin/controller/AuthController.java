package com.tritern.authlogin.controller;


import com.tritern.authlogin.entity.AuthEntity;
import com.tritern.authlogin.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class AuthController {

    @Autowired
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String createUser(@RequestBody AuthEntity authEntity) {
        return authService.signUp(authEntity);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/removeuser/{email}")
    public String deleteUser(@PathVariable("email") String email) {
        return authService.removeUser(email);
    }

    @RequestMapping(value = "/User/list/search/size",method = RequestMethod.GET)
    public Integer searchSize(@RequestParam(value = "keyword", required = false) String keyword){
        System.out.println("API for Search Size Length = "+authService.getSize(keyword));
        return authService.getSize(keyword);
    }

    @GetMapping(value = "/User/list/fullsize")
    public Integer getListSize(){
        System.out.println("API for Full Length = "+ authService.getSizeNor());
        return authService.getSizeNor();
    }

    @RequestMapping(value = "/User/list", method = RequestMethod.GET)
    public List<AuthEntity> showUsers( @RequestParam(defaultValue = "1",required = false) Integer pageNo){
        System.out.println("List API");
        return authService.getList(pageNo);
    }

    @RequestMapping(value = "/User/list/search", method = RequestMethod.GET)
    public List<AuthEntity> viewUsers(@RequestParam(value = "keyword", required = false) String keyword ,
                                      @RequestParam(value = "pageNo",required = false,defaultValue = "1") Integer pageNo) {
        System.out.println("Search API");
            return authService.getFilter(keyword,pageNo);
    }


    @GetMapping("/login/{email}/{password}")
    public String login(@PathVariable("email") String email, @PathVariable("password") String password) {
        return authService.loginUser(email, password);
    }


}



//    @RequestParam(defaultValue = "5",required = false) Integer pageSize
//            }
//        }else {
//            public List<AuthEntity> getAuthUserModel(){
//               return authService.getAuthUserModel();
//             }
//        }
