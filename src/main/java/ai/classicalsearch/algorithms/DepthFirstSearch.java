package ai.classicalsearch.algorithms;

import ai.classicalsearch.comparators.AlphabetComparator;
import ai.classicalsearch.model.Action;
import ai.classicalsearch.model.Node;
import ai.classicalsearch.model.Problem;
import ai.classicalsearch.model.State;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearch extends SearchAlgorithm {

    private AlphabetComparator alphabetComparator;

    public DepthFirstSearch() {
        this(false);
    }

    public DepthFirstSearch(boolean verbose) {
        super(verbose);
        alphabetComparator = new AlphabetComparator();
    }

    @Override
    public Node search(Problem problem) {
        if(verbose) {
            System.out.println("Order of the node expansion:");
            System.out.println("-----------------------------------------------------------------------------------------");
        }
        Node start = new Node(problem.getStartState());
        start.setPathCost(0);
        if(problem.goalTest(start.getState())) return start;
        Stack<Node> frontier = new Stack<>();
        frontier.add(start);
        Set<State> explored = new HashSet<>();

        do{
            if(frontier.isEmpty()) return null;
            Node node = frontier.pop();
            if(verbose) System.out.println(node.getState().getName());
            if(problem.goalTest(node.getState())) return node;
            explored.add(node.getState());
            List<Action> actions = problem.actions(node.getState());
            actions.sort(alphabetComparator.reversed());
            for(Action action : actions){
                Node child = childNode(node, action);
                if(!frontier.contains(child) && !explored.contains(child.getState())){
                    frontier.push(child);
                }
            }
            if(verbose) {
                System.out.println("  Frontier: ");
                System.out.println("\t" + frontier);
                System.out.println("  Explored:");
                System.out.println("\t" + explored);
            }
        } while (!frontier.isEmpty());
        return null;
    }
}
