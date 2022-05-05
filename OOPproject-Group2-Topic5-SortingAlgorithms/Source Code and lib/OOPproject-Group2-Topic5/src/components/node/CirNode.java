package components.node;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CirNode extends StackPane {
    public Integer id;
    public double radius;
    public int index;

    public CirNode(int id, Color color_1, Color color_2){
        this.id = id;
        Circle cir = new Circle();
        cir.setRadius(28);
        radius = cir.getRadius();
        Text text = new Text();
        cir.setFill(color_1);
        text.setText(Integer.toString(id));
        text.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 20));
        text.setFill(color_2);
        this.getChildren().addAll(cir, text);
    }
    public double getCenterOfCirNode_X(){
        double X;
        X = this.getLayoutX()+this.radius;
        return X;
    }
    public double getCenterOfCirNode_Y(){
        double Y;
        Y = this.getLayoutY()+this.radius;
        return Y;
    }
}
