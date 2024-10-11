package org.zs.phm3.models.ts;

import java.util.Comparator;

public class TsKvComparator<D extends TsKvEntity> implements Comparator<D> {
    @Override
    public int compare(D o1, D o2) {
        return Long.compare(o1.getId().getTs(), o2.getId().getTs());
    }
}
