package org.zs.phm3.service;

import org.zs.phm3.models.UomEntity;

import java.util.List;

public interface UomService {
    List<UomEntity> getAll();
    Double convert(UomEntity uomInput, UomEntity uomOutput, Double value);
    UomEntity getById(Integer id);
}
