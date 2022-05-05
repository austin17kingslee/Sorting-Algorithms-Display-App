package scenes.bucket;

import components.array.BucketArr;
import components.node.Node;
import sorts.*;

import javafx.animation.ParallelTransition;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import components.array.NodesList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class BucketSortGUI{
    BucketArr bucketArr = new BucketArr();
    int[] bucketList = {0,10,20,30,40,50,60,70,80,90};
    Vector<Integer>[] buckets = new Vector[10];

    Button button_1 = new Button ("Back to menu");
    Button button_5 = new Button("Random");
    Button button_2 = new Button ("Start Sorting");
    Button button_3 = new Button("Pause");
    Button button_4 = new Button("Finish");
    private boolean isStop = false;

    private final List<Button> buttons = new ArrayList<>();
    HBox hbox = new HBox();
    GridPane gridPane =new GridPane();

    public Parent createScene(){
        ParallelTransition t = new ParallelTransition();
        buttons.add(button_1);
        buttons.add(button_2);
        buttons.add(button_3);
        buttons.add(button_4);
        buttons.add(button_5);
        for (Button i : buttons) {
            i.setStyle("-fx-background-color: aquamarine; -fx-text-fill: black;");
            i.setPrefSize(100, 30);
        }
        Rectangle rec_1 = new Rectangle(800.0/5, 80);
        rec_1.setFill(Color.WHITE);
        Rectangle rec_2 = new Rectangle(800.0/5, 80);
        rec_2.setFill(Color.WHITE);
        Rectangle rec_3 = new Rectangle(800.0/5, 80);
        rec_3.setFill(Color.WHITE);
        Rectangle rec_4 = new Rectangle(800.0/5, 80);
        rec_4.setFill(Color.WHITE);
        Rectangle rec_5 = new Rectangle(800.0/5, 80);
        rec_5.setFill(Color.WHITE);

        StackPane buttonContainer_1 = new StackPane();
        StackPane buttonContainer_2 = new StackPane();
        StackPane buttonContainer_3 = new StackPane();
        StackPane buttonContainer_4 = new StackPane();
        StackPane buttonContainer_5 = new StackPane();

        buttonContainer_1.getChildren().addAll(rec_1, button_1);
        buttonContainer_2.getChildren().addAll(rec_2, button_2);
        buttonContainer_3.getChildren().addAll(rec_3, button_3);
        buttonContainer_4.getChildren().addAll(rec_4, button_4);
        buttonContainer_5.getChildren().addAll(rec_5, button_5);
        HBox bottom = new HBox();
        bottom.getChildren().addAll(buttonContainer_1, buttonContainer_5, buttonContainer_2, buttonContainer_3, buttonContainer_4);

        gridPane.setPadding(new Insets(8));
        gridPane.setVgap(8);
        gridPane.setHgap(8);
        gridPane.setLayoutX(200);
        hbox.setSpacing(8);

        Rectangle top = new Rectangle();
        top.setFill(Color.WHITE);
        top.setHeight(50);
        top.setWidth(750);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(top, gridPane);
        StackPane middle = new StackPane();
        Rectangle middle_background = new Rectangle();
        middle_background.setFill(Color.WHITE);
        middle_background.setWidth(760);
        middle_background.setHeight(580);
        middle.getChildren().addAll(middle_background, vbox);

        Rectangle left = new Rectangle();
        left.setFill(Color.WHITE);
        left.setHeight(580);
        left.setWidth(40);

        for(int i = 0; i < 10;i++){
            Node node = new Node(bucketList[i], Color.CORNFLOWERBLUE);
            node.text.setFill(Color.YELLOW);
            gridPane.add(node,i+1,2);
            for(int j = 3; j < 8;j ++){
                Node bNode =new Node(Color.AQUAMARINE);
                gridPane.add(bNode,i+1,j);
            }
        }

        button_3.setDisable(true);

        button_1.setOnAction(e -> {
            t.stop();
            NodesList.nodesList.clear();

            try{
                Parent root = FXMLLoader.load(getClass().getResource("/main-menu.fxml"));
                Stage stage;
                Scene mainScene = new Scene(root, 700, 400);
                stage = (Stage) button_1.getScene().getWindow();
                stage.setScene(mainScene);
                stage.show();
            }catch(IOException ex){
                System.out.println("IOException occurred.");
            }
        });

        button_2.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                t.getChildren().clear();
                BucketSort.counter = 0;
                BucketSort.bucketSort(t, buckets, gridPane, 0.5);
                t.setCycleCount(1);
                t.play();
                t.setOnFinished(e -> {
                    button_5.setDisable(false);
                    button_3.setDisable(true);
                });
                button_2.setDisable(true);
                button_3.setDisable(false);
                button_4.setDisable(false);
                button_5.setDisable(true);
            }
        });

        button_3.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (!isStop) {
                    t.pause();
                    button_3.setText("Continue");
                    isStop = true;

                } else {
                    t.play();
                    isStop = false;
                    button_3.setText("Pause");
                }
            }
        });
        button_4.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                BucketSort.counter = 0;
                t.stop();
                BucketSort.onFinished(gridPane);
                button_4.setDisable(true);
                button_3.setText("Pause");
                button_3.setDisable(true);
                button_2.setDisable(true);
                button_5.setDisable(false);
            }
        });
        button_5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BucketSort.counter = 0;
                button_3.setDisable(true);
                BucketSort.randomize(hbox, gridPane);
                button_4.setDisable(false);
                if (isStop){
                    isStop = false;
                    button_3.setText("Pause");
                }
                else button_2.setDisable(false);
            }
        });

        BucketArr.addNode();
        BucketArr.displayNodesList(hbox);

        gridPane.add(hbox,1,1,10,1); // place at 1 1, span to 10 cols, 1 line

        BorderPane root = new BorderPane();
        root.setCenter(middle);
        root.setBottom(bottom);
        root.setLeft(left);

        return root;
    }
}