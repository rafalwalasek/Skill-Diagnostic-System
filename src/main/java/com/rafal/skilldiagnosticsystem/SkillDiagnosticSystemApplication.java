package com.rafal.skilldiagnosticsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class SkillDiagnosticSystemApplication {
	public static void main(String[] args) {
		//SpringApplication.run(SkillDiagnosticSystemApplication.class, args);

		Scanner sc = new Scanner(System.in);

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

		List<Question> questions = new ArrayList<>();
		questions.add(q1);
		questions.add(q2);
		questions.add(q3);

		int total = questions.size();

		List<Double> results = new ArrayList<>();

		boolean isRunning = true;
		while (isRunning) {
			Menu.showMenu();

			int userChoice = sc.nextInt();
			sc.nextLine();

			switch (userChoice) {
				case 0 -> {
					System.out.println("Koniec!");
					isRunning = false;
				}
				case 1 -> {
					String odp;
					int score = 0;

					int numberOfQuestion = 1;
					for (Question question : questions) {
						System.out.print(numberOfQuestion++ + ". ");
						question.showQuestion();

						System.out.print("Odp: ");
						odp = sc.nextLine().trim().toLowerCase();
						if (question.isCorrect(odp)) {
							score++;
						}
					}

					double percent = (double) score / total * 100;
					results.add(percent);
				}
				case 2 -> {
					int count = 1;
					for (Double result : results) {
						System.out.println(count++ + ": " + String.format("%.0f", result) + "%");
					}
				}
				default -> System.out.println("Brak opcji, wybierz jeszcze raz :)");
			}
		}

		sc.close();
	}
}