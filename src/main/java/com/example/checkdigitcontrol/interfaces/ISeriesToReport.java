package com.example.checkdigitcontrol.interfaces;

/**
 * ISeriesToReport
 *
 * Interface to import series for reporting
 *
 * @author  Marcelo Gomes
 * @created 2022-10-01
 */

public interface ISeriesToReport {
    boolean importSeries(String reportFileName, String countriesFileName) throws Exception;
}
