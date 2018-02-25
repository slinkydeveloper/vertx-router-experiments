package io.slinkydeveloper.bench.ectreerouter;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class ECConstantNode extends ECBaseNode {

    String path;

    public ECConstantNode(String path) {
        this.path = path;
    }

    @Override
    public boolean route(String pathChunk) {
        if (pathChunk.startsWith(path))
            // Run handler
            return goDeep(pathChunk.substring(path.length()));
        else
            return false;
    }
}
