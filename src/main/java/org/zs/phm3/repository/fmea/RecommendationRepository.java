package org.zs.phm3.repository.fmea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.fmea.Recommendation;

import javax.transaction.Transactional;

@Repository
//@Transactional

public interface RecommendationRepository extends CrudRepository<Recommendation, Integer> {
}

