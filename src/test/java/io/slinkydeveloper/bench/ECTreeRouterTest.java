package io.slinkydeveloper.bench;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class ECTreeRouterTest {

    Router router = new ECTreeRouter().initializeForBench();

    @Test
    public void testPath1() {
        assertTrue(router.route("/feed"));
    }

    @Test
    public void testPath2() {
        assertTrue(router.route("/users/popular"));
    }

    @Test
    public void testPath3() {
        assertTrue(router.route("/users/user1"));
    }

    @Test
    public void testPath4() {
        assertTrue(router.route("/users/user1/events"));
    }

    @Test
    public void testPath5() {
        assertTrue(router.route("/users/user1/likes"));
    }

    @Test
    public void testPath6() {
        assertTrue(router.route("/users/user1/pages"));
    }

    @Test
    public void testPath7() {
        assertTrue(router.route("/users/user1/friends"));
    }

    @Test
    public void testPath8() {
        assertTrue(router.route("/users/user1/feed"));
    }

    @Test
    public void testPath9() {
        assertTrue(router.route("/posts/popular"));
    }

    @Test
    public void testPath10() {
        assertTrue(router.route("/posts/post1/tagged"));
    }

    @Test
    public void testPath11() {
        assertTrue(router.route("/posts/post1/photos"));
    }

    @Test
    public void testPath12() {
        assertTrue(router.route("/posts/post1/photos/photo1"));
    }

    @Test
    public void testPath13() {
        assertTrue(router.route("/events/popular"));
    }

    @Test
    public void testPath14() {
        assertTrue(router.route("/events/event1"));
    }

    @Test
    public void testPath15() {
        assertTrue(router.route("/events/event1/partecipants"));
    }

    @Test
    public void testPath16() {
        assertTrue(router.route("/events/event1/invited"));
    }

    @Test
    public void testPath17() {
        assertTrue(router.route("/events/event1/feed"));
    }

    @Test
    public void testPath18() {
        assertTrue(router.route("/pages/popular"));
    }

    @Test
    public void testPath19() {
        assertTrue(router.route("/pages/page1"));
    }

    @Test
    public void testPath20() {
        assertTrue(router.route("/pages/page1/likes"));
    }

    @Test
    public void testPath21() {
        assertTrue(router.route("/pages/page1/events"));
    }

    @Test
    public void testPath22() {
        assertTrue(router.route("/pages/page1/feed"));
    }

    @Test
    public void testPath23() {
        assertTrue(router.route("/pages/page1/feed/post1"));
    }


    @Test
    public void testNotFound1() {
        assertFalse(router.route("/posts/post1"));
    }

}
