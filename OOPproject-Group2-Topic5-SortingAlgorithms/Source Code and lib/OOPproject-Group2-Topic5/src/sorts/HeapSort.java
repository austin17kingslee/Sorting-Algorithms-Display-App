//I would love to move this to the sorts package but the HeapSortGUI.cirGroup is not visible
package sorts;

import components.array.CirArray;
import components.array.NumbArray;
import components.node.CirNode;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import scenes.heap.HeapSortGUI;

import java.util.Arrays;

public class HeapSort {
    public static double counter = 0;
    public static boolean isSorted = false;
    public static Timeline timeline = new Timeline();
    public static void considerCirArray(ParallelTransition t, CirNode a, CirNode b, Color colorCir, Color colorText , double timeJump){
        counter += timeJump;
        CirNode a_x = new CirNode(a.id, colorCir, colorText);
        CirNode b_x = new CirNode(b.id, colorCir, colorText);

        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> a.getChildren().add(a_x)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);

        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> b.getChildren().add(b_x)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
        considerNumbArray(t, a, b, colorCir, colorText);
    }

    public static void considerNumbArray(ParallelTransition t, CirNode a, CirNode b, Color colorRec, Color colorText){
        if (a.index < 0 || b.index < 0 || a.index >= 10 || b.index >= 10) {
            return;
        }
        Rectangle rec_1 = new Rectangle();
        rec_1.setHeight(47);
        rec_1.setWidth(47);
        rec_1.setFill(colorRec);

        Rectangle rec_2 = new Rectangle();
        rec_2.setHeight(47);
        rec_2.setWidth(47);
        rec_2.setFill(colorRec);

        Text text_1 = new Text(Integer.toString(NumbArray.ids[a.index]));
        text_1.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 17));
        text_1.setFill(colorText);

        Text text_2 = new Text(Integer.toString(NumbArray.ids[b.index]));
        text_2.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 17));
        text_2.setFill(colorText);

        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> NumbArray.elements.get(a.index).getChildren().addAll(rec_1, text_1)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);

        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> NumbArray.elements.get(b.index).getChildren().addAll(rec_2, text_2)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
    }

    public static void restoreColorNumbArray(ParallelTransition t, CirNode a, CirNode b){
        Rectangle rec_restore_1 = new Rectangle();
        rec_restore_1.setHeight(47.8);
        rec_restore_1.setWidth(47.8);
        rec_restore_1.setFill(Color.WHITE);

        Rectangle rec_restore_2 = new Rectangle();
        rec_restore_2.setHeight(47.8);
        rec_restore_2.setWidth(47.8);
        rec_restore_2.setFill(Color.WHITE);

        Text text_restore_1 = new Text(Integer.toString(NumbArray.ids[a.index]));
        text_restore_1.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 17));
        text_restore_1.setFill(Color.BLACK);

        Text text_restore_2 = new Text(Integer.toString(NumbArray.ids[b.index]));
        text_restore_2.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 17));
        text_restore_2.setFill(Color.BLACK);


        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> NumbArray.elements.get(a.index).getChildren().addAll(rec_restore_1, text_restore_1)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);

        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> NumbArray.elements.get(b.index).getChildren().addAll(rec_restore_2, text_restore_2)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);

    }

    public static void restoreColorCirArray(ParallelTransition t, CirNode a, CirNode b, double timeJump){
        counter += timeJump;
        CirNode a_restore_color = new CirNode(a.id, Color.AQUAMARINE, Color.BLACK);
        CirNode b_restore_color = new CirNode(b.id, Color.AQUAMARINE, Color.BLACK);

        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> a.getChildren().add(a_restore_color)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> b.getChildren().add(b_restore_color)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
        restoreColorNumbArray(t, a, b);

    }

    public static void swapCirArray(ParallelTransition t, CirNode a, CirNode b, double timeJump) {
        swapLogic(a, b);
        counter += timeJump;
        CirNode a_swap = new CirNode(a.id, Color.CORNFLOWERBLUE, Color.YELLOW);
        CirNode b_swap = new CirNode(b.id, Color.CORNFLOWERBLUE, Color.YELLOW);

        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> a.getChildren().add(a_swap)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);

        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> b.getChildren().add(b_swap)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
        swapNumbArray(t, a, b, Color.CORNFLOWERBLUE, Color.YELLOW, timeJump);
    }
    public static void swapNumbArray(ParallelTransition t, CirNode a, CirNode b, Color colorRec, Color colorText, double timeJump){

        Rectangle rec_1 = new Rectangle();
        rec_1.setHeight(47);
        rec_1.setWidth(47);
        rec_1.setFill(colorRec);

        Rectangle rec_2 = new Rectangle();
        rec_2.setHeight(47);
        rec_2.setWidth(47);
        rec_2.setFill(colorRec);

        Text text_1 = new Text(Integer.toString(NumbArray.ids[a.index]));
        text_1.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 17));
        text_1.setFill(colorText);

        Text text_2 = new Text(Integer.toString(NumbArray.ids[b.index]));
        text_2.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 17));
        text_2.setFill(colorText);


        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> NumbArray.elements.get(a.index).getChildren().addAll(rec_1, text_1)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> NumbArray.elements.get(b.index).getChildren().addAll(rec_2, text_2)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
        restoreAfterSwapCirArray(t, a,b, timeJump);


    }

    public static void restoreAfterSwapCirArray(ParallelTransition t, CirNode a, CirNode b, double timeJump){
        counter += timeJump;
        CirNode a_restore_color = new CirNode(a.id, Color.AQUAMARINE, Color.BLACK);
        CirNode b_restore_color = new CirNode(b.id, Color.AQUAMARINE, Color.BLACK);


        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> a.getChildren().add(a_restore_color)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> b.getChildren().add(b_restore_color)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
        restoreAfterSwapNumbArray(t, a,b);

    }
    public static void restoreAfterSwapNumbArray(ParallelTransition t, CirNode a, CirNode b){
        Rectangle rec_restore_1 = new Rectangle();
        rec_restore_1.setHeight(47.5);
        rec_restore_1.setWidth(47.5);
        rec_restore_1.setFill(Color.WHITE);

        Rectangle rec_restore_2 = new Rectangle();
        rec_restore_2.setHeight(47.5);
        rec_restore_2.setWidth(47.5);
        rec_restore_2.setFill(Color.WHITE);

        Text text_restore_1 = new Text(Integer.toString(NumbArray.ids[a.index]));
        text_restore_1.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 17));
        text_restore_1.setFill(Color.BLACK);

        Text text_restore_2 = new Text(Integer.toString(NumbArray.ids[b.index]));
        text_restore_2.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 17));
        text_restore_2.setFill(Color.BLACK);


        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> NumbArray.elements.get(a.index).getChildren().addAll(rec_restore_1, text_restore_1)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
        //timeline.play();

        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> NumbArray.elements.get(b.index).getChildren().addAll(rec_restore_2, text_restore_2)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
    }

    public static void swapLogic(CirNode a, CirNode b){
        int temp_id = a.id;
        a.id = b.id;
        b.id = temp_id;

        temp_id = NumbArray.ids[a.index];
        NumbArray.ids[a.index] = NumbArray.ids[b.index];
        NumbArray.ids[b.index] = temp_id;

    }

    public static void markAlreadySorted(ParallelTransition t, CirNode a, double timeJump){
        counter += timeJump;
        CirNode a_complete = new CirNode(a.id, Color.LIGHTGREEN, Color.BLACK);

        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> a.getChildren().add(a_complete)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
        Rectangle rec_complete = new Rectangle();
        rec_complete.setHeight(47);
        rec_complete.setWidth(47);
        rec_complete.setFill(Color.LIGHTGREEN);
        Text text_complete = new Text(Integer.toString(NumbArray.ids[a.index]));
        text_complete.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 17));
        text_complete.setFill(Color.BLACK);

        timeline = new Timeline(new KeyFrame(Duration.seconds(counter), e -> NumbArray.elements.get(a.index).getChildren().addAll(rec_complete, text_complete)));
        timeline.setCycleCount(1);
        t.getChildren().add(timeline);
    }
    public static void buildMaxHeap(ParallelTransition t, CirArray cirGroup, int i, int n, double timeJump){

        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;


        if (left < n && cirGroup.cirArray.get(max).id < cirGroup.cirArray.get(left).id) {
            max = left;
        }

        if (right < n && cirGroup.cirArray.get(max).id  < cirGroup.cirArray.get(right).id) {
            max = right;
        }

        considerCirArray(t, cirGroup.cirArray.get(i), cirGroup.cirArray.get(max), Color.CRIMSON, Color.WHITE, timeJump);
        if (max != i) {
            swapCirArray(t, cirGroup.cirArray.get(i), cirGroup.cirArray.get(max), timeJump);
            buildMaxHeap(t, cirGroup, max, n, timeJump);
        }
        else {
            considerCirArray(t, cirGroup.cirArray.get(i), cirGroup.cirArray.get(max), Color.CRIMSON, Color.WHITE, timeJump);
            restoreColorCirArray(t, cirGroup.cirArray.get(i), cirGroup.cirArray.get(max), timeJump);
        }
    }
    public static void heapSort(ParallelTransition t, CirArray cirGroup, double timeJump){
        for (int i = cirGroup.cirArray.size() / 2 - 1; i >= 0; i--) {
            buildMaxHeap(t, cirGroup, i, cirGroup.cirArray.size(), timeJump);
        }
        for (int i = cirGroup.cirArray.size() - 1; i > 0; i--) {
            considerCirArray(t, cirGroup.cirArray.get(0), cirGroup.cirArray.get(i), Color.CRIMSON, Color.BLACK, timeJump);
            swapCirArray(t, cirGroup.cirArray.get(0), cirGroup.cirArray.get(i), timeJump);
            markAlreadySorted(t, cirGroup.cirArray.get(i), timeJump);
            buildMaxHeap(t, cirGroup, 0, i, timeJump);
        }
        markAlreadySorted(t, cirGroup.cirArray.get(0), timeJump);
        t.setOnFinished(e -> markAlreadySorted(t, cirGroup.cirArray.get(0), timeJump));
        isSorted = true;
    }

    public static void onFinished(){
        Arrays.sort(NumbArray.ids);
        for (int i=0; i< 10; i++) {
            CirNode a_complete = new CirNode(NumbArray.ids[i], Color.LIGHTGREEN, Color.BLACK);
            HeapSortGUI.cirGroup.cirArray.get(i).getChildren().add(a_complete);
            Rectangle rec_complete = new Rectangle();
            rec_complete.setHeight(47);
            rec_complete.setWidth(47);
            rec_complete.setFill(Color.LIGHTGREEN);
            Text text_complete = new Text(Integer.toString(NumbArray.ids[i]));
            text_complete.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 17));
            text_complete.setFill(Color.BLACK);
            NumbArray.elements.get(i).getChildren().addAll(rec_complete, text_complete);
        }
    }
    public static void randomize(){
        for (int i=0; i<10; i++){
            NumbArray.ids[i] = CirArray.getRandomNumber();
        }
        for (int i=0; i<10; i++){
            HeapSortGUI.cirGroup.cirArray.get(i).id = NumbArray.ids[i];
        }
        for (int i=0; i<10; i++){
            CirNode a_random = new CirNode(HeapSortGUI.cirGroup.cirArray.get(i).id, Color.AQUAMARINE, Color.BLACK);
            HeapSortGUI.cirGroup.cirArray.get(i).getChildren().add(a_random);

            Rectangle rec_random = new Rectangle();
            rec_random.setHeight(47.5);
            rec_random.setWidth(47.5);
            rec_random.setFill(Color.WHITE);

            Text text_random = new Text(Integer.toString(NumbArray.ids[HeapSortGUI.cirGroup.cirArray.get(i).index]));
            text_random.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 17));
            text_random.setFill(Color.BLACK);
            NumbArray.elements.get(HeapSortGUI.cirGroup.cirArray.get(i).index).getChildren().addAll(rec_random, text_random);
        }

    } 
}