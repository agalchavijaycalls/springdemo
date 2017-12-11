package com.example.demo.restdemo.domain;

import com.example.demo.restdemo.security.SecurityUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractAuditingEntity implements Serializable{

    @CreatedBy
    @Column(name = "CREATED_BY", nullable = false, length = 50, updatable = false)
    @JsonIgnore
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY", length = 50)
    @JsonIgnore
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @JsonIgnore
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    @Version
    @Column(name = "VERSION")
    private Long version;

    @PrePersist
    public void preInsert() {
        String login = SecurityUtils.getCurrentUserLogin();
        LocalDateTime now = LocalDateTime.now();
        setCreatedBy(login);
        setCreatedDate(now);

        setLastModifiedBy(login);
        setLastModifiedDate(now);
    }

    @PreUpdate
    public void preUpdate() {
        String login = SecurityUtils.getCurrentUserLogin();
        LocalDateTime now = LocalDateTime.now();

        setLastModifiedBy(login);
        setLastModifiedDate(now);
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
