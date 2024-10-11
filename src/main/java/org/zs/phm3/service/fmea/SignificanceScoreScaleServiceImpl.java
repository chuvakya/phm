package org.zs.phm3.service.fmea;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.basic.ToDaoService;
import org.zs.phm3.models.fmea.SignificanceScoreScale;
import org.zs.phm3.repository.fmea.SignificanceScoreScaleRepository;
import org.zs.phm3.util.GetNullProperties;

import java.util.List;

@Service
@Qualifier("significanceScoreScaleService")
public class SignificanceScoreScaleServiceImpl implements CrudCommonServiceWithStringId<SignificanceScoreScale>,
        ToDaoService<SignificanceScoreScale> {

    @Autowired
    SignificanceScoreScaleRepository significanceScoreScaleRepository;

    @Override
    public List<SignificanceScoreScale> getAll() {
        return (List<SignificanceScoreScale>) significanceScoreScaleRepository.findAll();
    }

    @Override
    public SignificanceScoreScale save(SignificanceScoreScale occurrenceScoreScale) throws PhmException {
        return significanceScoreScaleRepository.save(toDaoData(occurrenceScoreScale));
    }

    @Override
    public void deleteById(String id) {
        significanceScoreScaleRepository.deleteById(id);
    }

    @Override
    public SignificanceScoreScale getById(String id) throws PhmException {
        return significanceScoreScaleRepository.findById(id).orElseThrow(() -> (new
                PhmException("SignificanceScoreScale with id " + id +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public Boolean isNew(String id) {
        return (significanceScoreScaleRepository.countById(id)==0);
    }

    @Override
    public SignificanceScoreScale toDaoData(SignificanceScoreScale significanceScoreScale) throws PhmException {
        if (isNew(significanceScoreScale.getId())) {
            return significanceScoreScale;
        } else {
            SignificanceScoreScale sss = getById(significanceScoreScale.getId());
            BeanUtils.copyProperties(significanceScoreScale, sss, GetNullProperties.getNullPropertyNames(significanceScoreScale));
            return sss;
        }
    }
}
