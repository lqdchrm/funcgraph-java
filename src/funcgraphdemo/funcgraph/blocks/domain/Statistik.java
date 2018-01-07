package funcgraphdemo.funcgraph.blocks.domain;

import funcgraphdemo.funcgraph.blocks.math.Anzahl;
import funcgraphdemo.funcgraph.blocks.math.Mittelwert;
import funcgraphdemo.funcgraph.blocks.math.StdAbw;
import funcgraphdemo.funcgraph.blocks.math.Summe;
import funcgraphdemo.funcgraph.blocks.math.Varianz;
import funcgraphdemo.funcgraph.runtime.Block;
import funcgraphdemo.funcgraph.runtime.Scope;

public class Statistik extends Block<funcgraphdemo.domain.Statistik> {

    private final funcgraphdemo.domain.Statistik statistik;
    
    public Statistik(Scope scope, funcgraphdemo.domain.Statistik statistik) {
        super(scope, Stichprobe.class, Summe.class, Varianz.class, StdAbw.class, Mittelwert.class, Anzahl.class);
        this.statistik = statistik;
    }

    @Override
    protected funcgraphdemo.domain.Statistik calculate(Scope scope) {
        statistik.stichprobe = scope.get(Stichprobe.class).getOutput();
        statistik.summe = scope.get(Summe.class).getOutput();
        statistik.varianz = scope.get(Varianz.class).getOutput();
        statistik.stdabw = scope.get(StdAbw.class).getOutput();
        statistik.mittelwert = scope.get(Mittelwert.class).getOutput();
        statistik.anzahl = scope.get(Anzahl.class).getOutput();
        return statistik;
    }
}
