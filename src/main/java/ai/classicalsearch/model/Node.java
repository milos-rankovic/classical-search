package ai.classicalsearch.model;

public class Node {

    private State state;

    private int pathCost;

    private Node parent;

    public Node(State state){
        this.state = state;
        parent = null;
    }

    public State getState(){
        return state;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public Node getParent(){
        return parent;
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    @Override
    public boolean equals(Object obj) {
        Node anotherNode = (Node) obj;
        return this.getState().equals(anotherNode.getState());
    }

    @Override
    public String toString() {
        return state.toString() + ", g=" + pathCost + ", f=" + (pathCost + state.getHeuristic()) + ")";
    }
}
