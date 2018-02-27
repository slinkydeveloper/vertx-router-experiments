package io.slinkydeveloper.bench.listrouter;

import io.slinkydeveloper.bench.Route;
import org.jetbrains.annotations.NotNull;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public abstract class ListBaseRoute implements Comparable, Route {

    public abstract boolean matches(String path);

    @Override
    public int compareTo(@NotNull Object o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public abstract String toString();

    @Override
    public boolean isRegexRoute() { return this instanceof ListRegexRoute; }

    @Override
    public boolean isConstantRoute() { return this instanceof ListConstantRoute; }

    @Override
    public boolean isParametrizedRoute() {return this instanceof ListParametrizedRoute; }
}
