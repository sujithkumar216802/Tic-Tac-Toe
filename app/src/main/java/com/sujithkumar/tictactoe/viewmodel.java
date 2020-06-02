package com.sujithkumar.tictactoe;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class viewmodel extends ViewModel {
    MutableLiveData<Integer> score1, score2, current;

    int round, currentround, direction, number, winner;
    int[][] grid = new int[3][3];
    private boolean computer = false, gameover = false, flag = false;
    private MutableLiveData<Boolean> currentroundover;
    private String name1;
    private String name2;


    public MutableLiveData<Integer> getScore1() {
        if (score1 == null)
            score1 = new MutableLiveData<>();
        return score1;
    }

    public MutableLiveData<Integer> getScore2() {
        if (score2 == null)
            score2 = new MutableLiveData<>();
        return score2;
    }

    public MutableLiveData<Integer> getCurrent() {
        if (current == null)
            current = new MutableLiveData<>();
        return current;
    }

    public void setCurrent(MutableLiveData<Integer> current) {
        this.current = current;
    }

    public int getCurrentround() {
        return currentround;
    }

    public void setCurrentround(int currentround) {
        this.currentround = currentround;
    }

    public boolean isComputer() {
        return computer;
    }

    public void setComputer(boolean computer) {
        this.computer = computer;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public String getName1() {
        if (name1 == null || name1.equals(""))
            name1 = "Anonymous";
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {

        if (name2 == null || name2.equals(""))
            name2 = "Anonymous";
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    int getgrid(int i, int j) {
        return grid[i][j];
    }

    void setgrid(int i, int j, int value) {
        grid[i][j] = value;
    }

    public void checkwinner() {
        if (!getCurrentroundover().getValue()) {
            //horizontal
            for (int i = 0; i < 3; i++) {
                if ((grid[i][0] == 1 && grid[i][1] == 1 && grid[i][2] == 1) || (grid[i][0] == 2 && grid[i][1] == 2 && grid[i][2] == 2)) {
                    if (grid[i][0] == 1)
                        winner = 1;
                    else
                        winner = 2;
                    getCurrentroundover().setValue(true);
                    direction = 1;
                    number = i;
                }

            }
            //vertical
            for (int i = 0; i < 3; i++) {
                if ((grid[0][i] == 1 && grid[1][i] == 1 && grid[2][i] == 1) || (grid[0][i] == 2 && grid[1][i] == 2 && grid[2][i] == 2)) {
                    if (grid[0][i] == 1)
                        winner = 1;
                    else
                        winner = 2;
                    getCurrentroundover().setValue(true);
                    direction = 2;
                    number = i;
                }
            }
            //diagonal
            if ((grid[0][0] == 1 && grid[1][1] == 1 && grid[2][2] == 1) || (grid[0][0] == 2 && grid[1][1] == 2 && grid[2][2] == 2)) {
                if (grid[1][1] == 1)
                    winner = 1;
                else
                    winner = 2;
                getCurrentroundover().setValue(true);
                direction = 3;
                number = 0;

            }


            if ((grid[0][2] == 1 && grid[1][1] == 1 && grid[2][0] == 1) || (grid[0][2] == 2 && grid[1][1] == 2 && grid[2][0] == 2)) {

                if (grid[1][1] == 1)
                    winner = 1;
                else
                    winner = 2;

                getCurrentroundover().setValue(true);
                direction = 3;
                number = 1;
            }


            //none
            flag = false;
            if (!getCurrentroundover().getValue()) {
                for (int i = 0; i < 3; i++) {
                    if (flag)
                        break;
                    for (int j = 0; j < 3; j++) {
                        if (grid[i][j] == 0) {
                            flag = true;
                            break;
                        }

                        if (i == 2 && j == 2) {
                            winner = 0;
                            getCurrentroundover().setValue(true);
                        }

                    }
                }
            }

            if (getCurrentroundover().getValue()) {
                if (winner == 1)
                    getScore1().setValue(getScore1().getValue() + 1);
                else if (winner == 2)
                    getScore2().setValue(getScore2().getValue() + 1);
            }

        }

    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getNumber() {
        return number;
    }


    public void setNumber(int number) {
        this.number = number;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }


    public MutableLiveData<Boolean> getCurrentroundover() {
        if (currentroundover == null)
            currentroundover = new MutableLiveData<Boolean>();
        return currentroundover;
    }

    void cleargrid() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                grid[i][j] = 0;
        if (currentround % 2 == 0)
            getCurrent().setValue(2);
        else
            getCurrent().setValue(1);

    }

    public int[][] getGrid() {
        return grid;
    }
}
