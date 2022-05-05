package scenes.radix;

import javafx.animation.ParallelTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sorts.RadixSort;
import components.node.Node;
import components.array.NodesList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RadixSortGUI{
    int[] digitsList = {0,1,2,3,4,5,6,7,8,9};
    Vector<Integer>[] digits = new Vector[10];
    HBox hbx = new HBox();
    GridPane gridPane = new GridPane();
    Button button_1 = new Button ("Back to menu");
    Button button_5 = new Button("Random");
    Button button_2 = new Button ("Start Sorting");
    Button button_3 = new Button("Pause");
    Button button_4 = new Button("Finish");
    private boolean isStop = false;
    private final List<Button> buttons = new ArrayList<>();

    public int getMax(int[] arr){
        int mx = arr[0];
        for(int i : arr){
            mx = Math.max(i, mx);
        }
        return mx;
    }

    public Parent createScene(){

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
        Rectangle top = new Rectangle();
        top.setFill(Color.WHITE);
        top.setHeight(50);
        top.setWidth(750);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(top, gridPane);
        StackPane middle = new StackPane();
        Rectangle middle_backgournd = new Rectangle();
        middle_backgournd.setFill(Color.WHITE);
        middle_backgournd.setWidth(760);
        middle_backgournd.setHeight(580);
        middle.getChildren().addAll(middle_backgournd, vbox);

        Rectangle left = new Rectangle();
        left.setFill(Color.WHITE);
        left.setHeight(580);
        left.setWidth(40);

        NodesList.addNode();
        NodesList.displayNodesList(hbx);

        RadixSort.createPhase1();
        RadixSort.createPhase2();
        RadixSort.createPhase3();
        RadixSort.backup();

        ParallelTransition t = new ParallelTransition();

        gridPane.setPadding(new Insets(8));
        gridPane.setVgap(8);
        gridPane.setHgap(8);
        hbx.setSpacing(8);

        gridPane.add(hbx,1,1,10,1);
        for(int i = 0; i< 10;i++){
            Node node= new Node(digitsList[i], Color.CORNFLOWERBLUE);
            node.text.setFill(Color.YELLOW);
            gridPane.add(node,i+1,2);
            for(int j = 3; j < 8; j++){
                Node bNode = new Node(Color.AQUAMARINE);
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
                RadixSort.loop = 1;
                t.getChildren().clear();
                RadixSort.counter = 0;
                RadixSort.radixSort(t, digits, gridPane,0, 0.3);
                t.setCycleCount(1);
                t.play();
                t.setOnFinished(e -> {
                    button_5.setDisable(false);
                    button_3.setDisable(true);
                    for (Node i : NodesList.nodesList){
                        i.getChildren().clear();
                        i.getChildren().add(new Node(NodesList.input[NodesList.nodesList.indexOf(i)],Color.LIGHTGREEN ));
                    }
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
                RadixSort.counter = 0;
                t.stop();
                RadixSort.onFinished(gridPane);
                button_4.setDisable(true);
                button_3.setText("Pause");
                button_3.setDisable(true);
                button_2.setDisable(true);
                button_5.setDisable(false);
            }
        });
        button_5.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                RadixSort.counter = 0;
                button_3.setDisable(true);
                RadixSort.randomize(hbx, gridPane);
                button_4.setDisable(false);
                if (isStop){
                    isStop = false;
                    button_3.setText("Pause");
                }
                else button_2.setDisable(false);
            }
        });

        BorderPane root = new BorderPane();
        root.setCenter(middle);
        root.setBottom(bottom);
        root.setLeft(left);

        return root;
    }
}