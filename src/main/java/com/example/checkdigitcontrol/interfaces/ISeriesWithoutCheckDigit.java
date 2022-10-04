package com.example.checkdigitcontrol.interfaces;

/**
 * ISeriesWithoutCheckDigit
 *
 * Interface to import series without check digits
 *
 * @author  Marcelo Gomes
 * @created 2022-10-01
 */

public interface ISeriesWithoutCheckDigit {
    boolean importSeries(String seriesFileName) throws Exception;
}
