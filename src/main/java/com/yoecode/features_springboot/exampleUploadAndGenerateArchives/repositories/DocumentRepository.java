package com.yoecode.features_springboot.exampleUploadAndGenerateArchives.repositories;

import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.entities.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity,Integer> {
}
