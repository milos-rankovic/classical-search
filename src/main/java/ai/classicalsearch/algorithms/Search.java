package ai.classicalsearch.algorithms;

import ai.classicalsearch.model.Node;
import ai.classicalsearch.model.Problem;

import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Search {

    private Problem problem;
    private Deque<Node> solution;

    public Search(Problem problem) {
        this.problem = problem;
        this.solution = null;
    }

    public void run(SearchAlgorithm algorithm) {
        solution = new ArrayDeque<>();
        Node node = algorithm.search(problem);
        while(node != null) {
            solution.add(node);
            node = node.getParent();
        }
    }

    public void printSolution(PrintStream out) {
        Objects.requireNonNull(solution);
        Node node = null;

        while((node = solution.pollLast()) != null) {
            out.print(node.getState().getName());
            if(solution.peekLast() != null) {
                out.print("->");
            }
        }

        out.print("\n");
    }
}
