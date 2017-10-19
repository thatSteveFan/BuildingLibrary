package com.mycompany.gamelibrary;

import com.mycompany.gamelibrary.UI.FocusRejectingSlider;
import com.mycompany.gamelibrary.UI.FrameCounter;
import com.mycompany.gamelibrary.UI.GamePane;
import com.mycompany.gamelibrary.game.level.DebuggingLevel;
import eu.lestard.advanced_bindings.api.MathBindings;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.application.Application.launch;


public class MainApp extends Application
{


    public final DoubleProperty buildingAngle = new SimpleDoubleProperty(70);
    private final DoubleProperty sin = new SimpleDoubleProperty();

    private final DoubleProperty cameraAngle = new SimpleDoubleProperty(0);

    private final DoubleProperty cameraSin = new SimpleDoubleProperty();

    
    private final Background background = new Background(new BackgroundFill(new Color(0.7,0.7,0.7,0.7), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background transparentBackground = new Background(new BackgroundFill(new Color(0.7,0.7,0.7,0.7), CornerRadii.EMPTY, Insets.EMPTY));
    
    
    
    {
        cameraSin.bind(MathBindings.sin(MathBindings.toRadians(cameraAngle)));
    }
    private final DoubleProperty cameraCos = new SimpleDoubleProperty();

    
    {
        cameraCos.bind(MathBindings.cos(MathBindings.toRadians(cameraAngle)));
    }

    private StringProperty fps = new SimpleStringProperty();

    private final DoubleProperty notreDameXTranslate = new SimpleDoubleProperty(200);
    private final DoubleProperty notreDameYTranslate = new SimpleDoubleProperty(200);

    private final DoubleProperty width = new SimpleDoubleProperty(200);
    private final DoubleProperty height = new SimpleDoubleProperty(300);
    private final DoubleProperty depth = new SimpleDoubleProperty(400);

    private final DoubleProperty zoom = new SimpleDoubleProperty(500);

    @Override
    public void start(Stage stage) throws Exception
    {
        
        stage.initStyle(StageStyle.DECORATED);

        StackPane root = new StackPane();

        DebuggingLevel level = new DebuggingLevel();

        //level.angleProperty().bind(cameraAngle);
        level.buildingAngleProperty().bind(buildingAngle);
        level.buildingHeightProperty().bind(height);
        level.buildingWidthProperty().bind(width);
        level.angleProperty().bind(cameraAngle);
        level.cameraDistanceProperty().bind(zoom);

        Scene scene = new Scene(root, 600, 600, true, SceneAntialiasing.BALANCED);

        level.displayDebug(1000, 10);

        Parent subPane = new GamePane(level);

        root.setAlignment(Pos.TOP_CENTER);

        VBox sliderArea = sliderArea(subPane);
        Pane sliderWrapper = new Pane(sliderArea);
        sliderWrapper.setPrefWidth(Double.POSITIVE_INFINITY);
        sliderWrapper.setPrefHeight(Double.MIN_NORMAL);

        sliderArea.setBackground(new Background(new BackgroundFill(new Color(0, 0.5, 0, 0.5),
                                                                   CornerRadii.EMPTY,
                                                                   Insets.EMPTY)));
        sliderWrapper.setBackground(new Background(new BackgroundFill(new Color(0.5, 0, 0, 0.5),
                                                                      CornerRadii.EMPTY,
                                                                      Insets.EMPTY)));
        
        //sliderWrapperWrapper.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        
        //sliderArea.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

        scene.onKeyPressedProperty().set(e
                -> 
                {
                    if(e.getCode() == KeyCode.TAB)
                    {
                        
                        if(sliderWrapper.getParent() != null)
                        {
                            root.getChildren().remove(sliderWrapper);
                            level.requestFocus();
                        } else
                        {
                            root.getChildren().add(sliderWrapper);
                            sliderArea.requestFocus();
                        }
                    } else if(e.getCode() == KeyCode.F11)
                    {
                        stage.setFullScreen(true);
                    }
        });

        //http://stackoverflow.com/questions/28287398/what-is-the-preferred-way-of-getting-the-frame-rate-of-a-javafx-application
        FrameCounter frameRateMeter = new FrameCounter();
        fps.bind(Bindings.convert(frameRateMeter.fpsProperty()));
        frameRateMeter.start();

        root.getChildren().addAll(subPane, sliderWrapper);

        subPane.requestFocus();
        //sliderWrapperWrapper.textProperty().bind(fps);
        stage.titleProperty().bind(fps);
        stage.setScene(scene);
        stage.show();

    }

    private VBox sliderArea(Node focusser)
    {

        Label xLabel = new Label("X Offset");
        Slider xSlider = new FocusRejectingSlider(0, 1000, notreDameXTranslate.getValue(), focusser);
        xSlider.showTickMarksProperty().set(true);
        xSlider.showTickLabelsProperty().set(true);
        xSlider.setMajorTickUnit(100);
        notreDameXTranslate.bind(xSlider.valueProperty());

        Label yLabel = new Label("Y Offset");
        Slider ySlider = new FocusRejectingSlider(0, 1000, notreDameYTranslate.getValue(), focusser);
        ySlider.showTickMarksProperty().set(true);
        ySlider.showTickLabelsProperty().set(true);
        ySlider.setMajorTickUnit(100);
        notreDameYTranslate.bind(ySlider.valueProperty());

        Label buildingAngleLabel = new Label("Building Angle");
        Slider buildingAngleSlider = new FocusRejectingSlider(0, 90, buildingAngle.getValue(), focusser);
        buildingAngle.bind(buildingAngleSlider.valueProperty());

        Label cameraAngleLabel = new Label("Camera Angle");
        Slider cameraAngleSlider = new FocusRejectingSlider(0, 90, cameraAngle.getValue(), focusser);
        cameraAngle.bind(cameraAngleSlider.valueProperty());

        Label widthLabel = new Label("Width");
        Slider widthSlider = new FocusRejectingSlider(Double.MIN_NORMAL, 1000, width.getValue(), focusser);
        widthSlider.showTickMarksProperty().set(true);
        widthSlider.showTickLabelsProperty().set(true);
        widthSlider.setMajorTickUnit(100);
        width.bind(widthSlider.valueProperty());
        
        widthLabel.setAlignment(Pos.CENTER);

        Label heightLabel = new Label("Height");
        Slider heightSlider = new FocusRejectingSlider(Double.MIN_NORMAL, 1000, width.getValue(), focusser);
        heightSlider.showTickMarksProperty().set(true);
        heightSlider.showTickLabelsProperty().set(true);
        heightSlider.setMajorTickUnit(100);
        height.bind(heightSlider.valueProperty());

        Label depthLabel = new Label("Depth");
        Slider depthSlider = new FocusRejectingSlider(Double.MIN_NORMAL, 1000, width.getValue(), focusser);
        depthSlider.showTickMarksProperty().set(true);
        depthSlider.showTickLabelsProperty().set(true);
        depthSlider.setMajorTickUnit(100);
        depth.bind(depthSlider.valueProperty());

        Label zoomLabel = new Label("Zoom");
        Slider zoomSlider = new FocusRejectingSlider(000, 5000, zoom.getValue(), focusser);
        zoom.bind(zoomSlider.valueProperty());

        return new VBox(xLabel, xSlider, yLabel, ySlider, buildingAngleLabel,
                        buildingAngleSlider, cameraAngleLabel, cameraAngleSlider,
                        widthLabel, widthSlider, heightLabel, heightSlider,
                        depthLabel, depthSlider, zoomLabel, zoomSlider);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        launch(args);
        System.out.println(System.getProperties());
    }

}
