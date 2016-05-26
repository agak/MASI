package com.mycompany.masi.repository;

import com.mycompany.masi.model.Skill;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Long>{
        
    @Override
    List< Skill> findAll();
}
