package org.zs.phm3.models.unit;

import java.util.Comparator;

public class UnitComparator implements Comparator<UnitEntity> {
    public int compare(UnitEntity a, UnitEntity b) {
        return b.getDateCreated().compareTo(a.getDateCreated());
    }
}
