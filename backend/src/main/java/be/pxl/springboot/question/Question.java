package be.pxl.springboot.question;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "question")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "actualquestion")
    private String actualQuestion;

    @Column(name = "rightanswer")
    private String rightAnswer;

    @Column(name = "possibleanswers")
    private String possibleAnswers;

    @Column(name = "quiztemplateid")
    private long quizTemplateId;

    @Column(name = "time")
    private int time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActualQuestion() {
        return actualQuestion;
    }

    public void setActualQuestion(String actualQuestion) {
        this.actualQuestion = actualQuestion;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(String possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public long getQuizTemplateId() {
        return quizTemplateId;
    }

    public void setQuizTemplateId(long quizTemplateId) {
        this.quizTemplateId = quizTemplateId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
