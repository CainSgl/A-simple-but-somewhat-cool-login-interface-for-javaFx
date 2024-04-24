package com.cainsgl.controlers;


import com.cainsgl.Tools.Const;
import com.cainsgl.inter.Handler;
import com.cainsgl.inter.init;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


public class TipControler implements init
{
    @FXML
    public Label TipSpecies;

    @FXML
    public AnchorPane Tip;

    @FXML
    public Label Measge;


    public static final String warning = String.valueOf('\ue601');
    public static final String error = String.valueOf('\ue600');
    public static final String normal = String.valueOf('\ue78a');
    public static final String refuse = String.valueOf('\ue644');
    public static final String pass = String.valueOf('\ue747');

    static String address="/Alert.fxml";
    private static TipControler CreateTipContorler()
    {
        FXMLLoader AlertLoder = new FXMLLoader(TipControler.class.getResource(address));
        try
        {
            AlertLoder.load();
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }

        return AlertLoder.getController();
    }
    private static void SetFAlertURL(String s)
    {
        address=s;
    }
    public static AnchorPane getTip(com.cainsgl.Tools.enums.TipSpecies Specie, String Measge)
    {
        TipControler tipControler=CreateTipContorler();
            switch (Specie)
            {
            case Warning:
                setFont(tipControler.TipSpecies, font32, warning);
                tipControler.TipSpecies.setTextFill(Paint.valueOf("#edcd49"));
                break;
            case Error:
                setFont(tipControler.TipSpecies, font32, error);
                tipControler.TipSpecies.setTextFill(Paint.valueOf("#f66363"));
                break;
            case Normal:
                setFont(tipControler.TipSpecies, font32, normal);
                tipControler.TipSpecies.setTextFill(Paint.valueOf("#39b836"));
                break;
            case Refuse:
                setFont(tipControler.TipSpecies, font32, refuse);
                tipControler.TipSpecies.setTextFill(Paint.valueOf("#ff943f"));
                break;
            case Pass:
                setFont(tipControler.TipSpecies, font32, pass);
                tipControler.TipSpecies.setTextFill(Paint.valueOf("#f1e32d"));
                break;
            }
        tipControler.Measge.setText(Measge);
        return tipControler.Tip;
    }

    public static Font font32;
    @Override
    public void initialize()
    {
        font32 = javafx.scene.text.Font.loadFont(getClass().getResourceAsStream("/iconfont.ttf"), 32);
    }

    public static void setFont(Labeled labeled, Font font, String ch)
    {
        labeled.setFont(font);
        labeled.setText(ch);
    }
// //不够会自己创造,存储自己，和Anmations里的那边的队列一起，当Anmations要的时候，如果不够会创建，如果队列里还有，就会在修改数据后送过去。
//   public static Queue<TipControler> Alerts = new LinkedList<TipControler>();
//
//    private static TipControler CreateAlert()
//    {
//        FXMLLoader AlertLoder = new FXMLLoader(Const.handler.getClass().getResource("/Alert.fxml"));
//        try
//        {
//            AlertLoder.load();
//        }
//        catch (IOException ex)
//        {
//            throw new RuntimeException(ex);
//        }
//        Alerts.add(AlertLoder.getController());
//        return AlertLoder.getController();
//    }
}
