package ai.classicalsearch.comparators;


import ai.classicalsearch.model.Node;

import java.util.Comparator;


public class GCostComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        if(Integer.compare(o1.getPathCost(), o2.getPathCost()) == 0){
            return o1.getState().getName().compareTo(o2.getState().getName());
        } else
            return Integer.compare(o1.getPathCost(), o2.getPathCost());
    }
}
