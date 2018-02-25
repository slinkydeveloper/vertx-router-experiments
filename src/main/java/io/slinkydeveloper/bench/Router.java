package io.slinkydeveloper.bench;

import java.util.Optional;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public interface Router {

    /**
     * The return value is empty if no route matches or contains the last Route matching
     * @param route
     * @return
     */
    Optional<Route> route(String route);

}
