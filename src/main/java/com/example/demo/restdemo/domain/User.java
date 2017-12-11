package com.example.demo.restdemo.domain;

import com.example.demo.restdemo.config.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

@Entity
public class User extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(name = "LOGIN", length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "PASSWORD_HASH", length = 60)
    private String password;

    @Size(max = 50)
    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Email
    @Size(max = 100)
    @Column(name = "EMAIL", length = 100, unique = true)
    private String email;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_NAME", referencedColumnName = "NAME")})
    @BatchSize(size = 20)
    private Set<Authority> authorities = Sets.newHashSet();


    public void setLogin(String login) {
        this.login = login.toLowerCase(Locale.ENGLISH);
    }

    public Set<Authority> getAuthorities() {
        return Collections.unmodifiableSet(authorities);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!login.equals(user.login)) {
            return false;
        }

        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' + "}";
    }
}
