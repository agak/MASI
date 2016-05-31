package com.mycompany.masi.repository;

import com.mycompany.masi.model.JobApplication;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface JobApplicationRepository extends CrudRepository<JobApplication, Long> {
    
    @Override
    List< JobApplication> findAll();
    
    JobApplication findByIdJobApplication(long idJobApplication);
}
