package com.CO527.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.CO527.LMS.model.Enrollment;
import com.CO527.LMS.repository.EnrollmentRepository;
import com.CO527.LMS.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentRepository repo;

    public EnrollmentController(EnrollmentRepository repo){
        this.repo = repo;

    }

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return repo.findAll();
    }
    @GetMapping("/{id}")
    public Enrollment getEntrollmentById(@PathVariable String id) {
        // This is a placeholder. In a real application, you would return a Entrollment from the database.
        return repo.findById(id).orElse(null);
    }
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public Enrollment enroll(@RequestParam String email,
                             @RequestParam String courseCode) {

        return enrollmentService.enrollUser(email, courseCode);
    }

    @DeleteMapping("/{id}")
    public String deleteEntrollment(@PathVariable String id) { 
        // This is a placeholder. In a real application, you would delete the Entrollment from the database.
        repo.deleteById(id);
        return "Entrollment with ID: " + id + " deleted.";
    }
    
}
