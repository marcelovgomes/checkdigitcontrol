package com.example.checkdigitcontrol.interfaces;

/**
 * ISeriesToDigitCheck
 *
 * Interface to import series for digit checking
 *
 * @author  Marcelo Gomes
 * @created 2022-10-01
 */

public interface ISeriesToDigitCheck {
    boolean importSeries(String seriesFileName) throws Exception;
}
