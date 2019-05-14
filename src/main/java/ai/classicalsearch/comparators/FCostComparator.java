package ai.classicalsearch.comparators;


import ai.classicalsearch.model.Node;

import java.util.Comparator;

public class FCostComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        if(Integer.compare(o1.getPathCost() + o1.getState().getHeuristic(), o2.getPathCost() + o2.getState().getHeuristic()) == 0){
            return o1.getState().getName().compareTo(o2.getState().getName());
        } else {
            return Integer.compare(o1.getPathCost() + o1.getState().getHeuristic(), o2.getPathCost() + o2.getState().getHeuristic());
        }
    }
}
