package io.slinkydeveloper.bench;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public interface Route {

    boolean isRegexRoute();
    boolean isConstantRoute();
    boolean isParametrizedRoute();

    String getPath();
    String getPattern();

}
