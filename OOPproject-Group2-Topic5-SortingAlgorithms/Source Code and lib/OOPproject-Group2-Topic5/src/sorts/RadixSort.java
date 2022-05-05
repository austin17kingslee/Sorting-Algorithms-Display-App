package sorts;

import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import components.array.RadixArr;
import components.node.Node;

import java.util.Arrays;
import java.util.Vector;

public class RadixSort {
    public static double counter = 0;
    public static int loop = 1;
    public static Timeline timeline = new Timeline();
    public static void backup(){
        System.arraycopy(RadixArr.inputPhase1, 0, RadixArr.input, 0, 10);
    }
    public static void phase(int exp) {

        Vector<Integer>[] digits = new Vector[10];
        for (int i = 0; i < 10; i++) {
            digits[i] = new Vector<>();
        }
        for (int i = 0; i < RadixArr.input.length; i++) {
            int idx = (RadixArr.input[i] / exp) % 10;
            digits[idx].add(RadixArr.input[i]);
        }
        // merge each bucket into initial array
        int index = 0;
        for (int i = 0; i < 10; i++) {
            if (!digits[i].isEmpty()) {
                for (int j : digits[i]) {
                    RadixArr.input[index] = j;
                    index++;
                }
            }
        }
    }
    public static void createPhase1(){
        System.arraycopy(RadixArr.input, 0, RadixArr.inputPhase1, 0, 10);
    }
    public static void createPhase2(){
        phase(1);
        System.arraycopy(RadixArr.input, 0, RadixArr.inputPhase2, 0, 10);
    }

    public static void createPhase3(){
        phase(10);
        System.arraycopy(RadixArr.input, 0, RadixArr.inputPhase3, 0, 10);
    }

