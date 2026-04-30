package com.rafal.skilldiagnosticsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkillDiagnosticSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(SkillDiagnosticSystemApplication.class, args);
	}
}

//		Scanner sc = new Scanner(System.in);
//		SkillAssessmentService skillAssessmentService = new SkillAssessmentService();
//		FileManager fileManager = new FileManager("wyniki.txt");
//
//		List<Question> questions = skillAssessmentService.listQuestions();
//		List<Double> results = fileManager.readFromFile();
//
//		boolean isRunning = true;
//		while (isRunning) {
//			Menu.showMenu();
//			System.out.print(">> ");
//
//			int userChoice = sc.nextInt();
//			sc.nextLine();
//			switch (userChoice) {
//				case 0 -> {
//					System.out.println("Koniec!");
//					isRunning = false;
//				}
//				case 1 -> {
//					List<String> answersUser = new ArrayList<>();
//
//					int numberOfQuestion = 1;
//					for (Question question : questions) {
//						System.out.print(numberOfQuestion++ + ". ");
//						question.showQuestion();
//
//						System.out.print("Answer: ");
//						answersUser.add(sc.nextLine().trim().toLowerCase());
//					}
//
//					results.add(skillAssessmentService.checkPerformance(answersUser, questions));
//				}
//				case 2 -> {
//					if (results.isEmpty()) {
//						System.out.println("Brak danych.");
//					} else {
//						List<String> performance;
//						performance = skillAssessmentService.showPerformance(results);
//
//						System.out.println("Wyniki:");
//						System.out.println("Średnia: " + performance.get(0) + "%");
//						System.out.println("Najwyższa: " + performance.get(1) + "%");
//						System.out.println("Trend: " + performance.get(2));
//					}
//				}
//				case 3 -> fileManager.writeToFile(results);
//				default -> System.out.println("Brak opcji, wybierz jeszcze raz :)");
//			}
//		}
//
//		sc.close();
