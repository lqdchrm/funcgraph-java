package funcgraphdemo.common;

public interface Calculation<VALUEPROVIDER extends ValueProvider, RESULT> {

    RESULT calculate(VALUEPROVIDER scope);
}
