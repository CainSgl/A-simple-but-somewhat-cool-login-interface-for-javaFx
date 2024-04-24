package com.cainsgl.Tools;


import com.cainsgl.Tools.enums.Arrow;
import com.cainsgl.Tools.enums.TipSpecies;
import com.cainsgl.controlers.TipControler;
import com.cainsgl.inter.Handler;
import javafx.animation.*;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.*;


public class Animations
{

    public static void Move(Duration duration, double layoutY, Node node)
    {
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(node.layoutYProperty(), layoutY);
        KeyFrame kf = new KeyFrame(duration, keyValue);

        timeline.getKeyFrames().add(kf);

        timeline.play();
    }

    public static void Move(Double duration, double layoutY, Node node, EventHandler<ActionEvent> eventHandler)
    {
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(node.layoutYProperty(), layoutY);
        KeyFrame kf = new KeyFrame(Duration.seconds(duration), eventHandler, keyValue);//会略微高一点
        timeline.getKeyFrames().addAll(kf);
        timeline.play();
    }

    //这个是先显示出来
    public static void FadeIn(Double duration, double fade, Double delay, Node... node)
    {
        if (node == null)
            return;
        for (Node n : node)
        {
            n.setVisible(true);
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(n.opacityProperty(), fade);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(duration), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.setDelay(Duration.seconds(delay));
            timeline.play();
        }

    }

    //这个完毕后会隐藏
    public static void Fade(Double duration, double fade, Node... node)
    {
        if (node == null)
            return;
        for (Node n : node)
        {
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(n.opacityProperty(), fade);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(duration), e ->
            {
                n.setVisible(false);
            }, keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        }
    }

    static ArrayList<TipPropercies> tips=new ArrayList<>();
    //从TipSettings的根位置出发，会根据arrow的位置挤出去
    public static synchronized void PutTip(Pane tip)
    {
       // =TipControler.getTip(Specie, Measge);
        tip.setLayoutX(TipSettings.originallayoutX);
        tip.setLayoutY(TipSettings.originallayoutY);
        TipSettings.parent.getChildren().add(tip);
        if(!tips.isEmpty())
        {
            for(TipPropercies tipPropercies:tips) //将之前的移走
            {
                tipPropercies.FixPostion();
                Timeline timeline=new Timeline();
                KeyValue keyValue=new KeyValue(tipPropercies.tip.layoutXProperty(),tipPropercies.layoutX);
                KeyValue keyValue1=new KeyValue(tipPropercies.tip.layoutYProperty(),tipPropercies.layoutY);
                KeyFrame keyFrame=new KeyFrame(Duration.seconds(0.2),keyValue,keyValue1);
                timeline.getKeyFrames().add(keyFrame);
                tipPropercies.CureentAnimation.jumpTo(Duration.seconds(0.2));//和下面的必须相同，这是为了防止在更新位置的时候，还没有出来
                timeline.play();
            }
        }

        Timeline timeline=new Timeline();
        TipPropercies newTip=new TipPropercies(tip,timeline);
        tips.add(newTip);

        KeyValue keyValue=new KeyValue(tip.layoutXProperty(),TipSettings.layoutX);
        KeyValue keyValue2=new KeyValue(tip.layoutYProperty(),TipSettings.layoutY);
        KeyFrame keyFrame=new KeyFrame(Duration.seconds(0.2),e->{DelTipWait(newTip);},keyValue,keyValue2);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();


        newTip.originallayoutX=TipSettings.FirstOrginalLayoutX;
        newTip.originallayoutY=TipSettings.FirstOrginalLayoutY;
    }
    private static void DelTipWait( TipPropercies tipPropercies)
    {
        // TipPropercies tipPropercies=tips.get(0);

        KeyFrame keyFrame=new KeyFrame(Duration.seconds(1.1+tipPropercies.DelayTime),e->{ DelTip(tipPropercies); });  //因为如果有后面的Tip显示，会导致直接跳到这里，所以为了避免后面还有这里必须等一会儿
       Timeline timeline=new Timeline();
       timeline.getKeyFrames().add(keyFrame);
       timeline.play();
    }
    private static void DelTip(TipPropercies tipPropercies)
    {

        Timeline timeline=new Timeline();
        KeyValue keyValue=new KeyValue(tipPropercies.tip.layoutXProperty(),tipPropercies.originallayoutX);
        KeyValue keyValue1=new KeyValue(tipPropercies.tip.layoutYProperty(),tipPropercies.originallayoutY);
        KeyFrame keyFrame=new KeyFrame(Duration.seconds(tipPropercies.DelayTime),e->{TipSettings.parent.getChildren().remove(tipPropercies.tip);},keyValue,keyValue1);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();


        tips.remove(tipPropercies);

    }
    public static void DuationHandler(Duration duration,Handler handler)
    {
        Timeline timeline=new Timeline();
        KeyFrame keyFrame=new KeyFrame(duration,e->{handler.handler();});
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
  public static Timeline Prorec;
    public static void ProRecPlay(Node main,double from,double to,Duration duration)
    {
        main.setLayoutX(from);
        Prorec=new Timeline();
        KeyValue keyValue=new KeyValue(main.layoutXProperty(),to);
        KeyFrame keyFrame=new KeyFrame(duration,keyValue);
        Prorec.setAutoReverse(true);
        Prorec.setCycleCount(Timeline.INDEFINITE);
        Prorec.getKeyFrames().add(keyFrame);
        Prorec.play();
    }
    public static void ProRecStop()
    {
        Prorec.stop();
    }
}
