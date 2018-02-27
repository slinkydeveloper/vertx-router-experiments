package io.slinkydeveloper.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;

@Warmup(iterations = 10)
@Measurement(iterations = 30)
@Fork(1)
public class ECListRouterBenchmark {

    @State(Scope.Thread)
    public static class RouterState {

        @Setup(Level.Iteration)
        public void doSetup() {
            BenchmarkStuff stuff = new BenchmarkStuff();
            compatiblePaths = stuff.getCompatiblePaths();
            probabilitiesForRandom = stuff.getProbabilitiesForRandom();

            router = new ECListRouter().initializeForBench();
        }

        @Setup(Level.Invocation)
        public void switchElement() {
            randomPath = compatiblePaths.get(
                    probabilitiesForRandom.get(
                            new Random().nextInt(probabilitiesForRandom.size())
                    )
            );
        }

        public Router router;
        String randomPath;
        List<String> compatiblePaths;
        List<Integer> probabilitiesForRandom;
    }

    @Benchmark @BenchmarkMode(Mode.Throughput) @Measurement(iterations = 100)
    public void routingRandomRoutes(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route(routerState.randomPath));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route1ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/feed"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route1ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/feed"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route2ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/popular"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route2ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/popular"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route3ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route3ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route4ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/events"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route4ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/events"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route5ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/likes"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route5ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/likes"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route6ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/pages"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route6ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/pages"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route7ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/friends"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route7ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/friends"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route8ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/feed"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route8ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/feed"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route9ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/popular"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route9ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/popular"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route10ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/post1/tagged"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route10ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/post1/tagged"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route11ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/post1/photos"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route11ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/post1/photos"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route12ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/post1/photos/photo1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route12ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/post1/photos/photo1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route13ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/popular"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route13ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/popular"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route14ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/event1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route14ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/event1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route15ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/event1/partecipants"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route15ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/event1/partecipants"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route16ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/event1/invited"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route16ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/event1/invited"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route17ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/event1/feed"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route17ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/event1/feed"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route18ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/popular"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route18ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/popular"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route19ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route19ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route20ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1/likes"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route20ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1/likes"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route21ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1/events"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route21ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1/events"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route22ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1/feed"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route22ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1/feed"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route23ECListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1/feed/post1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route23ECListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1/feed/post1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }

}