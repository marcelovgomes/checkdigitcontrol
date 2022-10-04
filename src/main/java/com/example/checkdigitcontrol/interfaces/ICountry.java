package com.example.checkdigitcontrol.interfaces;

import com.example.checkdigitcontrol.domains.Country;

import java.util.ArrayList;

/**
 * ICountry
 *
 * Country Interface
 *
 * @author  Marcelo Gomes
 * @created 2022-10-02
 */

public interface ICountry {
    ArrayList<Country> importCountries(String countriesFileName) throws Exception;
}
