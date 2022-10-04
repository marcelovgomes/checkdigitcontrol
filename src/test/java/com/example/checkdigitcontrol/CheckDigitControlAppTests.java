package com.example.checkdigitcontrol;

import com.example.checkdigitcontrol.config.AppConfig;
import com.example.checkdigitcontrol.services.CheckDigitControlService;
import com.example.checkdigitcontrol.services.SeriesToDigitCheckService;
import com.example.checkdigitcontrol.services.SeriesToReportService;
import com.example.checkdigitcontrol.services.SeriesWithoutCheckDigitService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CheckDigitControlAppTests
 *
 * This test application contains all unit tests.
 *
 * @author  Marcelo Gomes
 * @created 2022-10-02
 */

@SpringBootTest
class CheckDigitControlAppTests {

    public static AnnotationConfigApplicationContext ctx;

    /**
     * Start the context before test scenarios execution
     */
    @BeforeAll
    public static void setUp() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    /**
     * CheckDigitControlService test
     *
     * @throws Exception Error with the service execution
     */
    @Test
    public void executeCheckDigitControlServiceTest() {
        CheckDigitControlService checkDigitControlService = ctx.getBean(CheckDigitControlService.class);

        assertThat(checkDigitControlService.execute()).isEqualTo(true);
    }

    /**
     * SeriesWithoutCheckDigitService test - passing a right file
     *
     * @throws Exception Error with the service execution
     */
    @Test
    public void importSeriesWithoutCheckDigitServiceRightFileTest() throws Exception {
        SeriesWithoutCheckDigitService seriesWithoutCheckDigitService = ctx.getBean(SeriesWithoutCheckDigitService.class);

        assertThat(seriesWithoutCheckDigitService.importSeries("seriesWithoutCheckDigit.txt")).isEqualTo(true);
    }

    /**
     * SeriesWithoutCheckDigitService test - passing a wrong file
     */
    @Test
    public void importSeriesWithoutCheckDigitServiceWrongFileTest() {
        SeriesWithoutCheckDigitService seriesWithoutCheckDigitService = ctx.getBean(SeriesWithoutCheckDigitService.class);

        try {
            assertThat(seriesWithoutCheckDigitService.importSeries("XXXXXseriesWithoutCheckDigit.txt")).isEqualTo(false);
        } catch (final Exception e) {
            String message = "Error to load the file: XXXXXseriesWithoutCheckDigit.txt";

            assertThat(e.getMessage()).contains(message);
        }
    }

    /**
     * SeriesToDigitCheckService test - passing a right file
     *
     * @throws Exception Error with the service execution
     */
    @Test
    public void importSeriesToDigitCheckServiceRightFileTest() throws Exception {
        SeriesToDigitCheckService seriesToDigitCheckService = ctx.getBean(SeriesToDigitCheckService.class);

        assertThat(seriesToDigitCheckService.importSeries("seriesForChecking.txt")).isEqualTo(true);
    }

    /**
     * SeriesToDigitCheckService test - passing a wrong file
     */
    @Test
    public void importSeriesToDigitCheckServiceWrongFileTest() {
        SeriesToDigitCheckService seriesToDigitCheckService = ctx.getBean(SeriesToDigitCheckService.class);

        try {
            assertThat(seriesToDigitCheckService.importSeries("XXXXXseriesForChecking.txt")).isEqualTo(false);
        } catch (final Exception e) {
            String message = "Error to load the file";

            assertThat(e.getMessage()).contains(message);
        }
    }

    /**
     * SeriesToDigitCheckService test - passing a right file containing errors
     */
    @Test
    public void importSeriesToDigitCheckServiceWithErrorTest() {
        SeriesToDigitCheckService seriesToDigitCheckService = ctx.getBean(SeriesToDigitCheckService.class);

        try {
            assertThat(seriesToDigitCheckService.importSeries("seriesForCheckingWithError.txt")).isEqualTo(false);
        } catch (final Exception e) {
            String message = "Error to load/process the file";

            assertThat(e.getMessage()).contains(message);
        }
    }

    /**
     * SeriesToReportService test - passing a right file
     *
     * @throws Exception Error with the service execution
     */
    @Test
    public void importSeriesToReportServiceRightFileTest() throws Exception {
        SeriesToReportService seriesToReportService = ctx.getBean(SeriesToReportService.class);

        assertThat(seriesToReportService.importSeries("seriesToReport.txt", "countries.txt")).isEqualTo(true);
    }

    /**
     * SeriesToReportService test - passing a wrong file
     */
    @Test
    public void importSeriesToReportServiceWrongFileTest() {
        SeriesToReportService seriesToReportService = ctx.getBean(SeriesToReportService.class);

        try {
            assertThat(seriesToReportService.importSeries("seriesToReportXXXX.txt", "countries.txt")).isEqualTo(false);
        } catch (final Exception e) {
            String message = "Error to load the file";

            assertThat(e.getMessage()).contains(message);
        }
    }

    /**
     * SeriesToReportService test - passing a file containing errors
     */
    @Test
    public void importSeriesToReportServiceWithErrorTest() {
        SeriesToReportService seriesToReportService = ctx.getBean(SeriesToReportService.class);

        try {
            assertThat(seriesToReportService.importSeries("seriesToReport.txt", "countriesWithError.txt")).isEqualTo(false);
        } catch (final Exception e) {
            String message = "Error to load/process the file: countriesWithError.txt";

            assertThat(e.getMessage()).contains(message);
        }
    }

}
