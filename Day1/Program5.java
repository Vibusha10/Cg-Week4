import java.util.*;

abstract class JobRole {
    private String candidateName;
    private int experienceYears;

    public JobRole(String candidateName, int experienceYears) {
        this.candidateName = candidateName;
        this.experienceYears = experienceYears;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public abstract void evaluateResume();

    public void displayInfo() {
        System.out.println("Candidate: " + candidateName + ", Experience: " + experienceYears + " years");
        evaluateResume();
    }
}

class SoftwareEngineer extends JobRole {
    public SoftwareEngineer(String candidateName, int experienceYears) {
        super(candidateName, experienceYears);
    }

    @Override
    public void evaluateResume() {
        System.out.println("Evaluation: Checking programming skills and project experience");
    }
}

class DataScientist extends JobRole {
    public DataScientist(String candidateName, int experienceYears) {
        super(candidateName, experienceYears);
    }

    @Override
    public void evaluateResume() {
        System.out.println("Evaluation: Checking statistics, ML skills, and portfolio");
    }
}

class ProductManager extends JobRole {
    public ProductManager(String candidateName, int experienceYears) {
        super(candidateName, experienceYears);
    }

    @Override
    public void evaluateResume() {
        System.out.println("Evaluation: Checking leadership, roadmap planning, and communication skills");
    }
}

class Resume<T extends JobRole> {
    private T jobRole;

    public Resume(T jobRole) {
        this.jobRole = jobRole;
    }

    public T getJobRole() {
        return jobRole;
    }

    public void screen() {
        jobRole.displayInfo();
        System.out.println();
    }
}

class ScreeningSystem {
    public static <T extends JobRole> void processResume(Resume<T> resume) {
        System.out.println("Processing Resume:");
        resume.screen();
    }

    public static void processAllResumes(List<? extends Resume<? extends JobRole>> resumes) {
        for (Resume<? extends JobRole> resume : resumes) {
            processResume(resume);
        }
    }
}

public class Program5{
    public static void main(String[] args) {
        Resume<SoftwareEngineer> softwareResume = new Resume<>(new SoftwareEngineer("Alice", 5));
        Resume<DataScientist> dataResume = new Resume<>(new DataScientist("Bob", 3));
        Resume<ProductManager> productResume = new Resume<>(new ProductManager("Charlie", 7));

        List<Resume<? extends JobRole>> resumeList = Arrays.asList(softwareResume, dataResume, productResume);

        ScreeningSystem.processAllResumes(resumeList);
    }
}