package funcgraphdemo.funcgraph;

import funcgraphdemo.common.Calculation;
import funcgraphdemo.common.DependsOn;
import funcgraphdemo.common.ValueProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class Scope implements ValueProvider {

    private final HashMap<Class<? extends Calculation<? extends ValueProvider, ?>>, Block<?>> knownBlocks;
    private final HashMap<Class<? extends Calculation<? extends ValueProvider, ?>>, List<Class<? extends Calculation<? extends ValueProvider, ?>>>> blockDeps;
    private final TypeMap<Class<? extends Calculation<? extends ValueProvider, ?>>> typeMap;

    public Scope() {
        this(null);
    }

    public Scope(TypeMap typeMap) {
        this.knownBlocks = new HashMap<>();
        this.blockDeps = new HashMap<>();
        this.typeMap = new TypeMap(typeMap);
    }

    public final TypeMap getTypeMap() {
        return this.typeMap;
    }
    
    private Class<? extends Calculation<? extends ValueProvider, ?>> mapType(Class<? extends Calculation<? extends ValueProvider, ?>> type) {
        if (typeMap.containsKey(type))
            return typeMap.get(type);
        
        return type;
    }

    private <T> Block<T> getBlock(Class<? extends Calculation<? extends ValueProvider, T>> type) {
                
        // apply typemap
        type = (Class<? extends Calculation<? extends ValueProvider, T>>)mapType(type);
        
        // get or create block
        if (!knownBlocks.containsKey(type)) {
            Block<T> block = createBlock(type);
            knownBlocks.put(type, block);
            blockDeps.put(type, getCalcDeps(type));
        }

        return (Block<T>)knownBlocks.get(type);
    }
    
    private <T> Block<T> createBlock(Class<? extends Calculation<? extends ValueProvider, T>> type) {
        
        try {
            Calculation<? extends ValueProvider, T> calc = type.newInstance();
            Block<T> block = new Block(this, calc);
            return block;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Scope.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException();
        }
    }
    
    private List<Class<? extends Calculation<? extends ValueProvider, ?>>> getCalcDeps(Class<? extends Calculation<? extends ValueProvider, ?>> type) {
        
        if (type.isAnnotationPresent(DependsOn.class)) {
            Class<? extends Calculation<? extends ValueProvider, ?>>[] types = type.getAnnotation(DependsOn.class).value();
            Collection<Class<? extends Calculation<? extends ValueProvider, ?>>> _types = Arrays.asList(types);
            
            return _types.stream().map(t -> mapType(t)).collect(Collectors.toList());
        }
        
        return new ArrayList<>();
    }
    
    @Override
    public <T> T get(Class<? extends Calculation<? extends ValueProvider, T>> type) {
        Block<T> block = getBlock(type);
        return block.get();
    }

    @Override
    public <T> void set(Class<? extends Calculation<? extends ValueProvider, T>> type, T value) {
        Block<T> block = getBlock(type);
        block.set(value);
    }

    @Override
    public Collection<Class<? extends Calculation<? extends ValueProvider, ?>>> dependsOn(Class<? extends Calculation<? extends ValueProvider, ?>> type) {
        return blockDeps.get(mapType(type));
    }

    @Override
    public Collection<Class<? extends Calculation<? extends ValueProvider, ?>>> usedBy(Class<? extends Calculation<? extends ValueProvider, ?>> type) {
        return blockDeps.entrySet()
                .stream()
                .filter(e -> e.getValue().contains(mapType(type)))
                .map(e -> e.getKey())
                .collect(Collectors.toList());
    }
        
    protected final Collection<Block<?>> getPredecessors(Block<?> block) {
        Class<? extends Calculation<? extends ValueProvider, ?>> calc = (Class<? extends Calculation<? extends ValueProvider, ?>>) block.calc.getClass();
        Collection<Class<? extends Calculation<? extends ValueProvider, ?>>> dependencies = dependsOn(calc);
        
        return dependencies.stream().map(d -> knownBlocks.get(d)).collect(Collectors.toList());
    }
    
    protected final Collection<Block<?>> getSuccessors(Block<?> block) {
        Class<? extends Calculation<? extends ValueProvider, ?>> calc = (Class<? extends Calculation<? extends ValueProvider, ?>>) block.calc.getClass();
        Collection<Class<? extends Calculation<? extends ValueProvider, ?>>> dependencies = usedBy(calc);
        
        return dependencies.stream().map(d -> knownBlocks.get(d)).collect(Collectors.toList());
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
