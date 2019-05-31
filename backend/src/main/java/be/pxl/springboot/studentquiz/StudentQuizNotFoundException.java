package be.pxl.springboot.studentquiz;

public class StudentQuizNotFoundException extends RuntimeException {
    public StudentQuizNotFoundException(String message) {
        super(message);
    }
}
