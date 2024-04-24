package com.cainsgl.controlers;


import com.cainsgl.Tools.*;
import com.cainsgl.Tools.enums.Arrow;
import com.cainsgl.Tools.enums.TipSpecies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class loadmenu
{
    Stage mainStage;
    private double mouseX, mouseY;


    public void initialize()
    {
        //下面是一些基础按钮，让登录界面也具有窗体按钮
        closeButton.setOnAction(actionEvent ->
                {
                    System.exit(0);
                }
        );
        miniedButton.setOnAction(
                actionEvent ->
                {
                    mainStage.setIconified(true);
                }
        );

        windowPane.setOnMousePressed(
                e ->
                {
                    mouseX = e.getScreenX();
                    mouseY = e.getScreenY();
                }
        );
        windowPane.setOnMouseDragged(
                e ->
                {
                    mainStage.setX(mainStage.getX() + e.getScreenX() - mouseX);
                    mainStage.setY(mainStage.getY() + e.getScreenY() - mouseY);
                    mouseX = e.getScreenX();
                    mouseY = e.getScreenY();
                }
        );
        //下面是对密码输入栏的一些设定
        eyeButton.setOnAction(this::eyeButtonSeleclt);
        passwordFieldEntryp.setTextFormatter(new TextFormatter<>(this::passwordFieldEntryChange));
        passwordField.setTextFormatter(new TextFormatter<>(this::passwordFieldEntryChange));
        ContextMenu loadmenuRitgh = CreteMenu(accountField);
        ContextMenu Passmenu = new ContextMenu();
        passwordField.textProperty().bindBidirectional(passwordFieldEntryp.textProperty());
        passwordField.focusedProperty().addListener(ChangeListenerFactor.focusedPropertyMove(passwordField, PassWordLable, 271, 33, "PassWord", passwordFieldEntryp, Clear));
        passwordFieldEntryp.focusedProperty().addListener(ChangeListenerFactor.focusedPropertyMove(passwordFieldEntryp, PassWordLable, 271, 33, "PassWord", passwordField, Clear));
        passwordField.setContextMenu(Passmenu);
        passwordFieldEntryp.setContextMenu(Passmenu);
        passwordFieldEntryp.setOnKeyPressed(
                event ->
                {
                    if (event.getCode() == KeyCode.V && event.isControlDown())
                    {
                        event.consume();
                    }
                }
        );
        passwordField.setOnKeyPressed(
                event ->
                {
                    if (event.getCode() == KeyCode.V && event.isControlDown())
                    {
                        event.consume();
                    }
                }
        );
        //对用户输入栏的设置
        accountField.setTextFormatter(new TextFormatter<>(this::AccountChange));
        accountField.setContextMenu(loadmenuRitgh);


        //焦点事件
        accountField.focusedProperty().addListener(ChangeListenerFactor.focusedPropertyMove(accountField, AccountLable, 204, 33, "Account", accountField, Clear));
        //登录按钮

        Control[] FadeControls = {UserIcon, PassIcon, ForgetPassword, eyeButton, accountField, passwordFieldEntryp, loadButton, Clear};
        loadButton.setOnAction(e ->
                {
                    if (accountField.getText().equals(""))
                    {
                        Animations.PutTip(TipControler.getTip(TipSpecies.Warning, "账号不能为空"));
                        return;
                    }
                    if (passwordField.getText().equals(""))
                    {
                        Animations.PutTip(TipControler.getTip(TipSpecies.Warning, "密码不能为空"));
                        return;
                    }
                    eyeButton.setSelected(false);
                    eyeButtonSeleclt(new ActionEvent());
                    Animations.Fade(0.2, 0, FadeControls);
                    Animations.Move(Duration.seconds(0.2), 137, Icon);
                    Animations.FadeIn(0.2, 1, 0.0, ProgressRec,ProRec);
                    Animations.ProRecPlay(ProRec,54,274,Duration.seconds(1));
                    String user = load.load(accountField.getText(), passwordField.getText());
                    loadButton.setDisable(true);
                    if (user != null)
                    {
                        TipSettings.SetValue(56, 10, 56, -100, 8, TipContains, Arrow.Down);
                        TipSettings.SetOrignalDelta(0, 61 + 8);
                        TipSettings.SetFirstOrignal(56, -100);
                        TipSettings.SetDelayTime(0.1);
                        TipSettings.parent=MainPane;
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        Date date = new Date(System.currentTimeMillis());
                        Animations.PutTip(TipControler.getTip(TipSpecies.Normal, user + ":\n欢迎你\n" + formatter.format(date)));
                    }
                    else
                    {
                        Animations.PutTip(TipControler.getTip(TipSpecies.Warning, "提醒:\n请检查账号或密码大小写"));
                        Animations.PutTip(TipControler.getTip(TipSpecies.Error, "错误:\n请检查防火墙或网络设置\n无法访问到服务器"));

                        Animations.DuationHandler(Duration.seconds(1), () ->
                        {
                         //   Animations.ProRecPlay(ProRec,274,54,Duration.seconds(1));
                            Animations.Fade(0.2, 0, ProgressRec,ProRec);
                            Animations.FadeIn(0.2, 1, 0.0, FadeControls);
                            loadButton.setDisable(false);
                            Animations.Move(Duration.seconds(0.2), 57, Icon);

                        });


                    }
                }
        );
        Clear.setOnAction(e ->
        {
            if (Clear.getLayoutY() == 309)
            {
                if (!passwordField.getText().isEmpty())
                    Animations.PutTip(TipControler.getTip(TipSpecies.Normal, "成功清除密码.\n请重新输入."));
                passwordFieldEntryp.setText("");
            }
            else
            {
                if (!accountField.getText().isEmpty())
                    Animations.PutTip(TipControler.getTip(TipSpecies.Normal, "成功清除账号.\n请重新输入."));
                accountField.setText("");
            }
        });


        accountField.setStyle("-fx-font-size: 16px;");
        passwordField.setStyle("-fx-font-size: 16px;");
        //  passwordFieldEntryp.setFont(FontsLoader.Conther);
        passwordFieldEntryp.setStyle("-fx-font-size: 16px;");
        AccountLable.setStyle("-fx-font-size: 16px;");
        PassWordLable.setStyle("-fx-font-size: 16px;");
    }

    public final String refuseChar = "[^a-zA-Z0-9_-]+";
    int i = 0;

    TextFormatter.Change passwordFieldEntryChange(TextFormatter.Change change)
    {
        if (change.isAdded())
        {
            if (change.getText().matches(refuseChar))
            {
                Animations.PutTip(TipControler.getTip(TipSpecies.Refuse, "不受支持的:" + change.getText() + "\n目前仅支持字母以及_-。"));
                return null;
            }
            if (change.getControlNewText().length() > 18)
            {
                Animations.PutTip(TipControler.getTip(TipSpecies.Warning, "输入的字数大于18\n"));
                return null;
            }
        }
        if (change.getSelection().getStart() != change.getSelection().getEnd())
        {
            return null;
        }
        return change;
    }

    TextFormatter.Change AccountChange(TextFormatter.Change change)
    {
        if (change.isAdded())
        {
            if (change.getText().matches(refuseChar))
            {
                Animations.PutTip(TipControler.getTip(TipSpecies.Refuse, "不受支持的:" + change.getText() + "\n目前仅支持字母以及_-。"));
                return null;
            }
            if (change.getControlNewText().length() > 18)
            {
                Animations.PutTip(TipControler.getTip(TipSpecies.Warning, "输入的字数大于18\n"));
                return null;
            }
        }
        return change;
    }

    ContextMenu CreteMenu(TextField textField)
    {
        ContextMenu menu = new ContextMenu();
        MenuItem ReInput = new MenuItem("一键清除");
        MenuItem Paste = new MenuItem("一键粘贴");
        MenuItem Copy = new MenuItem("一键复制");
        // Paste.setStyle(".");
        Paste.setOnAction(e ->
        {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            // 判断剪切板中是否包含文本类型的内容
            if (clipboard.hasContent(DataFormat.PLAIN_TEXT))
            {
                if (((String) clipboard.getContent(DataFormat.PLAIN_TEXT)).length() <= 18)
                {
                    textField.setText((String) clipboard.getContent(DataFormat.PLAIN_TEXT));
                }
                else
                {
                    //提示过长
                    Animations.PutTip(TipControler.getTip(TipSpecies.Refuse, "你粘贴的字数过长，最大应不超过16位!"));
                }
            }

        });
        Copy.setOnAction(e ->
        {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(accountField.getText());
            clipboard.setContent(content);
            //提示粘贴成功
            Animations.PutTip(TipControler.getTip(TipSpecies.Normal, "复制成功.\n快去告诉你的小伙伴吧!"));
        });
        ReInput.setOnAction(e ->
        {
            textField.setText("");
            Animations.PutTip(TipControler.getTip(TipSpecies.Normal, "清除成功.\n请重新输入."));
        });
        menu.getItems().add(Copy);
        menu.getItems().add(Paste);
        menu.getItems().add(ReInput);
        return menu;
    }

    void eyeButtonSeleclt(ActionEvent event)
    {
        if (eyeButton.isSelected())
        {
            eyeButton.setText(eyeUnicode);
            passwordFieldEntryp.setVisible(false);
            passwordField.setVisible(true);
        }
        else
        {
            eyeButton.setText(uneyeUnicode);
            passwordFieldEntryp.setVisible(true);
            passwordField.setVisible(false);
        }
    }

    @FXML
    private Button miniedButton;

    @FXML
    private Button closeButton;

    @FXML
    private AnchorPane windowPane;
    @FXML
    private AnchorPane TipContains;

    @FXML
    private ToggleButton eyeButton;

    @FXML
    private TextField accountField;

    @FXML
    private TextField passwordField;
    @FXML
    private TextField passwordFieldEntryp;
    //看得见


    final String eyeUnicode = String.valueOf('\uec86');
    final String uneyeUnicode = String.valueOf('\uee34');
    @FXML
    private Button loadButton;
    @FXML
    private Hyperlink ForgetPassword;
    @FXML
    private Label PassIcon;
    @FXML
    private Label UserIcon;
    @FXML
    private Label AccountLable;
    @FXML
    private Label PassWordLable;
    @FXML
    private ImageView Icon;

    @FXML
    private Button Clear;
    @FXML
    private AnchorPane MainPane;
    @FXML
    private Rectangle ProgressRec;
    @FXML
    private Rectangle ProRec;

    void after()
    {
        Font font12 = FontsLoader.ICONFONT12;
        final String closeUnicode = String.valueOf('\ue6a6');
        final String miniUnicode = String.valueOf('\ue650');
        final String PasswordUnicode = String.valueOf('\ue8b2');
        final String UserUnicode = String.valueOf('\ue612');
        final String loadUnicode = String.valueOf('\ue626');
        Font font16 = Font.loadFont(getClass().getResourceAsStream("/iconfont.ttf"), 20);


        setFont(closeButton, font12, closeUnicode);
        setFont(eyeButton, font16, uneyeUnicode);
        setFont(loadButton, font16, loadUnicode);
        setFont(Clear, font12, closeUnicode);

        setFont(PassIcon, font16, PasswordUnicode);
        setFont(UserIcon, font16, UserUnicode);
//        TipSettings.SetValue(56,10,56,-100,8,TipContains,Arrow.Down);
//        TipSettings.SetOrignalDelta(0,61+8);
//        TipSettings.SetFirstOrignal(-300,0);
//        TipSettings.SetDelayTime(1);

        TipSettings.SetValue(100, 45, 100, 497 + 61 + 30, 10, TipContains, Arrow.Up);
        TipSettings.SetFirstOrignal(400, 45);
        TipSettings.SetOrignalDelta(0, -61 - 10);
        TipSettings.SetDelayTime(0.2);
        Image image = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/闪电.png")));
        Icon.setImage(image);
    }

    void setFont(Labeled button, Font font, String ch)
    {
        button.setFont(font);
        button.setText(ch);
    }


    public void setStage(Stage stage)
    {
        mainStage = stage;
        after();
    }
}
