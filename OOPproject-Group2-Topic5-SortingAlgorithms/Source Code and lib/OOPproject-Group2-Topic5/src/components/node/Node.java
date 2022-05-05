package components.node;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
public class Node extends StackPane {
    public Rectangle rec = new Rectangle();
    public Text text = new Text();

    public Node(int id){
        rec.setHeight(60);
        rec.setWidth(60);
        rec.setFill(Color.AQUAMARINE);

        text.setText(Integer.toString(id));
        text.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 20));
        this.getChildren().addAll(rec,text);
    }

    public Node(){
        rec.setWidth(60);
        rec.setHeight(60);
        rec.setFill(Color.AQUAMARINE);
        this.getChildren().add(rec);
    }
    public Node(int id, Color color){
        rec.setHeight(60);
        rec.setWidth(60);
        rec.setFill(color);
        text.setText(Integer.toString(id));
        text.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 20));
        this.getChildren().addAll(rec,text);
    }
    public Node(Color color){
        rec.setWidth(60);
        rec.setHeight(60);
        rec.setFill(color);
        this.getChildren().add(rec);
    }
}
