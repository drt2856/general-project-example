package com.cu.generalprojectexample.controller;


import com.cu.generalprojectexample.dto.TokenInfo;
import com.cu.generalprojectexample.dto.Userpass;
import com.cu.generalprojectexample.service.JwtService;
import com.cu.generalprojectexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;


    @GetMapping("/admin")
    public ResponseEntity<TokenInfo> admin() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        System.out.println(auth.getAuthorities());
        System.out.println(auth.isAuthenticated());
        System.out.println(auth.getCredentials());
        System.out.println(auth.getDetails());

        return ResponseEntity.ok(new TokenInfo("admin"));
    }

    @GetMapping("/public")
    public ResponseEntity<TokenInfo> publico() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        System.out.println(auth.getAuthorities());
        System.out.println(auth.isAuthenticated());
        System.out.println(auth.getCredentials());
        System.out.println(auth.getDetails());
        return ResponseEntity.ok(new TokenInfo("public"));
    }

    @PostMapping("/public/authentication")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody Userpass authentication) {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentication.getUsername(), authentication.getPassword()));

        final UserDetails userDetails = userService.loadUserByUsername(authentication.getUsername());

            final String jwt = jwtService.generateToken(userDetails);
            TokenInfo tokenInfo = new TokenInfo(jwt);

            return ResponseEntity.ok(tokenInfo);
    }



}
