package io.slinkydeveloper.bench.listrouter;

import org.jetbrains.annotations.NotNull;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class ListConstantRoute extends ListBaseRoute {

    String path;

    public ListConstantRoute(String path) {
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
