package ai.classicalsearch.parser;

import ai.classicalsearch.model.Problem;

import java.io.File;

public abstract class ProblemParser {

    protected File file;

    public ProblemParser(File file) {
        this.file = file;
    }

    public abstract Problem parse();

}
