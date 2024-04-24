package com.cainsgl.Tools;

import com.cainsgl.Tools.enums.Arrow;
import javafx.scene.layout.Pane;

public class TipSettings
{
    public static double layoutX;
    public static double layoutY;
    public static double originallayoutX;
    public static double originallayoutY;
    public static Pane parent;


    public static double interval;

    public static double LayoutXDelta;
    public static double LayoutYDelta;
    public static double originalLayoutXDelta;
    public static double originalLayoutYDelta;


    public static double FirstOrginalLayoutX=0;
    public static double FirstOrginalLayoutY=0;

    public static double FixedDelayTime;

    private TipSettings()
    {

    }
    public static void SetDelayTime(double delayTime)
    {
        FixedDelayTime=delayTime;
    }
    public static void SetValue(double layoutX, double layoutY, double originallayoutX, double originallayoutY, double interval, Pane parent, Arrow arrow)
    {
        SetPosition(layoutX,layoutY);

        TipSettings.originallayoutX = originallayoutX;
        TipSettings.originallayoutY = originallayoutY;
        TipSettings.interval = interval;
        TipSettings.parent = parent;
        setarrow(arrow ,interval);
    }
    public static void SetParent(Pane Parent)
    {
        TipSettings.parent = Parent;
    }

    public static void SetPosition(double x, double y)
    {
        TipSettings.layoutX = x;
        TipSettings.layoutY = y;
    }


    public static void setarrow(Arrow arrow,Double interval)
    {
        switch (arrow)
       {
        case Left:
            LayoutXDelta=-287-interval;
            LayoutYDelta=0;
            break;
        case Right:
            LayoutXDelta=287+interval;
            LayoutYDelta=0;
            break;
        case Up:
            LayoutYDelta=-61-interval;
            LayoutXDelta=0;
            break;
        case Down:
            LayoutYDelta=61+interval;
            LayoutXDelta=0;
            break;
        }
    }
    public static void setarrow( Arrow arrow)
    {
        setarrow(arrow,interval);
    }

    public static void SetFirstOrignal(double x, double y)
    {
        FirstOrginalLayoutX=x;
        FirstOrginalLayoutY=y;
    }
    public static void SetOrignalDelta(double originalLayoutXDelta, double originalLayoutYDelta)
    {
        TipSettings.originalLayoutXDelta = originalLayoutXDelta;
        TipSettings.originalLayoutYDelta = originalLayoutYDelta;
    }
}
