package sorts;

import java.util.ArrayList;

import components.node.ColNode;
import javafx.animation.Transition;

public class QuickSort extends AbstractSort {
    private ArrayList<Transition> transition;

    public QuickSort() {
        this.transition = new ArrayList<>();
    }

    private void quickSort(ArrayList<ColNode> list, int lo, int hi) {
        if (lo < hi) {
            int q = partition(list, lo, hi);
            quickSort(list, lo, q - 1);
            quickSort(list, q + 1, hi);
        }
    }

    //last element of array chosen as pivot
    private int partition(ArrayList<ColNode> list, int lo, int hi) {
        int i = lo;
        transition.add(colorNodes(list, AbstractSort.PIVOT_COLOR, hi));

        for (int j = lo; j < hi; j++) {
            transition.add(colorNodes(list, SELECT_COLOR, j));
            if (list.get(j).getHeight() < list.get(hi).getHeight()) {
                transition.add(swap(list, i, j));
                transition.add(colorNodes(list, AbstractSort.NODE_COLOR, i));
                i++;
            } else {
                transition.add(colorNodes(list, AbstractSort.NODE_COLOR, j));
            }
        }
        transition.add(swap(list, i, hi));
        transition.add(colorNodes(list, AbstractSort.NODE_COLOR, i));

        return i;
    }

    @Override
    public ArrayList<Transition> run(ArrayList<ColNode> nodes) {
        quickSort(nodes, 0, nodes.size() - 1);
        transition.add(colorNodes(nodes, SORTED_COLOR));

        return transition;
    }
}