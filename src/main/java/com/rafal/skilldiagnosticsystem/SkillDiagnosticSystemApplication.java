package com.rafal.skilldiagnosticsystem;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class SkillDiagnosticSystemApplication {
	public static void main(String[] args) {
		//SpringApplication.run(SkillDiagnosticSystemApplication.class, args);

		Scanner sc = new Scanner(System.in);
		SkillAssessmentService skillAssessmentService = new SkillAssessmentService();
		FileManager fileManager = new FileManager("wyniki.txt");

		List<Double> results = new ArrayList<>();

		boolean isRunning = true;
		while (isRunning) {
			Menu.showMenu();
			System.out.print(">> ");

			int userChoice = sc.nextInt();
			sc.nextLine();
			switch (userChoice) {
				case 0 -> {
					System.out.println("Koniec!");
					isRunning = false;
				}
				case 1 -> {
					List<Question> questions = skillAssessmentService.listQuestions();
					List<String> answersUser = new ArrayList<>();

					int numberOfQuestion = 1;
					for (Question question : questions) {
						System.out.print(numberOfQuestion++ + ". ");
						question.showQuestion();

						System.out.print("Answer: ");
						answersUser.add(sc.nextLine().trim().toLowerCase());
					}

					results.add(skillAssessmentService.checkPerformance(answersUser, questions));
				}
				case 2 -> {
					System.out.println("Wyniki:");
					for (String performance : skillAssessmentService.showPerformance(results)) {
						System.out.println(performance);
					}
				}
				case 3 -> ;
				default -> System.out.println("Brak opcji, wybierz jeszcze raz :)");
			}
		}

		sc.close();
	}
}
