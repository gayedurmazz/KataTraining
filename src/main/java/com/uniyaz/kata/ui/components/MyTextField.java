package com.uniyaz.kata.ui.components;

import com.vaadin.ui.TextField;

public class MyTextField extends TextField {
    private String caption;

    public MyTextField(String caption) {
        this.caption = caption;
        setCaption(caption);
    }

}
