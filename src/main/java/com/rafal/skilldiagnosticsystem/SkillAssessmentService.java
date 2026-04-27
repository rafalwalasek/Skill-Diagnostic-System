package com.rafal.skilldiagnosticsystem;

import java.util.ArrayList;
import java.util.List;

public class SkillAssessmentService {
    private final List<Question> questions = new ArrayList<>();

    public double checkPerformance(List<String> answersUser, List<Question> questions) {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).isCorrect(answersUser.get(i))) {
                score++;
            }
        }

        return (double) score / questions.size() * 100;
    }
    public List<String> showPerformance(List<Double> results) {
        List<String> performances = new ArrayList<>();

        double sum = 0.0;
        double max = 0.0;
        for (Double result : results) {
            sum = sum + result;
            if (result > max) {
                max = result;
            }
        }

        performances.add(String.format("%.0f", sum / results.size()));
        performances.add(String.format("%.0f", max));

        if (results.isEmpty()) {
            performances.add("Brak danych");
        } else if (results.getFirst() > results.getLast()) {
            performances.add("Malejący");
        } else if (results.getFirst() < results.getLast()) {
            performances.add("Rosnący");
        } else performances.add("Stały");

        return performances;
    }
    public List<Question> listQuestions() {
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