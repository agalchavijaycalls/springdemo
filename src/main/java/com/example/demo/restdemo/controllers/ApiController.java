package com.example.demo.restdemo.controllers;

import com.example.demo.restdemo.domain.Authority;
import com.example.demo.restdemo.domain.User;
import com.example.demo.restdemo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;

@RestController
public class ApiController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/api/hello")
    public ResponseEntity<?> hello() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        String msg = String.format("Hello %s", name);
        return new ResponseEntity<Object>(msg, HttpStatus.OK);
    }

    @GetMapping(path = "/api/me", produces = "application/json")
    public User me() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return accountService.findAccountByUsername(username);
    }

    @PostMapping(path = "/api/register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody User account) {
        try {
            Authority grantedAuthority = new Authority();
            grantedAuthority.setName("Authority");
            account.getAuthorities().add(grantedAuthority);
            return new ResponseEntity<Object>(
                    accountService.register(account), HttpStatus.OK);
        } catch (AccountException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(path = "/api/user/remove", produces = "application/json")
    public ResponseEntity<?> removeUser() {
        try {
            accountService.removeAuthenticatedAccount();
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.OK);
        }
    }

}
