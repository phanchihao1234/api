package com.example.spring_api.dto;

import com.example.spring_api.models.XepLoai;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    @NotBlank(message = "Tên không được để trống")
    private String ten;
    @NotBlank(message = "Thành phố không được để trống")
    private String thanhPho;
//    @DateTimeFormat(pattern = "dd-MM-YYYY")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Past(message = "không được là ngày quá khứ")
    private LocalDate ngaySinh;
    @NotNull(message = "Xếp loại không được để trống")
    @Enumerated(EnumType.STRING)
    private String xepLoai;
}
