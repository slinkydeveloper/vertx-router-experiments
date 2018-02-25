package io.slinkydeveloper.bench.ectreerouter;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class ECParametrizedNode extends ECBaseNode {

    String parameterName;

    public ECParametrizedNode(String path, boolean canStopHere) {
        super(canStopHere);
        this.parameterName = path.substring(2); // /:paramName
    }

    @Override
    public boolean route(String pathChunk) {
        if (pathChunk.isEmpty())
            return false; // Should contain something

        pathChunk = pathChunk.substring(1); // Remove /
        int indexOf = pathChunk.indexOf("/");
        if (indexOf == -1)
            return canStopHere;
        else
            return goDeep(pathChunk.substring(indexOf));
    }

}
