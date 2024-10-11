package org.zs.phm3.service.unit;

import org.zs.phm3.auditlog.ActionStatus;
import org.zs.phm3.auditlog.ActionType;
import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.dto.UnitAttributeDtoInput;
import org.zs.phm3.dto.UnitAttributeDtoOutput;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.CustomUnitEntity;
import org.zs.phm3.models.IdTextReturn;
import org.zs.phm3.models.UomEntity;
import org.zs.phm3.models.basic.IdComparator;
import org.zs.phm3.models.unit.UnitAttribute;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.zs.phm3.models.unit.UnitAttributeType;
import org.zs.phm3.repository.CustomUnitEntityRepository;
import org.zs.phm3.repository.UomEntityRepository;
import org.zs.phm3.repository.ts.TsKvRepository;
import org.zs.phm3.repository.unit.UnitAttributeRepository;
import org.zs.phm3.repository.unit.UnitAttributeSQLRepository;
import org.zs.phm3.repository.unit.UnitAttributeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
//import org.zs.phm3.repository.TsKvRepository;
import org.zs.phm3.service.BaseService;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UnitAttributeServiceImpl extends BaseService implements UnitAttributeService {
    @Autowired
    UnitAttributeRepository unitAttributeRepository;
    @Autowired
    UnitService unitService;
    @Autowired
    UomEntityRepository uomRepository;
    @Autowired
    UnitAttributeSQLRepository unitAttributeSQLRepo;
    @Autowired
    TsKvRepository tsKvRepo;
    @Autowired
    CustomUnitEntityRepository customUnitEntityRepo;
    @Autowired
    UnitAttributeTypeRepository unitAttributeTypeRepository;

    private String shortUnitId;
    private IdComparator<IdTextReturn> idComparator = new IdComparator<>();

    @Override
    public UnitAttribute save(UnitAttributeDtoInput unitAttributeDto) throws PhmException {

        final ActionType actionType;
        final String ACTION;

        UnitAttribute unitAttributeForSaving = unitAttributeDto.toDaoData();

        if (unitAttributeDto.getType() != null)
            unitAttributeForSaving.setType(unitAttributeTypeRepository.findById(unitAttributeDto.getType()).
                    orElseThrow(() ->
                            new PhmException("No type found with key=" + unitAttributeDto.getType(),
                                    PhmErrorCode.ITEM_NOT_FOUND)));

        if (unitAttributeDto.getUomInput() != null) {
            unitAttributeForSaving.setUomInput(uomGetById(unitAttributeDto.getUomInput()));
        }
        if (unitAttributeDto.getUomOutput() != null) {
            unitAttributeForSaving.setUomOutput(uomGetById(unitAttributeDto.getUomOutput()));
        }
        shortUnitId = getShortUnitId((unitAttributeForSaving.getId().getUnitId().toString()));
        if (!unitService.isPresent(shortUnitId))
            throw new PhmException("Unit with such Id is not present", PhmErrorCode.ITEM_NOT_FOUND);
        if (unitAttributeRepository.isNew(unitAttributeForSaving.getId().getAttributeKey(),
                shortUnitId)) {
            actionType = ActionType.UNIT_ATTRIBUTE_ADDED;
            ACTION = "New UnitAttribute " + unitAttributeForSaving.getId().getAttributeKey() + "=" +
                    unitAttributeForSaving.valueToString() + " saving";
        } else {
            actionType = ActionType.UNIT_ATTRIBUTE_UPDATED;
            ACTION = "New UnitAttribute " + unitAttributeForSaving.getId().getAttributeKey() + "=" +
                    unitAttributeForSaving.valueToString() + " updating";
        }
        try {
            UnitAttribute unitAttributeSaved = unitAttributeRepository.save(unitAttributeForSaving);
            logAction(shortUnitId, "", "USER", actionType, ActionStatus.SUCCESS,
                    ACTION, "");
            return unitAttributeSaved;
        } catch (Exception e) {
            logAction("", "", "USER",
                    actionType, ActionStatus.FAILURE, ACTION, e.toString());
            System.out.println(e.getMessage());
            throw handleException(e);
        }
    }

    @Override
    public List<UnitAttribute> saveAll(List<UnitAttribute> attributes) {
        return (List<UnitAttribute>) unitAttributeRepository.saveAll(attributes);
    }

    @Override
    public UnitAttribute findById(UnitAttributeKey UnitAttributeId) throws PhmException {

        shortUnitId = getShortUnitId((UnitAttributeId.getUnitId().toString()));
        if (!unitService.isPresent(shortUnitId))
            throw new PhmException("Unit is not present with such Id=" + UnitAttributeId.getUnitId().toString(),
                    PhmErrorCode.ITEM_NOT_FOUND);
        try {

            UnitAttribute unitAttributeReaded = unitAttributeRepository.findById(UnitAttributeId)
                    .orElseThrow(() ->
                            new PhmException("No attribute found with key=" + UnitAttributeId.getAttributeKey(),
                                    PhmErrorCode.ITEM_NOT_FOUND));

            return unitAttributeReaded;


        } catch (Exception e) {
            throw handleException(e);
        }

    }


    @Override
    public void deleteById(UnitAttributeKey UnitAttributeId) throws PhmException {
        shortUnitId = getShortUnitId((UnitAttributeId.getUnitId().toString()));
        if (!unitService.isPresent(shortUnitId))
            throw new PhmException("Unit is not present with such Id=" + UnitAttributeId.getUnitId().toString(),
                    PhmErrorCode.ITEM_NOT_FOUND);
        final String ACTION = "UnitModelAttribute " + UnitAttributeId.getAttributeKey() + " Deleting";
        shortUnitId = getShortUnitId((UnitAttributeId.getUnitId().toString()));
        try {
            unitAttributeRepository.deleteById(UnitAttributeId);
            logAction(shortUnitId, "", "USER", ActionType.UNITMODEL_ATTRIBUTE_DELETED,
                    ActionStatus.SUCCESS, ACTION, "");

        } catch (EmptyResultDataAccessException e) {
            logAction(shortUnitId, "", "USER", ActionType.UNITMODEL_ATTRIBUTE_DELETED,
                    ActionStatus.FAILURE, ACTION, e.getMessage());
            throw handleException(e);

        }

    }


    //    @Override
    private Boolean isNew(String attributeKey, String unitId) {
        return unitAttributeRepository.isNew(attributeKey, unitId);
    }

    @Override
    public List<UnitAttribute> getAllByUnitId(String unitId) {
        shortUnitId = getShortUnitId(unitId);
        return unitAttributeRepository.getAllByUnitId(shortUnitId);
    }

    private String getShortUnitId(String uuidUnitIdStr) {
        return UUIDConverter.fromTimeUUID(UUID.fromString(uuidUnitIdStr));
    }

    public LinkedList<IdTextReturn> getUomDataForDisplay() {

        List<IdTextReturn> uomForDisplay1 = new LinkedList<>();
        List<UomEntity> uomList = (List<UomEntity>) uomRepository.getAllForDisplay();
        uomList.forEach(item -> {

            uomForDisplay1.add(new IdTextReturn(item.toData().getId(), item.toData().getText()));

        });


        Collections.sort(uomForDisplay1, idComparator);
        return (LinkedList<IdTextReturn>) uomForDisplay1;
    }

    @Override
    public UomEntity uomGetById(Integer uomId) throws PhmException {
        return uomRepository.findById(uomId)
                .orElseThrow(() -> (new PhmException("UOM with id " + uomId +
                        " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public List<UnitAttributeDtoOutput> getAllForTableByUnitId(String UnitId) {
        final String shortUnitId = UUIDConverter.fromTimeUUID(UUID.fromString(UnitId));
        List<Object[]> unitAttributeTables = unitAttributeSQLRepo.getAllForTableByUnitId(shortUnitId);

        if (unitAttributeTables.size() > 0) {
            List<UnitAttributeDtoOutput> attribForDisplayList = new ArrayList<>();
            unitAttributeTables.forEach(record -> {

                UnitAttributeDtoOutput attrRec = new UnitAttributeDtoOutput();
                attrRec.setAttributeKey((String) record[0]);
                attrRec.setUnitId((String) record[1]);
                attrRec.setName((String) record[2]);

                if (record[3] != null) attrRec.setBooleanValue((Boolean) record[3]);
                if (record[4] != null) attrRec.setDoubleValue((Double) record[4]);
//            if (record[4]!=null) attrRec.setLongValue((Long) record[4]);
                BigInteger recBI = null;
                if (record[5] instanceof BigInteger) {
                    recBI = (BigInteger) record[5];

                }

                if (record[5] != null) {
                    assert recBI != null;
                    attrRec.setLongValue(recBI.longValue());
                }
                if (record[6] != null) attrRec.setStrValue((String) record[6]);
                if (record[7] != null) attrRec.setUom((String) record[7]);
                if (record[8] != null) attrRec.setUomOutput((String) record[8]);
                attribForDisplayList.add(attrRec);

            });
            return attribForDisplayList;
        }
        ;
        return null;
    }

    public List<IdTextReturn> getAllAttrKeysForUnitId(String unitId) {
        List<IdTextReturn> attrKeysReadedList = new LinkedList<>();
        List<String> attrKeysObject = tsKvRepo.getAllAttrKeysForUnitId(getShortUnitId(unitId));
        AtomicInteger counter = new AtomicInteger(0);
        attrKeysObject.forEach(item -> {
            attrKeysReadedList.add(new IdTextReturn(counter.addAndGet(1), item));
        });
        return attrKeysReadedList;
    }

    @Override
    public List<IdTextReturn> getAllCustomUnitId() {
        List<IdTextReturn> attrKeysReadedList = new LinkedList<>();
        List<CustomUnitEntity> CustomUnitList = (List<CustomUnitEntity>) customUnitEntityRepo.findAll();
        CustomUnitList.forEach(item -> {
            attrKeysReadedList.add(new IdTextReturn(item.getId(), item.customId));
        });

        return attrKeysReadedList;
    }

    public List<UnitAttributeType> getTypes() {
        return (List<UnitAttributeType>) unitAttributeTypeRepository.findAll();
    }

    @Override
    public UnitAttribute getUnitAttrFromUnitIdAndName(String unitId, String name) {
        return unitAttributeRepository.getUnitAttrFromUnitIdAndName(unitId, name);
    }
}
