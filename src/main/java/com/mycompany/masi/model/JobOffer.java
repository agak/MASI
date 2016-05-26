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

public class JobOffer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJobOffer;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "jobOffers_skills",
            joinColumns = @JoinColumn(name = "jobOffer_id",
                    referencedColumnName = "idJobOffer"),
            inverseJoinColumns = @JoinColumn(name = "skill_id",
                    referencedColumnName = "idSkill"))

    private List<Skill> skills;
    private String location;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date insertDate;
    private String industry;

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        this.insertDate = now;
    }

}
