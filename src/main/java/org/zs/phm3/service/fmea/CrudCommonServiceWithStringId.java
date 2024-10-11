package org.zs.phm3.service.fmea;

import org.zs.phm3.exception.PhmException;

import java.util.List;

public interface CrudCommonServiceWithStringId<T> {
    List<T> getAll();
    T save(T t) throws PhmException;
    void deleteById(String id);
    T getById(String id) throws PhmException;
    Boolean isNew(String id);
}
