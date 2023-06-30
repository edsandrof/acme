package br.edu.infnet.acme.infrastructure.util;

public abstract class Constants {
    
    private Constants() {
        throw new IllegalStateException("Constants class");
    }

    public static final long ONE_MONTH = 1L;
    public static final long THREE_MONTHS = 3L;
    public static final long SIX_MONTHS = 6L;
    public static final long TWELVE_MONTHS = 12L;

}
