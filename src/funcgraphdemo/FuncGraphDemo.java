package funcgraphdemo;

import funcgraphdemo.funcgraph.blocks.domain.Statistik;
import funcgraphdemo.funcgraph.blocks.domain.Stichprobe;
import funcgraphdemo.funcgraph.blocks.io.StichprobeFromKeyboard;
import funcgraphdemo.funcgraph.runtime.Scope;
import funcgraphdemo.funcgraph.runtime.TypeMap;

public class FuncGraphDemo {

    public static void main(String[] args) {
        useCase3();
    }
    
    private static void useCase1() {
        
        // create plain domain objects, which are not under our control and are not implemented using the framework
        funcgraphdemo.domain.Stichprobe stichprobe = new funcgraphdemo.domain.Stichprobe();
        funcgraphdemo.domain.Statistik statistik = new funcgraphdemo.domain.Statistik();
        
        Scope scope = new Scope();
        
        // create domain object wrappers
        new Stichprobe(scope, stichprobe);
        new Statistik(scope, statistik);

        // get domain object back from scope, this triggers the calculation
        statistik = scope.get(Statistik.class).getOutput();
        
        System.out.println(statistik);
        System.err.println(scope);
    }
    
    /**
     * Replace the hardcoded stichprobe with one, that reads input from the
     * keyboard, by passing a typemap to the scope's constructor.
     */
    private static void useCase2() {
        
        // create plain domain objects, which are not under our control and are not implemented using the framework
        funcgraphdemo.domain.Statistik statistik = new funcgraphdemo.domain.Statistik();
        
        // create typemap
        TypeMap typeMap = new TypeMap();
        typeMap.put(Stichprobe.class, StichprobeFromKeyboard.class);

        Scope scope = new Scope(typeMap);
        
        // create domain object wrappers
        new Statistik(scope, statistik);

        // get domain object back from scope, this triggers the calculation
        statistik = scope.get(Statistik.class).getOutput();
        
        System.out.println(statistik);
        System.err.println(scope);
    }

    /**
     * This use case shows how to overwrite intermediate values and automatic
     * recalculation only of depending blocks by internal dirty-checking
     * (see output: UpdateCount)
     */
    private static void useCase3() {

        // create plain domain objects, which are not under our control and are not implemented using the framework
        funcgraphdemo.domain.Stichprobe stichprobe = new funcgraphdemo.domain.Stichprobe();
        funcgraphdemo.domain.Statistik statistik = new funcgraphdemo.domain.Statistik();
        
        Scope scope = new Scope();
        
        // create domain object wrappers
        new Stichprobe(scope, stichprobe);
        new Statistik(scope, statistik);

        // get domain object back from scope, this triggers the calculation
        statistik = scope.get(Statistik.class).getOutput();
        
        System.err.println("\n\nSystem State before change:\n********");
        System.err.println(scope);
               
        // change content of domain data object
        stichprobe.add(10.0);
        stichprobe.add(11.0);
        scope.get(Stichprobe.class).setOutput(stichprobe);  // set changed value to trigger recalc of dependent blocks
        
        // get domain object back from scope, this triggers the calculation
        statistik = scope.get(Statistik.class).getOutput();

        System.err.println("\n\nSystem State after change:\n********");
        System.err.println(scope);
    }
}
