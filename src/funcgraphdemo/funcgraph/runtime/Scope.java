package funcgraphdemo.funcgraph.runtime;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class Scope {
    
    private final HashMap<Class<? extends Block>, Block> knownBlocks;
    private final TypeMap typeMap;
    
    public Scope() {
        this(null);
    }
    
    public Scope(TypeMap typeMap) {
        this.knownBlocks = new HashMap<>();
        this.typeMap = new TypeMap(typeMap);
    }
        
    public final TypeMap getTypeMap() { return this.typeMap; }
    
    protected final void add(Block block) {
        if (!knownBlocks.containsKey(block.getClass())) {
            knownBlocks.put(block.getClass(), block);
        }
    }
    
    public final <T> Block<T> get(Class<? extends Block<T>> type) {
        Block result = getUntyped(type);
        return (Block<T>)result;
    }
    
    protected final Block getUntyped(Class<? extends Block> type) {
        if (typeMap.containsKey(type))
            type = typeMap.get(type);
        
        if (!knownBlocks.containsKey(type)){
            try {
                Block<?> block = type.getConstructor(Scope.class).newInstance(this);
                knownBlocks.put(type, block);
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Scope.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return knownBlocks.get(type);
    }
    
    public final Collection<Block> getSuccessorsFor(Block block) {
        return knownBlocks.values()
                .stream()
                .filter(b -> b.dependsOn().contains(block))
                .collect(Collectors.toList());
    }
       
    @Override
    public String toString() {
        return "\n" + this.knownBlocks
                .values()
                .stream()
                .map(e -> e.toString())
                .collect(Collectors.joining("\n"));
    }
}