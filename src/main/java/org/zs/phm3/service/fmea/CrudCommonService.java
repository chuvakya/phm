package org.zs.phm3.service.fmea;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.fmea.FailureMode;
import org.zs.phm3.models.fmea.FailureReason;

import java.util.List;
import java.util.Set;

public interface CrudCommonService<T> {
    List<T> getAll();
    T save(T t) throws PhmException;
    void deleteById(Integer id);
    T getById(Integer id) throws PhmException;
    default List<T> findAllByName(String searchedString){
        return null;
    };
    default List<T> findByCategoryTypeId(Integer categoryTypeId){
        return null;
    };
}
