package com.example.spring_api.services;

import com.example.spring_api.dto.StudentDTO;
import com.example.spring_api.models.Student;
import com.example.spring_api.models.XepLoai;
import com.example.spring_api.repositories.StudentRepository;
import com.example.spring_api.responses.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{
    private final StudentRepository studentRepository;
    @Override
    public Student getStudentByID(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(StudentDTO c) {
        Student student = Student.builder()
                .ten(c.getTen())
                .thanhPho(c.getThanhPho())
                .ngaySinh(c.getNgaySinh())
                .xepLoai(XepLoai.fromXl(c.getXepLoai()))
                .build();
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, StudentDTO c) {
        Student student = getStudentByID(id);
//        return student;
        student.setTen(c.getTen());
        student.setThanhPho(c.getThanhPho());
        student.setNgaySinh(c.getNgaySinh());
        student.setXepLoai(XepLoai.fromXl(c.getXepLoai()));
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentRepository.findByTenContainsIgnoreCase(name);
    }

    @Override
    public List<Student> findByThanhPho(String name) {
        return studentRepository.findByThanhPho(name);
    }

    @Override
    public List<Student> findByThanhPhoVaTen(String name) {
        return studentRepository.findByThanhPhoAndTen(name);
    }

    @Override
    public Page<StudentResponse> getAllStudent(Pageable pageable) {
        return studentRepository.findAll(pageable).map(student -> {
            return StudentResponse.fromStudent(student);
        });
    }

    @Override
    public List<Student> findByXepLoai(XepLoai xepLoai) {
        return studentRepository.findByXepLoai(xepLoai);
    }

    @Override
    public List<Student> findByNgaySinhBetween(int startYear, int endYear) {
        return studentRepository.findByNgaySinhBetween(startYear,endYear);
    }

    @Override
    public List<Student> searchStudent(XepLoai xepLoai, String ten, int startYear, int endYear) {
        return  studentRepository.search(xepLoai,ten,startYear,endYear);
    }






//    @Override
//    public List<Student> searchByName(String nameStuden) {
//        return studentRepository.findByNameAndCity(nameStuden);
//    }
}
