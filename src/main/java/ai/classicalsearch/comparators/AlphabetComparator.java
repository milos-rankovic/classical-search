package ai.classicalsearch.comparators;


import ai.classicalsearch.model.Action;
import ai.classicalsearch.model.State;

import java.util.Comparator;


public class AlphabetComparator implements Comparator<Action> {
    @Override
    public int compare(Action o1, Action o2) {
        State state1 = o1.getActionResult();
        State state2 = o2.getActionResult();
        return state1.getName().compareTo(state2.getName());
    }
}
