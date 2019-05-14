package ai.classicalsearch.model;

public class State {

    private String name;
    private int heuristic;

    public State(String name, int heuristic){
        this.name = name;
        this.heuristic = heuristic;
    }

    public State(String name){
        this(name, 0);
    }

    public String getName(){
        return name;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public boolean equals(Object obj) {
        State anotherState = (State) obj;
        return this.name.equals(anotherState.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return name + "(h=" + heuristic;
    }
}
