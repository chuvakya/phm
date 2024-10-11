package org.zs.phm3.service.unit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.dto.DtoIdNameDescription;
import org.zs.phm3.dto.UnitDto;
import org.zs.phm3.dto.UnitDtoInput;
import org.zs.phm3.dto.UnitDtoOutput;
import org.zs.phm3.exception.DataValidationException;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.FileEntity;
import org.zs.phm3.models.SchemeEntity;
import org.zs.phm3.models.SchemeType;
import org.zs.phm3.models.unit.UnitAttribute;
import org.zs.phm3.models.unit.UnitComparator;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.repository.SchemeRepository;
import org.zs.phm3.repository.calculation.CalculationAttrRepository;
import org.zs.phm3.repository.ml.DataSchemaRepository;
import org.zs.phm3.repository.ml.MlServiceResultRepository;
import org.zs.phm3.repository.ts.TsKvAttributeRepository;
import org.zs.phm3.repository.unit.UnitAttributeRepository;
import org.zs.phm3.repository.unit.UnitRepository;
import org.zs.phm3.service.project.ProjectService;
import org.zs.phm3.util.mapping.PhmUtil;
import org.zs.phm3.validation.DataValidator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.zs.phm3.service.FileService;
import org.zs.phm3.service.PhmService;
import org.zs.phm3.service.SchemeService;
import org.zs.phm3.service.ptl.ModelPTLService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    SchemeRepository schemeRepository;
    @Autowired
    UnitAttributeService unitAttributeService;
    @Autowired
    SchemeService schemeService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PhmService phmService;
    @Autowired
    UnitAttributeRepository unitAttributeRepository;
    @Autowired
    MlServiceResultRepository mlServiceResultRepository;
    @Autowired
    CalculationAttrRepository calculationAttrRepository;
    @Autowired
    DataSchemaRepository dataSchemaRepository;
    @Autowired
    TsKvAttributeRepository tsKvAttributeRepository;
    @Autowired
    ModelPTLService modelPTLService;
    private final String serviceVersion = ".13";
    List<String> childsList = new LinkedList();

    private final DataValidator<UnitDtoInput> dataValidator =
            new DataValidator<>() {
                @Override
                protected void validateDataImpl(UnitDtoInput unit) {
                    if (unit.getProject_id() == null) {
                        throw new DataValidationException("For new Unit Project should be specified!");
                    }
                }
            };

    @Override
    @Modifying
    @Transactional
    public UnitDto save(UnitDtoInput unitDtoForSave) throws PhmException {
        UnitDtoOutput unitSavedDto;
        UnitEntity unitSaved;
        UnitEntity unitForSave;
        List<SchemeEntity> existingSchemes;

//        if (unitDtoForSave.getId() == null) {// new unit
        if (unitDtoForSave.isNew()) {
            dataValidator.validate(unitDtoForSave);
            unitForSave = fromDto(new UnitEntity(), unitDtoForSave);
            unitForSave.setDateCreated(LocalDateTime.now());
            unitForSave.setDateChanged(LocalDateTime.now());
            unitForSave.setId(Generators.timeBasedGenerator().generate());
            unitForSave.setActiveScheme(SchemeType.FIRST);
        } else {// existing unit (for PATCH)
            UnitEntity unitOriginal = unitRepository.findById(PhmUtil.toShortUUID(unitDtoForSave.getId()))
                    .orElseThrow(() ->
                            new PhmException("Error finding original unit", PhmErrorCode.ITEM_NOT_FOUND));

            unitForSave = fromDto(unitOriginal, unitDtoForSave);
            unitForSave.setDateChanged(LocalDateTime.now());
        }

/*        TODO !!!
        if (unitDtoForSave.getModelPtlId() == null & unitDtoForSave.getUnitTypePtlId() != null) {
            unitForSave.setModelPtl(modelPTLService.getEmptyModelForUnitType(unitDtoForSave.getUnitTypePtlId()));
        }
*/
        unitSaved = unitRepository.save(unitForSave);
        unitSavedDto = unitSaved.toData();
        if (unitSavedDto != null) { // new unit
            if (unitDtoForSave.getSchemes().size() == 0)

                existingSchemes = List.of(new SchemeEntity(
                        "New", SchemeType.FIRST,
                        "default scheme", phmService.getDefaultSchemeBody(), ""));
            else
                existingSchemes = unitDtoForSave.getSchemes();
            existingSchemes.forEach(scheme -> scheme.setUnitId(UUIDConverter.fromTimeUUID(unitSaved.getId())));
            schemeService.save(existingSchemes);
            unitSavedDto.setSchemes(schemeService.save(existingSchemes));
        }
        return unitSavedDto;
    }

    @Override
