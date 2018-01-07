package funcgraphdemo.funcgraph.runtime;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class Block<OUTPUT> {
    
    private final Scope scope;
    private final Collection<Block> inputs;  
    private OUTPUT result;
    
    private boolean dirty = true;
    private int updateCount = 0;
    
    public Block(Scope scope, Class<? extends Block>... inputs) {
        this.scope = scope;
        
        // map the passed-in classes to block instances
        this.inputs = Arrays.asList(inputs)
                .stream()
                .map(i -> scope.getUntyped(i))
                .collect(Collectors.toList());

        // TODO: cycle check
        
        // add type
        this.scope.add(this);
    }

    public final Scope getScope() { return scope; }
    protected final Collection<Block> getInputs() { return inputs; }

    public final Collection<Block> dependsOn() { return getInputs(); }
    public final Collection<Block> usedBy() { return this.scope.getSuccessorsFor(this); }
    
    public final void setOutput(OUTPUT value) {
        this.result = value;
        markSuccessorsDirty();
    }
        
    private void markSuccessorsDirty() {
        for(Block b : usedBy()) {
            b.dirty = true;
            b.markSuccessorsDirty();
        }
    }
    
    public final OUTPUT getOutput() {
        if (this.result == null || this.dirty) {
            this.result = calculate(this.inputs.stream().toArray(Block[]::new));
            ++this.updateCount;
            this.dirty = false;
        }
        return this.result;
    }
    
    protected abstract OUTPUT calculate(Block... inputs);
    
    @Override
    public final String toString() {
        return String.format("%s:\n\tDepends on: %s\n\tUsed by: %s\n\tUpdateCount: %d\n\tOutput:%s",
                this.getClass().getName(),
                dependsOn().stream().map(b -> b.getClass().getName()).collect(Collectors.joining(",")),
                usedBy().stream().map(b -> b.getClass().getName()).collect(Collectors.joining(",")),
                updateCount,
                getOutput().toString());
    }
}
