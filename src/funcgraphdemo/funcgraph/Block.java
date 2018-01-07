package funcgraphdemo.funcgraph;

import funcgraphdemo.common.Calculation;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class Block<OUTPUT> implements Calculation<Scope, OUTPUT> {

    private final Scope scope;
    private final Collection<? extends Block> inputs;
    private OUTPUT result;

    private boolean dirty = true;
    private int updateCount = 0;

    public Block(Scope scope, Class<? extends Block>... inputs) {
        this.scope = scope;
        this.inputs = this.scope.mapInputs(inputs);

        // add type
        this.scope.add(this);
    }

    protected final Collection<? extends Block> getInputs() {
        return inputs;
    }

    public final Collection<? extends Block> dependsOn() {
        return getInputs();
    }

    public final Collection<? extends Block> usedBy() {
        return this.scope.getSuccessorsFor(this);
    }

    protected final void set(OUTPUT value) {
        this.result = value;
        markSuccessorsDirty();
    }

    private void markSuccessorsDirty() {
        for (Block b : usedBy()) {
            b.dirty = true;
            b.markSuccessorsDirty();
        }
    }

    protected final OUTPUT get() {
        if (this.result == null || this.dirty) {
            this.result = calculate(this.scope);
            ++this.updateCount;
            this.dirty = false;
        }
        return this.result;
    }

    public abstract OUTPUT calculate(Scope scope);

    @Override
    public final String toString() {
        return String.format("%s:\n\tDepends on: %s\n\tUsed by: %s\n\tUpdateCount: %d\n\tOutput:%s",
                this.getClass().getName(),
                dependsOn().stream().map(b -> b.getClass().getName()).collect(Collectors.joining(",")),
                usedBy().stream().map(b -> b.getClass().getName()).collect(Collectors.joining(",")),
                updateCount,
                get().toString());
    }
}
