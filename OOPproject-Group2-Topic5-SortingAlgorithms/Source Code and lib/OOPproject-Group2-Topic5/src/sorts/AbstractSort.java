package sorts;

import java.util.ArrayList;
import java.util.Collections;

import components.node.ColNode;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public abstract class AbstractSort {
    public static int SELECTION = 1; 
    
    public final static int COL_COUNT = 20;
    public final static int WINDOW_W = 1000;
    public final static int WINDOW_H = 700;
    public final static double COL_W = WINDOW_W/(COL_COUNT+1);

    public final static Color NODE_COLOR = Color.rgb(255, 212, 110);
    public final static Color SELECT_COLOR = Color.rgb(255, 178, 138);
    public final static Color SORTED_COLOR = Color.rgb(249, 142, 113);
    public final static Color PIVOT_COLOR = Color.rgb(0, 184, 222);

    //Functions to color nodes
    ParallelTransition colorNodes(ArrayList<ColNode> list, Color color, int...a) {
        ParallelTransition pt = new ParallelTransition();
        for (int i=0;i<a.length;i++) {
          FillTransition ft = new FillTransition();
          ft.setShape(list.get(a[i]));
          ft.setToValue(color);
          ft.setDuration(Duration.millis(100));
          pt.getChildren().add(ft);
        }
        return pt;
    }
    ParallelTransition colorNodes(ArrayList<ColNode> list, Color color) {
      ParallelTransition pt = new ParallelTransition();
      for (ColNode c : list) {
          FillTransition ft = new FillTransition();
          ft.setShape(c);
          ft.setToValue(color);
          ft.setDuration(Duration.millis(100));
          pt.getChildren().add(ft);
      }
      return pt;
    }

    //Tested
    ParallelTransition swap(ArrayList<ColNode> list, int i, int j) {
        ParallelTransition pt = new ParallelTransition();
    
        int dxFactor = j - i;
    
        TranslateTransition t = new TranslateTransition();
        t.setNode(list.get(i));
        t.setDuration(Duration.millis(100));
        t.setByX((COL_W + 1) * dxFactor);

        TranslateTransition s = new TranslateTransition();
        s.setNode(list.get(j));
        s.setDuration(Duration.millis(100));
        s.setByX((-COL_W - 1) * dxFactor );

        pt.getChildren().addAll(t,s);

        Collections.swap(list, i, j);

        return pt;
      }
    public abstract ArrayList<Transition> run(ArrayList<ColNode> nodes);
}
