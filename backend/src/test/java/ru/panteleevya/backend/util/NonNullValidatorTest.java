package ru.panteleevya.backend.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NonNullValidatorTest {
    private final Supplier<Object> nullSupplier = mock(Supplier.class);
    private final Supplier<Object> nonNullSupplier = mock(Supplier.class);

    @BeforeEach
    void setUp() {
        when(nonNullSupplier.get()).thenReturn("NotNullObject");
        when(nullSupplier.get()).thenReturn(null);
    }

    @Test
    void validateHavingNull() {
        NonNullValidator nonNullValidator = new NonNullValidator();
        Map<Supplier<Object>, String> map = Map.of(nullSupplier, "myNullField");
        assertThrows(IllegalArgumentException.class, () -> nonNullValidator.validateNonNullFields(map));
    }

    @Test
    void validateNotHavingNull() {
        NonNullValidator nonNullValidator = new NonNullValidator();
        Map<Supplier<Object>, String> map = Map.of(nonNullSupplier, "myNonNullField");
        assertDoesNotThrow(() -> nonNullValidator.validateNonNullFields(map));
    }
}