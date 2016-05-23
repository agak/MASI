package com.mycompany.masi.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jsondoc.core.annotation.ApiObjectField;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Skill implements Serializable {

    @Id
    @GeneratedValue
    @ApiObjectField(name = "id", description = "Entity identifier")
    private String id;
    private String name;
    private String cetegory;
    private int points;
    
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "skills")
    private List<CurriculumVitae> curriculumVitaes;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "skills")
    private List<JobOffer> jobOffers;
}
