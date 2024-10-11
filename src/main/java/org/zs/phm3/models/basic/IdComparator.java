package org.zs.phm3.models.basic;

import java.util.Comparator;

public class IdComparator<D extends IdEntity> implements Comparator<D> {
    @Override
    public int compare(D o1, D o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
