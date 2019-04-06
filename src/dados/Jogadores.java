package dados;

public class Jogadores {

    private String name;
    private int totalScore;

    Jogadores(String name){
        this.name = name;
    }

    String getName(){
        return name;
    }

    void setName(){
        this.name = name;
    }  

    void addToTotalScore(int scoreForCurrentRound){
        totalScore += scoreForCurrentRound;
    }

    int getTotalScore(){
        return totalScore;
    }

}
