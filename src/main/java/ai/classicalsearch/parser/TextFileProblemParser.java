package ai.classicalsearch.parser;


import ai.classicalsearch.exceptions.FileFormatException;
import ai.classicalsearch.model.Action;
import ai.classicalsearch.model.Problem;
import ai.classicalsearch.model.State;

import java.io.*;
import java.util.*;

public class TextFileProblemParser extends ProblemParser {


    private static final String STATES_KEY =         "states:";
    private static final String STATES_DELIM =       ",";
    private static final String HEURISTICS_KEY =     "heuristics:";
    private static final String HEURISTICS_DELIM =   ",";
    private static final String STATE_ACTIONS_KEY =  "state_actions:";
    private static final String START_STATE_KEY =    "start:";
    private static final String ACTIONS_DELIM =      ",";
    private static final String PATH_COST_DELIM =    ",";
    private static final String GOAL_STATE_KEY =     "goal:";

    public TextFileProblemParser(File file) {
        super(file);
    }


    @Override
    public Problem parse() {
        try(BufferedReader reader = new BufferedReader(new FileReader(file));){

            Map<String, State> states = new HashMap<>();

            String line = reader.readLine();

            if(line != null && line.startsWith(STATES_KEY)) {
                int colonIndex = line.indexOf(STATES_KEY.substring(STATES_KEY.length() - 1));
                String statesStr = line.substring(colonIndex + 2);
                StringTokenizer stringTokenizer = new StringTokenizer(statesStr, STATES_DELIM);
                while(stringTokenizer.hasMoreTokens()) {
                    String stateName = stringTokenizer.nextToken().strip();
                    states.put(stateName, new State(stateName));
                }
            } else {
                throw new FileFormatException();
            }

            line = reader.readLine();

            if(line != null && line.startsWith(HEURISTICS_KEY)) {
                int colonIndex = line.indexOf(HEURISTICS_KEY.substring(HEURISTICS_KEY.length() - 1));
                String heuristicsStr = line.substring(colonIndex + 2);
                StringTokenizer stringTokenizer = new StringTokenizer(heuristicsStr, HEURISTICS_DELIM);
                if(stringTokenizer.countTokens() != states.size()) {
                    throw new FileFormatException();
                }
                Iterator<Map.Entry<String, State>> it = states.entrySet().iterator();
                while(stringTokenizer.hasMoreTokens()) {
                    int heuristic = Integer.parseInt(stringTokenizer.nextToken().strip());
                    it.next().getValue().setHeuristic(heuristic);
                }
            } else {
                throw new FileFormatException();
            }

            line = reader.readLine();

            Map<State, List<Action>> stateActions = new HashMap<>();

            if(line != null && line.startsWith(STATE_ACTIONS_KEY)){
                while((line = reader.readLine()) != null &&
                    !line.startsWith(START_STATE_KEY)) {
                    line = line.strip();
                    String stateName = line.substring(0, 1).strip();
                    State state = states.get(stateName);
                    String actionsStr = line.substring(3);
                    List<Action> actions = new ArrayList<>();
                    String pathCostsStr = reader.readLine();
                    pathCostsStr = pathCostsStr.substring(
                            pathCostsStr.indexOf(":") + 2);
                    StringTokenizer actionsTokenizer = new StringTokenizer(actionsStr, ACTIONS_DELIM);
                    StringTokenizer costsTokenizer = new StringTokenizer(pathCostsStr, PATH_COST_DELIM);
                    while(actionsTokenizer.hasMoreTokens() &&
                        costsTokenizer.hasMoreTokens()) {
                        String actionResult = actionsTokenizer.nextToken().trim();
                        State resultState = states.get(actionResult);
                        String costStr = costsTokenizer.nextToken().trim();
                        int cost = Integer.parseInt(costStr);
                        Action action = new Action(state.getName() + "->" + resultState.getName(),
                                cost, resultState);
                        actions.add(action);
                    }

                    stateActions.put(state, actions);
                }
            } else {
                throw new FileFormatException();
            }

            State startState = null;
            if(line != null && line.startsWith(START_STATE_KEY)) {
                String startStateStr = line.substring(
                        line.indexOf(START_STATE_KEY.substring(START_STATE_KEY.length() - 1)) + 2);
                String startStateName = startStateStr.trim();
                startState = states.get(startStateName);
            } else {
                throw new FileFormatException();
            }
            line = reader.readLine();
            State goalState = null;
            if(line != null && line.startsWith(GOAL_STATE_KEY)) {
                String goalStateStr = line.substring(
                        line.indexOf(GOAL_STATE_KEY.substring(GOAL_STATE_KEY.length() - 1)) + 2);
                String goalStateName = goalStateStr.trim();
                goalState = states.get(goalStateName);
            } else {
                throw new FileFormatException();
            }

            return new Problem.Builder()
                    .start(startState)
                    .goal(goalState)
                    .stateActions(stateActions)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
