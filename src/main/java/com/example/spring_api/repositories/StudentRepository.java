package com.example.spring_api.repositories;

import com.example.spring_api.models.Student;
import com.example.spring_api.models.XepLoai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    // Custom JPQL query
//    @Query("SELECT s FROM Student s WHERE s.ten = ?2 ")
//    List<Student> findByNameAndCity(String ten);
    List<Student> findByTenContainsIgnoreCase(String ten);

    List<Student> findByXepLoai(XepLoai xepLoai);
    /// select s from student -> s la` *
    @Query(" SELECT s from Student s where s.thanhPho like lower(concat('%',:name,'%')) ")
    List<Student> findByThanhPho(String name);
    @Query(" SELECT s from Student s where s.thanhPho like lower(concat('%',:name,'%')) or s.ten like lower(concat('%',:name,'%')) ")
    List<Student> findByThanhPhoAndTen(String name);

    Page<Student> findAll(Pageable pageable);

    @Query("select s from Student s where year(s.ngaySinh) BETWEEN :startYear and :endYear")
    List<Student> findByNgaySinhBetween(int startYear,int endYear);

    @Query("SELECT s FROM Student s where "+
            "(:xepLoai is null or s.xepLoai = :xepLoai) and " +
            "(:ten is null or s.ten like %:ten%) and"+
            "(:startYear is null or year(s.ngaySinh) >= :startYear) and " +
            "(:endYear is null or year(s.ngaySinh) <= :endYear)"
    )
    List<Student> search(
            @Param("xepLoai") XepLoai xepLoai,
            @Param("ten") String ten,
            @Param("startYear") int startYear,
            @Param("endYear") int endYear
            );

}
