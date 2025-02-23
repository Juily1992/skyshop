package org.skypro.skyshop.model.searchable;

import java.util.UUID;

public interface Searchable {
    String searchableName();

    String getName();

    String typeContent();
    default int getId() {
        return UUID.randomUUID().hashCode();
    }
    default String getStringRepreseentation() {
        return getName() + " - " + typeContent();
    }

}
