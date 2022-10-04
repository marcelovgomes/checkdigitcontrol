package com.example.checkdigitcontrol.services;

import com.example.checkdigitcontrol.interfaces.ISeriesToReport;
import com.example.checkdigitcontrol.interfaces.ISeriesToDigitCheck;
import com.example.checkdigitcontrol.interfaces.ISeriesWithoutCheckDigit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CheckDigitControlService
 *
 * This service provide the main point to call all services.
 *
 * @author  Marcelo Gomes
 * @created 2022-10-01
 */

@Service
public class CheckDigitControlService {

    @Autowired
    private ISeriesWithoutCheckDigit iSeriesWithoutCheckDigit;

    @Autowired
    private ISeriesToDigitCheck iSeriesToDigitCheck;

    @Autowired
    private ISeriesToReport iSeriesToReport;

    /**
     * This method is responsible to call all services
     *
     * @return true or false.
     */
    public boolean execute() {
        try {
            importSeriesWithoutCheckDigit("seriesWithoutCheckDigit.txt");
            importSeriesToDigitCheck("seriesForChecking.txt");
            importSeriesWithCheckDigitToReport("seriesToReport.txt", "countries.txt");

            return true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());

            return false;
        }
    }

    /**
     * Import the file with series without check digit, generate the check digits, and export a file with the series with check digit
     *
     * @param seriesFileName file with the series without check digits
     * @return true or false.
     * @throws Exception Error to import/process the file
     */
    private boolean importSeriesWithoutCheckDigit(String seriesFileName) throws Exception {
        return iSeriesWithoutCheckDigit.importSeries(seriesFileName);
    }

    /**
     * Import the file with check digits for validation
     *
     * @param seriesFileName file with the series for checking
     * @return true or false.
     * @throws Exception Error to import/process the file
     */
    private boolean importSeriesToDigitCheck(String seriesFileName) throws Exception {
        return iSeriesToDigitCheck.importSeries(seriesFileName);
    }

    /**
     * Import the series for reporting and create a file with summary totals
     *
     * @param reportFileName file for reporting
     * @param countriesFileName countries file
     * @return true or false.
     * @throws Exception Error to import/process the file
     */
    private boolean importSeriesWithCheckDigitToReport(String reportFileName, String countriesFileName) throws Exception {
        return iSeriesToReport.importSeries(reportFileName, countriesFileName);
    }

}
