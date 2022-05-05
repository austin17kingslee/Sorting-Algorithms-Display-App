package scenes.heap;

import components.array.CirArray;
import components.array.NumbArray;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sorts.HeapSort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HeapSortGUI {
    public static CirArray cirGroup;
    private final Button button_2 = new Button("Start Sorting");
    private final Button button_1 = new Button("Back to menu");
    private final Button button_3 = new Button("Pause");
    private final Button button_4 = new Button("Finish");
    private final Button button_5 = new Button("Random");
    private final List<Button> buttons = new ArrayList<>();
    private boolean isStop = false;

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
        button_3.setDisable(true);

        button_1.setOnAction(e -> {
            t.stop();
            NumbArray.arrayGUI.getChildren().clear();
            NumbArray.subSubContainers.getChildren().clear();
            NumbArray.subContainers.getChildren().clear();
            NumbArray.containers.getChildren().clear();
            NumbArray.elements.clear();
                try{
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main-menu.fxml")));
                Stage stage;
                Scene mainScene = new Scene(root, 700, 400);
                stage = (Stage) button_1.getScene().getWindow();
                stage.setScene(mainScene);
                stage.show();
                }catch(IOException ex){
                    System.out.println("IOException occurred.");
                }
        });

        button_2.setOnAction(e -> {
            t.getChildren().clear();
            HeapSort.counter = 0;
            HeapSort.heapSort(t, cirGroup, 0.4);
            t.setCycleCount(1);
            t.play();
            t.setOnFinished(event -> {
                button_5.setDisable(false);
                button_3.setDisable(true);
            });
            button_2.setDisable(true);
            button_3.setDisable(false);
            button_4.setDisable(false);
            button_5.setDisable(true);
        });

        button_3.setOnAction(e -> {
            if (!isStop) {
                t.pause();
                button_3.setText("Continue");
                isStop = true;

            } else {
                t.play();
                isStop = false;
                button_3.setText("Pause");
            }
        });

        button_5.setOnAction(e -> {
            button_3.setDisable(true);
            HeapSort.randomize();
            button_4.setDisable(false);
            if (isStop) {
                isStop = false;
                button_3.setText("Pause");
            } else button_2.setDisable(false);
        });

        button_4.setOnAction(e -> {
            HeapSort.counter = 0;
            t.stop();
            HeapSort.onFinished();
            button_4.setDisable(true);
            isStop = false;
            button_3.setText("Pause");
            button_3.setDisable(true);
            button_2.setDisable(true);
            button_5.setDisable(false);
        });

        cirGroup = new CirArray();
        cirGroup.addCir();
        NumbArray.displayNumbArray(cirGroup);
        Pane pane = new Pane();
        StackPane stack = new StackPane();
        cirGroup.displayHeap(pane);

        stack.getChildren().addAll(NumbArray.containers, pane);

        Rectangle rec_1 = new Rectangle(800.0 / 5, 90);
        rec_1.setFill(Color.WHITE);
        Rectangle rec_2 = new Rectangle(800.0 / 5, 90);
        rec_2.setFill(Color.WHITE);
        Rectangle rec_3 = new Rectangle(800.0 / 5, 90);
        rec_3.setFill(Color.WHITE);
        Rectangle rec_4 = new Rectangle(800.0 / 5, 90);
        rec_4.setFill(Color.WHITE);
        Rectangle rec_5 = new Rectangle(800.0 / 5, 90);
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

        //Root program
        BorderPane root = new BorderPane();
        root.setBottom(bottom);
        root.setCenter(stack);

        return root;
    }


}
