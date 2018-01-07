package funcgraphdemo.domain;

public class Statistik {
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
