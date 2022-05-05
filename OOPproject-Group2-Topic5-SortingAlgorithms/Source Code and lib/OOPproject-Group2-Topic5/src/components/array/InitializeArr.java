package components.array;

import components.node.ColNode;
import sorts.AbstractSort;
import java.util.ArrayList;
import javafx.scene.paint.Paint;

public class InitializeArr {
    public static ArrayList<ColNode> initArr(int n){
        ArrayList<ColNode> arr = new ArrayList<ColNode>();

        for(int i=0;i<n;i++){
            double RNGesus = Math.random();
            
            ColNode tmp = new ColNode();
            tmp.setStroke(Paint.valueOf("#fefedf"));
            tmp.setStrokeWidth(1);
            tmp.setWidth(AbstractSort.COL_W);
            tmp.setHeight(RNGesus*450 + 50);
            tmp.setFill(Paint.valueOf("#ffd46e"));
            arr.add(tmp);
        }

        return arr;
    }
}
