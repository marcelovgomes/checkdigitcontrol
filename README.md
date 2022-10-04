# Check Digit Control App

This stand-alone Java application is responsible to import series number files, checking/validating the series check digits, and exporting files.
The development is based on Spring Framework, SpringBoot, and microservices development.
This App will show how to work with spring microservices using dependency injection.

## Classes (src/main/java)

The project contains the following packages/classes:

com.example.checkdigitcontrol.config

- AppConfig: This Bean is responsible for scanning the Spring-managed components

com.example.checkdigitcontrol.domains

- Country: The Country domain class
- SerialNumber: The SerialNumber domain class

com.example.checkdigitcontrol.interfaces

- ICountry: Country Interface
- ISeriesToDigitCheck: Interface to import series for digit checking
- ISeriesToReport: Interface to import series for reporting
- ISeriesWithoutCheckDigit: Interface to import series without check digits

com.example.checkdigitcontrol.services

- BaseService: BaseService abstract class
- CheckDigitControlService: This service provide the main point to call all services.
- CountryService: This service is responsible to load/process the countries file.
- SeriesToDigitCheckService: This service is responsible to load/process the series number file and for validating the check digits.
- SeriesToReportService: This service is responsible to load/process the report series file.
- SeriesWithoutCheckDigitService: This service is responsible to load/process the series file without check digits.

com.example.checkdigitcontrol.utility

- SeriesNumberUtility: This utility class provides useful methods for conversion, calculating, and checking digit validation.

## Resource files

- countries.txt
- countriesWithError.txt
- seriesForChecking.txt
- seriesForCheckingWithError.txt
- seriesToReport.txt
- seriesWithoutCheckDigit.txt

## Unit tests (src/test/java)

To test all services were created unit tests using @SpringBootTest

com.example.checkdigitcontrol

### CheckDigitControlAppTests
This test application contains all unit tests.

#### executeCheckDigitControlServiceTest(): CheckDigitControlService test
#### importSeriesWithoutCheckDigitServiceRightFileTest(): SeriesWithoutCheckDigitService test - passing a right file
#### importSeriesWithoutCheckDigitServiceWrongFileTest(): SeriesWithoutCheckDigitService test - passing a wrong file
#### importSeriesToDigitCheckServiceRightFileTest(): SeriesToDigitCheckService test - passing a right file
#### importSeriesToDigitCheckServiceWrongFileTest(): SeriesToDigitCheckService test - passing a wrong file
#### importSeriesToDigitCheckServiceWithErrorTest(): SeriesToDigitCheckService test - passing a right file containing errors
#### importSeriesToReportServiceRightFileTest(): SeriesToReportService test - passing a right file
#### importSeriesToReportServiceWrongFileTest(): SeriesToReportService test - passing a wrong file
#### importSeriesToReportServiceWithErrorTest(): SeriesToReportService test - passing a file containing errors

## Running the application

mvn spring-boot:run

## Running the tests

mvn -Dtest=CheckDigitControlAppTests test

## Main prerequisites

- Java 11
- Apache Maven 3.8.5
- Spring Boot 2.7.4