//    @Modifying
    @Transactional
    public UnitDto SaveAfterImport(UnitEntity unitImported) {

        UnitEntity unitSaved = unitRepository.save(unitImported);
        UnitDto unitSavedDto = unitSaved.toData();
        List<SchemeEntity> existingSchemes = unitImported.getSchemes();
        unitSavedDto.setSchemes(schemeService.save(existingSchemes));

        List<UnitAttribute> existingAttributes = unitImported.getAttributes();
        unitSavedDto.setAttributes(unitAttributeService.saveAll(existingAttributes));

        return null;
    }

    @Override
//    now for case Service Unit only
    public UnitDto save(UnitEntity unitForSave) {
        if (unitForSave.getId() == null) {
            unitForSave.setId(Generators.timeBasedGenerator().generate());
        }
        UnitEntity unitSaved = unitRepository.save(unitForSave);
        return unitSaved.toData();
    }

    @Override
    public void saveAll(List<UnitEntity> unitsList) {
        unitRepository.saveAll(unitsList);
    }

    @Override
    public UnitDtoOutput findById(String unitIdStr) {
        String unitIdStrShort = PhmUtil.toShortUUID(unitIdStr);
        UnitEntity unitFinded = unitRepository.findById(unitIdStrShort).orElse(null);
        assert unitFinded != null;
        List<UnitAttribute> attributesFinded = unitAttributeService.getAllByUnitId(unitIdStr);
        List<SchemeEntity> schemeFinded = schemeService.getAllByUnitId(unitIdStr);
        if (attributesFinded.size() != 0) {
            unitFinded.setAttributes(unitAttributeService.getAllByUnitId(unitIdStr));
        }
        unitFinded.setSchemes(schemeFinded);

        UnitDtoOutput unitDtoFinded = unitFinded.toData();
        if (unitFinded.getIcon() != null)
            unitDtoFinded.setPic_id(unitFinded.getIcon().getId());
        if (unitFinded.getBackground() != null)
            unitDtoFinded.setPicbckgr_id(unitFinded.getBackground().getId());
        if (unitFinded.getParentId() != null)
            unitDtoFinded.setParentId(unitFinded.getParentId());
        unitDtoFinded.componentVersion = serviceVersion;
        return unitDtoFinded;
    }

    @Override

    public UnitEntity findByIdReturnEntity(String unitIdStr, Boolean getAllRelatedObjects) throws PhmException {
        String unitIdStrShort = PhmUtil.toShortUUID(unitIdStr);
        UnitEntity unitFinded = unitRepository.findById(unitIdStrShort)
                .orElseThrow(() -> (new PhmException("Unit with id " + unitIdStr +
                        " not found", PhmErrorCode.ITEM_NOT_FOUND)));
        if (getAllRelatedObjects) {
            unitFinded.setSchemes(schemeService.getAllByUnitId(unitIdStr));
            unitFinded.setAttributes(unitAttributeService.getAllByUnitId(unitIdStr));
        }
        return unitFinded;
    }

    @Override
    public UnitEntity findByProjectAndId(Integer projId, String unitIdStr) {

        String unitIdStrShort = PhmUtil.toShortUUID(unitIdStr);
        UnitEntity unitFinded = unitRepository.findByProjectAndIdSQL(projId, unitIdStrShort).orElse(null);
        assert unitFinded != null;
        List<UnitAttribute> attributesFinded = unitAttributeService.getAllByUnitId(unitIdStr);
        List<SchemeEntity> schemeFinded = schemeService.getAllByUnitId(unitIdStr);
        if (attributesFinded.size() != 0) {
            unitFinded.setAttributes(unitAttributeService.getAllByUnitId(unitIdStr));
        }
        unitFinded.setSchemes(schemeFinded);
        return unitFinded;
    }

    @Override

    public List<UnitEntity> getAll() {
        return (List<UnitEntity>) unitRepository.findAll();
    }

    @Override
    public List<UnitEntity> getAllByProject(Integer projId) {
        List<UnitEntity> unitList = unitRepository.findAllByProjectId(projId);
        UnitComparator unitcomp = new UnitComparator();
        unitList.sort(unitcomp);

/*        List<UnitEntity> unitsResult=UnitService.getHierarchicalList(unitList);
        List<UnitDtoOutput> UnitDtoOutputResult=new ArrayList<>();
        for (UnitEntity unit : unitsResult) {
            UnitDtoOutput new1 = new UnitDtoOutput();
            new1=unit;

        }*/
        return UnitService.getHierarchicalList(unitList);
    }

    @Override
    public UnitEntity getUnitById(String unitId) {
        return unitRepository.findById(unitId).orElse(null);//.get();
    }

    @Override
    public void assaignPicture(String unitIdStr, Integer pictureId) throws PhmException {
        FileEntity pictureSelected = fileService.findById(pictureId);
        String unitIdStrShort = PhmUtil.toShortUUID(unitIdStr);
        Optional<UnitEntity> unitFindedOpt = unitRepository.findById(unitIdStrShort);
        if (unitFindedOpt.isPresent()) {
            UnitEntity unitFinded = unitFindedOpt.orElse(null);
            unitFinded.setIcon(pictureSelected);
            unitRepository.save(unitFinded);
        }
    }

    //    @Modifying
    @Override
    public void assaignParent(String unitId, String parentId) {
        unitRepository.assignParent(unitId, parentId);
    }

    @Override
    @Modifying
    public void deleteByIdSQL(String unitForSaveIdStr) throws PhmException {
        if (!projectService.isFirstProjectUnit(unitForSaveIdStr))
            unitRepository.deleteByIdSQL(unitForSaveIdStr);
        else {
            throw new PhmException("You can't delete first unit of project", PhmErrorCode.GENERAL);
        }
    }

    @Override
    public void delete(UnitEntity unitForSaveForDel) {
        unitRepository.delete(unitForSaveForDel);
    }

    @Override
    @Transactional
