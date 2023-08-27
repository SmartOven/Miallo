package ru.panteleevya.backend.props;

import java.util.List;

public record Secret(List<Props> entries, String versionId) {
}
