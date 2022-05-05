package components.array;

import components.node.Node;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
public class NodesList {
    public static ArrayList<Node> nodesList = new ArrayList<>();
    //  input for radix sort
//  public int[] input = {13,438, 29, 745, 695, 62, 732, 388, 428, 367};
//  input for bucket sort
    public static int[] input = {11, 21, 61, 99, 97, 78, 92, 65, 82, 31};

    public static void addNode(){
        for(int i : input){
            nodesList.add(new Node(i));
        }
    }

    public static void displayNodesList(HBox hbox){
        int count = 0;
        for(Node n : nodesList){
            hbox.getChildren().add(count,n);
            count++;
        }
    }

    public static void removeNumber(HBox hbox, int index){
        hbox.getChildren().remove(index);
        hbox.getChildren().add(new Node());
    }
}
