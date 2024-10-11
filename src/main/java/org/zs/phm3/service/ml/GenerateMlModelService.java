package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.*;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.models.user.UserEntity;
import org.zs.phm3.repository.ts.TsKvAttributeRepository;
import org.zs.phm3.scheduled.TrainService;
import org.zs.phm3.service.ts.TsKvAttributeService;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.util.mapping.PhmUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenerateMlModelService {

    @Autowired
    private DatasetService datasetService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private DataSchemaService dataSchemaService;

    @Autowired
    private ModelSchemaService modelSchemaService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private TsKvAttributeRepository tsKvAttributeRepository;

    @Autowired
    private MlModelService mlModelService;

    @Autowired
    private DataSchemaTsKvAttributeService dataSchemaTsKvAttributeService;

    @Autowired
    private UserService userService;

    @Scheduled(fixedRate = 5000)
    public void generator() {
        if (!GenerateMlModel.getGenerateMlModels().isEmpty()) {
            GenerateMlModel generateMlModel = GenerateMlModel.getGenerateMlModels().getFirst();
            generateMlModel(generateMlModel.getUnitId(), generateMlModel.getMlModelId(), generateMlModel.getModelName(),
                    userService.getByLogin("Admin"));
            GenerateMlModel.getGenerateMlModels().pollFirst();
        }
    }

    @Transactional
    public void generateMlModel(String unitId, Long mlModelId, String modelName, UserEntity user) {

        String newUnitIdShort = PhmUtil.toShortUUID(unitId);
        UnitEntity unitEntity = unitService.getUnitById(newUnitIdShort);
        MlModel mlModel = mlModelService.getById(mlModelId);


        // создание новой даташемы
        String newDataSchemaName = dataSchemaService.getNameForNewDataSchema(mlModel.getProjectId());
        DataSchema newDataSchema = new DataSchema(newDataSchemaName, newUnitIdShort, unitEntity.getProject().getId(),
                user, System.currentTimeMillis(),
                tsKvAttributeRepository.getByNameAndUnitId(mlModel.getDataSchema().getBitAttribute().getName(), newUnitIdShort),
                mlModel.getDataSchema().getBitErrorCode());
        dataSchemaService.save(newDataSchema);

        for (TsKvAttribute tsKvAttribute : dataSchemaTsKvAttributeService.getAllAttributesByDataSchemaId(mlModel.getDataSchema().getId())) {
            TsKvAttribute newTsKvAttribute = tsKvAttributeRepository.getByNameAndUnitId(tsKvAttribute.getName(), newUnitIdShort);
            dataSchemaTsKvAttributeService.save(new DataSchemaTsKvAttribute(newDataSchema, newTsKvAttribute));
        }


        // создание датасета
        // 1604384894654 device1, 1604384949261 device2
        String newDatasetName = datasetService.getNameForNewDataset(unitEntity.getProject().getId());
        Dataset newDataset = new Dataset(newDatasetName, null, unitEntity.getProject().getId(), newDataSchema);
        newDataset.setModifiedBy(user);
        JSONObject jsonObjectPeriod = null;
        try {
            jsonObjectPeriod = (JSONObject) new JSONParser().parse(dataSchemaService.getMaxPeriodForDataSchema(newDataSchema));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не хватает атрибутов у юнита: " + unitEntity.getName());
            return;
        }
        newDataset.setTimeFrom((Long) jsonObjectPeriod.get("timeFrom"));
        newDataset.setTimeTo((Long) jsonObjectPeriod.get("timeTo"));
        if (newDataset.getTimeFrom() == null) {
            System.out.println("Нет данных по указанным атрибутам");
            return;
        }
        try {
            datasetService.createDataset(newDataset);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка в создании датасета");
            return;
        }
        datasetService.save(newDataset);


        // копирование ModelSchema
        ModelSchema modelSchema = mlModel.getModelSchema();
        ModelSchema newModelSchema = new ModelSchema(modelSchema.getMlAlgorithm(), modelSchema.getDataset(),
                unitEntity.getProject().getId(), modelSchema.getName(), modelSchema.getWindowLength(), modelSchema.getTrainPeriod());
        newModelSchema.setModifiedBy(user);
        newModelSchema.setCreateTime(System.currentTimeMillis());
        modelSchemaService.save(newModelSchema);

        // создание и запуск Job'a
        MlJob newMlJob = new MlJob(modelSchema.getName(), user, newModelSchema);
        newMlJob.setProjectId(unitEntity.getProject().getId());
        newMlJob.setCreateTime(System.currentTimeMillis());
        try {
            trainService.train(newMlJob);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
