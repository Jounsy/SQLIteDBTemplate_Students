package Model;

public class Student {
    private int id;
    private String faculty;
    private String surname;
    private String name;
    private int averageScore;

    public Student(int id, String faculty, String surname, String name, int averageScore) {
        this.id = id;
        this.faculty = faculty;
        this.surname = surname;
        this.name = name;
        this.averageScore = averageScore;
    }

    public Student(String faculty, String family, String name, int averageScore) {
        this.faculty = faculty;
        this.surname = family;
        this.name = name;
        this.averageScore = averageScore;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }
}
