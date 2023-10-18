//package com.tutorial.apidemo.controllers;
//import com.tutorial.apidemo.dtos.Object;
//import jakarta.validation.Valid;
//import java.util.List;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/api/v1/categories")
////@Validated
//public class HelloController {
//
//    @GetMapping("")//http://localhost:8088/api/v1/categories
//    public ResponseEntity<String> hello() {
//        return ResponseEntity.ok("Chao ban nha");
//
//    }
//
//    @PostMapping("")//http://localhost:8088/api/v1/categories
//    public ResponseEntity<String> insert(
//            @RequestParam("page") int page,
//            @RequestParam("limit") int limit) {
//        return ResponseEntity.ok(String.format("insert,page=%d,limit=%d", page, limit));
//    }
//
//    @PutMapping("")
//    public ResponseEntity<?> insertObject(@Valid @RequestBody Object object, BindingResult result)
//    {
//        if (result.hasErrors()) {
//            List<String> errorrMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
//                    .toList();
//
//            return ResponseEntity.badRequest().body(errorrMessages);
//        }
//        return ResponseEntity.ok("Hehid");
//
//
//    }
//}
//
//
//
//
//
//
