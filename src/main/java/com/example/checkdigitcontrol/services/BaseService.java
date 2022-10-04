package com.example.checkdigitcontrol.services;

import java.io.InputStream;

/**
 * BaseService abstract class
 *
 * @author  Marcelo Gomes
 * @created 2022-10-02
 */

public abstract class BaseService {

    /**
     * Return the input file as an InputStream object
     *
     * @param fileName file name
     * @return a file as an InputStream object or null if not possible to load the file
     */
    protected InputStream getFileFromResourceAsStream(String fileName) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new Exception("Error to load the file: " + fileName);
        } else {
            return inputStream;
        }
    }
}
