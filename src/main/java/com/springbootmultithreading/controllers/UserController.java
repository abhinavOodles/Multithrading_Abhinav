package com.springbootmultithreading.controllers;

import com.springbootmultithreading.entities.User;
import com.springbootmultithreading.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/multithreading")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/users")
    public ResponseEntity<Void> saveUsers(@RequestParam("file") MultipartFile[] files) {
        try {
            for (MultipartFile file : files) {
                service.saveUser(file);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public CompletableFuture<List<User>> getUsers() {
        return service.findAll();
    }
}
