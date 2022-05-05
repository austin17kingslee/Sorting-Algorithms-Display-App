package components.array;

import components.node.Node;
import components.array.NodesList;

import javafx.scene.paint.Color;

public class BucketArr extends NodesList {
    public final static Integer[] counter = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public final static int MAX = 5;

    public static void addNode(){
        random();
        for(int i : input){
            nodesList.add(new Node(i, Color.WHITE));
        }
    }

    public static int getRandomNumber() {
        final int min = 0;
        final int max = 99;
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void random(){
        for (int i = 0; i <= 9; i++) {
            input[i] = getRandomNumber();
            int divtest = input[i]/10;
            counter[divtest]++;
            if (counter[divtest] > MAX){
                do {
                    input[i] = getRandomNumber();
                } while (input[i] == divtest);
            }
        }
    }
}
