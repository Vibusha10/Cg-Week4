import java.util.*;

abstract class CourseType {
    private String courseName;
    private int credits;

    public CourseType(String courseName, int credits) {
        this.courseName = courseName;
        this.credits = credits;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public abstract void evaluationMethod();

    public void displayCourseInfo() {
        System.out.println("Course: " + courseName + ", Credits: " + credits);
        evaluationMethod();
    }
}

class ExamCourse extends CourseType {
    public ExamCourse(String courseName, int credits) {
        super(courseName, credits);
    }

    @Override
    public void evaluationMethod() {
        System.out.println("Evaluation: Final Exam");
    }
}

class AssignmentCourse extends CourseType {
    public AssignmentCourse(String courseName, int credits) {
        super(courseName, credits);
    }

    @Override
    public void evaluationMethod() {
        System.out.println("Evaluation: Continuous Assignments");
    }
}

class ResearchCourse extends CourseType {
    public ResearchCourse(String courseName, int credits) {
        super(courseName, credits);
    }

    @Override
    public void evaluationMethod() {
        System.out.println("Evaluation: Research Project");
    }
}

class Course<T extends CourseType> {
    private String department;
    private T courseType;

    public Course(String department, T courseType) {
        this.department = department;
        this.courseType = courseType;
    }

    public void showDetails() {
        System.out.println("Department: " + department);
        courseType.displayCourseInfo();
        System.out.println();
    }

    public T getCourseType() {
        return courseType;
    }
}

class CourseManager {
    public static void displayAllCourses(List<? extends Course<? extends CourseType>> courses) {
        for (Course<? extends CourseType> course : courses) {
            course.showDetails();
        }
    }
}

public class Program3 {
    public static void main(String[] args) {
        Course<ExamCourse> math101 = new Course<>("Mathematics", new ExamCourse("Math 101", 3));
        Course<AssignmentCourse> cs201 = new Course<>("Computer Science", new AssignmentCourse("Programming 201", 4));
        Course<ResearchCourse> bio301 = new Course<>("Biology", new ResearchCourse("Genetics 301", 5));

        List<Course<? extends CourseType>> catalog = new ArrayList<>();
        catalog.add(math101);
        catalog.add(cs201);
        catalog.add(bio301);

        CourseManager.displayAllCourses(catalog);
    }
}
