import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String registrationNumber;
    private int age;
    //
    private List<Course> courses;

    public Student(String studentName, String regNumber, int studentAge) {
        this.name = studentName;
        this.registrationNumber = regNumber;
        this.age = studentAge;
        //
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name; 
    }
    public String getReg() {
        return registrationNumber; 
    }
    public int getAge() {
        return age; 
    }
    
    //
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course){
        courses.remove(course);
    }

    // getter for showing list
    public List<Course> getCourse() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', registrationNumber='" + 
        registrationNumber + "', age=" + age + ",\nCourses=\n" +
        courses + '}'; // Student{name=' ', registraionNumber=' ', age= , courses= }
    }

    // public String toString() {
    //     return "Student{" + "name='" + name
    //     + '\'' + ", registrationNumber='" +
    //     registrationNumber + '\'' + ", age='" +
    //     age + '}'; // Student{name=' ', registraionNumber=' ', age=' '}
    // }
    
}
