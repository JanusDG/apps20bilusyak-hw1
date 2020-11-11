package ua.edu.ucu.tempseries;

public final class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;
    public TempSummaryStatistics(double average,
                                 double deviation,
                                 double min,
                                 double max) {
        this.avgTemp = average;
        this.devTemp = deviation;
        this.minTemp = min;
        this.maxTemp = max;
    }

    public double checkAvg( ){
        return this.avgTemp;
    }

    public double checkDev( ){
        return this.devTemp;
    }
    public double checkMin( ){
        return this.minTemp;
    }
    public double checkMax( ){
        return this.maxTemp;
    }

}
