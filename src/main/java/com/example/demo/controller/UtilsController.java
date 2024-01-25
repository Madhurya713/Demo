//package com.example.demo.controller;
//
//import com.example.demo.utils.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController("/authenticate")
//public class UtilsController {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//    @GetMapping("getToken")
//    public String generateToken(String userName){
//        return jwtUtil.generateToken(userName);
//    }
//}
