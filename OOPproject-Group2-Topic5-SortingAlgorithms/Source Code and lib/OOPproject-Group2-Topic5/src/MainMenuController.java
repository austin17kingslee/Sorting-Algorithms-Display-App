import components.array.NumbArray;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import scenes.bucket.BucketSortGUI;
import scenes.heap.HeapSortGUI;
import scenes.radix.RadixSortGUI;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;

import sorts.*;

public class MainMenuController implements Initializable{

    Scene scene2;
    @FXML 
    private Button SubmitBtn;
    @FXML
    private ChoiceBox<String> sorts;
    
    @Override
    public void initialize (URL arg0, ResourceBundle arg1){
        ArrayList<String> st = new ArrayList<>();
        st.add("Bubble Sort");
        st.add("Quick Sort");
        st.add("Merge Sort");
        st.add("Heap Sort");
        st.add("Radix Sort");
        st.add("Bucket Sort");
        sorts.setItems(FXCollections.observableArrayList(st));
        
    }

    public void SubmitBtnHandler(ActionEvent evt) throws IOException{
        Stage stage;    
        String algo = sorts.getSelectionModel().getSelectedItem();
        try {
            switch (algo) {
                case "Quick Sort":
                    AbstractSort.SELECTION = 2;
                    break;
                case "Merge Sort":
                    AbstractSort.SELECTION = 3;
                    break;
                case "Heap Sort":
                    AbstractSort.SELECTION = 4;
                    break;
                case "Bubble Sort":
                    AbstractSort.SELECTION = 1;
                    break;
                case "Radix Sort":
                    AbstractSort.SELECTION = 5;
                    break;
                case "Bucket Sort":
                    AbstractSort.SELECTION = 6;
                    break;
                default:
                    break;
            }
        }
        catch (Exception ignored){};
        initScene(AbstractSort.SELECTION);
        stage = (Stage) SubmitBtn.getScene().getWindow();
        stage.setScene(scene2);
        stage.show();
    }   
    
    public void initScene(int i) throws IOException{
        Parent root;
        if(i <= 3){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/bubble_quick_merge/sort-scene.fxml")));
            scene2 = new Scene(root);
        }
        else if(i == 4){
            HeapSortGUI tmp = new HeapSortGUI();
            root = tmp.createScene();
            HeapSort.randomize();
            NumbArray.arrayGUI.setGridLinesVisible(true);
            scene2 = new Scene(root);
        }
        else if(i == 5){
            RadixSortGUI tmp = new RadixSortGUI();
            root = tmp.createScene();
            scene2 = new Scene(root);
        }
        else if(i == 6){
            BucketSortGUI tmp = new BucketSortGUI();
            root = tmp.createScene();
            scene2 = new Scene(root);
        }
    }
}
