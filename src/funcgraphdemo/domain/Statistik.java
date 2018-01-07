package funcgraphdemo.domain;

import funcgraphdemo.common.Calculation;
import funcgraphdemo.common.DependsOn;
import funcgraphdemo.common.ValueProvider;
import java.util.List;

@DependsOn({Stichprobe.class, Summe.class, Varianz.class, StdAbw.class, Mittelwert.class, Anzahl.class})
public class Statistik implements Calculation<ValueProvider, Statistik.StatistikData> {

    private final StatistikData data;

    public Statistik() {
        this.data = new StatistikData();
    }

    @Override
    public StatistikData calculate(ValueProvider scope) {
        data.stichprobe = scope.get(Stichprobe.class);
        data.summe = scope.get(Summe.class);
        data.varianz = scope.get(Varianz.class);
        data.stdabw = scope.get(StdAbw.class);
        data.mittelwert = scope.get(Mittelwert.class);
        data.anzahl = scope.get(Anzahl.class);
                
        return data;
    }

    public static class StatistikData {

        public List<Double> stichprobe;
        public Double summe;
        public Double varianz;
        public Double stdabw;
        public Double mittelwert;
        public Long anzahl;

        @Override
        public String toString() {
            return String.format("Sample: %s Sum: %f Var: %f Std: %f Avg: %f Cnt: %d", stichprobe.toString(), summe, varianz, stdabw, mittelwert, anzahl);
        }
    }

}
