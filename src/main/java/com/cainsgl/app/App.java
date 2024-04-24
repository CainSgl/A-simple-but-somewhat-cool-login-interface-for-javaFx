package com.cainsgl.app;

import com.cainsgl.controlers.loadmenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application
{
    @Override
    public void start(Stage loadStage) throws Exception
    {
        loadStage.initStyle(StageStyle.TRANSPARENT);
        loadStage.setTitle("登录界面");
        loadStage.getIcons().add(new Image(getClass().getResourceAsStream("/闪电.png")));

        FXMLLoader loadmenuFXML=new FXMLLoader(getClass().getResource("/loadmenu.fxml"));

        Scene loadmenuScene=new Scene(loadmenuFXML.load());
        loadmenuScene.setFill(Color.TRANSPARENT);
        loadmenuScene.getStylesheets().add(getClass().getResource("/loadmenu.css").toExternalForm());

        loadStage.setScene(loadmenuScene);
        ((loadmenu)loadmenuFXML.getController()).setStage(loadStage);
        loadStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
