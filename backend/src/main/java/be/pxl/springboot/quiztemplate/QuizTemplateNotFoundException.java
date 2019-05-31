package be.pxl.springboot.quiztemplate;

public class QuizTemplateNotFoundException extends RuntimeException {
    public QuizTemplateNotFoundException(String message) {
        super(message);
    }
}
