package ai.classicalsearch.algorithms;

import ai.classicalsearch.comparators.AlphabetComparator;
import ai.classicalsearch.model.Action;
import ai.classicalsearch.model.Node;
import ai.classicalsearch.model.Problem;
import ai.classicalsearch.model.State;

import java.util.List;

public class DepthLimitedSearch extends SearchAlgorithm {

    private int limit;
    private AlphabetComparator alphabetComparator;

    public DepthLimitedSearch(int limit) {
        this(false, limit);
    }

    public DepthLimitedSearch(boolean verbose, int limit) {
        super(verbose);
        this.limit = limit;
        alphabetComparator = new AlphabetComparator();
    }

    void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public Node search(Problem problem) {
        Node node = new Node(problem.getStartState());
        node.setPathCost(0);
        return recursiveDLS(node, problem, limit);
    }

    private Node recursiveDLS(Node node, Problem problem, int limit) {

        if(verbose) System.out.println("recursiveDLS(" + node.getState().getName() + ", " + limit + ")");
        if (problem.goalTest(node.getState())) return node;
        else if (limit == 0) {
            if(verbose) System.out.println("    -> cutoff");
            return new Node(new State("cutoff"));
        } else {
            boolean cutoffOccurred = false;
            List<Action> actions = problem.actions(node.getState());
            actions.sort(alphabetComparator);
            for (Action action : actions) {
                Node child = childNode(node, action);

                Node result = recursiveDLS(child, problem, limit - 1);


                if (result.getState().getName().equals("cutoff")) cutoffOccurred = true;
                else if (result != null) {
                    if(verbose) System.out.println("    -> " + result.getState().getName());
                    return result;
                }
            }
            if (cutoffOccurred) {
                return new Node(new State("cutoff"));
            } else {
                if(verbose) System.out.println("    -> failure");
                return null;
            }
        }
    }
}
