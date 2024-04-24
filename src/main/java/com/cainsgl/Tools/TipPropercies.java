package com.cainsgl.Tools;

import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TipPropercies
{
    Pane tip;
    public  double layoutX;
    public  double layoutY;
    public  double originallayoutX;
    public  double originallayoutY;


    public double DelayTime=0.2;

    public Timeline CureentAnimation;
    public void FixPostion()
    {
        layoutX+=TipSettings.LayoutXDelta;
        layoutY+=TipSettings.LayoutYDelta;
        originallayoutX+=TipSettings.originalLayoutXDelta;
        originallayoutY+=TipSettings.originalLayoutYDelta;
        DelayTime+=TipSettings.FixedDelayTime;
    }
    //构造必须在动画前就执行
    public TipPropercies(Pane tip, Timeline Animation)
    {
        layoutX=TipSettings.layoutX;
        layoutY=TipSettings.layoutY;
        originallayoutX=tip.getLayoutX();
        originallayoutY=tip.getLayoutY();
        this.tip=tip;
        CureentAnimation=Animation;
    }
}
