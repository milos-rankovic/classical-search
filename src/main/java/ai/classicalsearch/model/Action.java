package ai.classicalsearch.model;

public class Action {

    private String name;

    private State actionResult;

    private int actionCost;

    public Action(String name, int actionCost, State actionResult){
        this.name = name;
        this.actionCost = actionCost;
        this.actionResult = actionResult;
    }

    public State getActionResult(){
        return actionResult;
    }

    public int getActionCost() {
        return actionCost;
    }

    @Override
    public boolean equals(Object obj) {
        Action anotherAction = (Action) obj;
        return this.name.equals(anotherAction.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
