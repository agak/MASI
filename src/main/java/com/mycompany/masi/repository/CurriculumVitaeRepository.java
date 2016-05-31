package com.mycompany.masi.repository;

import com.mycompany.masi.model.CurriculumVitae;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CurriculumVitaeRepository extends CrudRepository<CurriculumVitae, Long> {
    
    @Override
    List< CurriculumVitae> findAll();
    
    CurriculumVitae findByIdCurriculumVitae(long idCurriculumVitae);
}
