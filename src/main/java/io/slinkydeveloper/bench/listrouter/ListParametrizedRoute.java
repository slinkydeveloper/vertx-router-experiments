package io.slinkydeveloper.bench.listrouter;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class ListParametrizedRoute extends ListBaseRoute {

    String startingPath;
    List<String> splittedPath;

    final static String SPLITTER_REGEX = Pattern.quote("/");

    public ListParametrizedRoute(String path) {
        this.startingPath = path;
        this.splittedPath = Arrays.asList(path.split(SPLITTER_REGEX));
    }

    public boolean matches(String input) {
        List<String> splittedInput = Arrays.asList(input.split(SPLITTER_REGEX));
        if (splittedInput.size() != splittedPath.size()) return false;
        for (int i = 0; i < this.splittedPath.size(); i++) {

            if (!this.splittedPath.get(i).contains(":") && !this.splittedPath.get(i).equals(splittedInput.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        return this.startingPath;
    }
}
