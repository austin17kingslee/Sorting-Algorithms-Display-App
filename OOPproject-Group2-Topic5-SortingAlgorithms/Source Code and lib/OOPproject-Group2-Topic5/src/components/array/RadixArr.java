package components.array;

import components.array.NodesList;
import components.node.Node;
import javafx.scene.paint.Color;

public class RadixArr extends NodesList {
    public static int[] inputPhase1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] inputPhase2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] inputPhase3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public final static Integer[] counter = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public final static int MAX = 5;


    public static void addNode(){
        random();
        for (int i=0; i<10; i++){
            nodesList.add(new Node(input[i], Color.AQUAMARINE));
        }
    }


    public static int getRandomNumber() {
        final int min = 0;
        final int max = 999;
        return (int) ((Math.random() * (max - min)) + min);
    }


    public static void random(){
        for (int i = 0; i <= 9; i++) {
            input[i] = getRandomNumber();
            int divtest = input[i]/100;
            counter[divtest]++;
            if (counter[divtest] > MAX){
                do {
                    input[i] = getRandomNumber();
                } while (input[i] == divtest);
            }
        }
    }

}
