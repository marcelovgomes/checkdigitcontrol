package com.example.checkdigitcontrol.services;

import com.example.checkdigitcontrol.domains.Country;
import com.example.checkdigitcontrol.interfaces.ICountry;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Country Service
 *
 * This service is responsible to load/process the countries file.
 *
 * @author  Marcelo Gomes
 * @created 2022-10-02
 */

@Service
public class CountryService extends BaseService implements ICountry {

    /**
     * Import the countries file
     *
     * @param countriesFileName countries file name for importing
     * @return ArrayList with all countries
     * @throws Exception Error to import/process the file
     */
    public ArrayList<Country> importCountries(String countriesFileName) throws Exception {

        ArrayList<Country> countries = new ArrayList<Country>();

        try {
            InputStream isFile = this.getFileFromResourceAsStream(countriesFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(isFile, "UTF-8"));

            while (br.ready()) {
                String line = br.readLine();

                Country country = new Country();
                country.setCountryCode(line.substring(0, line.indexOf(";")));
                country.setCountryName(line.substring(line.indexOf(";") + 1));
                countries.add(country);
            }

            return countries;
        } catch (Exception ex) {
            throw new Exception("Error to load/process the file: " + countriesFileName);
        }
    }
}
