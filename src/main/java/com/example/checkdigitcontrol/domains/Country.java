package com.example.checkdigitcontrol.domains;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Country
 *
 * The Country domain class
 *
 * @author  Marcelo Gomes
 * @created 2022-10-01
 */

@Getter
@Setter
public class Country implements Serializable {

    private String countryCode;
    private String countryName;

}
