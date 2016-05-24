package com.mycompany.masi.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserAccount extends Account {

    public UserAccount(String id, String login, String hashPassword, String firstName, String secondName) {
        this.id = id;
        this.login = login;
        this.hashPassword = hashPassword;
        this.firstName = firstName;
        this.secondName = secondName;
        isBloced = false;

    }

    protected String firstName;
    protected String secondName;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDate;
    @OneToOne
    private CurriculumVitae curriculumVitaes;
}
