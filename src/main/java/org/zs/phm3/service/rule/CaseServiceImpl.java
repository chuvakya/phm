package org.zs.phm3.service.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.rule.Case;
import org.zs.phm3.repository.rule.CaseRepository;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseRepository caseRepository;

    @Override
    public Case save(Case aCase) {
        return caseRepository.save(aCase);
    }
}
