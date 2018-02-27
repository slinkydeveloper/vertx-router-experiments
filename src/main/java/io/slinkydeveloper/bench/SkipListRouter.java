package io.slinkydeveloper.bench;

import io.slinkydeveloper.bench.skiplistrouter.SkipListBaseRoute;
import io.slinkydeveloper.bench.skiplistrouter.SkipListConstantRoute;
import io.slinkydeveloper.bench.skiplistrouter.SkipListParametrizedRoute;
import io.slinkydeveloper.bench.skiplistrouter.SkipListRegexRoute;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.regex.Pattern;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class SkipListRouter implements Router {

    ConcurrentSkipListSet<SkipListBaseRoute> routes;

    public SkipListRouter() {
        routes = new ConcurrentSkipListSet<>();
    }

    @Override
    public void addConstantRoute(String s) {
        routes.add(new SkipListConstantRoute(s));
    }

    @Override
    public void addParametrizedRoute(String s) {
        routes.add(new SkipListParametrizedRoute(s));
    }

    @Override
    public void addRegexRoute(Pattern r) {
        routes.add(new SkipListRegexRoute(r));
    }

    public boolean route(String path) {
        for (SkipListBaseRoute route : routes) {
            if (route.matches(path))
                return true;
        }
        return false;
    }
}
