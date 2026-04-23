package com.rafal.skilldiagnosticsystem;

import java.util.ArrayList;
import java.util.List;

public class SkillAssessmentService {
    private final int total = listQuestions().size();

    private final List<Double> results = new ArrayList<>();

    public void runQuiz(String userAnswer) {
        int score = 0;
        int numberOfQuestion = 1;

        for (Question question : listQuestions()) {
            System.out.print(numberOfQuestion++ + ". ");
            question.showQuestion();

            System.out.print("Answer: ");
            if (question.isCorrect(userAnswer)) {
                score++;
            }
        }

        double percent = (double) score / total * 100;
        results.add(percent);
    }
    private List<Question> listQuestions() {
        List<Question> questions = new ArrayList<>();

        Question q1 = new Question("Co oznacza private w Javie?",
                "Pole jest dostępne wszędzie",
                "Pole jest dostępne tylko w tej samej klasie",
                "Pole jest dostępne tylko w pakiecie",
                "Pole jest dostępne tylko w podklasach",
                "b");
        Question q2 = new Question("Dlaczego używa się getterów i setterów?",
                "Żeby zwiększyć szybkość programu",
                "Żeby ukryć implementację i kontrolować dostęp do danych",
                "Żeby zmniejszyć ilość klas",
                "Żeby zastąpić konstruktory",
                "b");
        Question q3 = new Question("Co jest problemem w tym kodzie (mentalnie, bez kodu jeszcze w aplikacji):\npublic int age;",
                "Zwiększa bezpieczeństwo danych",
                "Umożliwia pełną kontrolę nad danymi",
                "Łamie zasadę enkapsulacji",
                "Przyspiesza działanie programu",
                "c");

        questions.add(q1);
        questions.add(q2);
        questions.add(q3);

        return questions;
    }
}
