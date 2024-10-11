package org.zs.phm3;

import org.zs.phm3.config.GlobalProperties;
import org.zs.phm3.dto.UnitDtoInput;
import org.zs.phm3.models.CustomUnitEntity;
import org.zs.phm3.models.ProjectEntity;
import org.zs.phm3.models.UomEntity;
import org.zs.phm3.models.UomTypes;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.models.ts.TsKvId;
import org.zs.phm3.models.unit.UnitAttributeType;
import org.zs.phm3.util.parquet.csv.PhmCSV;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.dto.UnitDto;
import org.zs.phm3.exception.NotFoundException;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.repository.CustomUnitEntityRepository;
import org.zs.phm3.repository.UomEntityRepository;
import org.zs.phm3.repository.ts.TsKvRepository;
import org.zs.phm3.repository.unit.UnitAttributeTypeRepository;
import org.zs.phm3.service.project.ProjectService;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.util.Randoms;
import org.zs.phm3.util.mapping.PhmUtil;
import si.uom.NonSI;
import systems.uom.unicode.CLDR;
import tech.units.indriya.unit.Units;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.*;

//import org.zs.phm3.repository.TsKvRepository;


@Service
public class PhmInstallService {
    @Autowired
    UomEntityRepository uomEntityRepository;
    @Autowired
    CustomUnitEntityRepository customUnitEntityRepo;
    @Autowired
    GlobalProperties globalProperties;
    @Autowired
    private ProjectService projectsService;
    @Autowired
    private UnitService unitService;
    @Autowired
    TsKvRepository tsKvRepository;
    @Autowired
    UnitAttributeTypeRepository unitAttributeTypeRepository;

    private static final Logger logger = LoggerFactory.getLogger(PhmInstallService.class);


    public String performInstall() throws Exception {
        final String uuidSaved;
        StringBuilder resultReturn = new StringBuilder();
        String newline = System.getProperty("line.separator");

        /*
        createUomDate();

        resultReturn.append("UOM Data Created");
        resultReturn.append(newline);*/
        createCustomeUnitData();
        resultReturn.append("Custome Unit Data Created");
        uuidSaved=createProjUnitData();
        resultReturn.append(newline);
        resultReturn.append("Project and Unit Data Created");

        /*
        createCustomDataSet(uuidSaved);
        resultReturn.append(newline);
        resultReturn.append("Custom Data Set Created");
        createUnitsAttributsTypes();
        resultReturn.append(newline);
        resultReturn.append("Units Attribute's Types Created");
        resultReturn.append("TsKv Attribute's Types Created");*/
        return resultReturn.toString();
    }

    private void createUnitsAttributsTypes() {
        List<UnitAttributeType> unitAttributeTypesList = new ArrayList<>();
        unitAttributeTypesList.add(new UnitAttributeType("Status"));
        unitAttributeTypesList.add(new UnitAttributeType("Environmental"));
        unitAttributeTypesList.add(new UnitAttributeType("Signal"));
        unitAttributeTypesList.add(new UnitAttributeType("Calibration"));
        unitAttributeTypesList.add(new UnitAttributeType("BIT"));
        unitAttributeTypesList.add(new UnitAttributeType("Business data"));
        unitAttributeTypesList.add(new UnitAttributeType("Log information"));
        unitAttributeTypeRepository.saveAll(unitAttributeTypesList);
    }

    public void createUomDate() throws IllegalAccessException, NoSuchFieldException {
        saveProperties(Units.getInstance(), true, true);
        saveProperties(CLDR.getInstance(), true, false);
        saveProperties(NonSI.getInstance(), false, false);
    }

