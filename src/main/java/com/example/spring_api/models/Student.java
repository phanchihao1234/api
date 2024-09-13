package com.example.spring_api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="student")
@Builder
public class Student extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Tên không được để trống")
    private String ten;
    @NotBlank(message = "Thành phố không được để trống")
    private String thanhPho;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
//    @JsonFormat(pattern = "dd-MM-YYYY")
    @Past(message = "không được là ngày quá khứ")
    private LocalDate ngaySinh;
    @NotNull(message = "Xếp loại không được để trống")
    @Enumerated(EnumType.STRING)
    private XepLoai xepLoai;

}
