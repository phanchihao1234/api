package com.example.spring_api.services;

import com.example.spring_api.dto.StudentDTO;
import com.example.spring_api.dto.StudentImageDTO;
import com.example.spring_api.models.Student;
import com.example.spring_api.models.StudentImage;
import com.example.spring_api.models.XepLoai;
import com.example.spring_api.responses.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {

    Student getStudentByID(Long id);
    List<Student> getAllStudent();
    Student saveStudent(StudentDTO studentDTO);
    Student updateStudent(Long id,StudentDTO studentDTO);
    void deleteStudent(Long id);

    List<Student> findByName(String name);
    List<Student> findByThanhPho(String name);
    List<Student> findByThanhPhoVaTen(String name);
    Page<StudentResponse> getAllStudent(Pageable pageable);
    List<Student> findByXepLoai(XepLoai xepLoai);
    List<Student> findByNgaySinhBetween(int startYear,int endYear);
    List<Student> searchStudent(XepLoai xepLoai,String ten, int startYear,int endYear);

    //Image student
    StudentImage saveStudentImage(Long studentId, StudentImageDTO studentImageDTO);
    List<StudentImage> getAllStudentImages(Long studentId);

    List<StudentImage> getAllStudentImages2(Long studentId) ;

//    Page<CategoryResponse> getAllCategory(PageRequest pageRequest);
}
