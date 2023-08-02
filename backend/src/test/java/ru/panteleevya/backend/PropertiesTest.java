package ru.panteleevya.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class PropertiesTest {
    private final String testProperty;

    public PropertiesTest(@Value("${test-property}") String testProperty) {
        this.testProperty = testProperty;
    }

    @Test
    void checkTestProperties() {
        assertEquals("test-value", testProperty);
    }
}
