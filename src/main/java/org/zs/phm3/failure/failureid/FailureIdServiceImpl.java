package org.zs.phm3.failure.failureid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FailureIdServiceImpl implements FailureIdService {

    @Autowired
    FailureIdRepository failureIdRepository;

    @Override
    public FailureIdEntity save(FailureIdEntity failureIdEntity) {
        return failureIdRepository.save(failureIdEntity);
    }

    @Override
    public Iterable<FailureIdEntity> findAll() {
        return failureIdRepository.findAll();
    }

    @Override
    public FailureIdEntity findById(Integer id) {
        return failureIdRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        failureIdRepository.deleteById(id);
    }
}
