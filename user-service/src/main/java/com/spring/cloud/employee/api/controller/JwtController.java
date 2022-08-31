package com.spring.cloud.employee.api.controller;

import com.spring.cloud.employee.api.DTO.JwtRequestDTO;
import com.spring.cloud.employee.api.DTO.JwtResponseDTO;
import com.spring.cloud.employee.api.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    // Get End point

    @PostMapping("/authenticate")
    public JwtResponseDTO createJwtTokenFromUserNamePassword(@RequestBody JwtRequestDTO jwtRequestDTO){
        return  jwtService.createJwtToken(jwtRequestDTO);
    }

}
