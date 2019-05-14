package ai.classicalsearch.algorithms;

import ai.classicalsearch.model.Action;
import ai.classicalsearch.model.Node;
import ai.classicalsearch.model.Problem;

public abstract class SearchAlgorithm {

    protected boolean verbose;

    public SearchAlgorithm() {
        this(false);
    }
    public SearchAlgorithm(boolean verbose) {
        this.verbose = verbose;
    }

    public abstract Node search(Problem problem);

    protected Node childNode(Node parent, Action action){
        Node child = new Node(action.getActionResult());
        child.setPathCost(action.getActionCost() + parent.getPathCost());
        child.setParent(parent);
        return child;
    }
}
