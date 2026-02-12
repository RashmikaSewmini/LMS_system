package com.CO527.LMS.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.CO527.LMS.model.Course;
import com.CO527.LMS.repository.CourseRepository;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository repo;

    public CourseController(CourseRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return repo.findAll();
    }
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }
    @PostMapping
    public String createCourse(@RequestBody Course course) {
        repo.save(course);
        return "Course created: " + course;
    }

    @PutMapping("/{id}")
    public String updateCourse(@PathVariable String id, @RequestBody Course course) {
        for (Course c: repo.findAll()) {
            if (c.getId().equals(id)) {
                c.setCourseName(course.getCourseName());
                c.setDescription(course.getDescription());
                repo.save(c);
                return "Course with ID: " + id + " updated to: " + c;
            }
        }
        return "Course with ID: " + id + " not found.";      
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable String id) { 
        repo.deleteById(id);
        return "Course with ID: " + id + " deleted.";
    }
    
}
