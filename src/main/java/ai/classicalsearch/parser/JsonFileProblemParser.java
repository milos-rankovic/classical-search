package ai.classicalsearch.parser;

import ai.classicalsearch.model.Action;
import ai.classicalsearch.model.Problem;
import ai.classicalsearch.model.State;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class JsonFileProblemParser extends ProblemParser {

    private static final String STATES_KEY =              "states";
    private static final String STATE_NAME_KEY =          "name";
    private static final String HEURISTIC_KEY =           "heuristic";
    private static final String ACTIONS_KEY =             "actions";
    private static final String ACTION_RESULT_NAME_KEY =  "state";
    private static final String ACTION_RESULT_PATH_COST = "cost";
    private static final String START_STATE_KEY =         "start_state";
    private static final String GOAL_STATE_KEY =          "goal_state";

    public JsonFileProblemParser(File file) {
        super(file);
    }

    @Override
    public Problem parse() {
        try(Stream<String> lines = Files.lines(Path.of(file.toURI()))){
            StringBuilder builder = new StringBuilder();

            lines.forEach(line -> builder.append(line));
            JSONObject jsonObject = new JSONObject(builder.toString());
            JSONArray statesArr = jsonObject.getJSONArray(STATES_KEY);

            Map<String, State> states = new HashMap<>();
            for(int i = 0; i < statesArr.length(); i++) {
                JSONObject stateObj = statesArr.getJSONObject(i);
                String stateName = stateObj.getString(STATE_NAME_KEY);
                int stateHeuristic = stateObj.getInt(HEURISTIC_KEY);

                State state = new State(stateName, stateHeuristic);
                states.put(stateName, state);
            }

            Map<State, List<Action>> stateActions = new HashMap<>();

            for(int i = 0; i < statesArr.length(); i++) {
                JSONObject stateObj = statesArr.getJSONObject(i);
                String stateName = stateObj.getString(STATE_NAME_KEY);
                JSONArray actionsArr = stateObj.getJSONArray(ACTIONS_KEY);
                List<Action> actions = new ArrayList<>();
                for(int j = 0; j < actionsArr.length(); j++) {
                    JSONObject actionObj = actionsArr.getJSONObject(j);
                    String resultStateName = actionObj.getString(ACTION_RESULT_NAME_KEY);
                    int resultStateCost = actionObj.getInt(ACTION_RESULT_PATH_COST);
                    State resultState = states.get(resultStateName);
                    Action action = new Action(stateName + "->" + resultStateName,
                            resultStateCost, resultState);
                    actions.add(action);
                }
                stateActions.put(states.get(stateName), actions);
            }

            String startStateName = jsonObject.getString(START_STATE_KEY);
            String goalStateName = jsonObject.getString(GOAL_STATE_KEY);

            State startState = states.get(startStateName);
            State goalState = states.get(goalStateName);

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
