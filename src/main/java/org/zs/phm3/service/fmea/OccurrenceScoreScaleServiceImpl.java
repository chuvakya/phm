package org.zs.phm3.service.fmea;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.basic.ToDaoService;
import org.zs.phm3.models.fmea.OccurrenceScoreScale;
import org.zs.phm3.repository.fmea.OccurrenceScoreScaleRepository;
import org.zs.phm3.util.GetNullProperties;

import java.util.List;

@Service
@Qualifier("occurrenceScoreScaleService")
public class OccurrenceScoreScaleServiceImpl implements CrudCommonServiceWithStringId<OccurrenceScoreScale>,
        ToDaoService<OccurrenceScoreScale> {

    @Autowired
    OccurrenceScoreScaleRepository occurrenceScoreScaleRepository;

    @Override
    public List<OccurrenceScoreScale> getAll() {
        return (List<OccurrenceScoreScale>) occurrenceScoreScaleRepository.findAll();
    }

    @Override
    public OccurrenceScoreScale save(OccurrenceScoreScale occurrenceScoreScale) throws PhmException {
        return occurrenceScoreScaleRepository.save(toDaoData(occurrenceScoreScale));
    }

    @Override
    public void deleteById(String id) {
        occurrenceScoreScaleRepository.deleteById(id);
    }

    @Override
    public OccurrenceScoreScale getById(String id) throws PhmException {
        return occurrenceScoreScaleRepository.findById(id).orElseThrow(() -> (new
                PhmException("OccurrenceScoreScale with id " + id +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public Boolean isNew(String id) {
        return (occurrenceScoreScaleRepository.countById(id)==0);
    }

    @Override
    public OccurrenceScoreScale toDaoData(OccurrenceScoreScale occurrenceScoreScale) throws PhmException {
        if (isNew(occurrenceScoreScale.getId())) {
            return occurrenceScoreScale;
        }else{
            OccurrenceScoreScale oss = getById(occurrenceScoreScale.getId());
            BeanUtils.copyProperties(occurrenceScoreScale, oss, GetNullProperties.getNullPropertyNames(occurrenceScoreScale));
            return oss;
        }
    }
}
