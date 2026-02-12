package com.CO527.LMS.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "entrollments")
public class Enrollment {

    private String userId;
    private String courseId; 
    @Id
    private String id;  
    private String enrollmentDate;
    private String status;

    public String getUserId(){
        return userId;
    }
    public String getCourseId(){
        return courseId;
    }
    public  String getId(){
        return id;
    } 
    public String getEnrollmentDate(){
        return enrollmentDate;
    }
    public String getStatus(){
        return status;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setEnrollmentDate(String enrollmentDate){
        this.enrollmentDate = enrollmentDate;
    }
    public void setStatus(String status){
        this.status = status;
    }   
    public void setUserId(String userId){
        this.userId = userId;
    }
    public void setCourseId(String courseId){
        this.courseId = courseId;
    }
}
