public class Teacher{
    private String teacherId;
    private String subject;
    private String name;
    
    public Teacher(String name, String teacherId, String subject) {
        this.name = name;
        this.teacherId = teacherId;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }
    public String getTeacherId() {
        return teacherId;
    }
    public String getSubject() {
        return subject;
    }

    @Override
    public String toString(){
        return "Teacher: " + name + " (ID: " + teacherId + "), Subject: " + subject;
    } // Teacher:  (ID: ), Subject: 

}
