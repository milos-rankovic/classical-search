package ai.classicalsearch.algorithms;

import ai.classicalsearch.comparators.HCostComparator;
import ai.classicalsearch.model.Action;
import ai.classicalsearch.model.Node;
import ai.classicalsearch.model.Problem;
import ai.classicalsearch.model.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GreedyBestFirstSearch extends SearchAlgorithm {

    private HCostComparator hCostComparator;

    public GreedyBestFirstSearch() {
        this(false);
    }

    public GreedyBestFirstSearch(boolean verbose) {
        super(verbose);
        hCostComparator = new HCostComparator();
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
        List<Node> frontier = new ArrayList<>();

        frontier.add(start);
        Set<State> explored = new HashSet<>();

        do{
            frontier.sort(hCostComparator);
            if(frontier.isEmpty()) return null;
            Node node = frontier.get(0); frontier.remove(0);
            if(verbose) System.out.println(node.getState().getName());
            if(problem.goalTest(node.getState())) return node;
            explored.add(node.getState());

            for(Action action : problem.actions(node.getState())){
                Node child = childNode(node, action);
                if(!frontier.contains(child) && !explored.contains(child.getState())){

                    frontier.add(child);
                } else if(frontier.contains(child)) {

                    int childIndex = frontier.indexOf(child);
                    Node n = frontier.get(childIndex);
                    if (n.equals(child) && (n.getPathCost() > child.getPathCost())){
                        frontier.set(childIndex, child);
                    }
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
