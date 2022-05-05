package sorts;

import java.util.ArrayList;

import components.node.ColNode;
import javafx.animation.Transition;

public class BubbleSort extends AbstractSort{
    
    private ArrayList<Transition> compare(ArrayList<ColNode> list, int a, int b){
        ArrayList<Transition> tr = new ArrayList<Transition>();
        //Color the nodes being considered
        int[] arr = {a,b};
        tr.add(colorNodes(list,AbstractSort.SELECT_COLOR,arr));
        if(list.get(a).getHeight() > list.get(b).getHeight()){
            //Swap the nodes
            tr.add(swap(list, a, b));
        }
        //Return their original color
        tr.add(colorNodes(list,AbstractSort.NODE_COLOR,arr));
        return tr;    
    }
    
    public ArrayList<Transition> run(ArrayList<ColNode> list){
        ArrayList<Transition> transition = new ArrayList<Transition>();
        int n = list.size();
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){
                transition.addAll(compare(list, j, j+1));
            }
            //Color the n-i-1 node as Sorted
            int[] arr = {n-i-1};
            transition.add(colorNodes(list,AbstractSort.SORTED_COLOR,arr));
        }
        //Color the last node
        int[] arr = {0};
        transition.add(colorNodes(list,AbstractSort.SORTED_COLOR,arr));
        return transition;
    }
}
