package funcgraphdemo.funcgraph;

import java.util.HashMap;

public final class TypeMap<T> extends HashMap<T, T> {

    public TypeMap() {
        this(null);
    }

    public TypeMap(HashMap<T, T> typeMap) {
        super();

        if (typeMap != null) {
            this.putAll(typeMap);
        }
    }
}
