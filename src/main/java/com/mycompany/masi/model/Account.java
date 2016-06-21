package com.mycompany.masi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
@MappedSuperclass
public abstract class Account implements  Serializable {
    private static final long serialVersionUID = 1L;
    
    public Account(String login, String password){
        this.login=login;
        this.password=password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idAccount;
    protected String login;
    protected String password;
    protected String adress;
    protected String telephone;
    protected boolean isBloced;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastModified;

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        this.createdAt = now;
        this.lastModified = now;
    }

    @PreUpdate
    public void preUpdate() {
        Date now = new Date();
        this.lastModified = now;
    }
}
