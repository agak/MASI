package com.mycompany.masi.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurriculumVitae implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurriculumVitae;
    @ManyToMany
    @JoinTable(name = "curriculumVitaes_skills", joinColumns = @JoinColumn(name = "curriculumVitae_id", referencedColumnName = "idCurriculumVitae"), inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "idSkill"))
    private List<Skill> skills;
    @OneToMany()
    private List<ExternalDocument> externalDocuments;
}
