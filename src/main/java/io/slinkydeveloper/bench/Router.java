package io.slinkydeveloper.bench;

import java.util.regex.Pattern;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public interface Router {

    boolean route(String route);

    /**
     * This method should manage only regex routes
     * @param p
     */
    void addRegexRoute(Pattern p);

    /**
     * This method should manage constant routes
     * @param s
     */
    void addConstantRoute(String s);

    /**
     * This method should manage parametrized routes
     * @param s
     */
    void addParametrizedRoute(String s);

    /**
     * This method should manage parametrized and constant routes
     * @param s
     */
    default void addRoute(String s) {
        if (s.contains(":"))
            addParametrizedRoute(s);
        else
            addConstantRoute(s);
    }

    default Router initializeForBench() {
        addConstantRoute("/feed");
        addConstantRoute("/users/popular");
        addRegexRoute(Pattern.compile("\\/users\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})"));
        addRegexRoute(Pattern.compile("\\/users\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/events"));
        addRegexRoute(Pattern.compile("\\/users\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/likes"));
        addRegexRoute(Pattern.compile("\\/users\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/pages"));
        addRegexRoute(Pattern.compile("\\/users\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/friends"));
        addRegexRoute(Pattern.compile("\\/users\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/feed"));

        addConstantRoute("/posts/popular");
        addParametrizedRoute("/posts/:post_id/tagged");
        addParametrizedRoute("/posts/:post_id/photos");
        addParametrizedRoute("/posts/:post_id/photos/:photo_id");

        addConstantRoute("/events/popular");
        addParametrizedRoute("/events/:event_id");
        addParametrizedRoute("/events/:event_id/partecipants");
        addParametrizedRoute("/events/:event_id/invited");
        addParametrizedRoute("/events/:event_id/feed");

        addConstantRoute("/pages/popular");
        addRegexRoute(Pattern.compile("\\/pages\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})"));
        addRegexRoute(Pattern.compile("\\/pages\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/likes"));
        addRegexRoute(Pattern.compile("\\/pages\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/events"));
        addRegexRoute(Pattern.compile("\\/pages\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/feed"));
        addRegexRoute(Pattern.compile("\\/pages\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})\\/feed\\/(?<postid>[a-zA-Z][a-zA-Z0-9]{3,20})"));

        return this;
    }

}
