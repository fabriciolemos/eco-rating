package com.eco.rating.adapter;

import com.eco.rating.application.RatingApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.Double.parseDouble;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StandardInputListenerTest {

    private StandardInputListener fixture;

    @Mock
    private RatingApplicationService applicationService;

    @Test
    public void dataInput_singleLine_callService() {
        String userName = "John";
        String countryName = "Canada";
        String stateName = "Ontario";
        String cityName = "Toronto";
        String rValue = "1.5";
        String format = format("\"%s\" \"%s/%s/%s\" %s\n\n", userName, countryName, stateName, cityName, rValue);

        fixture = new StandardInputListener(new ByteArrayInputStream(format.getBytes()), null, applicationService);

        fixture.processDataInput();

        verify(applicationService).processNewUser(userName, parseDouble(rValue), countryName, stateName, cityName);
    }

    @Test
    public void dataInput_multiLine_callService() {
        String userName1 = "John";
        String countryName1 = "Canada";
        String stateName1 = "Ontario";
        String cityName1 = "Toronto";
        String rValue1 = "1.5";

        String userName2 = "Alicia";
        String countryName2 = "US";
        String stateName2 = "Arizona";
        String cityName2 = "Phoenix";
        String rValue2 = "3.456";


        String line1 = format("\"%s\" \"%s/%s/%s\" %s\n", userName1, countryName1, stateName1, cityName1, rValue1);
        String line2 = format("\"%s\" \"%s/%s/%s\" %s\n\n", userName2, countryName2, stateName2, cityName2, rValue2);

        fixture = new StandardInputListener(new ByteArrayInputStream((line1 + line2).getBytes()), null, applicationService);

        fixture.processDataInput();

        verify(applicationService).processNewUser(userName1, parseDouble(rValue1), countryName1, stateName1, cityName1);
        verify(applicationService).processNewUser(userName2, parseDouble(rValue2), countryName2, stateName2, cityName2);
    }

    @Test
    public void dataInput_valuesWithSpace_callService() {
        String userName = "Tony Stark";
        String countryName = "United States of America";
        String stateName = "New York";
        String cityName = "New Jersey";
        String rValue = "50.000";
        String input = format("\"%s\" \"%s/%s/%s\" %s\n\n", userName, countryName, stateName, cityName, rValue);

        fixture = new StandardInputListener(new ByteArrayInputStream(input.getBytes()), null, applicationService);

        fixture.processDataInput();

        verify(applicationService).processNewUser(userName, parseDouble(rValue), countryName, stateName, cityName);
    }

    @Test
    public void queryInput_singleLine_callService() {
        String userName = "Tony Stark";
        String countryName = "United States of America";
        String stateName = "New York";
        String cityName = "New Jersey";

        String input = format("\"%s\" \"%s/%s/%s", userName, countryName, stateName, cityName);

        int rating = 7;
        when(applicationService.getRating(userName, countryName, stateName, cityName)).thenReturn(rating);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        fixture = new StandardInputListener(new ByteArrayInputStream((input + "\n\n") .getBytes()), new PrintStream(out), applicationService);

        fixture.processQueryInput();

        verify(applicationService).getRating(userName, countryName, stateName, cityName);

        assertEquals(input + ' ' + rating + '\n', out.toString());
    }

    @Test
    public void queryInput_multiLine_callService() {
        String userName1 = "Tony Stark";
        String countryName1 = "United States of America";
        String stateName1 = "New York";
        String cityName1 = "New Jersey";

        String line1 = format("\"%s\" \"%s/%s/%s", userName1, countryName1, stateName1, cityName1);
        int rating1 = 10;

        String userName2 = "Bruce Wayne";
        String countryName2 = "United States of America";
        String stateName2 = "New York";
        String cityName2 = "New Jersey";

        String line2 = format("\"%s\" \"%s/%s/%s", userName2, countryName2, stateName2, cityName2);
        int rating2 = 3;

        when(applicationService.getRating(userName1, countryName1, stateName1, cityName1)).thenReturn(rating1);
        when(applicationService.getRating(userName2, countryName2, stateName2, cityName2)).thenReturn(rating2);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        fixture = new StandardInputListener(new ByteArrayInputStream((line1 + '\n' + line2 +  "\n\n") .getBytes()), new PrintStream(out), applicationService);

        fixture.processQueryInput();

        verify(applicationService).getRating(userName1, countryName1, stateName1, cityName1);
        verify(applicationService).getRating(userName2, countryName2, stateName2, cityName2);

        assertEquals(line1 + ' ' + rating1 + '\n' + line2 + ' ' + rating2 + '\n', out.toString());
    }

    @Test
    public void queryInput_onlyCountry_callService() {
        String userName = "Tony Stark";
        String countryName = "United States of America";

        String input = format("\"%s\" \"%s", userName, countryName);

        int rating = 1;
        when(applicationService.getRating(userName, countryName, null, null)).thenReturn(rating);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        fixture = new StandardInputListener(new ByteArrayInputStream((input + "\n\n") .getBytes()), new PrintStream(out), applicationService);

        fixture.processQueryInput();

        verify(applicationService).getRating(userName, countryName, null, null);

        assertEquals(input + ' ' + rating + '\n', out.toString());
    }

    @Test
    public void queryInput_onlyCountryAndState_callService() {
        String userName = "Tony Stark";
        String countryName = "United States of America";
        String stateName = "New York";

        String input = format("\"%s\" \"%s/%s", userName, countryName, stateName);

        int rating = 9;
        when(applicationService.getRating(userName, countryName, stateName, null)).thenReturn(rating);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        fixture = new StandardInputListener(new ByteArrayInputStream((input + "\n\n") .getBytes()), new PrintStream(out), applicationService);

        fixture.processQueryInput();

        verify(applicationService).getRating(userName, countryName, stateName, null);

        assertEquals(input + ' ' + rating + '\n', out.toString());
    }
}