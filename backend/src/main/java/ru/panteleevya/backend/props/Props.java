package ru.panteleevya.backend.props;

public record Props(String key, String textValue) {
    @Override
    public String toString() {
        return key + ": " + textValue;
    }
}
