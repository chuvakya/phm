package org.zs.phm3.repository.rule;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.rule.Case;

@Transactional
@Repository
public interface CaseRepository extends CrudRepository<Case, Long> {
}
