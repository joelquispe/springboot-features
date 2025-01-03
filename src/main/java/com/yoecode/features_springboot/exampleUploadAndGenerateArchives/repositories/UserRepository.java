package com.yoecode.features_springboot.exampleUploadAndGenerateArchives.repositories;

import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
