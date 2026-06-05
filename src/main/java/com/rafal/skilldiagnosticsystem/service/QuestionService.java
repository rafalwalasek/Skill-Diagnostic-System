package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.dto.QuestionRequestDto;
import com.rafal.skilldiagnosticsystem.dto.QuestionResponseDto;
import com.rafal.skilldiagnosticsystem.model.Category;
import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.model.Question;
import com.rafal.skilldiagnosticsystem.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuestionResponseDto addQuestionToDB(QuestionRequestDto questionRequestDto) {
        Question question = new Question();
        question.setContent(questionRequestDto.getContent());
        question.setAnswerA(questionRequestDto.getAnswerA());
        question.setAnswerB(questionRequestDto.getAnswerB());
        question.setAnswerC(questionRequestDto.getAnswerC());
        question.setAnswerD(questionRequestDto.getAnswerD());
        question.setCorrectAnswer(questionRequestDto.getCorrectAnswer());
        question.setDifficultyLevel(questionRequestDto.getDifficulty());
        question.setCategory(questionRequestDto.getCategory());

        Question saved = questionRepository.save(question);

        return mapToDto(saved);
    }
    private QuestionResponseDto mapToDto(Question q) {
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setContent(q.getContent());
        questionResponseDto.setAnswerA(q.getAnswerA());
        questionResponseDto.setAnswerB(q.getAnswerB());
        questionResponseDto.setAnswerC(q.getAnswerC());
        questionResponseDto.setAnswerD(q.getAnswerD());
        questionResponseDto.setCorrectAnswer(q.getCorrectAnswer());
        questionResponseDto.setDifficulty(q.getDifficultyLevel());
        questionResponseDto.setCategory(q.getCategory());

        return questionResponseDto;
    }

    public Long questionCount(Category st, DifficultyLevel diff) {
        return questionRepository.countByCategoryAndDifficultyLevel(st, diff);
    }
    public List<Question> getAllQuestions(Category st, DifficultyLevel diff) {
        return questionRepository.findByCategoryAndDifficultyLevel(st, diff);
    }
}
