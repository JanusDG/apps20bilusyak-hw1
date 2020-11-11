package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

//    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

//    @Ignore
    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.741657;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindClosestToValue() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3;

        double actualResult = seriesAnalysis.findTempClosestToValue(2.5);

        assertEquals(expResult, actualResult, 0.00001);

        expResult = 1;

        actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMaxMin() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;

        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);

        expResult = 5;

        actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    public void testSummaryStats() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        TempSummaryStatistics actualResult = seriesAnalysis.summaryStatistics();

        double expResult = 1.0;

        assertEquals(expResult, actualResult.avgTemp, 0.00001);

        expResult = 3.74165738;

        assertEquals(expResult, actualResult.devTemp, 0.00001);

        expResult = -5.0;

        assertEquals(expResult, actualResult.minTemp, 0.00001);

        expResult = 5.0;

        assertEquals(expResult, actualResult.maxTemp, 0.00001);
    }

    @Test
    public void testFindGreaterLess() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {3.0, 1.0, 5.0};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(0);
        if (expResult.length != actualResult.length){fail();}
        for (int i = 0; i < expResult.length; i++) {
            assertEquals(expResult[i], actualResult[i], 0.00001);
        }

        double[] expResult2 = {-5.0};

        double[] actualResult2 = seriesAnalysis.findTempsLessThen(0);
        if (expResult2.length != actualResult2.length){fail();}
        for (int i = 0; i < expResult2.length; i++) {
            assertEquals(expResult2[i], actualResult2[i], 0.00001);
        }
    }


    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 8;

        double[] seriesToAdd = {3.0, -5.0, 1.0, 5.0};
        int actualResult = seriesAnalysis.addTemps(seriesToAdd);

        assertEquals(expResult, actualResult, 0.00001);
    }




}
