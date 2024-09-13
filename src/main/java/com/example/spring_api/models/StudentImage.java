package com.example.spring_api.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @Column(name = "image_url",length = 300)
    private String imageUrl;
}
