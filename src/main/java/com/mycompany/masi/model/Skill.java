package com.mycompany.masi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSkill;
    private String name;
    private String cetegory;
    private int points;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "skills")
    private List<CurriculumVitae> curriculumVitaes;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "skills")
    private List<JobOffer> jobOffers;
}
