package funcgraphdemo.funcgraph;

import funcgraphdemo.common.Calculation;
import java.util.Collection;
import java.util.stream.Collectors;

class Block<OUTPUT> {

    private final Scope scope;
    protected final Calculation<Scope, OUTPUT> calc;
    private OUTPUT result;

    protected boolean dirty = true;
    private int updateCount = 0;

    public Block(Scope scope, Calculation<Scope, OUTPUT> calc) {
        this.scope = scope;
        this.calc = calc;
    }
    
    protected final void set(OUTPUT value) {
        this.result = value;
        markSuccessorsDirty();
    }

    private final void markSuccessorsDirty() {
        for (Block b : scope.getSuccessors(this)) {
            b.dirty = true;
            b.markSuccessorsDirty();
        }
    }

    protected final OUTPUT get() {
        if (this.result == null || this.dirty) {
            this.result = this.calc.calculate(scope);
            ++this.updateCount;
            this.dirty = false;
        }
        return this.result;
    }

    @Override
    public final String toString() {
        Collection<Block<?>> pre = this.scope.getPredecessors(this);
        Collection<Block<?>> suc = this.scope.getSuccessors(this);
        
        return String.format("%s:\n\tDepends on: %s\n\tUsed by: %s\n\tUpdateCount: %d\n\tOutput:%s",
                this.calc.getClass().getName(),
                (pre != null && pre.size() > 0) ? pre.stream().map(b -> b.getClass().getName()).collect(Collectors.joining(",")) : "",
                (suc != null && suc.size() > 0) ? suc.stream().map(b -> b.getClass().getName()).collect(Collectors.joining(",")) : "",
                updateCount,
                get().toString());
    }
}
