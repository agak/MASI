package com.mycompany.masi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class JobApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJobApplication;
    private Long idJobOffer;
    private Long idUser;
    private String userMessage;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date applicationDate;

    public JobApplication(Long idJobOffer, Long idUser, String userMessage) {
        this.idJobOffer = idJobOffer;
        this.idUser = idUser;
        this.userMessage = userMessage;
    }

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        this.applicationDate = now;
    }

}
