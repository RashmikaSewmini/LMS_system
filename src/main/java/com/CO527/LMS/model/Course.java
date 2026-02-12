package com.CO527.LMS.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "courses")
public class Course{

    private String courseName;

    @Id
    private String id;
    
    private String courseCode;
    private String description;

    public String getCourseName(){
        return courseName;
    }
    public String getCourseCode(){
        return courseCode;
    }
    public String getDescription(){
        return description;
    }
    public String getId(){
        return id;
    }
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }
    public void setCourseCode(String courseCode){
        this.courseCode = courseCode;
    }   
    public void setDescription(String description){
        this.description = description;
    }
    

}