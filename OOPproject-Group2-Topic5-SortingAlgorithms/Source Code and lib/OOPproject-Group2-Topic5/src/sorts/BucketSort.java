package sorts;

import components.array.BucketArr;
import components.node.Node;

import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class BucketSort {
    public static double counter = 0;
    public static Timeline timeline = new Timeline();
    public static void considerElement(ParallelTransition t, Node element, double timeJump){
        counter += timeJump;
        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> {
            element.rec.setFill(Color.RED);
            element.text.setFill(Color.WHITE);
        }));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);

        counter += timeJump;
        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> {
            element.getChildren().remove(element.text);
            element.rec.setFill(Color.WHITE);
        }));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);

    }
    public static void moveElementToBucket(ParallelTransition t, GridPane gridPane, int a, int b, int c){
        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> gridPane.add(new Node(BucketArr.input[a], Color.AQUAMARINE),b,c)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);

    }
    public static void sortElementInBucket(ParallelTransition t, GridPane gridPane, int j, int i, int count, double timeJump){
        counter += timeJump;
        Node bkn = new Node(j, Color.LIGHTGREEN);
        bkn.text.setFill(Color.BLACK);
        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> gridPane.add(bkn,i+1, count)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
    }
    public static void mergeBucketsBackToArray(ParallelTransition t, Node element, GridPane gridPane, int j, int i, int count, double timeJump){
        counter += timeJump;
        Node temp = new Node(j, Color.LIGHTGREEN);
        temp.text.setFill(Color.BLACK);
        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> {
            element.getChildren().add(temp.rec);
            element.getChildren().add(temp.text);
            gridPane.add(new Node(Color.AQUAMARINE),i+1,count);
        }));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
    }

    public static void bucketSort(ParallelTransition t, Vector<Integer>[] buckets, GridPane gridPane, double timeJump){
        initializeBuckets(t, buckets, gridPane, timeJump);
        sortBuckets(t, buckets, gridPane, timeJump);
        mergeBuckets(t, buckets, gridPane, timeJump);
    }
    public static void initializeBuckets(ParallelTransition t, Vector<Integer>[] buckets, GridPane gridPane, double timeJump){
        for(int i = 0; i < 10;i++){
            buckets[i] = new Vector<>();
        }
        for(int i = 0; i< BucketArr.input.length;i++){
            considerElement(t, BucketArr.nodesList.get(i), timeJump);
            int idx = BucketArr.input[i]/10;
            buckets[idx].add(BucketArr.input[i]);
            moveElementToBucket(t, gridPane, i, idx+1, buckets[idx].size()+2);
        }
    }
    public static void sortBuckets(ParallelTransition t, Vector<Integer>[] buckets, GridPane gridPane, double timeJump){
        for(int i = 0; i < BucketArr.input.length;i++){
            if(!buckets[i].isEmpty()){
                Collections.sort(buckets[i]);
                int count = 3;
                for(int j : buckets[i]){
                    sortElementInBucket(t, gridPane, j, i, count, timeJump);
                    count++;
                }
            }
        }
    }

    public static void mergeBuckets(ParallelTransition t, Vector<Integer>[] buckets, GridPane gridPane, double timeJump){
        int index = 0;
        for(int i = 0;i < BucketArr.input.length; i++){
            if(!buckets[i].isEmpty()){
                int count = 3;
                for(int j : buckets[i]){
                    mergeBucketsBackToArray(t, BucketArr.nodesList.get(index), gridPane, j, i, count, timeJump);
                    count++;
                    index++;
                }
            }
        }
    }
    public static void randomize(HBox hbox, GridPane gridPane){
        hbox.getChildren().clear();
        BucketArr.nodesList.clear();
        BucketArr.addNode();
        BucketArr.displayNodesList(hbox);
        for(int i = 0; i < 10;i++){
            for(int j = 3; j < 8;j ++){
                Node bNode =new Node(Color.AQUAMARINE);
                gridPane.add(bNode,i+1,j);
            }
        }
    }
    public static void onFinished(GridPane gridPane){
        Arrays.sort(BucketArr.input);
        for (int i=0; i<=9; i++){
            BucketArr.nodesList.get(i).getChildren().add(new Node(BucketArr.input[i], Color.LIGHTGREEN));
        }
        for(int i = 0; i < 10;i++){
            for(int j = 3; j < 8;j ++){
                Node bNode =new Node(Color.AQUAMARINE);
                gridPane.add(bNode,i+1,j);
            }
        }
    }
}
