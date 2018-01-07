package funcgraphdemo;

import funcgraphdemo.common.ValueProvider;
import funcgraphdemo.domain.Statistik;
import funcgraphdemo.domain.Stichprobe;
import funcgraphdemo.domain.StichprobeFromKeyboard;
import funcgraphdemo.funcgraph.Scope;
import funcgraphdemo.funcgraph.TypeMap;
import java.util.List;

public class FuncGraphDemo {

    public static void main(String[] args) {
        useCase3();
    }

    private static void useCase1() {

        ValueProvider scope = new Scope();

        // get domain object from scope, this triggers the calculation
        Statistik.StatistikData statistik = scope.get(Statistik.class);

        System.out.println(statistik);
        System.err.println(scope);
    }

    /**
     * Replace the hardcoded stichprobe with one, that reads input from the
     * keyboard, by passing a typemap to the scope's constructor.
     */
    private static void useCase2() {

        // create typemap
        TypeMap typeMap = new TypeMap();
        typeMap.put(Stichprobe.class, StichprobeFromKeyboard.class);

        ValueProvider scope = new Scope(typeMap);

        // get domain object back from scope, this triggers the calculation
        Statistik.StatistikData statistik = scope.get(Statistik.class);

        System.out.println(statistik);
        System.err.println(scope);
    }

    /**
     * This use case shows how to overwrite intermediate values and automatic
     * recalculation only of depending blocks by internal dirty-checking (see
     * output: UpdateCount)
     */
    private static void useCase3() {

        ValueProvider scope = new Scope();

        // get domain object back from scope, this triggers the calculation
        Statistik.StatistikData statistik = scope.get(Statistik.class);

        System.err.println("\n\nSystem State before change:\n********");
        System.err.println(scope);

        // change content of domain data object
        List<Double> stichprobe = scope.get(Stichprobe.class);
        stichprobe.add(10.0);
        stichprobe.add(11.0);
        scope.set(Stichprobe.class, stichprobe);  // set changed value to trigger recalc of dependent blocks

        // get domain object back from scope, this triggers the calculation
        statistik = scope.get(Statistik.class);

        System.err.println("\n\nSystem State after change:\n********");
        System.err.println(scope);
    }
}
