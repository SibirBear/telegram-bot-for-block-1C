package info.fermercentr.service;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static info.fermercentr.service.CheckDateTime.validate;
import static org.junit.Assert.*;

public class CheckDateTimeTest {

    @Test
    public void testValidateDate() {
        assertTrue(validate(String.valueOf(LocalDate.now())));
        assertTrue(validate(String.valueOf(LocalDate.now().plusDays(7))));
        assertFalse(validate(String.valueOf(LocalDate.now().minusMonths(2))));
        assertFalse(validate(String.valueOf(LocalDate.now().minusMonths(6))));
        assertFalse(validate(String.valueOf(LocalDate.now().plusMonths(2))));
    }

    @Test
    public void testValidateTime() {
        assertTrue(validate(String.valueOf(LocalTime.now().plusMinutes(10).withSecond(0).withNano(0)),
                String.valueOf(LocalDate.now())));
        assertFalse(validate(String.valueOf(LocalTime.now().withSecond(0).withNano(0)),
                String.valueOf(LocalDate.now())));
        assertFalse(validate(String.valueOf(LocalTime.now().minusMinutes(10).withSecond(0).withNano(0)),
                String.valueOf(LocalDate.now())));
    }
}