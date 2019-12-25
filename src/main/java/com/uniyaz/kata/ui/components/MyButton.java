package com.uniyaz.kata.ui.components;

import com.vaadin.ui.Button;

public class MyButton extends Button {
    String btnCaption;

    public MyButton(String btnCaption) {
        this.btnCaption = btnCaption;
        setCaption(btnCaption);
    }
}
