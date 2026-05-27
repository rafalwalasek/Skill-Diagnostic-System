package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.dto.QuestionRequestDto;
import com.rafal.skilldiagnosticsystem.dto.QuestionResponseDto;
import com.rafal.skilldiagnosticsystem.model.Category;
import com.rafal.skilldiagnosticsystem.model.Question;
import com.rafal.skilldiagnosticsystem.repository.CategoryRepository;
import com.rafal.skilldiagnosticsystem.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;

    public QuestionService(QuestionRepository questionRepository,
                           CategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
    }

    public QuestionResponseDto addQuestionToDB(QuestionRequestDto questionRequestDto) {
        Question question = new Question();
        question.setContent(questionRequestDto.getContent());
        question.setAnswerA(questionRequestDto.getAnswerA());
        question.setAnswerB(questionRequestDto.getAnswerB());
        question.setAnswerC(questionRequestDto.getAnswerC());
        question.setAnswerD(questionRequestDto.getAnswerD());
        question.setDifficultyLevel(questionRequestDto.getDifficulty());

        Category category = categoryRepository.findById(
                questionRequestDto.getCategoryId()
        ).orElseThrow(() -> new RuntimeException("Category not found"));
        question.setCategory(category);

        Question saved = questionRepository.save(question);

        return que.save(saved);
    }
}
