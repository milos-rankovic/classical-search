package ai.classicalsearch;



import ai.classicalsearch.algorithms.*;
import ai.classicalsearch.model.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try{
            File problemFile = new File(Main.class.getResource("/problem3.txt").toURI());
            Problem problem = Problem.fromFile(Problem.FileType.TXT, problemFile);

            Search search = new Search(problem);

            search.run(new DepthFirstSearch(true));
            search.printSolution(System.out);

            search.run(new BreadthFirstSearch(true));
            search.printSolution(System.out);

            search.run(new UniformCostSearch(true));
            search.printSolution(System.out);

            search.run(new GreedyBestFirstSearch(true));
            search.printSolution(System.out);

            search.run(new AStarSearch(true));
            search.printSolution(System.out);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    private static Problem problemExample1(){
        var stateS = new State("S");
        var stateA = new State("A", 4);
        var stateB = new State("B", 5);
        var stateC = new State("C", 5);
        var stateD = new State("D", 3);
        var stateE = new State("E", 0);

        HashMap<State, List<Action>> stateActions = new HashMap<>();
        /**
         * Actions for node S
         */
        List<Action> actionsS = new ArrayList<>();
        var sa = new Action("S->A", 4, stateA);
        var sb = new Action("S->B", 4, stateB);
        var sc = new Action("S->C", 1, stateC);
        Collections.addAll(actionsS, sa, sb, sc);
        stateActions.put(stateS, actionsS);
        /**
         * Actions for node A
         */
        List<Action> actionsA = new ArrayList<>();
        var as = new Action("A->S", 4, stateS);
        var ad = new Action("A->D", 4, stateD);
        Collections.addAll(actionsA, as, ad);
        stateActions.put(stateA, actionsA);
        /**
         * Actions for node B
         */
        List<Action> actionsB = new ArrayList<>();
        var bs = new Action("B->S", 4, stateS);
        var bc = new Action("B->C", 1, stateC);
        var be = new Action("B->E", 9, stateE);
        Collections.addAll(actionsB, bs, bc, be);
        stateActions.put(stateB, actionsB);
        /**
         * Actions for node C
         */
        List<Action> actionsC = new ArrayList<>();
        var cs = new Action("C->S", 1, stateS);
        var cb = new Action("C->B", 1, stateB);
        var cd = new Action("C->D", 2, stateD);
        var ce = new Action("C->E", 6, stateE);
        Collections.addAll(actionsC, cs, cb, cd, ce);
        stateActions.put(stateC, actionsC);
        /**
         * Actions for node D
         */
        List<Action> actionsD = new ArrayList<>();
        var da = new Action("D->A", 4, stateA);
        var dc = new Action("D->C", 2, stateC);
        var de = new Action("D->E", 3, stateE);
        Collections.addAll(actionsD, da, dc, de);
        stateActions.put(stateD, actionsD);
        /**
         * Actions for node E
         */
        List<Action> actionsE =  new ArrayList<>();
        var eb = new Action("E->B", 9, stateB);
        var ec = new Action("E->C", 6, stateC);
        var ed = new Action("E->D", 3, stateD);
        Collections.addAll(actionsE, eb, ec, ed);
        stateActions.put(stateE, actionsE);

        Problem problem = new Problem(stateActions);
        problem.setStartState(stateS);
        problem.setGoalState(stateE);

        return problem;
    }

    private static Problem problemExample2() {
        var stateS = new State("S");
        var stateA = new State("A", 50);
        var stateB = new State("B", 45);
        var stateC = new State("C", 46);
        var stateD = new State("D", 41);
        var stateE = new State("E", 48);
        var stateF = new State("F", 42);
        var stateH = new State("H", 34);
        var stateI = new State("I", 5);
        var stateK = new State("K", 0);

        Map<State, List<Action>> stateActions = new HashMap<>();

        /**
         * Actions for node S
         */
        List<Action> actionsS = new ArrayList<>();
        var sa = new Action("S->A", 100, stateA);
        var sb = new Action("S->B", 3, stateB);
        var se = new Action("S->E", 14, stateE);
        var sf = new Action("S->F", 4, stateF);
        Collections.addAll(actionsS, sa, sb, se, sf);
        stateActions.put(stateS, actionsS);

        /**
         * Actions for node A
         */
        List<Action> actionsA = new ArrayList<>();
        var as = new Action("A->S", 100, stateS);
        var ac = new Action("A->C", 4, stateC);
        Collections.addAll(actionsA, as, ac);
        stateActions.put(stateA, actionsA);

        /**
         * Actions for node B
         */
        List<Action> actionsB = new ArrayList<>();
        var bs = new Action("B->S", 3, stateS);
        var bd = new Action("B->D", 4, stateD);
        Collections.addAll(actionsB, bs, bd);
        stateActions.put(stateB, actionsB);

        /**
         * Actions for node C
         */
        List<Action> actionsC = new ArrayList<>();
        var ca = new Action("C->A", 4, stateA);
        var ci = new Action("C->I", 50, stateI);
        Collections.addAll(actionsC, ca, ci);
        stateActions.put(stateC, actionsC);
        /**
         * Actions for node D
         */
        List<Action> actionsD = new ArrayList<>();
        var db = new Action("D->B", 4, stateB);
        var di = new Action("D->I", 50, stateI);
        Collections.addAll(actionsD, db, di);
        stateActions.put(stateD, actionsD);

        /**
         * Actions for node E
         */
        List<Action> actionsE = new ArrayList<>();
        var es = new Action("E->S", 14, stateS);
        var eh = new Action("E->H", 16, stateH);
        Collections.addAll(actionsE, es, eh);
        stateActions.put(stateE, actionsE);

        /**
         * Actions for node F
         */
        List<Action> actionsF = new ArrayList<>();
        var fs = new Action("F->S", 4, stateS);
        var fh = new Action("F->H", 16, stateH);
        Collections.addAll(actionsF, fs, fh);
        stateActions.put(stateF, actionsF);

        /**
         * Actions for node H
         */
        List<Action> actionsH = new ArrayList<>();
        var he = new Action("H->E", 16, stateE);
        var hf = new Action("H->F", 16, stateF);
        var hi = new Action("H->I", 30, stateI);
        Collections.addAll(actionsH, he, hf, hi);
        stateActions.put(stateH, actionsH);

        /**
         * Actions for node I
         */
        List<Action> actionsI = new ArrayList<>();
        var ic = new Action("I->C", 50, stateC);
        var id = new Action("I->D", 50, stateD);
        var ih = new Action("I->H", 30, stateH);
        var ik = new Action("I->K", 10, stateK);

        Collections.addAll(actionsI, ic, id, ih, ik);

        stateActions.put(stateI, actionsI);
        /**
         * Actions for node K
         */
        List<Action> actionsK = new ArrayList<>();
        var ki = new Action("K->I", 10, stateI);
        actionsK.add(ki);
        stateActions.put(stateK, actionsK);


        Problem problem = new Problem(stateActions);
        problem.setStartState(stateS);
        problem.setGoalState(stateK);

        return problem;
    }

    private static Problem problemExample3() {
        var stateS = new State("S");
        var stateA = new State("A", 60);
        var stateB = new State("B", 51);
        var stateC = new State("C", 55);
        var stateD = new State("D", 50);
        var stateE = new State("E", 56);
        var stateF = new State("F", 50);
        var stateH = new State("H", 39);
        var stateI = new State("I", 0);
        var stateK = new State("K", 0);

        HashMap<State, List<Action>> stateActions = new HashMap<>();

        /**
         * Actions for node S
         */
        List<Action> actionsS = new ArrayList<>();
        var sa = new Action("S->A", 100, stateA);
        var sb = new Action("S->B", 2, stateB);
        var se = new Action("S->E", 14, stateE);
        var sf = new Action("S->F", 4, stateF);
        Collections.addAll(actionsS, sa, sb, se, sf);
        stateActions.put(stateS, actionsS);

        /**
         * Actions for node A
         */
        List<Action> actionsA = new ArrayList<>();
        var as = new Action("A->S", 100, stateS);
        var ac = new Action("A->C", 4, stateC);
        Collections.addAll(actionsA, as, ac);
        stateActions.put(stateA, actionsA);

        /**
         * Actions for node B
         */
        List<Action> actionsB = new ArrayList<>();
        var bs = new Action("B->S", 2, stateS);
        var bd = new Action("B->D", 4, stateD);
        Collections.addAll(actionsB, bs, bd);
        stateActions.put(stateB, actionsB);

        /**
         * Actions for node C
         */
        List<Action> actionsC = new ArrayList<>();
        var ca = new Action("C->A", 4, stateA);
        var ci = new Action("C->I", 50, stateI);
        Collections.addAll(actionsC, ca, ci);
        stateActions.put(stateC, actionsC);
        /**
         * Actions for node D
         */
        List<Action> actionsD = new ArrayList<>();
        var db = new Action("D->B", 4, stateB);
        var di = new Action("D->I", 50, stateI);
        Collections.addAll(actionsD, db, di);
        stateActions.put(stateD, actionsD);

        /**
         * Actions for node E
         */
        List<Action> actionsE = new ArrayList<>();
        var es = new Action("E->S", 14, stateS);
        var eh = new Action("E->H", 16, stateH);
        Collections.addAll(actionsE, es, eh);
        stateActions.put(stateE, actionsE);

        /**
         * Actions for node F
         */
        List<Action> actionsF = new ArrayList<>();
        var fs = new Action("F->S", 4, stateS);
        var fh = new Action("F->H", 16, stateH);
        Collections.addAll(actionsF, fs, fh);
        stateActions.put(stateF, actionsF);

        /**
         * Actions for node H
         */
        List<Action> actionsH = new ArrayList<>();
        var he = new Action("H->E", 16, stateE);
        var hf = new Action("H->F", 16, stateF);
        var hi = new Action("H->I", 30, stateI);
        Collections.addAll(actionsH, he, hf, hi);
        stateActions.put(stateH, actionsH);

        /**
         * Actions for node I
         */
        List<Action> actionsI = new ArrayList<>();
        var ic = new Action("I->C", 50, stateC);
        var id = new Action("I->D", 50, stateD);
        var ih = new Action("I->H", 30, stateH);
        var ik = new Action("I->K", 10, stateK);

        Collections.addAll(actionsI, ic, id, ih, ik);

        stateActions.put(stateI, actionsI);
        /**
         * Actions for node K
         */
        List<Action> actionsK = new ArrayList<>();
        var ki = new Action("K->I", 10, stateI);
        actionsK.add(ki);
        stateActions.put(stateK, actionsK);

        Problem.Builder builder = new Problem.Builder().start(stateS).goal(stateK).stateActions(stateActions);

        return builder.build();
    }
}
