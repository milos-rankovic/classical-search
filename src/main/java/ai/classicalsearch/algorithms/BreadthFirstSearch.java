package ai.classicalsearch.algorithms;

import ai.classicalsearch.comparators.AlphabetComparator;
import ai.classicalsearch.model.Action;
import ai.classicalsearch.model.Node;
import ai.classicalsearch.model.Problem;
import ai.classicalsearch.model.State;

import java.util.*;

public class BreadthFirstSearch extends SearchAlgorithm {

    private AlphabetComparator alphabetComparator;

    public BreadthFirstSearch() {
        this(false);
    }

    public BreadthFirstSearch(boolean verbose) {
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
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(start);
        Set<State> explored = new HashSet<>();

        do{
            if(frontier.isEmpty()) return null;
            Node node = frontier.poll();
            if(verbose) System.out.println(node.getState().getName());
            explored.add(node.getState());
            List<Action> actions = problem.actions(node.getState());
            actions.sort(alphabetComparator);
            for(Action action : actions){
                Node child = childNode(node, action);
                if(!frontier.contains(child) && !explored.contains(child.getState())){
                    if(problem.goalTest(child.getState())) return child;
                    frontier.add(child);
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
