package com.rafal.skilldiagnosticsystem.service;

//import com.rafal.skilldiagnosticsystem.dto.QuestionRequestDto;
import com.rafal.skilldiagnosticsystem.dto.QuestionResponseDto;
import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.model.Question;
import com.rafal.skilldiagnosticsystem.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Long questionCount(Long subtopicId, DifficultyLevel diff) {
        return questionRepository.countBySubtopicIdAndDifficultyLevel(subtopicId, diff);
    }
    public List<QuestionResponseDto> getRandomQuestions(Long subtopicId, DifficultyLevel difficulty) {
        List<Question> questions = questionRepository.findBySubtopicIdAndDifficultyLevel(subtopicId, difficulty);
        Collections.shuffle(questions);

        return mapQuestionsToDto(questions.subList(0, min(3, questions.size())));
    }
    private List<QuestionResponseDto> mapQuestionsToDto(List<Question> questions) {
        List<QuestionResponseDto> questionResponseDtoList = new ArrayList<>();

        for (Question question : questions) {
            QuestionResponseDto questionResponseDto = new QuestionResponseDto();
            questionResponseDto.setContent(question.getContent());
            questionResponseDto.setAnswerA(question.getAnswerA());
            questionResponseDto.setAnswerB(question.getAnswerB());
            questionResponseDto.setAnswerC(question.getAnswerC());
            questionResponseDto.setAnswerD(question.getAnswerD());

            questionResponseDtoList.add(questionResponseDto);
        }

        return questionResponseDtoList;
    }

//    public QuestionResponseDto addQuestionToDB(QuestionRequestDto questionRequestDto) {
//        Question question = new Question();
//        question.setContent(questionRequestDto.getContent());
//        question.setAnswerA(questionRequestDto.getAnswerA());
//        question.setAnswerB(questionRequestDto.getAnswerB());
//        question.setAnswerC(questionRequestDto.getAnswerC());
//        question.setAnswerD(questionRequestDto.getAnswerD());
//        question.setCorrectAnswer(questionRequestDto.getCorrectAnswer());
//        question.setDifficultyLevel(questionRequestDto.getDifficulty());
//        question.setCategory(questionRequestDto.getCategory());
//
//        Question saved = questionRepository.save(question);
//
//        return mapToDto(saved);
//    }
//    private QuestionResponseDto mapToDto(Question q) {
//        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
//        questionResponseDto.setContent(q.getContent());
//        questionResponseDto.setAnswerA(q.getAnswerA());
//        questionResponseDto.setAnswerB(q.getAnswerB());
//        questionResponseDto.setAnswerC(q.getAnswerC());
//        questionResponseDto.setAnswerD(q.getAnswerD());
//        questionResponseDto.setCorrectAnswer(q.getCorrectAnswer());
//        questionResponseDto.setDifficulty(q.getDifficultyLevel());
//        questionResponseDto.setCategory(q.getCategory());
//
//        return questionResponseDto;
//    }
}
