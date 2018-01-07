package funcgraphdemo.funcgraph;

import funcgraphdemo.common.Calculation;
import funcgraphdemo.common.ValueProvider;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class Scope implements ValueProvider {

    private final HashMap<Class<? extends Block>, Block> knownBlocks;
    private final TypeMap<Class<? extends Block>> typeMap;

    public Scope() {
        this(null);
    }

    public Scope(TypeMap typeMap) {
        this.knownBlocks = new HashMap<>();
        this.typeMap = new TypeMap(typeMap);
    }

    public final TypeMap getTypeMap() {
        return this.typeMap;
    }

    protected final void add(Block block, Class<? extends Block>... inputs) {
        if (!knownBlocks.containsKey(block.getClass())) {
            knownBlocks.put(block.getClass(), block);
        }
    }

    //public final <T> T get(Class<? extends Block<T>> type) {
    //    return getTyped(type).get();
    //}
    @Override
    public <T> T get(Class<? extends Calculation<? extends ValueProvider, T>> type) {
        return getTyped(type).get();
    }

    @Override
    public <T> void set(Class<? extends Calculation<? extends ValueProvider, T>> type, T value) {
        getTyped(type).set(value);
    }

    private <T> Block<T> getTyped(Class<? extends Calculation<? extends ValueProvider, T>> type /*Class<? extends Block> type*/) {
        Class<? extends Block<T>> casted = (Class<? extends Block<T>>) type;
        Block result = getUntyped(casted);
        return (Block<T>) result;
    }

    private Block getUntyped(Class<? extends Block> type) {
        if (typeMap.containsKey(type)) {
            type = typeMap.get(type);
        }

        if (!knownBlocks.containsKey(type)) {
            try {
                Block<?> block = type.getConstructor(Scope.class).newInstance(this);
                knownBlocks.put(type, block);
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Scope.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return knownBlocks.get(type);
    }

    protected final Collection<? extends Block> mapInputs(Class<? extends Block>... inputs) {
        return Arrays.asList(inputs)
                .stream()
                .map(i -> getUntyped(i))
                .collect(Collectors.toList());
    }

    protected final Collection<Block> getSuccessorsFor(Block block) {
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
