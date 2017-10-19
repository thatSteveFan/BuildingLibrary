/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game.level;

import com.mycompany.gamelibrary.buildingLibrary.Building;
import com.mycompany.gamelibrary.game.sprite.Sprite;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;


/**
 *
 * @author Pramukh
 */
public abstract class Level<T extends SpriteSteppedEvent> extends Parent
{

    GridPane xDimDebug;
    GridPane yDimDebug;
    GridPane zDimDebug;

    protected BooleanProperty upKeyDown = new SimpleBooleanProperty(false);
    protected BooleanProperty downKeyDown = new SimpleBooleanProperty(false);
    protected BooleanProperty leftKeyDown = new SimpleBooleanProperty(false);
    protected BooleanProperty rightKeyDown = new SimpleBooleanProperty(false);

    protected BooleanProperty wKeyDown = new SimpleBooleanProperty(false);
    protected BooleanProperty aKeyDown = new SimpleBooleanProperty(false);
    protected BooleanProperty sKeyDown = new SimpleBooleanProperty(false);
    protected BooleanProperty dKeyDown = new SimpleBooleanProperty(false);

    private final ObservableList<Building<? extends Shape>> buildings;
    private final ObservableList<Sprite<? extends Shape>> sprites;
    private final ObservableList<TriggerSpot<? extends Shape, T>> triggers;

    public Level()
    {
        this(FXCollections.observableArrayList(), FXCollections
             .observableArrayList());
    }

    public Level(ObservableList<Building<? extends Shape>> buildings)
    {
        this(buildings, FXCollections.observableArrayList());
    }

    public Level(ObservableList<Building<? extends Shape>> buildings,
                 ObservableList<Sprite<? extends Shape>> sprites)
    {
        this(buildings, sprites, FXCollections.observableArrayList());

//        Pane testPane = new Pane();
//        testPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
//        getChildren().add(testPane);
//        testPane.setPrefHeight(100000);
//        testPane.setPrefWidth(100000);
    }

    public Level(ObservableList<Building<? extends Shape>> buildings,
                 ObservableList<Sprite<? extends Shape>> sprites,
                 ObservableList<TriggerSpot<? extends Shape,  T>> spots)
    {
        this.buildings = buildings;
        this.sprites = sprites;
        this.triggers = spots;
        getChildren().addAll(buildings);
        getChildren().addAll(sprites);
    }

    public void displayDebug(int size, int num)
    {
        xDimDebug = grid(size, num);
        yDimDebug = grid(size, num);
        yDimDebug.getTransforms().add(new Rotate(-90,
                                                 Rotate.X_AXIS));
        zDimDebug = grid(size, num);
        zDimDebug.getTransforms().add(new Rotate(90,
                                                 Rotate.Y_AXIS));
        getChildren().addAll(xDimDebug, yDimDebug, zDimDebug);
    }

    public ObservableList<Sprite<? extends Shape>> getSpritesUnmodifiable()
    {

        return FXCollections.unmodifiableObservableList(sprites);
    }

    public void addSprite(Sprite<? extends Shape> s)
    {
        sprites.add(s);
        getChildren().add(s);
    }

    public ObservableList<Building<? extends Shape>> getBuildingsUnmodifiable()
    {
        return FXCollections.unmodifiableObservableList(buildings);
    }

    public void addBuilding(Building<? extends Shape> b)
    {
        buildings.add(b);
        getChildren().add(b);
    }

    private GridPane grid(double size, int num)
    {
        GridPane temp = new GridPane();
        List<ColumnConstraints> cList = new ArrayList<>();
        for(int i = 0;i < num;i++)
        {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(size / num);
            cList.add(cc);
        }
        temp.getColumnConstraints().addAll(cList);

        List<RowConstraints> rList = new ArrayList<>();
        for(int i = 0;i < num;i++)
        {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(size / num);
            rList.add(rc);
        }
        temp.getRowConstraints().addAll(rList);

        //temp.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        temp.setPrefHeight(size);
        temp.setPrefWidth(size);
        temp.setGridLinesVisible(true);

        return temp;
    }

    public void tick()
    {
//        for(TriggerSpot<? extends Shape> t : triggers)
//        {
//            for(Sprite<? extends Shape> s : sprites)
//            {
//                if(s.getShape().getBoundsInParent().intersects(t.getShape()
//                        .getBoundsInParent()))
//                {
//                    t.stepped(s);
//                }
//            }
//        }
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

//    public final ObservableList<TriggerSpot<? extends Shape>> getTriggerSpots()
//    {
//        return triggers;
//    }
//    
//    public final void addTrigger(TriggerSpot<? extends Shape> trigger)
//    {
//        triggers.add(trigger);
//    }
//    
//    public final void removeTrigger(TriggerSpot<? extends Shape> trigger)
//    {
//        triggers.remove(trigger);
//    }

}
