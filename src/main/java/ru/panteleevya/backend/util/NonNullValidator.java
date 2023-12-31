package ru.panteleevya.backend.util;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@Service
public class NonNullValidator {
    public void validateNonNullFields(Map<Supplier<Object>, String> fieldProviderToFieldNameMap) {
        List<String> nullFieldNames = new ArrayList<>();
        for (Map.Entry<Supplier<Object>, String> entry : fieldProviderToFieldNameMap.entrySet()) {
            Supplier<Object> provider = entry.getKey();
            String fieldName = entry.getValue();
            if (Objects.isNull(provider.get())) {
                nullFieldNames.add(fieldName);
            }
        }
        if (!nullFieldNames.isEmpty()) {
            throw new IllegalArgumentException(String.format("Fields [%s] can't be null", nullFieldNames));
        }
    }
}
