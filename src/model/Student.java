package model;

import java.util.*;

public class Student extends Person implements Evaluation {

    // In this case, Student and Course has a one-to-many relationship (one directional) - a student can be enrolled in many courses. This is a simplified way of forming the relationship.
    private List<Course> enrolledCourses;

    public Student(String personId, String name, String email, Date birthDate) {
        super(personId, name, email, birthDate);
        // Create an empty enrolledCourses ArrayList for each student object to update his/her enrolled courses:
        enrolledCourses = new ArrayList<>();
    }

    public boolean enrollToCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            return true;
        }
        return false;
    }

    public Course getEnrolledCourse(String courseId) {
        for (Course course : enrolledCourses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    @Override
    public List<Course> getPassedCourses() {
        return enrolledCourses
        .stream()
        .filter(course -> course.getGrade() >= course.PASS_MIN_GRADE)
        .toList();
    }

    @Override
    public List<Course> getFailedCourses() {
        return enrolledCourses
        .stream()
        .filter(course -> course.getGrade() < course.PASS_MIN_GRADE)
        .toList();
    }

    @Override
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public String printEachCourse(List<Course> courses) {
        String allCourses = "";
        for (Course course : courses) {
            allCourses += course.toString() + ", \n";
        };
        if (allCourses.isBlank()) {
            allCourses += "None.\n";
        }
        return allCourses;
    }

    @Override
    public String toString() {
        return "\nStudent: " 
        + super.toString() 
        + "\nEnrolled Courses: \n" 
        + printEachCourse(getEnrolledCourses());
    }

}


