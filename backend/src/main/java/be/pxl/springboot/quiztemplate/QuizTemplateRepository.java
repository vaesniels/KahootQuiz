package be.pxl.springboot.quiztemplate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizTemplateRepository extends JpaRepository<QuizTemplate, Long> {

}
