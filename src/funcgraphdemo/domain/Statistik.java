package funcgraphdemo.domain;

import funcgraphdemo.common.Calculation;
import funcgraphdemo.common.ValueProvider;

public class Statistik implements Calculation<ValueProvider, Statistik.StatistikData> {

    private final StatistikData data;

    public Statistik() {
        this.data = new StatistikData();
    }

    @Override
    public StatistikData calculate(ValueProvider scope) {
        return data;
    }

    public static class StatistikData {

        public Stichprobe stichprobe;
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
