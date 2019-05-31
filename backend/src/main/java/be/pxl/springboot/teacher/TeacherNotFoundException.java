package be.pxl.springboot.teacher;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String message) {
        super(message);
    }
}
