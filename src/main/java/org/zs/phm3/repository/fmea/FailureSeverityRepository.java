package org.zs.phm3.repository.fmea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.fmea.FailureSeverity;

@Repository

public interface FailureSeverityRepository extends CrudRepository<FailureSeverity, String> {
    int countById(String id);
}
