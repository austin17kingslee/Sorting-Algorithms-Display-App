package scenes.bubble_quick_merge;

import components.array.InitializeArr;
import components.node.ColNode;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import sorts.*;

public class SortSceneController implements Initializable{
    Scene mainScene;
    private boolean isStop = false;
    SequentialTransition sq = new SequentialTransition();
    TranslateTransition corgitt;

    @FXML
    private Button finishbtn;
    @FXML
    private Button pausebtn;
    @FXML
    private Button playbtn;
    @FXML
    private Button randombtn;
    @FXML
    private Button backbtn;
    @FXML
    private HBox display;
    @FXML
    private ImageView corgi;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ArrayList<ColNode> nodes = new ArrayList<>();
        nodes = InitializeArr.initArr(AbstractSort.COL_COUNT);
        for(ColNode i:nodes){
            display.getChildren().add(i);
        }
        pausebtn.setDisable(true);
        finishbtn.setDisable(true);
    }
    public void playBtnHandler(ActionEvent evt){
        sq.getChildren().clear();
        randombtn.setDisable(true);
        playbtn.setDisable(true);
        pausebtn.setDisable(false);
        finishbtn.setDisable(false);

        //Run corgi
        
        corgitt = new TranslateTransition();
        corgitt.setNode(corgi);
        corgitt.setFromX(-200);
        corgitt.setToX(AbstractSort.WINDOW_W + 200);
        corgitt.setDuration(Duration.seconds(6));
        corgitt.setCycleCount(Animation.INDEFINITE);
        corgitt.play();
        
        ArrayList<ColNode> nodes = new ArrayList<>();
        for(javafx.scene.Node i: display.getChildren()){
            nodes.add((ColNode)i);
        }
        
        if(AbstractSort.SELECTION == 1){
            sq.getChildren().addAll(new BubbleSort().run(nodes));
        }
        else if(AbstractSort.SELECTION == 2){
            sq.getChildren().addAll(new QuickSort().run(nodes));
        }
        else{
            sq.getChildren().addAll(new MergeSort().run(nodes));
        }

        sq.setOnFinished(e -> {
            randombtn.setDisable(false);
            corgitt.stop();
            finishbtn.setDisable(true);
            pausebtn.setDisable(true);
        });

        sq.play();
    }
    
    public void randomBtnHandler(ActionEvent evt){
        ArrayList<ColNode> nodes = new ArrayList<>();
        nodes = InitializeArr.initArr(AbstractSort.COL_COUNT);
        display.getChildren().clear();
        for(ColNode i:nodes){
            display.getChildren().add(i);
        }
        playbtn.setDisable(false);
    }

    public void backBtnHandler(ActionEvent evt) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/main-menu.fxml"));
        Stage stage;
        mainScene = new Scene(root, 700, 400);
        stage = (Stage) backbtn.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }
    
    public void pauseBtnHandler(ActionEvent evt){
        if (!isStop) {
            sq.pause();
            pausebtn.setText("Continue");
            isStop = true;
        } else {
            sq.play();
            isStop = false;
            pausebtn.setText("Pause");
        }
    }

    public void finishBtnHandler(ActionEvent evt){
        sq.jumpTo(sq.getTotalDuration());
        corgitt.stop();
    }
}

