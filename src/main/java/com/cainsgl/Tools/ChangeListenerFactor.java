package com.cainsgl.Tools;

import com.cainsgl.controlers.loadmenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.util.Duration;



public class ChangeListenerFactor
{
    //这里是一个屎山代码，我懒得更改了
    public static ChangeListener<Boolean> focusedPropertyMove(TextField textField, Label label, double LayOut, double down, String s, TextField anotherTextField, Button clear)
    {
        boolean flag=s.equals("PassWord");


        return new ChangeListener<Boolean>()
        {

            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue)
            {
                if (newValue)
                {
                    if(flag)
                    {
                        clear.setLayoutY(309);


                    }else
                    {
                        clear.setLayoutY(309-303+237);
                    }
                    clear.setVisible(true);

                    label.setVisible(true);
                    Animations.Move(0.1, LayOut-8, label, e ->
                    {
                        Animations.Move(Duration.seconds(0.05),LayOut,label);
                    });
                }
                else
                {
                    if(flag)
                    {
                        anotherTextField.setPromptText("");
                    }
                    textField.setPromptText("");
                    Animations.Move(0.1, LayOut + down+8, label, e ->
                    {
                        Animations.Move(0.05,LayOut+down,label,ev->
                        {
                            if(flag)
                            {
                                anotherTextField.setPromptText(s);
                            }
                            textField.setPromptText(s);
                            label.setVisible(false);
                        });
                    });
                    clear.setVisible(false);
                }
            }
        };
    }
}
