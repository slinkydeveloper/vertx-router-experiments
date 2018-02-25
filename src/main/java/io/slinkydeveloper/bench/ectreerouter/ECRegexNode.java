package io.slinkydeveloper.bench.ectreerouter;

import io.slinkydeveloper.bench.Route;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class ECRegexNode extends ECBaseNode {

    Pattern pattern;

    public ECRegexNode(Pattern pattern, boolean canStopHere) {
        super(canStopHere);
        this.pattern = pattern;
    }

    public ECRegexNode(String pattern, boolean canStopHere) {
        super(canStopHere);
        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public boolean route(String pathChunk) {
        Matcher m = pattern.matcher(pathChunk);
        if (m.lookingAt()) {
            pathChunk = pathChunk.substring(m.end());
            return this.goDeep(pathChunk);
        }
        return false;
    }
}
