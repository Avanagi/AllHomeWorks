package org.example;

import java.sql.SQLException;

import Editor.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://127.0.0.1:5432/school";
        String user = "postgres";
        String password = "2281488";
        //dbEditor.createStudent("Alexander", "Ramzanov", "Borisovich", 2);
        //dbEditor.createSubject("Chemistry", "Lopashov");
        //dbEditor.deleteStudent("Alexander");
        //dbEditor.deleteSubject("Chemistry");
        //dbUpdate.updateStudent();

        //ArrayList<Student> stl = new ArrayList<Student>();
        //Student st = new Student("Artem", "Dobryi", "Grob", 1);
        //stl.add(st);
        //st = new Student("Lodgf", "ngfh", "Grotttb", 3);
        //stl.add(st);
        //DBUpdateStudent dbUpdate = new DBUpdateStudent();
        //dbUpdate.createStudent(stl);


        StudentDaoImpl dbinfo = new StudentDaoImpl();
        dbinfo.getCurrStudentSubjects(url, user, password, "Roman");
    }

}
