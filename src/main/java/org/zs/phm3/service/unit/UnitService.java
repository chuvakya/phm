package org.zs.phm3.service.unit;

import org.zs.phm3.dto.DtoIdNameDescription;
import org.zs.phm3.dto.UnitDto;
import org.zs.phm3.dto.UnitDtoInput;
import org.zs.phm3.dto.UnitDtoOutput;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.unit.UnitEntity;

import java.util.ArrayList;
import java.util.List;

public interface UnitService {
    UnitDto save(UnitDtoInput unitForSave) throws PhmException;

    UnitDto save(UnitEntity unitForSave);

    void saveAll(List<UnitEntity> unitsList);

    UnitDto SaveAfterImport(UnitEntity unitFromImport);

    UnitDtoOutput findById(String unitIdStr);

    UnitEntity findByIdReturnEntity(String unitIdStr, Boolean getAllRelatedObjects) throws PhmException;

    UnitEntity getUnitById(String unitId);

    UnitEntity findByProjectAndId(Integer projId, String unitIdStr);

    List<UnitEntity> getAll();

    List<UnitEntity> getAllByProject(Integer projId);

    void assaignPicture(String unitIdStr, Integer pictureId) throws PhmException;

    void assaignParent(String unitId, String parentId);

    void deleteByIdSQL(String unitIdStr) throws PhmException;

    public void deleteWithLinkedObjects(String unitForSaveIdStr) throws PhmException;

    void delete(UnitEntity unitForDel);

    void showUnitId();

    Boolean isPresent(String unitId);

    String getServiceUnitForProject(Integer projectId);

    List<String> getUnitIds();

    List<String[]> getUnitIdParentIds();

    static List<UnitEntity> getHierarchicalList(List<UnitEntity> originalList) {
        final List<UnitEntity> copyList = new ArrayList<>(originalList);

        copyList.forEach(element -> {
            originalList
                    .stream()
                    .filter(parent -> parent.getId().toString().equals(element.getParentId()))
                    .findAny()
                    .ifPresent(parent -> {
                        if (parent.getChilds() == null) {
                            parent.setChilds(new ArrayList<>());
                        }
                        parent.getChilds().add(element);
                        originalList.remove(element);
                    });
        });
        return originalList;
    }
    void getAllChilds(String unitIdStr);

    List<String> getChilds(String unitId);

    List<DtoIdNameDescription> getAllByProjectShort(Integer projectId) throws PhmException;

    Long getModelPTLIdByUnitId(String unitId);

    UnitEntity fromDto(UnitEntity unitEntity, UnitDtoInput unitDto) throws PhmException;

    List<List<Object>> getAllUnitIdsAndNameAndParentId(Integer projectId);

    List<List<Object>> getAllUnitIdAndParentId();
}
