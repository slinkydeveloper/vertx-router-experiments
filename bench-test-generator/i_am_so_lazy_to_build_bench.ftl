<#assign val = JsonPath.parse(documents[0].content).read("$") + JsonPath.parse(documents[1].content).read("$")>
package io.slinkydeveloper.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;

@Warmup(iterations = 10)
@Measurement(iterations = 30)
@Fork(1)
public class ${val.routerType}Benchmark {

    @State(Scope.Thread)
    public static class RouterState {

        @Setup(Level.Iteration)
        public void doSetup() {
            BenchmarkStuff stuff = new BenchmarkStuff();
            compatiblePaths = stuff.getCompatiblePaths();
            probabilitiesForRandom = stuff.getProbabilitiesForRandom();

            router = ${val.routerType}.initializeBench();
        }

        @Setup(Level.Invocation)
        public void switchElement() {
            randomPath = compatiblePaths.get(
                    probabilitiesForRandom.get(
                            new Random().nextInt(probabilitiesForRandom.size())
                    )
            );
        }

        public ${val.routerType} router;
        String randomPath;
        List<String> compatiblePaths;
        List<Integer> probabilitiesForRandom;
    }

    @Benchmark @BenchmarkMode(Mode.Throughput) @Measurement(iterations = 100)
    public void routingRandomRoutes(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route(routers.randomPath));
    }

    <#list val.routes as i>
    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route${i?counter}${val.routerType}(RouterState routerState, Blackhole bh) {
        bh.consume(routerState.router.route("${i}"));
    }

    @Benchmark @BenchmarkMode(Mode.Throughput)
    public void route${i?counter}${val.routerType}WithLoad(RouterState routerState, Blackhole bh) {
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routers.randomPath));
        bh.consume(routerState.router.route("${i}"));
        for (int i = 0; i < 5; i++) bh.consume(routerState.router.route(routers.randomPath));
    }
    </#list>

}