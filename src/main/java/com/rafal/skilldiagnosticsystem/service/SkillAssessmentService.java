package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.model.Category;
import com.rafal.skilldiagnosticsystem.model.Question;
import com.rafal.skilldiagnosticsystem.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkillAssessmentService {
    private final QuestionRepository questionRepository;

    public SkillAssessmentService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public double checkPerformance(List<String> answersUser, List<Question> questions) {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).isCorrect(answersUser.get(i))) {
                score++;
            }
        }

        return (double) score / questions.size() * 100;
    }
    public Map<Category, Integer> showCategoryResult(List<String> answersUser, List<Question> questions) {
        Map<Category, Integer> categoryResults = new HashMap<>();

        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).isCorrect(answersUser.get(i))) {
                Category category = questions.get(i).getCategory();

                if (!categoryResults.containsKey(category)) {
                    categoryResults.put(category, 0);
                }

                int count = categoryResults.get(category);
                count++;
                categoryResults.put(category, count);
            }
        }

        return categoryResults;
    }
//    public List<Question> listQuestions() {
//        List<Question> questions = new ArrayList<>();
//
//        Question q1 = new Question("Co oznacza private w Javie?",
//                "Pole jest dostępne wszędzie",
//                "Pole jest dostępne tylko w tej samej klasie",
//                "Pole jest dostępne tylko w pakiecie",
//                "Pole jest dostępne tylko w podklasach",
//                "b",
//                Category.ENKAPSULACJA);
//        Question q2 = new Question("Dlaczego używa się getterów i setterów?",
//                "Żeby zwiększyć szybkość programu",
//                "Żeby ukryć implementację i kontrolować dostęp do danych",
//                "Żeby zmniejszyć ilość klas",
//                "Żeby zastąpić konstruktory",
//                "b",
//                Category.ENKAPSULACJA);
//        Question q3 = new Question("Co jest problemem w tym kodzie (mentalnie, bez kodu jeszcze w aplikacji):\npublic int age;",
//                "Zwiększa bezpieczeństwo danych",
//                "Umożliwia pełną kontrolę nad danymi",
//                "Łamie zasadę enkapsulacji",
//                "Przyspiesza działanie programu",
//                "c",
//                Category.ENKAPSULACJA);
//
//        questions.add(q1);
//        questions.add(q2);
//        questions.add(q3);
//
//        return questions;
//    }
}

//    public List<String> showPerformance(List<Double> results) {
//        List<String> performances = new ArrayList<>();
//
//        double sum = 0.0;
//        double max = 0.0;
//        for (Double result : results) {
//            sum = sum + result;
//            if (result > max) {
//                max = result;
//            }
//        }
//
//        performances.add(String.format("%.0f", sum / results.size()));
//        performances.add(String.format("%.0f", max));
//
//        if (results.isEmpty()) {
//            performances.add("Brak danych");
//        } else if (results.getFirst() > results.getLast()) {
//            performances.add("Malejący");
//        } else if (results.getFirst() < results.getLast()) {
//            performances.add("Rosnący");
//        } else performances.add("Stały");
//
//        return performances;
//    }
