package io.slinkydeveloper.bench;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class ECListRouterTest extends BaseRouterTest {

    @Override
    Router getRouter() {
        return new ECListRouter().initializeForBench();
    }
}
