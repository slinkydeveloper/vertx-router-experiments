package io.slinkydeveloper.bench;

import io.slinkydeveloper.bench.ectreerouter.ECBaseNode;
import io.slinkydeveloper.bench.ectreerouter.ECConstantNode;
import io.slinkydeveloper.bench.ectreerouter.ECParametrizedNode;
import io.slinkydeveloper.bench.ectreerouter.ECRegexNode;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class ECTreeRouter implements Router {

    ECBaseNode root;

    public ECTreeRouter() { }

    public boolean route(String route) {
        return this.root.goDeep(route);
    }

    public void setRoot(ECBaseNode root) {
        this.root = root;
    }

    @Override
    public ECTreeRouter initializeForBench() {
        // Root level
        ECBaseNode root = new ECConstantNode("", false);
        setRoot(root);

        // First level
        ECBaseNode n11 = new ECConstantNode("/feed", true);
        ECBaseNode n12 = new ECConstantNode("/users", false);
        ECBaseNode n13 = new ECConstantNode("/posts", false);
        ECBaseNode n14 = new ECConstantNode("/events", false);
        ECBaseNode n15 = new ECConstantNode("/pages", false);

        root.addNode(n11);
        root.addNode(n12);
        root.addNode(n13);
        root.addNode(n14);
        root.addNode(n15);

        // Second level
        ECBaseNode n12_1 = new ECRegexNode("\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})", true);
        ECBaseNode n12_2 = new ECConstantNode("/popular", true);
        ECBaseNode n13_1 = new ECParametrizedNode("/:post_id", false);
        ECBaseNode n13_2 = new ECConstantNode("/popular", true);
        ECBaseNode n14_1 = new ECParametrizedNode("/:event_id", true);
        ECBaseNode n14_2 = new ECConstantNode("/popular", true);
        ECBaseNode n15_1 = new ECRegexNode("\\/(?<id>[a-zA-Z][a-zA-Z0-9]{3,20})", true);
        ECBaseNode n15_2 = new ECConstantNode("/popular", true);

        n12.addNode(n12_2);
        n12.addNode(n12_1);
        n13.addNode(n13_2);
        n13.addNode(n13_1);
        n14.addNode(n14_2);
        n14.addNode(n14_1);
        n15.addNode(n15_2);
        n15.addNode(n15_1);

        // Third level
        ECBaseNode n12_1_1 = new ECConstantNode("/events", true);
        ECBaseNode n12_1_2 = new ECConstantNode("/likes", true);
        ECBaseNode n12_1_3 = new ECConstantNode("/pages", true);
        ECBaseNode n12_1_4 = new ECConstantNode("/friends", true);
        ECBaseNode n12_1_5 = new ECConstantNode("/feed", true);

        n12_1.addNode(n12_1_1);
        n12_1.addNode(n12_1_2);
        n12_1.addNode(n12_1_3);
        n12_1.addNode(n12_1_4);
        n12_1.addNode(n12_1_5);

        ECBaseNode n13_1_1 = new ECConstantNode("/tagged", true);
        ECBaseNode n13_1_2 = new ECConstantNode("/photos", true);

        n13_1.addNode(n13_1_1);
        n13_1.addNode(n13_1_2);

        ECBaseNode n14_1_1 = new ECConstantNode("/partecipants", true);
        ECBaseNode n14_1_2 = new ECConstantNode("/invited", true);
        ECBaseNode n14_1_3 = new ECConstantNode("/feed", true);

        n14_1.addNode(n14_1_1);
        n14_1.addNode(n14_1_2);
        n14_1.addNode(n14_1_3);

        ECBaseNode n15_1_1 = new ECConstantNode("/likes", true);
        ECBaseNode n15_1_2 = new ECConstantNode("/events", true);
        ECBaseNode n15_1_3 = new ECConstantNode("/feed", true);

        n15_1.addNode(n15_1_1);
        n15_1.addNode(n15_1_2);
        n15_1.addNode(n15_1_3);

        // Forth level
        ECBaseNode n13_1_2_1 = new ECParametrizedNode("/:photo_id", true);
        n13_1_2.addNode(n13_1_2_1);

        ECBaseNode n15_1_3_1 = new ECRegexNode("\\/(?<postid>[a-zA-Z][a-zA-Z0-9]{3,20})", true);
        n15_1_3.addNode(n15_1_3_1);

        return this;
    }

}
