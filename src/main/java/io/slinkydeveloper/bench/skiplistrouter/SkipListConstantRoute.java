package io.slinkydeveloper.bench.skiplistrouter;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class SkipListConstantRoute extends SkipListBaseRoute {

    String path;

    public SkipListConstantRoute(String path) {
        this.path = path;
    }

    public boolean matches(String input) {
        return path.equals(input);
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        return this.path;
    }
}
