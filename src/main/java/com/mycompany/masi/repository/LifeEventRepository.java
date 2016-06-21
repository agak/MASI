package com.mycompany.masi.repository;

import com.mycompany.masi.model.LifeEvent;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface LifeEventRepository extends CrudRepository<LifeEvent, Long>{
        
    @Override
    List< LifeEvent> findAll();
}
