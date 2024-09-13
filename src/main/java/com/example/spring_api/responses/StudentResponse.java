package com.example.spring_api.responses;

import com.example.spring_api.models.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse extends BaseResponse{
    private Long id;
    private String ten;
    private String thanhPho;
    private LocalDate ngaySinh;
    private String xepLoai;
    public static StudentResponse fromStudent(Student student){
        StudentResponse studentResponse = StudentResponse.builder()
                .id(student.getId())
                .ten(student.getTen())
                .thanhPho(student.getThanhPho())
                .ngaySinh(student.getNgaySinh())
                .xepLoai(String.valueOf(student.getXepLoai()))
                .build();
        return studentResponse;
    }
}
