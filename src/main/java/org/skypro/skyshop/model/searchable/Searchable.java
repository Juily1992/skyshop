package org.skypro.skyshop.model.searchable;

import java.util.UUID;

public interface Searchable {
    String searchableName();

    String typeContent();
   UUID getId();
   default String getStringRepreseentation() {
        return searchableName();
    }

}
