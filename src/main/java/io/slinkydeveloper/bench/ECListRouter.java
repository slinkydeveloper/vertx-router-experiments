package io.slinkydeveloper.bench;

import io.slinkydeveloper.bench.listrouter.ListBaseRoute;
import io.slinkydeveloper.bench.listrouter.ListConstantRoute;
import io.slinkydeveloper.bench.listrouter.ListParametrizedRoute;
import io.slinkydeveloper.bench.listrouter.ListRegexRoute;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.regex.Pattern;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class ECListRouter implements Router {

    MutableList<ListBaseRoute> routes;

    public ECListRouter() {
        routes = Lists.mutable.empty();
    }

    @Override
    public boolean route(String path) {
        for (ListBaseRoute route : routes) {
            if (route.matches(path))
                return true;
        }
        return false;
    }

    @Override
    public void addRegexRoute(Pattern p) {
        routes.add(new ListRegexRoute(p));
    }

    @Override
    public void addConstantRoute(String s) {
        routes.add(new ListConstantRoute(s));
    }

    @Override
    public void addParametrizedRoute(String s) {
        routes.add(new ListParametrizedRoute(s));
    }
}
