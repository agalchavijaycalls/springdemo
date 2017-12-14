package com.example.demo.restdemo.services;

import com.example.demo.restdemo.dao.UserRepository;
import com.example.demo.restdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountException;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private UserRepository accountRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findAccountByUsername(String username) throws UsernameNotFoundException {
        Optional<User> account = accountRepo.findOneWithAuthoritiesByUsername(username);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }

    }

    public User register(User account) throws AccountException {
        if (account != null) {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            return accountRepo.save(account);
        } else {
            throw new AccountException(String.format("Username[%s] already taken.", account.getUsername()));
        }
    }

    @Transactional // To successfully remove the date @Transactional annotation must be added
    public void removeAuthenticatedAccount() throws UsernameNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User acct = findAccountByUsername(username);
        accountRepo.delete(acct.getId());

    }
}
