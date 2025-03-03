package com.springorm;

import com.springorm.dao.StudentDao;
import com.springorm.entities.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    static {
        System.out.println("*************Welcome to my crud application of student***************");
    }

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean go = true;
        while (go) {
            System.out.println("press 1 for add new student");
            System.out.println("press 2 for display all student");
            System.out.println("press 3 for get the detail of single student");
            System.out.println("press 4 for delete student");
            System.out.println("press 5 for update student");
            System.out.println("press 6 for exit student");

            try {
                int input = Integer.parseInt(br.readLine());
                switch (input) {
                    case 1:
                        // add a new student
                        System.out.println("Enter the student id: ");
                        int id = Integer.parseInt(br.readLine());
                        System.out.println("Enter the name of student: ");
                        String name = br.readLine();
                        System.out.println("Enter the student city: ");
                        String city = br.readLine();
                        Student st = new Student(id, name, city);
                        int result = studentDao.insert(st);
                        System.out.println("Student insert successfully " + result);
                        break;
                    case 2:
                        // display all st
                        List<Student> student1 = studentDao.getStudent();
                        student1.forEach(x -> System.out.println(x));
                        break;
                    case 3:
                        // get single st
                        System.out.println("Enter the student id for display details: ");
                        int stId = Integer.parseInt(br.readLine());
                        Student student = studentDao.getStudentById(stId);
                        System.out.println(student);
                        break;
                    case 4:
                        // delete st
                        System.out.println("Enter the student id for display details: ");
                        int deleteId = Integer.parseInt(br.readLine());
                        studentDao.deleteById(deleteId);
                        System.out.println("successfully deleted ");
                        break;
                    case 5:
                        // update st
                        Student student2 = new Student();
                        System.out.println("Give me id do you want to update");
                        int updateId = Integer.parseInt(br.readLine());
                        System.out.println("Enter the updated name");
                        student2.setStudentName(br.readLine());
                        System.out.println("Enter the updated city");
                        student2.setCity(br.readLine());
                        String updateResult = studentDao.updateById(updateId, student2);
                        System.out.println(updateResult);
                        break;
                    case 6:
                        // exit
                        System.out.println("Thanks for using my application");
                        go = false;
                        break;
                }


            } catch (Exception e) {
                System.out.println("invalid input try with another one");
                System.out.println(e.getMessage());
            }

        }


//            insert student information
//        Student st = new Student();
//        st.setStudentId(1);
//        st.setStudentName("Almas");
//        st.setCity("Burhanpur");
//        int result = studentDao.insert(st);
//        System.out.println("done" + result);

//        // read one student
//        Student student = studentDao.getStudentById(1);
//        System.out.println(student);
//
//        //read all student
//        List<Student> student1 = studentDao.getStudent();
//        System.out.println(student1);

        //delete by userid
//        studentDao.deleteById(1);
//        System.out.println("delete student successfully");

        //update by id
//        Student student = new Student();
//        student.setStudentName("Maddy");
//        student.setCity("Indore");
//        String s = studentDao.updateById(1, student);
//        System.out.println(s);


    }

}