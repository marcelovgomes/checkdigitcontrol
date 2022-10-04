package com.example.checkdigitcontrol.services;

import com.example.checkdigitcontrol.domains.SerialNumber;
import com.example.checkdigitcontrol.interfaces.ISeriesToDigitCheck;
import com.example.checkdigitcontrol.utility.SeriesNumberUtility;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

/**
 * SeriesToDigitCheckService
 *
 * This service is responsible to load/process the series number file and for validating the check digits.
 *
 * @author  Marcelo Gomes
 * @created 2022-10-01
 */

@Service
public class SeriesToDigitCheckService extends BaseService implements ISeriesToDigitCheck {

    /**
     * Import the series file for checking and create a file with the series checked
     *
     * @param seriesFileName series file name
     * @return true or false.
     * @throws Exception Error to import/process the file
     */
    public boolean importSeries(String seriesFileName) throws Exception {

        try {
            InputStream isFile = this.getFileFromResourceAsStream(seriesFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(isFile, "UTF-8"));

            ArrayList<SerialNumber> serialNumbers = new ArrayList<SerialNumber>();

            while (br.ready()) {
                String line = br.readLine();
                boolean isCheckDigit = SeriesNumberUtility.validateCheckDigit(line);

                line += " " + isCheckDigit;

                SerialNumber serialNumber = new SerialNumber();
                serialNumber.setSerialNumber(line);
                serialNumbers.add(serialNumber);
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("seriesChecked.txt")));

            for (SerialNumber serialNumber: serialNumbers) {
                bw.write(serialNumber.getSerialNumber() + "\n");
            }

            bw.flush();
            bw.close();

            return true;
        } catch (Exception ex) {
            if (ex.getMessage().toLowerCase().indexOf("error") != -1)
                throw new Exception(ex);
            else
                throw new Exception("Error to load/process the file: " + seriesFileName);
        }
    }
}
