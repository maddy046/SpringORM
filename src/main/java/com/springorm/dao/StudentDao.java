package com.springorm.dao;

import com.springorm.entities.Student;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDao {

    Transaction transaction = null;
    Integer studentid = null;
    List<Student> students;

    private SessionFactory sessionFactory;

    public StudentDao() {
    }

    public StudentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //insert query
    @Transactional
    public Integer insert(Student student) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            studentid = (Integer) session.save(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return studentid;
    }

    //read one object
    public Student getStudentById(Integer studentId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.get(Student.class, studentId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get all student
    public List<Student> getStudent() {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("FROM Student", Student.class);
            students = query.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    //delete by id
    public void deleteById(Integer studentid) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Student student = session.get(Student.class, studentid);
            if (student != null) {
                session.delete(student);
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student with ID " + studentid + " not found.");
            }

            session.getTransaction().commit();
        }
    }

    //update
    public String updateById(Integer studentid, Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Student student1 = session.get(Student.class, studentid);
            student1.setStudentName(student.getStudentName());
            student1.setCity(student.getCity());

            session.update(student1);
            session.getTransaction().commit();
            return "updated successfully";
        }
    }

}
