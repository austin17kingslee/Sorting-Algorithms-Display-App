package sorts;

import java.util.ArrayList;

import components.node.ColNode;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;

public class MergeSort extends AbstractSort {
    private ColNode[] tmp;

    public ArrayList<Transition> Merge(ArrayList<ColNode> list, int p, int q, int r){
        ArrayList<Transition> transition = new ArrayList<>();
        ArrayList<ColNode> tmpList = new ArrayList<>();

        for (int i = p; i <= r; i++) {
            tmp[i] = list.get(i);
            tmpList.add(tmp[i]);
        }
        int i = p;
        int j = q + 1;
        int k = p;
        while (i <= q && j <= r) {
            if (tmp[i].getHeight() <= tmp[j].getHeight()) {
                list.set(k++,tmp[i++]);
            } else {
                list.set(k++,tmp[j++]);
            }
        }
        while (i <= q) {
            if(k>=COL_COUNT) break;
            list.set(k++,tmp[i++]);
        }
        while (j <= r) {
            if(k>=COL_COUNT) break;
            list.set(k++,tmp[j++]);
        }

        transition.add(colorNodes(tmpList, AbstractSort.SELECT_COLOR));
        ParallelTransition pt = new ParallelTransition();

        for (int x = p; x <= r; x++) {
            for (int y = p; y <= r; y++) {
                if (tmp[x].equals(list.get(y))) {
                    pt.getChildren().add(tmp[x].moveX((AbstractSort.COL_W + 1.0) * (y - x)));
                }
            }
        }

        transition.add(pt);
        transition.add(colorNodes(tmpList, AbstractSort.SELECT_COLOR));

        return transition;
    }

    private ArrayList<Transition> mergeSort(ArrayList<ColNode> list, int p, int r) {
        ArrayList<Transition> transition = new ArrayList<>();

        if (p < r) {
            int q = (p + r) / 2;
            transition.addAll(mergeSort(list, p, q));
            transition.addAll(mergeSort(list, q + 1, r));
            transition.addAll(Merge(list, p, q, r));
        }
        return transition;
    }

    @Override
    public ArrayList<Transition> run(ArrayList<ColNode> nodes){
        ArrayList<Transition> transition = new ArrayList<>();
        this.tmp = new ColNode[nodes.size()];
        transition.addAll(mergeSort(nodes, 0, nodes.size() - 1));
        transition.add(colorNodes(nodes, AbstractSort.SORTED_COLOR));
        return transition;
    }
}