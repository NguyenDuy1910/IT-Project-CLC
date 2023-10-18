package com.tutorial.apidemo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.tutorial.apidemo.dtos.ProductDTO;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @GetMapping("")
    public ResponseEntity<String> getProducts(@RequestParam("page") int page,
                                              @RequestParam("limit") int limit) {
        return ResponseEntity.ok("getProducts here");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String productId) {
        return ResponseEntity.ok("Product with id: " + productId);
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(@Valid @ModelAttribute ProductDTO productDTO,
                                           //@RequestParam(value = "file", required = false) MultipartFile file,
                                           BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errors = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errors);
            }
            MultipartFile file= productDTO.getFile();
            if (file != null) {
                if (file.getSize() > 10 * 1024 * 1024) {
                    throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "File is too large! Maximum size is 10MB");
                }
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image");
                }
                //lưu file và cập nhật thumbnail trong DTO
                String filename = storeFile(file);
                //lưu file vào đối tượng product trong Database
            }
            return ResponseEntity.ok("Product created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String storeFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
        //Đường dẫn đầy đủ đến file

        java.nio.file.Path uploadDir=Paths.get("uploads");
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        java.nio.file.Path destination=Paths.get(uploadDir.toString(),uniqueFilename);
        Path filePath = uploadDir.resolve(uniqueFilename);
        Files.copy(file.getInputStream(),destination, StandardCopyOption. REPLACE_EXISTING);


        return uniqueFilename;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {
        return ResponseEntity.ok(String.format("Product with id %d has been deleted", id));
    }
}