//    @Modifying
//    https://thorben-janssen.com/avoid-cascadetype-delete-many-assocations/
    public void deleteWithLinkedObjects(String unitIdStr) throws PhmException {
        if (!projectService.isFirstProjectUnit(unitIdStr)) {
            childsList.add(unitIdStr);
            getAllChilds(unitIdStr);

            List<String> reverseChildsList = Lists.reverse(childsList);//childsList.rev
            reverseChildsList.forEach(unitId -> {
                System.out.println("Deleting " + unitId);
                tsKvAttributeRepository.deleteByUnit(unitId);
                dataSchemaRepository.deleteByUnit(unitId);
                calculationAttrRepository.deleteByUnit(unitId);
                mlServiceResultRepository.deleteByUnit(unitId);
                unitAttributeRepository.deleteByUnit(unitId);
                schemeRepository.deleteByUnit(unitId);
                unitRepository.deleteByIdSQL(unitId);
            });
        } else {
            throw new PhmException("You can't delete first unit of project", PhmErrorCode.GENERAL);
        }
    }

    @Override
    public void getAllChilds(String unitIdStr) {
        List<String> childsListWork = unitRepository.findChilds(unitIdStr);
        if (childsListWork.size() > 0) {
            childsListWork.forEach(unitId -> {
                childsList.add(unitId);
                getAllChilds(unitId);
            });
        }
    }

    @Override
    public List<String> getChilds(String unitId) {
        return unitRepository.findChilds(unitId);
    }

    @Override
    public void showUnitId() {
        List<UnitEntity> unitList = (List<UnitEntity>) unitRepository.findAll();
        unitList.forEach(item -> System.out.println(item.getName() + " | " + item.getId() + " | " +
                UUIDConverter.fromTimeUUID(UUID.fromString(item.getId().toString())) + " | " +
                item.getDateCreated()));
    }

    @Override
    public Boolean isPresent(String unitId) {
        return unitRepository.isPresent(unitId);
    }

    @Override
    public String getServiceUnitForProject(Integer projectId) {
        return unitRepository.getServiceUnitForProject(projectId);
    }

    @Override
    public List<String> getUnitIds() {
        return unitRepository.getUnitIds();
    }

    @Override
    public List<String[]> getUnitIdParentIds() {
        return unitRepository.getUnitIdParentIds();
    }

    @Override
    public List<DtoIdNameDescription> getAllByProjectShort(Integer projectId) {

        List<String[]> unitsList = unitRepository.findByProjectShortSQL(projectId);
        List<DtoIdNameDescription> unitsShortList = new ArrayList<>();
        for (String[] fields : unitsList) {
            unitsShortList.add(new DtoIdNameDescription(PhmUtil.toLongUUID(fields[0]), fields[1], fields[2]));
        }
        return unitsShortList;
    }

