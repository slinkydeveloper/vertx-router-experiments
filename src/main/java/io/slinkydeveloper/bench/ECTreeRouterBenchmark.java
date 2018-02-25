package io.slinkydeveloper.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;

@Warmup(iterations = 10)
@Measurement(iterations = 30)
@Fork(1)
public class ECTreeRouterBenchmark {

    @State(Scope.Thread)
    public static class RouterState {

        @Setup(Level.Iteration)
        public void doSetup() {
            BenchmarkStuff stuff = new BenchmarkStuff();
            compatiblePaths = stuff.getCompatiblePaths();
            probabilitiesForRandom = stuff.getProbabilitiesForRandom();

            router = new ECTreeRouter().initializeForBench();
        }

        @Setup(Level.Invocation)
        public void switchElement() {
            randomPath = compatiblePaths.get(
                    probabilitiesForRandom.get(
                            new Random().nextInt(probabilitiesForRandom.size())
                    )
            );
        }

        public ECTreeRouter router;
        String randomPath;
        List<String> compatiblePaths;
        List<Integer> probabilitiesForRandom;
    }

    @Benchmark @BenchmarkMode(Mode.Throughput) @Measurement(iterations = 100)
    public void routingRandomRoutes(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route(routerState.randomPath));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route1ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/feed"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route1ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/feed"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route2ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/popular"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route2ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/popular"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route3ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route3ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route4ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/events"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route4ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/events"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route5ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/likes"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route5ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/likes"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route6ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/pages"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route6ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/pages"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route7ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/friends"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route7ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/friends"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route8ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/feed"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route8ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/feed"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route9ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/popular"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route9ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/popular"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route10ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/post1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route10ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/post1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route11ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/post1/tagged"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route11ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/post1/tagged"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route12ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/post1/photos"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route12ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/post1/photos"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route13ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/post1/photos/photo1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route13ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/post1/photos/photo1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route14ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/popular"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route14ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/popular"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route15ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/event1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route15ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/event1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route16ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/event1/partecipants"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route16ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/event1/partecipants"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route17ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/event1/invited"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route17ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/event1/invited"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route18ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/event1/feed"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route18ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/event1/feed"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route19ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/popular"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route19ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/popular"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route20ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route20ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route21ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1/likes"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route21ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1/likes"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route22ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1/events"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route22ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1/events"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route23ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1/feed"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route23ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1/feed"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route24ECTreeRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1/feed/post1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route24ECTreeRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1/feed/post1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }

}