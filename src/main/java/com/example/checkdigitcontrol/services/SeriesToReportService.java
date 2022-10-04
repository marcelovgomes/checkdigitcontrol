package com.example.checkdigitcontrol.services;

import com.example.checkdigitcontrol.domains.Country;
import com.example.checkdigitcontrol.domains.SerialNumber;
import com.example.checkdigitcontrol.interfaces.ICountry;
import com.example.checkdigitcontrol.interfaces.ISeriesToReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Series to report Service
 *
 * This service is responsible to load/process the report series file.
 *
 * @author  Marcelo Gomes
 * @created 2022-10-01
 */

@Service
public class SeriesToReportService extends BaseService implements ISeriesToReport {

    @Autowired
    private ICountry iCountry;

    /**
     * Import the report and countries files to create a summarized list with the totals
     *
     * @param reportFileName report file name
     * @param countriesFileName countries file name
     * @return true or false.
     * @throws Exception Error to import/process the file
     */
    public boolean importSeries(String reportFileName, String countriesFileName) throws Exception {

        try {
            InputStream isFile = this.getFileFromResourceAsStream(reportFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(isFile, "UTF-8"));

            ArrayList<SerialNumber> serialNumbers = new ArrayList<SerialNumber>();

            while (br.ready()) {
                String line = br.readLine();

                SerialNumber serialNumber = new SerialNumber();
                serialNumber.setSerialNumber(line);
                serialNumbers.add(serialNumber);
            }

            ArrayList<Country> countries = iCountry.importCountries(countriesFileName);

            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("listWithTotals.txt")));

            for (Country country: countries) {
                List<SerialNumber> serialNumberList = serialNumbers.stream()
                        .filter(serialNumber -> serialNumber.getSerialNumber().contains(country.getCountryCode()))
                        .collect(Collectors.toList());

                if (serialNumberList.size() > 0)
                    bw.write(country.getCountryName() + "-" + serialNumberList.size() + "\n");
            }

            bw.flush();
            bw.close();

            return true;
        } catch (Exception ex) {
            if (ex.getMessage().toLowerCase().indexOf("erro") != -1)
                throw new Exception(ex);
            else
                throw new Exception("Error to load/process the file: " + reportFileName);
        }
    }
}
