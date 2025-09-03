package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;

	@Test
	void questionInsertTest() {
        Question question = new Question();
        question.setSubject("sbb가 무엇인가요?");
        question.setContent("sbb에 대해 알고 싶어요");
        question.setCreateDate(java.time.LocalDateTime.now());
        this.questionRepository.save(question);

        Question question2 = new Question();
        question2.setSubject("스프링부트 모델 질문입니다.");
        question2.setContent("id는 자동 생성 되나요??");
        question2.setCreateDate(java.time.LocalDateTime.now());
        this.questionRepository.save(question2);
	}

    @Test
    void questionFindAllTest(){
        List<Question> all = this.questionRepository.findAll();
        assertEquals(3,all.size());

        Question q = all.get(0);
        assertEquals("스프링부트 모델 질문입니다.",q.getSubject());
    }

    @Test
    void questionFindByIdtest(){
        Optional<Question> oq = this.questionRepository.findById(2);
        if(oq.isPresent()){
            Question q = oq.get();
            assertEquals("스프링부트 모델 질문입니다.",q.getSubject());
        }
    }

    @Test
    void questionFindBySubjectTest(){
        Question q = this.questionRepository.findBySubject("sbb에 대해 알고 싶어요");
        assertEquals(3, q.getId());
    }
}
