import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Enrollment> enrollments = new ArrayList<>();
    //
    private final String DATA_FOLDER = "data";
    private final String STUDENT_FILE = DATA_FOLDER + "/student.csv";
    private final String TEACHER_FILE = DATA_FOLDER + "/teachers.csv";
    private final String COURSE_FILE = DATA_FOLDER + "/courses.csv";
    private final String ENROLLMENT_FILE = DATA_FOLDER + "/enrollments.csv";

    public StudentManagementSystem(){
        createDataFolder();
        createFilesIfMissing();
    }

    private void createDataFolder(){
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()){
            folder.mkdir();
            System.out.println("Created folder: "  + DATA_FOLDER);
        }
    }

    private void createFilesIfMissing() {
        createFile(STUDENT_FILE);
        createFile(TEACHER_FILE);
        createFile(COURSE_FILE);
        createFile(ENROLLMENT_FILE);
    }

    private void createFile(String filePath){
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.createNewFile();

                System.out.println("Created file: " + filePath);
            }
        } catch(IOException e) {
            System.out.println("Error creating file: " + filePath);
        }
    }

    // ==================== SAVE METHODS =================
    
    private void saveStudents() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILE));
            for (Student s : students) {
                writer.println(s.getName() + "," + s.getReg() + "," + s.getAge());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving students.");
        }
    }

    private void saveTeachers() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(TEACHER_FILE));
            for (Teacher t : teachers) {
                writer.println(t.getName() + "," + t.getTeacherId() + "," + t.getSubject());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving teachers.");
        }
    }

        private void saveCourses() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(COURSE_FILE));
            for (Course c : courses) {
                writer.println(c.getCourseName() + "," + c.getCourseCode() + "," + c.getUnit());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving courses.");
        }
    }

        private void saveEnrollments() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(ENROLLMENT_FILE));
            for (Enrollment e : enrollments) {
                writer.println(e.getStudent().getReg() + "," + e.getCourse().getCourseCode());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving enrollments.");
        }
    }


////////////////// MAIN PROGRAM//////////////////////////////////////
    public void start(){
        // load data first // load methods are below

            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to load saved data? (yes/no): ");
            String chooseLoad = scanner.nextLine().trim().toLowerCase();

            if(chooseLoad.equals("yes")) {
                loadAllData();
                System.out.println("Data loaded successfully!");
            } else{
                System.out.println("Starting with empty data...");
            }
            boolean running = true;

            while(running){
                System.out.println("\n=== Student Management System ===");
                System.out.println("1. Add Stdent");
                System.out.println("2. Teacher");
                System.out.println("3. Add Course");
                System.out.println("4. Enroll Student to Course");
                System.out.println("5. View Students");
                System.out.println("6. View Teachers");
                System.out.println("7. View Courses");
                System.out.println("8. View Enrollment");
                System.out.println("0. Exit");
                System.out.println("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        addStudent(scanner);
                    break;
                    case 2:
                        addTeacher(scanner);
                    break;
                    case 3: 
                        addCourse(scanner);
                    break;
                    case 4:
                        enrollStudent(scanner);
                    break;
                    case 5:
                        listStudents();
                    break;
                    case 6:
                        listTeachers();
                    break;
                    case 7:
                        listCourses();
                    break;
                    case 8:
                        listEnrollments();
                    break;
                    case 0: 
                        running = false;
                        System.out.println("Existing system...");
                    break;
                    default:
                        System.out.println("Invalid Choice. Try again.");
                }
            }
        

    }

    // Adding data into the student list
    private void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter registration number: ");
        String reg = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Student student = new Student (name, reg, age);
        students.add(student);
        saveStudents(); // To save it to the files upward

        System.out.print("Student added succesfully!");
    }

    // Adding data into the teacher list
    private void addTeacher(Scanner scanner) {
        System.out.print("Enter teacher name: ");
        String name = scanner.nextLine();

        System.out.print("Enter teacher ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter subject taught: ");
        String subject = scanner.nextLine();

        Teacher teacher = new Teacher(name, id, subject);
        teachers.add(teacher);
        saveTeachers();// To save it to the files upward

        System.out.print("Teacher added succesfully!");
    }

    // Adding data into the course list
    private void addCourse(Scanner scanner) {
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        System.out.print("Enter course code: ");
        String code = scanner.nextLine();

        System.out.print("Enter course unit: ");
        int unit = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Course course = new Course(name, code, unit);
        courses.add(course);
        saveCourses();// To save it to the files upward

        System.out.print("Course added succesfully!");
    }

    // Adding data into the enrollment list
    private void enrollStudent(Scanner scanner) {
        if (students.isEmpty() || courses.isEmpty()){
            System.out.println
            ("You must have at least one student and one course to enroll");
            return;
        }
        System.out.println("Select a student to enroll:");
        for (int i = 0; i < students.size(); i++){
            System.out.println((i + 1) + ". " + students.get(i).getName());
        }
        int studentChoice = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        System.out.println("Select a course");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getCourseName());
        }
        int courseChoice = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        Enrollment enrollment = new Enrollment(students.get(studentChoice), courses.get(courseChoice));
        enrollments.add(enrollment);
        saveEnrollments(); // To save it to the files upward
        System.out.println("Enrollment successful!");
    }

    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n--- Students ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

   public void listTeachers() {
        if (teachers.isEmpty()) {
            System.out.println("No teachers available.");
            return;
        }

        System.out.println("\n--- Teachers ---");
        for (Teacher t : teachers) {
            System.out.println(t);
        }
    }

    public void listCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("\n--- Courses ---");
        for (Course c  : courses) {
            System.out.println(c);
        }
    }

    public void listEnrollments() {
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments available.");
            return;
        }

        System.out.println("\n--- Enrollments ---");
        for (Enrollment e : enrollments) {
            System.out.println(e);
        }
    }

