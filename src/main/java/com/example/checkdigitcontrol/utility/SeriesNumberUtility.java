package com.example.checkdigitcontrol.utility;

/**
 * SeriesNumberUtility
 *
 * This utility class provides useful methods for conversion, calculating, and checking digit validation.
 *
 * @author  Marcelo Gomes
 * @created 2022-10-02
 */

public class SeriesNumberUtility {

    /**
     * Convert a decimal value to hexadecimal value
     *
     * @param vlDecimal valor decimal
     * @return decimal value in String format
     * @throws Exception Error to convert the value
     */
    private static String decimalToHexadecimal(int vlDecimal) throws Exception {
        try {
            int rem;
            String vlHexadecimal = "";
            char hexChars[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

            while(vlDecimal > 0)
            {
                rem = vlDecimal % 16;
                vlHexadecimal = hexChars[rem] + vlHexadecimal;
                vlDecimal = vlDecimal / 16;
            }

            return vlHexadecimal;
        } catch (Exception ex) {
            throw new Exception("Error to convert to hexadecimal value: " + vlDecimal);
        }
    }

    /**
     * Calculate the check digit
     *
     * @param seriesNumber series number without check digit
     * @return check digit in String format
     * @throws Exception Error to calculate the check digit
     */
    public static String calculateCheckDigit(String seriesNumber) throws Exception {
        try {
            int total = 0, rest = 0;

            for (int i = 0; i < seriesNumber.length(); i++) {
                total += (int)seriesNumber.charAt(i);
            }

            rest = total % 16;

            if (rest == 0)
                return "0";
            else
                return decimalToHexadecimal(rest);
        } catch (Exception ex) {
            if (ex.getMessage().toLowerCase().indexOf("error") != -1)
                throw new Exception(ex);
            else
                throw new Exception("Error to calculate the check digit to the value: " + seriesNumber);
        }
    }

    /**
     * Validate the check digit
     *
     * @param seriesNumber series number with check digit
     * @return true (check digit is valid) or false (check digit is invalid)
     * @throws Exception The series number is invalid
     */
    public static boolean validateCheckDigit(String seriesNumber) throws Exception {
        try {
            int total = 0, rest = 0;
            String valueWithOutCheckDigit = seriesNumber.substring(0, seriesNumber.indexOf("-"));
            String checkDigit = seriesNumber.substring(seriesNumber.indexOf("-") + 1);

            for (int i = 0; i < valueWithOutCheckDigit.length(); i++) {
                total += (int)valueWithOutCheckDigit.charAt(i);
            }

            rest = total % 16;

            if (decimalToHexadecimal(rest).compareTo(checkDigit) == 0)
                return true;
            else
                return false;
        } catch (Exception ex) {
            if (ex.getMessage().toLowerCase().indexOf("error") != -1)
                throw new Exception(ex);
            else
                throw new Exception("This series number is invalid: " + seriesNumber);
        }
    }
}