    public static void considerElement(ParallelTransition t,Node element, double timeJump){
        counter += timeJump;

        if (loop == 1) {
            Node temp = new Node(RadixArr.inputPhase1[RadixArr.nodesList.indexOf(element)], Color.CRIMSON);
            temp.text.setFill(Color.WHITE);
            timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> element.getChildren().add(temp)));
            timeline.setCycleCount(1);
            t.getChildren().add(timeline);
        }
        else if (loop == 2){
            Node temp = new Node(RadixArr.inputPhase2[RadixArr.nodesList.indexOf(element)], Color.CRIMSON);
            temp.text.setFill(Color.WHITE);
            timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> element.getChildren().add(temp)));
            timeline.setCycleCount(1);
            t.getChildren().add(timeline);
        }
        else if (loop == 3){
            Node temp = new Node(RadixArr.inputPhase3[RadixArr.nodesList.indexOf(element)], Color.CRIMSON);
            temp.text.setFill(Color.WHITE);
            timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> element.getChildren().add(temp)));
            timeline.setCycleCount(1);
            t.getChildren().add(timeline);
        }
    }
    public static void considerRadixPart(ParallelTransition t, Node element, int index, double timeJump){
        int id = 0;
        counter += timeJump;
        if (loop == 1){
            id = RadixArr.inputPhase1[RadixArr.nodesList.indexOf(element)];
        }
        else if (loop == 2){
            id = RadixArr.inputPhase2[RadixArr.nodesList.indexOf(element)];
        }
        else if (loop == 3){
            id = RadixArr.inputPhase3[RadixArr.nodesList.indexOf(element)];
        }
        Node left = new Node(id, Color.AQUAMARINE);
        Node mid = new Node(id, Color.AQUAMARINE);
        Node right = new Node(id, Color.AQUAMARINE);

        left.rec.setWidth(element.rec.getWidth()/3);
        mid.rec.setWidth(element.rec.getWidth()/3);
        right.rec.setWidth(element.rec.getWidth()/3);

        left.text.setText(Integer.toString(id/100));
        mid.text.setText(Integer.toString(id/10 - (id/100)*10));
        right.text.setText(Integer.toString(id%10));

        HBox container = new HBox();
        container.getChildren().addAll(left, mid, right);
        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> {
            element.getChildren().clear();
            element.getChildren().add(container);
        }));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);

        counter += timeJump;
        if (index == 1){
            timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> {
                left.text.setFill(Color.WHITE);
                left.rec.setFill(Color.RED);
            }));
            timeline.setCycleCount(1);
            t.getChildren().add(timeline);
        }
        else if (index == 2){
            timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> {
                mid.text.setFill(Color.WHITE);
                mid.rec.setFill(Color.RED);
            }));
            timeline.setCycleCount(1);
            t.getChildren().add(timeline);
        }
        else if (index == 3){
            timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> {
                right.text.setFill(Color.WHITE);
                right.rec.setFill(Color.RED);
            }));
            timeline.setCycleCount(1);
            t.getChildren().add(timeline);
        }

    }
    public static void elementDisappear(ParallelTransition t, Node element, double timeJump){
        counter += timeJump;
        Node temp = new Node(Color.WHITE);
        counter += timeJump;
        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> {
            element.getChildren().clear();
            element.getChildren().add(temp);
        }));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
    }

    public static void moveToBucket(ParallelTransition t, GridPane gridPane, Node element, int b, int c){
        if (loop == 1) {
            timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> gridPane.add(new Node(RadixArr.inputPhase1[RadixArr.nodesList.indexOf(element)], Color.AQUAMARINE), b, c)));
            timeline.setCycleCount(1);
            t.getChildren().add(timeline);
        }
        else if (loop == 2){
            timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> gridPane.add(new Node(RadixArr.inputPhase2[RadixArr.nodesList.indexOf(element)], Color.AQUAMARINE), b, c)));
            timeline.setCycleCount(1);
            t.getChildren().add(timeline);
        }
        else if (loop == 3){
            timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> gridPane.add(new Node(RadixArr.inputPhase3[RadixArr.nodesList.indexOf(element)], Color.AQUAMARINE), b, c)));
            timeline.setCycleCount(1);
            t.getChildren().add(timeline);
        }

    }

    public static void mergeBucketBackToArray(ParallelTransition t, Node element, GridPane gridPane, int j, int i, int index, int count, double timeJump){
        counter += timeJump;
        Node temp = new Node(j, Color.AQUAMARINE);
        temp.text.setFill(Color.BLACK);
        //temp.rec.setFill(Color.LIGHTGREEN);
        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> {
            element.getChildren().add(temp.rec);
            element.getChildren().add(temp.text);
            gridPane.add(new Node(Color.AQUAMARINE),i+1,count);
        }));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
        RadixArr.input[index] = j;
    }
    public static int getMax(int[] arr){
        int mx = arr[0];
        for(int i : arr){
            mx = Math.max(i, mx);
        }
        return mx;
    }
    public static void countSort(ParallelTransition t,Vector<Integer>[] digits, GridPane gridPane, int exp, int index, double timeJump){
        initialize(t, digits, gridPane, exp, timeJump);
        merge(t, digits, gridPane, index, timeJump);
    }
    public static void initialize(ParallelTransition t, Vector<Integer>[] digits, GridPane gridPane, int exp, double timeJump) {
        for (int i = 0; i < 10; i++) {
            digits[i] = new Vector<>();
        }
        int radixPart;
        for (int i = 0; i < RadixArr.input.length; i++) {
            int idx = (RadixArr.input[i] / exp) % 10;
            if (exp < 10) radixPart = 3;
            else if (exp < 100) radixPart = 2;
            else radixPart = 1;

            digits[idx].add(RadixArr.input[i]);
            considerElement(t, RadixArr.nodesList.get(i), timeJump);
            considerRadixPart(t, RadixArr.nodesList.get(i), radixPart, timeJump);
            elementDisappear(t, RadixArr.nodesList.get(i), timeJump);
            moveToBucket(t, gridPane, RadixArr.nodesList.get(i), idx + 1, digits[idx].size() + 2);
        }
    }
    public static void merge(ParallelTransition t, Vector<Integer>[] digits, GridPane gridPane, int index, double timeJump){
        for(int i = 0; i < 10; i++){
            if(!digits[i].isEmpty()){
                int count = 3;
                for(int j : digits[i]){
                    mergeBucketBackToArray(t, RadixArr.nodesList.get(index), gridPane, j, i, index,  count, timeJump);
                    count++;
                    index++;
                }
            }
        }
    }
    public static void radixSort(ParallelTransition t,Vector<Integer>[] digits, GridPane gridPane, int index, double timeJump){
        int mx = getMax(RadixArr.input);
        for(int exp = 1; mx/exp > 0; exp *=10){
            countSort(t,digits, gridPane,  exp, index, timeJump);
            loop++;
        }
    }
    public static void randomize(HBox hbox, GridPane gridPane){
        hbox.getChildren().clear();
        RadixArr.nodesList.clear();
        RadixArr.addNode();
        RadixArr.displayNodesList(hbox);
        RadixSort.createPhase1();
        RadixSort.createPhase2();
        RadixSort.createPhase3();
        RadixSort.backup();
    }
    public static void onFinished(GridPane gridPane){
        Arrays.sort(RadixArr.input);
        for (int i=0; i<=9; i++){
            RadixArr.nodesList.get(i).getChildren().add(new Node(RadixArr.input[i], Color.LIGHTGREEN));
        }
        for(int i = 0; i < 10;i++){
            for(int j = 3; j < 8;j ++){
                Node bNode =new Node(Color.AQUAMARINE);
                gridPane.add(bNode,i+1,j);
            }
        }
    }
}
