package components.array;

import components.array.CirArray;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class NumbArray {
    public static GridPane arrayGUI = new GridPane();
    public static VBox containers = new VBox();
    public static HBox subSubContainers = new HBox();
    public static StackPane subContainers = new StackPane();
    public static Integer[] ids ={0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static ArrayList<StackPane> elements = new ArrayList<>();
    public static void displayNumbArray(CirArray cir){
        Rectangle rec1 = new Rectangle();
        rec1.setHeight(460);
        rec1.setWidth(800);
        rec1.setFill(Color.WHITE);
        Rectangle rec2 = new Rectangle();
        rec2.setWidth(150);
        rec2.setHeight(50);
        rec2.setFill(Color.WHITE);
        Rectangle rec3 = new Rectangle();
        rec3.setHeight(50);
        rec3.setWidth(800);
        rec3.setFill(Color.WHITE);

        for (int i=0; i<10; i++) {
            ids[i]=(cir.cirArray.get(i).id);
            StackPane element = new StackPane();
            element.setMinHeight(50);
            element.setMinWidth(50);
            Text text = new Text(Integer.toString(ids[i]));
            text.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 17));
            element.getChildren().add(text);
            elements.add(element);
        }
        int count = 0;
        arrayGUI.setGridLinesVisible(false);
        for (StackPane i : elements) {
            arrayGUI.add(i, count, 1);
            count++;
        }
        subSubContainers.getChildren().addAll(rec2, arrayGUI);
        subContainers.getChildren().addAll(rec3, subSubContainers);
        containers.getChildren().addAll(rec1, subContainers);
    }
}
