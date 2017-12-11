package com.example.demo;

import com.example.demo.restdemo.dao.AccountRepository;
import com.example.demo.restdemo.dao.BookmarkRepository;
import com.example.demo.restdemo.domain.Account;
import com.example.demo.restdemo.domain.Bookmark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(AccountRepository accountRepository, BookmarkRepository bookmarkRepository) {
        return (evt) -> {
            logger.debug("Initializing database");
            Arrays.asList(
                    "vijay,ankur,mayank,rahul,aman".split(","))
                    .forEach(
                            a -> {
                                Account account = accountRepository.save(new Account(a,
                                        "password"));
                                bookmarkRepository.save(new Bookmark(account,
                                        "http://bookmark.com/1/" + a, "A description"));
                                bookmarkRepository.save(new Bookmark(account,
                                        "http://bookmark.com/2/" + a, "A description"));
                            });
            logger.debug("Initialization database : Done");
        };
    }
}