/*    @Override
    public String getAllByProjectShortNew(Integer projectId) {
        JSONArray jsonArray = new JSONArray();

        List<String[]> unitsList = unitRepository.findByProjectShortSQL(projectId);
        for (String[] fields : unitsList) {
            JSONObject json = new JSONObject();
            json.put("unitId", PhmUtil.toLongUUID(fields[0]));
            json.put("name", fields[1]);
            json.put("description", fields[2]);
            json.put("unitTypePictureId", fields[3]);
            jsonArray.add(json);
        }
        return jsonArray.toJSONString();
    }*/

    @Override
    public Long getModelPTLIdByUnitId(String unitId) {
        return unitRepository.getModelPTLIdByUnitId(unitId);
    }

    public UnitEntity fromDto(UnitEntity unitEntity, UnitDtoInput unitDto) throws PhmException {
        /* data mapping unitDto —> unitEntity, ONLY for Saving */
        if (unitEntity.getProject() == null) /* Changing Project Data for existing unit is impossible */
            unitEntity.setProject(projectService.findProjectById(unitDto.getProject_id()));

        if (unitDto.getName() != null)
            unitEntity.setName(unitDto.getName());

        if (unitDto.getDescription() != null)
            unitEntity.setDescription(unitDto.getDescription());

        if (unitDto.getPic_id() != null)
            unitEntity.setIcon(fileService.findById(unitDto.getPic_id()));

        if (unitDto.getPicbckgr_id() != null)
            unitEntity.setBackground(fileService.findById(unitDto.getPicbckgr_id()));

        if (unitDto.getCustomId() != null)
            unitEntity.setCustomId(unitDto.getCustomId());

        if ((unitDto.getParentId() != null) && (!"".equals(unitDto.getParentId())))
            try {
                UnitEntity unitParent = unitRepository.findById(PhmUtil.toShortUUID(unitDto.getParentId()))
                        .orElseThrow(() ->
                                new PhmException("No parent found with key=" + unitDto.getParentId(),
                                        PhmErrorCode.ITEM_NOT_FOUND));
                //noinspection NumberEquality
                if (unitParent.getProject().getId() == unitEntity.getProject().getId())
                    unitEntity.setParent(unitParent);
                else
                    throw new PhmException("Attempt assign foreign project to unit (parent from another project)"
                            , PhmErrorCode.INVALID_ARGUMENTS);
            } catch (java.lang.IllegalArgumentException e) {
                throw new PhmException("Error reading parent object with id='" + unitDto.getParentId()
                        + "' for new unit", PhmErrorCode.ITEM_NOT_FOUND);
            }

        if (unitDto.getAttributes().size() > 0)
            unitEntity.setAttributes(unitDto.getAttributes());

        unitEntity.setActiveScheme(unitDto.getActiveScheme());

        if (unitDto.getPtlInput() != null) {
            if (unitDto.getPtlInput().getModelPtlId() != null) {
                unitEntity.setModelPtl(modelPTLService.getById(unitDto.getPtlInput().getModelPtlId()));
            } else {
                if (unitDto.getPtlInput().getUnitTypePtlId() != null)
                    unitEntity.setModelPtl(modelPTLService.getEmptyModelForUnitType(unitDto.getPtlInput().getUnitTypePtlId()));
            }
        }
/*        if (unitDto.getPtlInput().getModelPtlId()== null & unitDto.getPtlInput().getUnitTypePtlId() != null) {
            unitEntity.setModelPtl(modelPTLService.getEmptyModelForUnitType(unitDto.getPtlInput().getUnitTypePtlId()));
        }*/

        if (unitDto.getSerialNumber() != null)
            unitEntity.setSerialNumber(unitDto.getSerialNumber());

        return unitEntity;
    }

    @Override
    public List<List<Object>> getAllUnitIdsAndNameAndParentId(Integer projectId) {
        return unitRepository.getAllUnitIdsAndNameAndParentIdSQL(projectId);
    }

    @Override
    public List<List<Object>> getAllUnitIdAndParentId() {
        return unitRepository.getAllUnitIdAndParentId();
    }


    @SuppressWarnings("unused")
    public JsonNode getBody() throws PhmException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("!!!!! classpath: " + ResourceUtils.getURL("classpath"));

            return mapper.readTree(ResourceUtils.getURL("classpath:phm/DefaultScheme.json").openStream());
        } catch (
                IOException e) {
            throw new PhmException("Error reading default scheme body", PhmErrorCode.INVALID_ARGUMENTS);
        }
    }
}
