package io.slinkydeveloper.bench.ectreerouter;

import io.slinkydeveloper.bench.Route;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public abstract class ECBaseNode implements Route, Comparable {
    MutableList<ECBaseNode> childs;

    public ECBaseNode() {
        childs = new FastList<>();
        childs = childs.asSynchronized();
    }

    public void addNode(ECBaseNode node) {
        childs.add(node);
    }

    public abstract boolean route(String pathChunk);

    public boolean goDeep(String pathChunk) {
        if (pathChunk.length() != 0) {
            for (ECBaseNode child : childs)
                if (child.route(pathChunk))
                    return true;
            return false;
        } else
            return true;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public boolean isRegexRoute() {
        return (this instanceof ECRegexNode);
    }

    @Override
    public boolean isConstantRoute() {
        return (this instanceof ECConstantNode);
    }

    @Override
    public boolean isParametrizedRoute() {
        return (this instanceof ECParametrizedNode);
    }
}