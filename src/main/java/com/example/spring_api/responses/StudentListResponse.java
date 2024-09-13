package com.example.spring_api.responses;

import lombok.*;

import java.util.List;

@Builder
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentListResponse {
    private List<StudentResponse> studentResponseList;
    private int totalPages;
}
