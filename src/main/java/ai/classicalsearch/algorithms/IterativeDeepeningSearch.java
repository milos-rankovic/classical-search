package ai.classicalsearch.algorithms;

import ai.classicalsearch.model.Node;
import ai.classicalsearch.model.Problem;

public class IterativeDeepeningSearch extends SearchAlgorithm {

    private static final int DEFAULT_DEPTH_LIMIT = 100;

    private DepthLimitedSearch depthLimitedSearch;
    private int depthLimit;

    public IterativeDeepeningSearch() {
        this(false, DEFAULT_DEPTH_LIMIT);
    }

    public IterativeDeepeningSearch(int depthLimit) {
        this(false, depthLimit);
    }

    public IterativeDeepeningSearch(boolean verbose) {
        this(verbose, DEFAULT_DEPTH_LIMIT);
    }

    public IterativeDeepeningSearch(boolean verbose, int depthLimit) {
        super(verbose);
        this.depthLimit = depthLimit;
        depthLimitedSearch = new DepthLimitedSearch(verbose, depthLimit);
    }

    @Override
    public Node search(Problem problem) {
        if(verbose) {
            System.out.println("Order of the function calls and return values:");
            System.out.println("-----------------------------------------------------------------------------------------");
        }
        for(int limit = 0; limit < depthLimit; limit++){
            depthLimitedSearch.setLimit(limit);
            Node result = depthLimitedSearch.search(problem);
            if(!result.getState().getName().equals("cutoff")) return result;
        }
        return null;
    }
}
