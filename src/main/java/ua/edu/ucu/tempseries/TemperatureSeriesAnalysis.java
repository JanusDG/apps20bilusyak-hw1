package ua.edu.ucu.tempseries;

import java.lang.Math;

public class TemperatureSeriesAnalysis {

    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
    }

    public double average() throws IllegalArgumentException {
        int len = this.temperatureSeries.length;
        if (len == 0){
            throw new IllegalArgumentException("the series is empty");
        }
        double sum = 0;
        for (double tempr:this.temperatureSeries){
            sum += tempr;
        }
        return sum/len;
    }
//    Обчислює середнє значення температури. Якщо ряд порожній, генерує IllegalArgumentException.

    public double deviation() throws IllegalArgumentException{
        int len = this.temperatureSeries.length;
        if (len == 0){
            throw new IllegalArgumentException("the series is empty");
        }

        double mean = average();
        double sum = 0;
        for (double tempr:this.temperatureSeries){
            sum += Math.pow((tempr - mean),2);
        }
        return  Math.sqrt(sum/len);
    }
//    Обчислю середньоквадратичне відхилення. Якщо ряд порожній, генерує IllegalArgumentException.


    public double min() throws IllegalArgumentException{
        int len = this.temperatureSeries.length;
        if (len == 0){
            throw new IllegalArgumentException("the series is empty");
        }
        double min = this.temperatureSeries[0];
        for (double tempr:this.temperatureSeries){
            if (min < tempr){
                min = tempr;
            }
        }
        return min;
    }
//    Повертає мінімальну температуру. Якщо ряд порожній, генерує IllegalArgumentException.

    public double max() throws IllegalArgumentException{
        int len = this.temperatureSeries.length;
        if (len == 0){
            throw new IllegalArgumentException("the series is empty");
        }
        double max = this.temperatureSeries[0];
        for (double tempr:this.temperatureSeries){
            if (max > tempr){
                max = tempr;
            }
        }
        return max;
    }
//    Повертає максимальну температуру. Якщо ряд порожній, генерує IllegalArgumentException.


    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }
//    Повертає значення температури найближче до 0. Якщо ряд порожній, генерує IllegalArgumentException.
//Якщо у ряді є декілька значень однаково наближених до 0 (наприклад -0.2 і 0.2), то повертається додатнє значення (тобто 0.2)

    public double findTempClosestToValue(double tempValue) {
        double cur = this.temperatureSeries[0];
        for (double tempr:this.temperatureSeries){
            if (Math.sqrt(cur - tempValue) < Math.sqrt(tempr - tempValue)){
                cur = tempr;
            }else if (Math.sqrt(cur - tempValue) == Math.sqrt(tempr - tempValue)){
                if (tempr > cur){
                    cur = tempr;
                }
            }
        }
        return cur;
    }
//    Аналогічно попередньому методі, тільки повертається значення найближче до заданого tempValue


    public double[] findTempsLessThen(double tempValue) {
        int i = 0;
        for (double tempr:this.temperatureSeries){
            if (tempValue > tempr){
                i++;
            }
        }
        double[] ret = new double[i];
        int j = 0;
        for (double tempr:this.temperatureSeries){
            if (tempValue > tempr){
                ret[j] = tempr;
                j++;
            }
        }
        return ret;
    }
//    Повертає масив зі значеннями температури менше заданого tempValue.

    public double[] findTempsGreaterThen(double tempValue) {
        int i = 0;
        for (double tempr:this.temperatureSeries){
            if (tempValue <= tempr){
                i++;
            }
        }
        double[] ret = new double[i];
        int j = 0;
        for (double tempr:this.temperatureSeries){
            if (tempValue <= tempr){
                ret[j] = tempr;
                j++;
            }
        }
        return ret;
    }
//    Повертає масив зі значеннями температури більше або рівне заданому tempValue.


    public TempSummaryStatistics summaryStatistics() throws IllegalArgumentException{
        if (this.temperatureSeries.length == 0){throw new IllegalArgumentException("the series is empty");}
        double avarage = average();
        double deviation = deviation();
        double min = min();
        double max = max();
        return new TempSummaryStatistics(avarage, deviation, min, max);
    }
//    Повертає immutable екземпляр классу TempSummaryStatistics в якому міститься інформація:
//            - double avgTemp;
//- double devTemp;
//- double minTemp;
//- double maxTemp;
//    Якщо ряд порожній, генерує IllegalArgumentException.


    public int addTemps(double... temps) {
        int len = this.temperatureSeries.length;
        int i = 0;
        for (double item: this.temperatureSeries){
            i++;
        }
        if (len - i < temps.length){
            double[] storage = new double[len*2];
            for (int j=0; j<len; j++){
                storage[j] = this.temperatureSeries[j];
            }
            this.temperatureSeries = storage;
            len = this.temperatureSeries.length;
        }

        for (int k=len; k<len; k++){
            this.temperatureSeries[k] = temps[k];
        }
        i = 0;
        for (double item: this.temperatureSeries){
            i++;
        }
        return i;


    }
//    Додає в кінець ряду вже існуючих даних нові значення температур, і повертає сумарне число значень температур.
//    Структура (масив) в класі TemperatureSeriesAnalysis
//    для зберігання вже переданих температур повинна збільшуватися в 2 рази, якщо в ній немає місця для зберігання нових значень.
}
