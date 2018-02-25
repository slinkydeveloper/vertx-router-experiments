package io.slinkydeveloper.bench.skiplistrouter;

import io.slinkydeveloper.bench.Route;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public abstract class SkipListBaseRoute implements Comparable, Route {

    public abstract boolean matches(String path);

    @Override
    public int compareTo(@NotNull Object o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public abstract String toString();

    @Override
    public boolean isRegexRoute() { return this instanceof SkipListRegexRoute; }

    @Override
    public boolean isConstantRoute() { return this instanceof SkipListConstantRoute; }

    @Override
    public boolean isParametrizedRoute() {return this instanceof SkipListParametrizedRoute; }
}
