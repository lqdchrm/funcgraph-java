package funcgraphdemo.funcgraph.runtime;

import java.util.HashMap;

public final class TypeMap extends HashMap<Class<? extends Block>, Class<? extends Block>>{

    public TypeMap() {
        this(null);
    }
    
    public TypeMap(HashMap<Class<? extends Block>, Class<? extends Block>> typeMap) {
        super();
        
        if (typeMap != null) {
            this.putAll(typeMap);
        }
    }
}
