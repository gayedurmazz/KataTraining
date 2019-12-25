package com.uniyaz.kata.ui.views;

import com.uniyaz.kata.ui.components.MyButton;
import com.uniyaz.kata.ui.components.MyTextField;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;

public class UserView extends VerticalLayout {

    FormLayout mainLayout;
    public UserView() {

        mainLayout = new FormLayout();
        MyTextField satirTextField = new MyTextField("Satır Sayısı");
        mainLayout.addComponent(satirTextField);

        MyTextField sutunTextField = new MyTextField("Sütun Sayısı");
        mainLayout.addComponent(sutunTextField);

        MyButton createBtn = new MyButton("Oluştur");
        mainLayout.addComponent(createBtn);
        createBtn.setIcon(FontAwesome.SMILE_O);
        createBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                int row = Integer.parseInt(satirTextField.getValue());
                int column = Integer.parseInt(sutunTextField.getValue());
                Board board = new Board(row, column);
                addComponent(board);
            }
        });

        setSpacing(true);
        setMargin(true);
        addComponent(mainLayout);
    }
}
