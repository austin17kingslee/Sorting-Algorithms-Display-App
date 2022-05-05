package components.array;

import components.node.CirNode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
//import sample.Controller;

import java.util.ArrayList;

public class CirArray {
        private final ArrayList<Line> lines = new ArrayList<>();
        public ArrayList<CirNode> cirArray = new ArrayList<>();
    
        public void addCir() {
            for (int i = 0; i < 10; i++) {
                CirNode cir = new CirNode(getRandomNumber(), Color.AQUAMARINE, Color.BLACK);
                cirArray.add(cir);
                cir.index = cirArray.indexOf(cir);
            }
        }

    public static int getRandomNumber() {
        final int min = 0;
        final int max = 999;
        return (int) ((Math.random() * (max - min)) + min);
    }

        public void displayHeap(Pane pane) {
            Rectangle rec = new Rectangle();
            rec.setWidth(800);
            rec.setHeight(600);
    
            this.cirArray.get(0).setLayoutX(rec.getLayoutX()+rec.getWidth()/2);
            this.cirArray.get(0).setLayoutY(rec.getLayoutY()+50);
    
            this.cirArray.get(1).setLayoutX(rec.getLayoutX()+rec.getWidth()/4);
            this.cirArray.get(1).setLayoutY(rec.getLayoutY()+150);
    
            this.cirArray.get(2).setLayoutX(rec.getLayoutX()+rec.getWidth()*3/4);
            this.cirArray.get(2).setLayoutY(rec.getLayoutY()+150);
    
            this.cirArray.get(3).setLayoutX(rec.getLayoutX()+rec.getWidth()/8);
            this.cirArray.get(3).setLayoutY(rec.getLayoutY()+250);
    
            this.cirArray.get(4).setLayoutX(rec.getLayoutX()+rec.getWidth()*3/8);
            this.cirArray.get(4).setLayoutY(rec.getLayoutY()+250);
    
            this.cirArray.get(5).setLayoutX(rec.getLayoutX()+rec.getWidth()*5/8);
            this.cirArray.get(5).setLayoutY(rec.getLayoutY()+250);
    
            this.cirArray.get(6).setLayoutX(rec.getLayoutX()+rec.getWidth()*7/8);
            this.cirArray.get(6).setLayoutY(rec.getLayoutY()+250);
    
            this.cirArray.get(7).setLayoutX(rec.getLayoutX()+rec.getWidth()/16);
            this.cirArray.get(7).setLayoutY(rec.getLayoutY()+350);
    
            this.cirArray.get(8).setLayoutX(rec.getLayoutX()+rec.getWidth()*3/16);
            this.cirArray.get(8).setLayoutY(rec.getLayoutY()+350);
    
            this.cirArray.get(9).setLayoutX(rec.getLayoutX()+rec.getWidth()*5/16);
            this.cirArray.get(9).setLayoutY(rec.getLayoutY()+350);
    
            Line line0_1 = new Line(this.cirArray.get(0).getCenterOfCirNode_X(), this.cirArray.get(0).getCenterOfCirNode_Y(),
                    this.cirArray.get(1).getCenterOfCirNode_X(), this.cirArray.get(1).getCenterOfCirNode_Y());
            lines.add(line0_1);
    
            Line line0_2 = new Line(this.cirArray.get(0).getCenterOfCirNode_X(), this.cirArray.get(0).getCenterOfCirNode_Y(),
                    this.cirArray.get(2).getCenterOfCirNode_X(), this.cirArray.get(2).getCenterOfCirNode_Y());
            lines.add(line0_2);
    
            Line line1_3 = new Line(this.cirArray.get(1).getCenterOfCirNode_X(), this.cirArray.get(1).getCenterOfCirNode_Y(),
                    this.cirArray.get(3).getCenterOfCirNode_X(), this.cirArray.get(3).getCenterOfCirNode_Y());
            lines.add(line1_3);
    
            Line line1_4 = new Line(this.cirArray.get(1).getCenterOfCirNode_X(), this.cirArray.get(1).getCenterOfCirNode_Y(),
                    this.cirArray.get(4).getCenterOfCirNode_X(), this.cirArray.get(4).getCenterOfCirNode_Y());
            lines.add(line1_4);
    
            Line line2_5 = new Line(this.cirArray.get(2).getCenterOfCirNode_X(), this.cirArray.get(2).getCenterOfCirNode_Y(),
                    this.cirArray.get(5).getCenterOfCirNode_X(), this.cirArray.get(5).getCenterOfCirNode_Y());
            lines.add(line2_5);
    
            Line line2_6 = new Line(this.cirArray.get(2).getCenterOfCirNode_X(), this.cirArray.get(2).getCenterOfCirNode_Y(),
                    this.cirArray.get(6).getCenterOfCirNode_X(), this.cirArray.get(6).getCenterOfCirNode_Y());
            lines.add(line2_6);
    
            Line line3_7 = new Line(this.cirArray.get(3).getCenterOfCirNode_X(), this.cirArray.get(3).getCenterOfCirNode_Y(),
                    this.cirArray.get(7).getCenterOfCirNode_X(), this.cirArray.get(7).getCenterOfCirNode_Y());
            lines.add(line3_7);
    
            Line line3_8 = new Line(this.cirArray.get(3).getCenterOfCirNode_X(), this.cirArray.get(3).getCenterOfCirNode_Y(),
                    this.cirArray.get(8).getCenterOfCirNode_X(), this.cirArray.get(8).getCenterOfCirNode_Y());
            lines.add(line3_8);
    
            Line line4_9 = new Line(this.cirArray.get(4).getCenterOfCirNode_X(), this.cirArray.get(4).getCenterOfCirNode_Y(),
                    this.cirArray.get(9).getCenterOfCirNode_X(), this.cirArray.get(9).getCenterOfCirNode_Y());
            lines.add(line4_9);
            for (Line i : lines){
                i.setStrokeWidth(4.5);
            }
            pane.getChildren().addAll(lines);
            pane.getChildren().addAll(cirArray);
        }
}
