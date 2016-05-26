package com.mycompany.masi.repository;

import com.mycompany.masi.model.JobOffer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface JobOffersRepository extends CrudRepository<JobOffer, Long> {
    
    @Override
    List< JobOffer> findAll();
    
    JobOffer findByIdJobOffer(long idJobOffer);
}
