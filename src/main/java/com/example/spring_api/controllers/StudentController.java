package com.example.spring_api.controllers;

import com.example.spring_api.dto.StudentDTO;
import com.example.spring_api.exceptions.ResoureNotFoundException;
import com.example.spring_api.models.Student;
import com.example.spring_api.models.XepLoai;
import com.example.spring_api.responses.ApiResponse;
import com.example.spring_api.responses.StudentListResponse;
import com.example.spring_api.responses.StudentResponse;
import com.example.spring_api.services.StudentService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleInfoNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/student")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getStudent(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(
            page, size, Sort.by("createAt").descending()
        );
        Page<StudentResponse> studentResponses = studentService.getAllStudent(pageable);
        int totalPages =  studentResponses.getTotalPages();
        List<StudentResponse> responseList = studentResponses.getContent();
        StudentListResponse studentListResponse = StudentListResponse.builder()
                .studentResponseList(responseList)
                .totalPages(totalPages)
                .build();
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Show students Successfully")
                .data(studentListResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllStudent(){
        ApiResponse apiResponse = ApiResponse.builder()
                .data(studentService.getAllStudent())
                .status(HttpStatus.OK.value())
                .message("okela")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
    @PostMapping("/")
    public ResponseEntity<ApiResponse> createStudent(@Valid @RequestBody StudentDTO stu, BindingResult result){
//        if(result.hasErrors()){
//            List<String> errors = new ArrayList<>();
//            for (FieldError fieldError : result.getFieldErrors()){
//                errors.add(fieldError.getField());
//            }
//            return errors.toString();
//        }
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(errors)
                    .message("Validation failed")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        ApiResponse apiResponse = ApiResponse.builder()
                .data(stu)
                .message("Add student success")
                .status(HttpStatus.OK.value())
                .build();
        studentService.saveStudent(stu);
        return ResponseEntity.ok(apiResponse);
//        return studentService.saveStudent(stu);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long id){
        Student student = studentService.getStudentByID(id);
        if (student == null){
            throw new ResoureNotFoundException("Student khong tim thay "+id);
        }
        studentService.deleteStudent(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .data(id)
                .message("delete successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse>  updateStudent(@Valid @PathVariable Long id,@RequestBody StudentDTO studentDTO ,BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(errors)
                    .message("Validation failed")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        Student student = studentService.updateStudent(id,studentDTO);
        if(student == null){
            throw new ResoureNotFoundException("Student khong tim thay vs id: "+id);
        }
        ApiResponse apiResponse = ApiResponse.builder()
                .data(student)
                .message("Update successfully")
                .status(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/search1")
    public ResponseEntity<ApiResponse> searchStudent(@RequestParam String name){
        ApiResponse apiResponse = ApiResponse.builder()
                .data(studentService.findByName(name))
                .message("Search Successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/search2")
    public ResponseEntity<ApiResponse> searchThanhPho(@RequestParam String name){
        ApiResponse apiResponse = ApiResponse.builder()
                .data(studentService.findByThanhPho(name))
                .message("Search Successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/search3")
    public ResponseEntity<ApiResponse> searchThanhPhoVaTen(@RequestParam String name){
        ApiResponse apiResponse = ApiResponse.builder()
                .data(studentService.findByThanhPhoVaTen(name))
                .message("Search Successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/searchNS")
    public ResponseEntity<ApiResponse> searchNgaySinh(@RequestParam int startYear,int endYear){
        ApiResponse apiResponse = ApiResponse.builder()
                .data(studentService.findByNgaySinhBetween(startYear,endYear))
                .message("Search Successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/searchXL")
    public ResponseEntity<ApiResponse> searchXepLoai(@RequestParam("xepLoai") String xepLoaiStr){
        ApiResponse apiResponse = ApiResponse.builder()
                .data(studentService.findByXepLoai(XepLoai.fromXl(xepLoaiStr)))
                .message("Search Successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> search(
            @RequestParam(value = "xepLoai",required = false) String xepLoai,
            @RequestParam(value = "ten",required = false) String ten,
            @RequestParam(value = "startYear",required = false) int startYear,
            @RequestParam(value = "endYear",required = false) int endYear
    ){
        ApiResponse apiResponse = ApiResponse.builder()
                .data(studentService.searchStudent(XepLoai.fromXl(xepLoai),ten,startYear,endYear))
                .message("Search Successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
