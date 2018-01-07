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
    protected funcgraphdemo.domain.Statistik calculate(Block... inputs) {
        int i = 0;
        statistik.stichprobe = ((Stichprobe)inputs[i++]).getOutput();
        statistik.summe = ((Summe)inputs[i++]).getOutput();
        statistik.varianz = ((Varianz)inputs[i++]).getOutput();
        statistik.stdabw = ((StdAbw)inputs[i++]).getOutput();
        statistik.mittelwert = ((Mittelwert)inputs[i++]).getOutput();
        statistik.anzahl = ((Anzahl)inputs[i++]).getOutput();
        return statistik;
    }
}
