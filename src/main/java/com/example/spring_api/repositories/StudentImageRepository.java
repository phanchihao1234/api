package com.example.spring_api.repositories;

import com.example.spring_api.models.StudentImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentImageRepository extends JpaRepository<StudentImage,Long> {
    List<StudentImage> findByStudentId(Long id);
}
