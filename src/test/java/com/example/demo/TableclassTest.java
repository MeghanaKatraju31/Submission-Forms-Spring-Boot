package com.example.demo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TableclassTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenAllFieldsAreValid_thenNoViolations() {
        Tableclass tableclass = new Tableclass();
        tableclass.setCid("abc123");
        tableclass.setCname("John Doe");
        tableclass.setCemail("john.doe@example.com");
        tableclass.setDateOfBirth("1990-01-01");
        tableclass.setTimezone("UTC");
        tableclass.setTime("10:00 AM");

        Set<ConstraintViolation<Tableclass>> violations = validator.validate(tableclass);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void whenCidIsInvalid_thenViolation() {
        Tableclass tableclass = new Tableclass();
        tableclass.setCid("abc"); // invalid cid
        tableclass.setCname("John Doe");
        tableclass.setCemail("john.doe@example.com");
        tableclass.setDateOfBirth("1990-01-01");
        tableclass.setTimezone("UTC");
        tableclass.setTime("10:00 AM");

        Set<ConstraintViolation<Tableclass>> violations = validator.validate(tableclass);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("College ID must be alphanumeric and 6-10 characters long", violations.iterator().next().getMessage());
    }

    @Test
    public void whenCnameIsInvalid_thenViolation() {
        Tableclass tableclass = new Tableclass();
        tableclass.setCid("abc123");
        tableclass.setCname("J1"); // invalid name
        tableclass.setCemail("john.doe@example.com");
        tableclass.setDateOfBirth("1990-01-01");
        tableclass.setTimezone("UTC");
        tableclass.setTime("10:00 AM");

        Set<ConstraintViolation<Tableclass>> violations = validator.validate(tableclass);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
       // assertEquals("Name should be between 2 and 50 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void whenCemailIsInvalid_thenViolation() {
        Tableclass tableclass = new Tableclass();
        tableclass.setCid("abc123");
        tableclass.setCname("John Doe");
        tableclass.setCemail("john.doe"); // invalid email
        tableclass.setDateOfBirth("1990-01-01");
        tableclass.setTimezone("UTC");
        tableclass.setTime("10:00 AM");

        Set<ConstraintViolation<Tableclass>> violations = validator.validate(tableclass);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Invalid email format", violations.iterator().next().getMessage());
    }

    @Test
    public void whenDateOfBirthIsEmpty_thenViolation() {
        Tableclass tableclass = new Tableclass();
        tableclass.setCid("abc123");
        tableclass.setCname("John Doe");
        tableclass.setCemail("john.doe@example.com");
        tableclass.setDateOfBirth(""); // empty date of birth
        tableclass.setTimezone("UTC");
        tableclass.setTime("10:00 AM");

        Set<ConstraintViolation<Tableclass>> violations = validator.validate(tableclass);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Date of Birth is required", violations.iterator().next().getMessage());
    }

    @Test
    public void whenTimezoneIsEmpty_thenViolation() {
        Tableclass tableclass = new Tableclass();
        tableclass.setCid("abc123");
        tableclass.setCname("John Doe");
        tableclass.setCemail("john.doe@example.com");
        tableclass.setDateOfBirth("1990-01-01");
        tableclass.setTimezone(""); // empty timezone
        tableclass.setTime("10:00 AM");

        Set<ConstraintViolation<Tableclass>> violations = validator.validate(tableclass);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Timezone is required", violations.iterator().next().getMessage());
    }

    @Test
    public void whenTimeIsEmpty_thenViolation() {
        Tableclass tableclass = new Tableclass();
        tableclass.setCid("abc123");
        tableclass.setCname("John Doe");
        tableclass.setCemail("john.doe@example.com");
        tableclass.setDateOfBirth("1990-01-01");
        tableclass.setTimezone("UTC");
        tableclass.setTime(""); // empty time

        Set<ConstraintViolation<Tableclass>> violations = validator.validate(tableclass);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Time is required", violations.iterator().next().getMessage());
    }

    // Add more tests for other edge cases as needed
}

