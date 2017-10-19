/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.UI;

import com.mycompany.gamelibrary.game.level.CameraLevel;
import com.mycompany.gamelibrary.game.level.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.Region;
import javafx.util.Duration;


/**
 *
 * @author Pramukh
 */
public class GamePane extends Region
{

    private final ObjectProperty<Level> level = new SimpleObjectProperty<>();

    BooleanProperty upKeyDown = new SimpleBooleanProperty(false);
    BooleanProperty downKeyDown = new SimpleBooleanProperty(false);
    BooleanProperty leftKeyDown = new SimpleBooleanProperty(false);
    BooleanProperty rightKeyDown = new SimpleBooleanProperty(false);

    BooleanProperty wKeyDown = new SimpleBooleanProperty(false);
    BooleanProperty aKeyDown = new SimpleBooleanProperty(false);
    BooleanProperty sKeyDown = new SimpleBooleanProperty(false);
    BooleanProperty dKeyDown = new SimpleBooleanProperty(false);

    private final SubScene levelScene;

    public GamePane()
    {
        this(null, false);
    }

    public GamePane(Level startingLevel)
    {
        this(startingLevel, false);
    }

    public GamePane(boolean antialiasing)
    {
        this(null, antialiasing);
    }

    public GamePane(Level startingLevel, boolean antialiasing)
    {

        level.set(startingLevel);
        levelScene = new SubScene(new Group(), Double.NaN, Double.NaN,
                                  true,
                                  antialiasing ? SceneAntialiasing.BALANCED : SceneAntialiasing.DISABLED);
        levelScene.widthProperty().bind(widthProperty());
        levelScene.heightProperty().bind(heightProperty());
        newLevelStuff(startingLevel);

        level.addListener(
                (ObservableValue<? extends Level> observable, Level oldValue, Level newValue)
                -> 
                {

                    newLevelStuff(newValue);

                    //add level change stuff
        });

        Timeline t = new Timeline(new KeyFrame(new Duration(5), "animation", e
                                               -> 
                                               {
                                                   level.get().tick();

                                       }));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();

        addListeners();
        getChildren().add(levelScene);

    }

    private void newLevelStuff(Level l)
    {
        if(l instanceof CameraLevel)
        {
            levelScene.setCamera(((CameraLevel) l)
                    .getCamera());
        }
        levelScene.rootProperty().set(l);
        l.wKeyDownProperty().bind(wKeyDown);
        l.aKeyDownProperty().bind(aKeyDown);
        l.sKeyDownProperty().bind(sKeyDown);
        l.dKeyDownProperty().bind(dKeyDown);
        l.upKeyDownProperty().bind(upKeyDown);
        l.downKeyDownProperty().bind(downKeyDown);
        l.leftKeyDownProperty().bind(leftKeyDown);
        l.rightKeyDownProperty().bind(rightKeyDown);
    }

    private void addListeners()
    {
        onKeyReleasedProperty().set(e
                -> 
                {
                    switch(e.getCode())
                    {
                        case UP:
                            upKeyDownProperty().setValue(false);
                            e.consume();
                            break;
                        case LEFT:
                            leftKeyDownProperty().setValue(false);
                            e.consume();
                            break;
                        case DOWN:
                            downKeyDownProperty().setValue(false);
                            e.consume();
                            break;
                        case RIGHT:
                            rightKeyDownProperty().setValue(false);
                            e.consume();
                            break;
                        case W:
                            wKeyDownProperty().setValue(false);
                            e.consume();
                            break;
                        case A:
                            aKeyDownProperty().setValue(false);
                            e.consume();
                            break;
                        case S:
                            sKeyDownProperty().setValue(false);
                            e.consume();
                            break;
                        case D:
                            dKeyDownProperty().setValue(false);
                            e.consume();
                            break;
                    }
        });
        onKeyPressedProperty().set((e)
                -> 
                {
                    //System.out.println("Key Pressed: " + e.getCode());

                    switch(e.getCode())
                    {
                        case UP:
                            this.upKeyDownProperty().setValue(true);
                            e.consume();
                            break;
                        case LEFT:
                            this.leftKeyDownProperty().setValue(true);
                            e.consume();
                            break;
                        case DOWN:
                            this.downKeyDownProperty().setValue(true);
                            e.consume();
                            break;
                        case RIGHT:
                            this.rightKeyDownProperty().setValue(true);
                            e.consume();
                            break;
                        case W:
                            this.wKeyDownProperty().setValue(true);
                            e.consume();
                            break;
                        case A:
                            this.aKeyDownProperty().setValue(true);
                            e.consume();
                            break;
                        case S:
                            this.sKeyDownProperty().setValue(true);
                            e.consume();
                            break;
                        case D:
                            this.dKeyDownProperty().setValue(true);
                            e.consume();
                            break;
                    }
        });
    }

    @Override
    public void requestFocus()
    {
//        System.out.printf(
//                "focus requested for the GamePane, which %b had a level\n",
//                level.get() != null);
        if(levelProperty().get() == null)
        {
            super.requestFocus();
        } else
        {
            levelProperty().get().requestFocus();
        }
    }

    public Level getLevel()
    {
        return level.get();
    }

    public ReadOnlyObjectProperty<Level> levelProperty()
    {
        return level;
    }

    public void setLevel(Level level)
    {
        this.level.set(level);

    }

    public final void setUpKeyDown(Boolean value)
    {
        upKeyDown.set(value);
    }

    public final Boolean getUpKeyDown()
    {
        return upKeyDown.get();
    }

    public final BooleanProperty upKeyDownProperty()
    {
        return upKeyDown;
    }

    public final void setDownKeyDown(Boolean value)
    {
        downKeyDown.set(value);
    }

    public final Boolean getDownKeyDown()
    {
        return downKeyDown.get();
    }

    public final BooleanProperty downKeyDownProperty()
    {
        return downKeyDown;
    }

    public final void setLeftKeyDown(Boolean value)
    {
        leftKeyDown.set(value);
    }

    public final Boolean getLeftKeyDown()
    {
        return leftKeyDown.get();
    }

    public final BooleanProperty leftKeyDownProperty()
    {
        return leftKeyDown;
    }

    public final void setRightKeyDown(Boolean value)
    {
        rightKeyDown.set(value);
    }

    public final Boolean getRightKeyDown()
    {
        return rightKeyDown.get();
    }

    public final BooleanProperty rightKeyDownProperty()
    {
        return rightKeyDown;
    }

    public final void setWKeyDown(Boolean value)
    {
        wKeyDown.set(value);
    }

    public final Boolean getWKeyDown()
    {
        return wKeyDown.get();
    }

    public final BooleanProperty wKeyDownProperty()
    {
        return wKeyDown;
    }

    public final void setAKeyDown(Boolean value)
    {
        aKeyDown.set(value);
    }

    public final Boolean getAKeyDown()
    {
        return aKeyDown.get();
    }

    public final BooleanProperty aKeyDownProperty()
    {
        return aKeyDown;
    }

    public final void setSKeyDown(Boolean value)
    {
        sKeyDown.set(value);
    }

    public final Boolean getSKeyDown()
    {
        return sKeyDown.get();
    }

    public final BooleanProperty sKeyDownProperty()
    {
        return sKeyDown;
    }

    public final void setDKeyDown(Boolean value)
    {
        dKeyDown.set(value);
    }

    public final Boolean getDKeyDown()
    {
        return dKeyDown.get();
    }

    public final BooleanProperty dKeyDownProperty()
    {
        return dKeyDown;
    }

}
