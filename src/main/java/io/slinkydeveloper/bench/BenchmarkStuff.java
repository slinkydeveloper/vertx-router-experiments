package io.slinkydeveloper.bench;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class BenchmarkStuff {

    List<String> compatiblePaths;
    List<Integer> probabilitiesForRandom;

    public BenchmarkStuff() {
        try {
            JsonNode node = new ObjectMapper().readTree(new File("src/main/resources/paths.json"));
            compatiblePaths = StreamSupport.stream(node.get("paths").spliterator(), false)
                    .map(JsonNode::asText)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        probabilitiesForRandom = new ArrayList<>();

        probabilitiesForRandom.add(0);
        probabilitiesForRandom.add(0);
        probabilitiesForRandom.add(0);
        probabilitiesForRandom.add(0);
        probabilitiesForRandom.add(0);
        probabilitiesForRandom.add(0);
        probabilitiesForRandom.add(0);
        probabilitiesForRandom.add(0);

        probabilitiesForRandom.add(1);
        probabilitiesForRandom.add(1);

        probabilitiesForRandom.add(2);
        probabilitiesForRandom.add(2);
        probabilitiesForRandom.add(2);
        probabilitiesForRandom.add(2);

        probabilitiesForRandom.add(3);

        probabilitiesForRandom.add(4);

        probabilitiesForRandom.add(5);

        probabilitiesForRandom.add(6);

        probabilitiesForRandom.add(7);
        probabilitiesForRandom.add(7);

        probabilitiesForRandom.add(8);
        probabilitiesForRandom.add(8);

        probabilitiesForRandom.add(9);

        probabilitiesForRandom.add(10);

        probabilitiesForRandom.add(11);

        probabilitiesForRandom.add(12);

        probabilitiesForRandom.add(13);
        probabilitiesForRandom.add(13);
        probabilitiesForRandom.add(13);

        probabilitiesForRandom.add(14);
        probabilitiesForRandom.add(14);

        probabilitiesForRandom.add(15);
        probabilitiesForRandom.add(15);

        probabilitiesForRandom.add(16);

        probabilitiesForRandom.add(17);
        probabilitiesForRandom.add(17);

        probabilitiesForRandom.add(18);
        probabilitiesForRandom.add(18);
        probabilitiesForRandom.add(18);
        probabilitiesForRandom.add(18);

        probabilitiesForRandom.add(19);
        probabilitiesForRandom.add(19);
        probabilitiesForRandom.add(19);
        probabilitiesForRandom.add(19);

        probabilitiesForRandom.add(20);

        probabilitiesForRandom.add(21);

        probabilitiesForRandom.add(22);
        probabilitiesForRandom.add(22);
        probabilitiesForRandom.add(22);

        probabilitiesForRandom.add(23);

        Collections.shuffle(probabilitiesForRandom);
    }

    public List<String> getCompatiblePaths() {
        return compatiblePaths;
    }

    public List<Integer> getProbabilitiesForRandom() {
        return probabilitiesForRandom;
    }

    public static BenchmarkStuff getBenchmarkStuff() {
        return new BenchmarkStuff();
    }


}
