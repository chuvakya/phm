package org.zs.phm3.repository.unit;

import org.zs.phm3.models.unit.UnitModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UnitModelRepository extends CrudRepository<UnitModel, Integer> {

    List<UnitModel> getAllByType_Id(Integer typeId);

}
