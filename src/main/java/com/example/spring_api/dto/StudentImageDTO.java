package com.example.spring_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentImageDTO {
    @JsonProperty("student_id")
    @Min(value = 1,message = "Id of student > 1")
    private Long studentId;
    @Size(min=5,max = 200,message = "Tên của hình ảnh phải từ 5 kí tự, không quá hơn 200 kí tự")
    @JsonProperty("image_url")
    private String imageUrl;
}
