package io.slinkydeveloper.bench.skiplistrouter;

import io.slinkydeveloper.bench.Route;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class SkipListRegexRoute extends SkipListBaseRoute {

    Pattern regex;

    public SkipListRegexRoute(String regex) {
        this.regex = Pattern.compile(regex);
    }

    public SkipListRegexRoute(Pattern regex) {
        this.regex = regex;
    }

    public boolean matches(String path) {
        Matcher m = regex.matcher(path);
        if (m.lookingAt()) {
            // Run handler
            return m.end() == path.length(); // Only if perfectly match, stop routing
        }
        return false;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        return regex.toString();
    }
}