// ========= Loading File Methods =============
protected void loadStudents() {
    try {
        File file = new File(STUDENT_FILE);
        if (!file.exists()) return;   // no data yet

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            String reg = parts[1];
            int age = Integer.parseInt(parts[2]);

            students.add(new Student(name, reg, age));
        }
        reader.close();

    } catch (IOException e) {
        System.out.println("Error loading students.");
    }
}

protected void loadTeachers() {
    try {
        File file = new File(TEACHER_FILE);
        if (!file.exists()) return;   // no data yet

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            String id = parts[1];
            String subject = parts[2];

            teachers.add(new Teacher(name, id, subject));
        }
        reader.close();
    } catch (IOException e) {
        System.out.println("Error loading teachers.");
    }
}

protected void loadCourses() {
    try {
        File file = new File(COURSE_FILE);
        if (!file.exists()) return;   // no data yet

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            String code = parts[1];
            int unit = Integer.parseInt(parts[2]);

            courses.add(new Course(name, code, unit));
        }
        reader.close();
    } catch (IOException e) {
        System.out.println("Error loading courses.");
    }
}

protected void loadEnrollments() {
    try {
        File file = new File(ENROLLMENT_FILE);
        if (!file.exists()) return;   // no data yet

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String reg = parts[0];
            String code = parts[1];

            Student student = findStudentByReg(reg);
            Course course = findCourseByCode(code);

            if(student != null && course != null){
                enrollments.add(new Enrollment(student, course));
            }
        }
        reader.close();
    } catch (IOException e) {
        System.out.println("Error loading enrollments.");
    }
}

private void loadAllData(){
        loadStudents();
        loadTeachers();
        loadCourses();
        loadEnrollments();

}


// TWO METHODS THAT SUPPORTS loadEnrollment METHOD
private Student findStudentByReg(String reg)  {
    for (Student s : students) {
        if (s.getReg().equals(reg)) {
            return s;
        }
    }
    return null;
}
 
private Course findCourseByCode(String code)  {
    for (Course c : courses) {
        if (c.getCourseCode().equals(code)) {
            return c;
        }
    }
    return null;
}

/// --- TESTING HELPERS (used only by TestSMS) ---
public void testAddStudent(Student student){
    students.add(student);
}

public void testAddTeacher(Teacher teacher){
    teachers.add(teacher);
}

public void testAddCourse(Course course){
    courses.add(course);
}

public void testAddEnrollment(Enrollment enrollment){
    enrollments.add(enrollment);
}
}