    private void saveProperties(Object oClass, Boolean display, Boolean basic) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {

        Class<?> aClass = oClass.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        Map<String, String> logEntries = new HashMap<>();
        Set<String> uomTypes = new TreeSet();
//        outerloop:
        for (Field field : declaredFields) {
            if ((field.getName().equals("INSTANCE")) || (field.getName().equals("SYSTEM_NAME")))
//                break outerloop;
                continue;
            field.setAccessible(true);

            String uomType = field.getAnnotatedType().getType().toString().replace("javax.measure.Unit<javax.measure.quantity.", "")
                    .replace(">", "");
            if (uomType.contains("Information"))//("javax.measure"))
                uomType = uomType.replace("javax.measure.Unit<systems.uom.quantity.", "");
            else if (uomType.contains("Consumption"))
                uomType = uomType.replace("javax.measure.Unit<systems.uom.quantity.Consumption<javax.measure.quantity.", "");
            else if (uomType.contains("Concentration"))
                uomType = uomType.replace("javax.measure.Unit<systems.uom.quantity.Concentration<javax.measure.quantity.", "");

            uomTypes.add(uomType);
            Object[] arguments = new Object[]{
                    field.getName(),
                    field.getType().getSimpleName(),
                    String.valueOf(field.get(oClass)),//(this))
                    uomType
            };

            String template = "- Property: {0} (Type: {1}, Value: {2}, Type: {3})";

            String description = String.valueOf(field.getName().toLowerCase().replace("_", " "));
            description = Character.toString(description.charAt(0)).toUpperCase() + description.substring(1);

            switch (uomType) {
                case "javax.measure.Unit<si.uom.quantity.Luminance" -> uomType = "Luminance";
                case "javax.measure.Unit<si.uom.quantity.Level<javax.measure.quantity.Dimensionless" -> uomType = "Dimensionless";
                case "javax.measure.Unit<si.uom.quantity.KinematicViscosity" -> uomType = "KinematicViscosity";
                case "javax.measure.Unit<si.uom.quantity.IonizingRadiation" -> uomType = "IonizingRadiation";
                case "javax.measure.Unit<si.uom.quantity.DynamicViscosity" -> uomType = "DynamicViscosity";
                case "javax.measure.Unit<si.uom.quantity.MagneticFieldStrength" -> uomType = "MagneticFieldStrength";
            }


            if (field.getName().equals("STANDARD_GRAVITY_DIVIDEND")) continue;
            if (field.getName().equals("STANDARD_GRAVITY_DIVISOR")) continue;
            if (field.getName().equals("AVOIRDUPOIS_POUND_DIVIDEND")) continue;
            if (field.getName().equals("AVOIRDUPOIS_POUND_DIVISOR")) continue;

            UomEntity uom = new UomEntity(field.getName(), String.valueOf(field.get(oClass)), UomTypes.findByAbbr(uomType), description);

            uom.setDescription(description);

            if (display == true)
                uom.setDisplay(true);
            if (basic == true)
                uom.setBasic(true);
            try {
                uomEntityRepository.save(uom);
            } catch (Exception e) {
                logger.warn(uom.name + " not saved");
            }

            String logMessage = System.getProperty("line.separator")
                    + MessageFormat.format(template, arguments);

            logEntries.put(field.getName(), logMessage);
        }

        /*
        // Art
        uomEntityRepository.save(new UomEntity("FAILURE_RATE", "1/hour", UomTypes.test, "Failure rate"));
        uomEntityRepository.save(new UomEntity("MTBF", "hour", UomTypes.test, "Mean time between failures"));
        uomEntityRepository.save(new UomEntity("PROBABILITY", "1/100%", UomTypes.test, "Probability"));
        uomEntityRepository.save(new UomEntity("UNIX_TIME", "sec/1000", UomTypes.test, "Unix time"));
        // Art
        */

        SortedSet<String> sortedLog = new TreeSet<>(logEntries.keySet());

        StringBuilder sb = new StringBuilder("Class properties:");

        Iterator<String> it = sortedLog.iterator();
        while (it.hasNext()) {
            String key = it.next();
            sb.append(logEntries.get(key));
        }
        System.out.println(uomTypes.toString());
        System.out.println(sb.toString());
        logger.warn("Initialisation finished");
    }

    private void createCustomeUnitData() {

        List<CustomUnitEntity> customeUnitlist = new ArrayList<>();

        final int length = 20;
        for (int i = 0; i < length; i++) {
            {
                customeUnitlist.add(new CustomUnitEntity(Randoms.generate_ID(), Randoms.generate_string()));
            }
            List<CustomUnitEntity> ret= (List<CustomUnitEntity>) customUnitEntityRepo.saveAll(customeUnitlist);
        }
    }

    private String createProjUnitData() throws NotFoundException, PhmException, JsonProcessingException {
        ProjectEntity projectForSave=new ProjectEntity("Test Project " +
                LocalDateTime.now(),"");

        ProjectEntity projectSaved = projectsService.save(projectForSave);

        UnitDtoInput newUnitDto1 = new UnitDtoInput(projectSaved.getId(), "Test Unit 1 " + LocalDateTime.now(),"");
        newUnitDto1.setParentId(UUIDConverter.fromString(projectSaved.getFirstUnitId()).toString());
        UnitDto unitSavedDto1 = unitService.save(newUnitDto1);
        System.out.println("Saved Unit: " + unitSavedDto1.getId() + ", " + unitSavedDto1.getName());

        UnitDtoInput newUnitDto2 = new UnitDtoInput(projectSaved.getId(), "Test Unit 2 " + LocalDateTime.now(),"");
        newUnitDto2.setParentId(UUIDConverter.fromString(projectSaved.getFirstUnitId()).toString());
        UnitDto unitSavedDto2 = unitService.save(newUnitDto2);
        System.out.println("Saved Unit: " + unitSavedDto2.getId() + ", " + unitSavedDto2.getName());
        return unitSavedDto1.getId();
    }

    private void createCustomDataSet(String uuidSaved) throws FileNotFoundException {
        final String DEFAULT_RESOURCE_LOADER_PATH = "classpath:/templates/";

        PhmCSV test = new PhmCSV();
        test.setPath(globalProperties.getSourceTestFilePath());

        final String unitId = PhmUtil.toShortUUID(uuidSaved);
//        final String unitId = "1ea789c22658045827d67414c3434df";

        long start = System.currentTimeMillis() / 1000;
        // read data from file
        test.read();

        // get keys
        List<String> keys = test.getKeys();

        test.toMap();
        // get keys values
        List<Map<String, String>> keysValues = test.getKeysValues();
        List<TsKvEntity> tsKvListForSave = new ArrayList<>();
        keysValues.forEach(e -> {
            long ts = System.currentTimeMillis();
            keys.forEach(keyMap -> {
                if (!keyMap.equals("timestamp")) {
                    TsKvEntity tsKvForSave = new TsKvEntity(new TsKvId(unitId, keyMap, ts), Double.parseDouble(e.get(keyMap)));
                    tsKvListForSave.add(tsKvForSave);
                    byte a = 1;
                }
            });
            tsKvRepository.saveAll(tsKvListForSave);

            tsKvListForSave.clear();
        });

        long finish = System.currentTimeMillis() / 1000;
        System.out.println("Time read (sec): " + (finish - start));
    }
}

