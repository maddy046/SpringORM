package com.springorm.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table
public class Student {

    @Id
    @Column(name = "id")
    private int studentId;
    @Column(name = "name")
    private String studentName;
    @Column(name = "city")
    private String city;

    public Student() {
    }

    public Student(int studentId, String studentName, String city) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    //getter setter
}
