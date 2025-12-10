public class Enrollment {
    private Student student;
    private Course course;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent(){
        return student;
    }
    public Course getCourse(){
        return course;
    } 
    

    public String toString() {
        return "Enrollment: " + student.getName() + " 'n " + 
        course.getCourseName(); // Enrollment: 'n(Enter next line)
    }
    

    

    
}
