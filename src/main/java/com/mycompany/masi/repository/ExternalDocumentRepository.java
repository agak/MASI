package com.mycompany.masi.repository;

import com.mycompany.masi.model.ExternalDocument;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ExternalDocumentRepository extends CrudRepository<ExternalDocument, Long> {

    @Override
    List< ExternalDocument> findAll();
}
