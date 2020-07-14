package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Student;
import Utils.Util;

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CARS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_FACULTY + " TEXT,"
                + Util.KEY_SURNAME + " TEXT,"
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_AVERAGE_SCORE + " INTEGER" + ")";
        db.execSQL(CREATE_CARS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "
                + Util.TABLE_NAME );

        onCreate(db);
    }

    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_FACULTY,student.getFaculty());
        contentValues.put(Util.KEY_SURNAME,student.getSurname());
        contentValues.put(Util.KEY_NAME,student.getName());
        contentValues.put(Util.KEY_AVERAGE_SCORE,student.getAverageScore());

        db.insert(Util.TABLE_NAME,null,contentValues);
        db.close();
    }

    public Student getStudent(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME,new String[] {Util.KEY_ID,
                        Util.KEY_FACULTY,
                        Util.KEY_SURNAME,
                        Util.KEY_NAME,
                        Util.KEY_AVERAGE_SCORE},Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null, null,
                null, null);
        Student student = new Student();

        if(cursor != null){
            cursor.moveToFirst();
            student = new Student(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    Integer.parseInt(cursor.getString(4)));
        }
        return student;
    }

    public List<Student> getAllStudents(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Student> studentList = new ArrayList<Student>();
        String selectAllStudents = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllStudents, null);

        if (cursor.moveToFirst()){
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setFaculty(cursor.getString(1));
                student.setSurname(cursor.getString(2));
                student.setName(cursor.getString(3));
                student.setAverageScore(Integer.parseInt(cursor.getString(4)));
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        return studentList;
    }

    public int updateStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_FACULTY, student.getFaculty());
        contentValues.put(Util.KEY_SURNAME, student.getSurname());
        contentValues.put(Util.KEY_NAME, student.getName());
        contentValues.put(Util.KEY_AVERAGE_SCORE, student.getAverageScore());
        return db.update(Util.TABLE_NAME, contentValues,Util.KEY_ID + "=?",new String[]{String.valueOf(student.getId())});
    }

    public void deleteStudent (Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",new String[]{String.valueOf(student.getId())});
    }
}
