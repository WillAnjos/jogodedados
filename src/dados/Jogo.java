package dados;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Jogo {

    List<Jogadores> listOfPlayers = new ArrayList<>();
    private Dado dado = new Dado();
    private int scoreToWin = 200;

    private void run() {

        while (true) {
        	
        	System.out.println();
        	System.out.println("Escolha uma opção: \n\n" + 
        	" (1) Começar novo jogo\n" +
        	" (2) Jogar primeira rodada\n" +
        	" (3) Quem esta ganhando?\n" +
        	" (4) Ajuda\n" +
        	" (5) Sair\n"
        	); 

            try {

                Scanner sc = new Scanner(System.in);
                int optionSelected = sc.nextInt();

                while (optionSelected < 0 || 5 < optionSelected) {
                    System.out.println("Opção invalida escolha de 1 à 5");
                    optionSelected = sc.nextInt();
                }

                if (optionSelected == 5) {
                    System.out.println("Exiting...");
                    break;
                }

                this.selectOption(optionSelected);

            } catch (InputMismatchException e) {
                System.out.println("Opção invalida escolha de 1 à 5");
            }

            if (this.checkIfAnyoneHasWon()) {
                System.out.println("Fim do Jogo!");
                break;
            }
        }
    }

    private void selectOption(int optionSelected) {
        switch (optionSelected) {
            case 1:
                this.startNewGame();
                break;
            case 2:
                this.playOneRound();
                break;
            case 3:
                this.whoIsLeading();
                break;
            case 4:
                this.displayGameInstruction();
                break;
            default:
                break;
        }
    }

    private void startNewGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Gostaria de criar um jogador? Y/N.");

        String answer = sc.nextLine();

        while (answer.equalsIgnoreCase("y")) {
            System.out.println("Qual o nome do jogador?");
            String playerName = sc.nextLine();
            Jogadores newPlayer = new Jogadores(playerName);
            listOfPlayers.add(newPlayer);

            System.out.println("Você gostaria de criar outro jogador? Y/N.");
            answer = sc.nextLine();
        }

        System.out.println("Com quantos pontos acaba o jogo?");
        scoreToWin = sc.nextInt();
    }

    private void playOneRound() {

        for (Jogadores p : listOfPlayers) {
            int currentRoundScore;
            int firstResult = dado.roll();
            int secondResult = dado.roll();

            if (firstResult == secondResult) {
                currentRoundScore = (firstResult + secondResult) * 2;
                p.addToTotalScore(currentRoundScore);
                System.out.format("%s rolled %d and %d, "
                        + "and scored %d points(BONUS DOUBLE POINTS), "
                        + "for a total of %d points.%n",
                        p.getName(), firstResult, secondResult,
                        currentRoundScore, p.getTotalScore());
            } else {
                currentRoundScore = (firstResult + secondResult);
                p.addToTotalScore(currentRoundScore);
                System.out.format("%s rolled %d and %d, "
                        + "and scored %d points, "
                        + "for a total of %d points.%n",
                        p.getName(), firstResult, secondResult,
                        currentRoundScore, p.getTotalScore());
            }
        }
    }

    private void whoIsLeading() {

        List<Jogadores> leaders = new ArrayList<>();

        int highestScore = 0;

        for (Jogadores p : listOfPlayers) {
            if (p.getTotalScore() > highestScore) {
                leaders = new ArrayList<>();
                leaders.add(p);
                highestScore = p.getTotalScore();
            } else if (p.getTotalScore() == highestScore) {
                leaders.add(p);
            }

        }

        for (Jogadores p : leaders) {
            System.out.format("%s is currently leading with "
                    + "%d points.%n", p.getName(), p.getTotalScore());
        }
    }

    private void displayGameInstruction() {
        System.out.println("All players roll a dice twice per turn.");
        System.out.println("If 2 dice rolls have the same value, the player scores 2 times the sum two dice rolls.");
        System.out.println("If 2 dice rolls have different values, the player simply scores the sum of two dice rolls.");
        System.out.println("For each player, result is incremented after each turn.");
        System.out.println("First player to reach or exceed the maxScore wins the game");
    }

    private boolean checkIfAnyoneHasWon() {

        List<Jogadores> winners = new ArrayList<>();

        listOfPlayers.stream().filter((p) -> (p.getTotalScore() >= scoreToWin)).forEach((p) -> {
            winners.add(p);
        });

        winners.stream().forEach((p) -> {
            System.out.printf("%s has reached the game limit of %d for a total "
                    + "of %d points, %s has won the game!%n",
                    p.getName(), scoreToWin,
                    p.getTotalScore(), p.getName());
        });

        return !winners.isEmpty();

    }

    public static void main(String[] args) {
        Jogo newGame = new Jogo();
        System.out.println("Bem vindo ao jogo de Dados do Will!");
        newGame.run();

    }
}


