/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gamelibrary.game;

import com.mycompany.gamelibrary.buildingLibrary.Building;
import com.mycompany.gamelibrary.sprites.Sprite;
import java.util.ArrayList;
import java.util.List;
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
public class Level extends Parent
{

    GridPane xDimDebug;
    GridPane yDimDebug;
    GridPane zDimDebug;

    final ObservableList<Building<? extends Shape>> buildings;
    final ObservableList<Sprite<? extends Shape>> sprites;



    
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
        this.buildings = buildings;
        this.sprites = sprites;
        getChildren().addAll(buildings);
        getChildren().addAll(sprites);

//        Pane testPane = new Pane();
//        testPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
//        getChildren().add(testPane);
//        testPane.setPrefHeight(100000);
//        testPane.setPrefWidth(100000);
    }

    public void displayDebug(int size, int num)
    {
        xDimDebug = grid(size, num);
        yDimDebug = grid(size, num);
        yDimDebug.getTransforms().add(new Rotate(-90,
                                                 Rotate.X_AXIS));
        zDimDebug = grid(size, num);
        zDimDebug.getTransforms().add(new Rotate(90,
                                                 Rotate.Z_AXIS));
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


}
