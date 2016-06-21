package com.mycompany.masi.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
public class CompanyAccount extends Account {
    
    public CompanyAccount (Long idAccount, String login, String password, String companyName) {
        this.idAccount = idAccount;
        this.login = login;
        this.password =password;
        this.companyName = companyName;
        isBloced = false;
    }

private String companyName;
private String NIP;
private String REGON;

 @OneToMany()
private List<JobOffer> jobOffers;
}
