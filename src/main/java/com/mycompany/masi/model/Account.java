package com.mycompany.masi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.jsondoc.core.annotation.ApiObjectField;
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

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    protected String id;
    protected String login;
    protected String hashPassword;
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
