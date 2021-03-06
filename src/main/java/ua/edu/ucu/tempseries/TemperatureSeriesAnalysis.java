package ua.edu.ucu.tempseries;

public class TemperatureSeriesAnalysis {
    final static private double minimal = -273.0;
    final static private double delta = 0.00001;
    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
    }

    public double average() throws IllegalArgumentException {
        int len = this.temperatureSeries.length;
        if (len == 0) {
            throw new IllegalArgumentException("the series is empty");
        }
        double sum = 0;
        for (double tempr: this.temperatureSeries) {
            sum += tempr;
        }
        return sum/len;
    }

    public double deviation() throws IllegalArgumentException {
        int len = this.temperatureSeries.length;
        if (len == 0) {
            throw new IllegalArgumentException("the series is empty");
        }

        double mean = average();
        double sum = 0;
        for (double tempr:this.temperatureSeries) {
            sum += (tempr - mean)*(tempr - mean);
        }
        return  Math.sqrt(sum/len);
    }


    public double min() throws IllegalArgumentException {
        int len = this.temperatureSeries.length;
        if (len == 0) {
            throw new IllegalArgumentException("the series is empty");
        }
        double min = this.temperatureSeries[0];
        for (double tempr:this.temperatureSeries) {
            if (min > tempr) {
                min = tempr;
            }
        }
        return min;
    }

    public double max() throws IllegalArgumentException {
        int len = this.temperatureSeries.length;
        if (len == 0) {
            throw new IllegalArgumentException("the series is empty");
        }
        double max = this.temperatureSeries[0];
        for (double tempr: this.temperatureSeries) {
            if (max < tempr) {
                max = tempr;
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        double cur = this.temperatureSeries[0];
        for (double tempr: this.temperatureSeries) {
            double diffCur = (cur - tempValue)*(cur - tempValue);
            double diffVal = (tempr - tempValue)*(tempr - tempValue);
            if (diffCur > diffVal) {
                cur = tempr;
            } else if (Math.abs(diffCur - diffVal) < delta) {
                if (tempr > cur) {
                    cur = tempr;
                }
            }
        }
        return cur;
    }
    public double[] findTemps(double tempValue, boolean less) {
        int i = 0;
        for (double tempr: this.temperatureSeries) {
            if (less) {
                if (tempValue > tempr) {
                    i++;
                }
            } else{if (tempValue < tempr) {
                i++;
            }
            }
        }
        double[] ret = new double[i];
        int j = 0;
        for (double tempr: this.temperatureSeries) {
            if (less) {
                if (tempValue > tempr) {
                    ret[j] = tempr;
                    j++;
                }
            } else{if (tempValue < tempr) {
                ret[j] = tempr;
                j++;
            }}
        }
        return ret;
    }
    public double[] findTempsLessThen(double tempValue) {
        return findTemps(tempValue, true);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return findTemps(tempValue, false);
    }

    public TempSummaryStatistics summaryStatistics()
            throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("the series is empty");
        }
        double avarage = average();
        double deviation = deviation();
        double min = min();
        double max = max();
        return new TempSummaryStatistics(avarage, deviation, min, max);
    }

    public int addTemps(double... temps) throws IllegalArgumentException {
        for (double item: temps) {
            if (item < minimal) {
                throw new IllegalArgumentException("the series is empty");
            }
        }
        int len = this.temperatureSeries.length;
        double[] storage = new double[len * 2];
        for (int j = 0; j < len; j++) {
            storage[j] = this.temperatureSeries[j];
        }
        this.temperatureSeries = storage;
        int newLen = this.temperatureSeries.length;

        for (int k = len; k < newLen; k++) {
            this.temperatureSeries[k] = temps[k-len];
        }
        return newLen;
    }
}
