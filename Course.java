public class Course {
    private String courseName;
    private String courseCode;
    private int unit;

    public Course(String courseName, String courseCode, int unit){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.unit = unit;
    }

    public String getCourseName() {
        return courseName;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public int getUnit() {
        return unit;
    }

    public String toString() {
        return "Course: " + courseName + " (" + courseCode + "), Unit: "
        + unit; // Course: " (" "), Unit: "
    }

}
