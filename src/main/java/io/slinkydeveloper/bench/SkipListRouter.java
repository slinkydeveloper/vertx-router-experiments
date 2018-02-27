package io.slinkydeveloper.bench;

import io.slinkydeveloper.bench.listrouter.ListBaseRoute;
import io.slinkydeveloper.bench.listrouter.ListConstantRoute;
import io.slinkydeveloper.bench.listrouter.ListParametrizedRoute;
import io.slinkydeveloper.bench.listrouter.ListRegexRoute;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.regex.Pattern;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class SkipListRouter implements Router {

    ConcurrentSkipListSet<ListBaseRoute> routes;

    public SkipListRouter() {
        routes = new ConcurrentSkipListSet<>();
    }

    @Override
    public void addConstantRoute(String s) {
        routes.add(new ListConstantRoute(s));
    }

    @Override
    public void addParametrizedRoute(String s) {
        routes.add(new ListParametrizedRoute(s));
    }

    @Override
    public void addRegexRoute(Pattern r) {
        routes.add(new ListRegexRoute(r));
    }

    public boolean route(String path) {
        for (ListBaseRoute route : routes) {
            if (route.matches(path))
                return true;
        }
        return false;
    }
}
