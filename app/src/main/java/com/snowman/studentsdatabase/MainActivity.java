package com.snowman.studentsdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import Data.DataBaseHandler;
import Model.Student;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Student> allStudents;

        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);

        dataBaseHandler.addStudent(new Student("Physics", "Petr","Petrov",4));
        dataBaseHandler.addStudent(new Student("Chemistries", "Ivan","Ivanov",5));
        dataBaseHandler.addStudent(new Student("Mathematics", "Sergey","Sergeev",3));
        dataBaseHandler.addStudent(new Student("Philosophies", "Ilyia","Ilyushin",4));
        dataBaseHandler.addStudent(new Student("Sociology", "Alexey","Alyoshin",5));

        allStudents = (ArrayList) dataBaseHandler.getAllStudents();

        for(Student student: allStudents){
            Log.i("Student", student.getId() + " Faculty " + student.getFaculty()
                    + " Surname " + student.getSurname()
                    + " Name " + student.getName()
                    + " Average Score " + student.getAverageScore());
        }
    }
}