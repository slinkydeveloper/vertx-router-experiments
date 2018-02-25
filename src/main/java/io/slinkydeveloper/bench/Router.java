package io.slinkydeveloper.bench;

import java.util.Optional;
import java.util.regex.Pattern;

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

    /**
     * This method should manage only regex routes
     * @param p
     */
    void addRoute(Pattern p);

    /**
     * This method should manage constant and parametrized routes
     * @param s
     */
    void addRoute(String s);

    default void initializeForBench() {
        addRoute("/feed");
        addRoute("/users/popular");
        addRoute(Pattern.compile("\\/users\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})"));
        addRoute(Pattern.compile("\\/users\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/events"));
        addRoute(Pattern.compile("\\/users\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/likes"));
        addRoute(Pattern.compile("\\/users\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/pages"));
        addRoute(Pattern.compile("\\/users\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/friends"));
        addRoute(Pattern.compile("\\/users\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/feed"));

        addRoute("/posts/popular");
        addRoute("/posts/:post_id/tagged");
        addRoute("/posts/:post_id/photos");
        addRoute("/posts/:post_id/photos/:photo_id");

        addRoute("/events/popular");
        addRoute("/events/:event_id");
        addRoute("/events/:event_id/partecipants");
        addRoute("/events/:event_id/invited");
        addRoute("/events/:event_id/feed");

        addRoute("/pages/popular");
        addRoute(Pattern.compile("\\/pages\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})"));
        addRoute(Pattern.compile("\\/pages\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/likes"));
        addRoute(Pattern.compile("\\/pages\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/events"));
        addRoute(Pattern.compile("\\/pages\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/feed"));
        addRoute(Pattern.compile("\\/pages\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/feed\\/(?<postid>[a-zA-Z][a-zA-Z0-9]{3,20})"));
    }

}
