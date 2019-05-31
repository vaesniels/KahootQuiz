package be.pxl.springboot.quiz;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException(String message) {
        super(message);
    }
}
