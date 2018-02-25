package io.slinkydeveloper.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;

@Warmup(iterations = 10)
@Measurement(iterations = 30)
@Fork(1)
public class SkipListRouterBenchmark {

    @State(Scope.Thread)
    public static class RouterState {

        @Setup(Level.Iteration)
        public void doSetup() {
            BenchmarkStuff stuff = new BenchmarkStuff();
            compatiblePaths = stuff.getCompatiblePaths();
            probabilitiesForRandom = stuff.getProbabilitiesForRandom();

            router = new SkipListRouter().initializeForBench();
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
    public void route1SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/feed"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route1SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/feed"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route2SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/popular"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route2SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/popular"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route3SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route3SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route4SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/events"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route4SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/events"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route5SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/likes"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route5SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/likes"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route6SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/pages"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route6SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/pages"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route7SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/friends"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route7SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/friends"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route8SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/users/user1/feed"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route8SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/users/user1/feed"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route9SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/popular"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route9SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/popular"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route10SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/post1/tagged"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route10SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/post1/tagged"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route11SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/post1/photos"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route11SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/post1/photos"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route12SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/posts/post1/photos/photo1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route12SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/posts/post1/photos/photo1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route13SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/popular"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route13SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/popular"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route14SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/event1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route14SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/event1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route15SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/event1/partecipants"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route15SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/event1/partecipants"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route16SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/event1/invited"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route16SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/event1/invited"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route17SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/events/event1/feed"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route17SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/events/event1/feed"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route18SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/popular"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route18SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/popular"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route19SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route19SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route20SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1/likes"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route20SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1/likes"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route21SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1/events"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route21SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1/events"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route22SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1/feed"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route22SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1/feed"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route23SkipListRouter(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("/pages/page1/feed/post1"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route23SkipListRouterWithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
        bh.consume(routerState.router.route("/pages/page1/feed/post1"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routerState.randomPath));
    }

}