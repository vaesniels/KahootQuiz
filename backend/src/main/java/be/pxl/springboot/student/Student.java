package be.pxl.springboot.student;

import be.pxl.springboot.studentquiz.StudentQuiz;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @OneToMany
    @JoinColumn(name = "studentid")
    private List<StudentQuiz> studentQuizzes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<StudentQuiz> getStudentQuizzes() {
        return studentQuizzes;
    }

    public void setStudentQuizzes(List<StudentQuiz> studentQuizzes) {
        this.studentQuizzes = studentQuizzes;
    }


}
