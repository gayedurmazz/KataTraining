package com.uniyaz.kata.ui.views;

import com.uniyaz.kata.ui.components.MyButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Random;

public class Board extends VerticalLayout {

    private HorizontalLayout horizontalLayout;
    private int rowCount;
    private int columnCount;
    private int clickedRowIndex;
    private int clickedColumnIndex;
    private MyButton[][] myButtons;

    public Board(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;

        myButtons = new MyButton[rowCount][columnCount];
        createBoard(myButtons);
    }

    private void createBoard(MyButton[][] myButtons) {
        Random random = new Random();
        int valueBtn;

        for (int i = 0; i < myButtons.length; i++) {
            horizontalLayout = new HorizontalLayout();
            for (int j = 0; j < myButtons[i].length; j++) {
                myButtons[i][j] = new MyButton(" ");
                valueBtn = random.nextInt(2);
                myButtons[i][j].setData(valueBtn);
                myButtons[i][j].setDescription(String.valueOf(valueBtn));

                int finalI = i;
                int finalJ = j;
                myButtons[i][j].addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {

                        clickedRowIndex = finalI;
                        clickedColumnIndex = finalJ;
                        int neighbours = findNeighbours();
                        if (neighbours >= 3){
                            myButtons[clickedRowIndex][clickedColumnIndex].setData(1);
                            myButtons[clickedRowIndex][clickedColumnIndex].setDescription("1");
                            myButtons[clickedRowIndex][clickedColumnIndex].setStyleName(ValoTheme.BUTTON_FRIENDLY);
                            System.out.println("yaşat");
                        }else {
                            myButtons[clickedRowIndex][clickedColumnIndex].setData(0);
                            myButtons[clickedRowIndex][clickedColumnIndex].setDescription("0");
                            myButtons[clickedRowIndex][clickedColumnIndex].setStyleName(ValoTheme.BUTTON_DANGER);
                            System.out.println("öldür");
                        }
                    }
                });
                horizontalLayout.addComponent(myButtons[i][j]);
                horizontalLayout.setSpacing(true);
            }
            setSpacing(true);
            addComponent(horizontalLayout);
        }
    }

    private int findNeighbours() {
        int btnValue = (int) myButtons[clickedRowIndex][clickedColumnIndex].getData();

        int k = 1;
        int sumOfAlive = 0;

        if(btnValue == 0){
            return sumOfAlive;
        }else if (btnValue == 1){
            // Sol üst köşe
            if(clickedRowIndex == 0 && clickedColumnIndex == 0){
                sumOfAlive += (int) myButtons[clickedRowIndex + 1][clickedColumnIndex + 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex + 1][clickedColumnIndex].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex ][clickedColumnIndex + 1].getData();
                return sumOfAlive;

            // Sağ üst köşe
            }else if(clickedRowIndex == 0 && clickedColumnIndex == columnCount - 1){
                sumOfAlive += (int) myButtons[clickedRowIndex + 1][clickedColumnIndex - 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex + 1][clickedColumnIndex].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex ][clickedColumnIndex - 1].getData();
                return sumOfAlive;

            // Sol alt köşe
            }else if(clickedRowIndex == rowCount - 1 && clickedColumnIndex == 0){
                sumOfAlive += (int) myButtons[clickedRowIndex - 1][clickedColumnIndex + 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex - 1][clickedColumnIndex].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex ][clickedColumnIndex + 1].getData();
                return sumOfAlive;

            // Sağ alt köşe
            }else if (clickedRowIndex == rowCount - 1 && clickedColumnIndex == columnCount - 1){
                sumOfAlive += (int) myButtons[clickedRowIndex - 1][clickedColumnIndex - 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex - 1][clickedColumnIndex].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex ][clickedColumnIndex - 1].getData();
                return sumOfAlive;

            // Sol kenar
            }else if(clickedColumnIndex == 0){
                sumOfAlive += (int) myButtons[clickedRowIndex - 1][clickedColumnIndex ].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex - 1][clickedColumnIndex + 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex + 1][clickedColumnIndex ].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex + 1][clickedColumnIndex + 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex ][clickedColumnIndex + 1].getData();
                return sumOfAlive;

            // Sağ kenar
            }else if(clickedColumnIndex == columnCount -1){
                sumOfAlive += (int) myButtons[clickedRowIndex - 1][clickedColumnIndex ].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex - 1][clickedColumnIndex - 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex + 1][clickedColumnIndex ].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex + 1][clickedColumnIndex - 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex ][clickedColumnIndex - 1].getData();
                return sumOfAlive;

            // Üst kenar
            }else if(clickedRowIndex == 0){
                sumOfAlive += (int) myButtons[clickedRowIndex][clickedColumnIndex + 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex][clickedColumnIndex - 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex + 1][clickedColumnIndex ].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex + 1][clickedColumnIndex + 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex + 1][clickedColumnIndex - 1].getData();
                return sumOfAlive;

            // Alt kenar
            }else if(clickedRowIndex == rowCount - 1) {
                sumOfAlive += (int) myButtons[clickedRowIndex][clickedColumnIndex + 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex][clickedColumnIndex - 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex - 1][clickedColumnIndex].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex - 1][clickedColumnIndex + 1].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex - 1][clickedColumnIndex - 1].getData();
                return sumOfAlive;

            // Hiçbir istisna durum değilse ~~ ortadaki butonsa
            }else {
                for (int i = clickedColumnIndex - k; i <= clickedColumnIndex + k; i++) {
                    sumOfAlive += (int) myButtons[clickedRowIndex - k][i].getData();
                    sumOfAlive += (int) myButtons[clickedRowIndex + k][i].getData();
                }
                sumOfAlive += (int) myButtons[clickedRowIndex][clickedColumnIndex - k].getData();
                sumOfAlive += (int) myButtons[clickedRowIndex][clickedColumnIndex + k].getData();
                return sumOfAlive;
            }
        }
        return sumOfAlive;
    }
}