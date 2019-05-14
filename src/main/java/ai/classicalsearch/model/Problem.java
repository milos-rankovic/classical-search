package ai.classicalsearch.model;

import ai.classicalsearch.parser.JsonFileProblemParser;
import ai.classicalsearch.parser.ProblemParser;
import ai.classicalsearch.parser.TextFileProblemParser;

import java.io.File;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Problem {

    private final Map<State, List<Action>> stateActions;

    private State startState;

    private State goalState;

    private Problem(Builder builder) {
        stateActions = builder.stateActions;
        startState = builder.startState;
        goalState = builder.goalState;
    }

    public Problem(Map<State, List<Action>> stateActions){
        this.stateActions = stateActions;

    }

    public List<Action> actions(State state){
        return stateActions.get(state);
    }

    public boolean goalTest(State state){
        return state.equals(goalState);
    }

    public State getStartState() {
        return startState;
    }

    public void setStartState(State startState) {
        this.startState = startState;
    }

    public void setGoalState(State goalState) {
        this.goalState = goalState;
    }

    public static Problem fromFile(FileType fileType, File file) {
        if(fileType == FileType.TXT) {
            return new TextFileProblemParser(file).parse();
        } else if(fileType == FileType.JSON) {
            return new JsonFileProblemParser(file).parse();
        }

        return null;
    }

    public static class Builder {
        private Map<State, List<Action>> stateActions = null;
        private State startState = null;
        private State goalState = null;

        public Builder() {}

        public Problem build() {
           return new Problem(this);
        }

        public Builder stateActions(Map<State, List<Action>> stateActionMap) {
            this.stateActions = stateActionMap;
            return this;
        }

        public Builder start(State startState) {
            this.startState = startState;
            return this;
        }

        public Builder goal(State goalState) {
            this.goalState = goalState;
            return this;
        }

    }



    public static enum FileType {
        TXT, JSON
    }
}
