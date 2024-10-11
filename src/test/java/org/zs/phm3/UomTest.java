package org.zs.phm3;

import org.zs.phm3.models.UomEntity;
import org.zs.phm3.models.UomTypes;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.repository.UomEntityRepository;
import org.zs.phm3.service.UomService;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.*;

import si.uom.NonSI;
import systems.uom.unicode.CLDR;
import tech.units.indriya.unit.Units;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UomTest {//}  extends TestCase {
    @Autowired
    UomEntityRepository uomEntityRepository;

    @Autowired
    UomService uomService;

    @Test
    public void n1_createDB() throws IllegalAccessException, NoSuchFieldException {
//        Unit<Length> Kilometer = MetricPrefix.KILO(METRE);

        // compilation error
        // Unit<Length> Centimeter = MetricPrefix.CENTI(LITRE);
//        Unit<Length> Centimeter = MetricPrefix.CENTI(METRE);
//        Unit pressure = Units.PASCAL;
//        System.out.println(pressure);

        saveProperties(Units.getInstance(), true, true);
        byte a=1;
        saveProperties(CLDR.getInstance(), true, false);

        saveProperties(NonSI.getInstance(), false, false);
    }

    public void saveProperties(Object oClass, Boolean display, Boolean basic) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
//        Class<?> aClass = this.getClass();
        Class<?> aClass = oClass.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        Map<String, String> logEntries = new HashMap<>();
        Set<String> uomTypes = new TreeSet();
//        outerloop:
        for (Field field : declaredFields) {
            if ((field.getName().equals("INSTANCE")) && (field.getName().equals("SYSTEM_NAME")))
//                break outerloop;
                continue;
            field.setAccessible(true);

//            Class o1=new Class.forName(field);

//            java.lang.reflect.Field f = java.lang.reflect.Method.class.getDeclaredField("signature");
//            java.lang.reflect.Field f = java.lang.reflect.Field.class.getDeclaredField("name");
//
//            f.setAccessible(true);
//            String sigature = f.getName();//.get(yourMethod);


//            Field f = aClass.getClass().getDeclaredField("AMPERE"); //NoSuchFieldException
//            f.setAccessible(true);

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
//                    field.getAnnotatedType().getType().toString().replace("javax.measure.Unit<javax.measure.quantity.","")
//                            .replace(">","")

            };

            String template = "- Property: {0} (Type: {1}, Value: {2}, Type: {3})";

            String description = String.valueOf(field.getName().toLowerCase().replace("_", " "));
            description = Character.toString(description.charAt(0)).toUpperCase() + description.substring(1);
            UomEntity uom = new UomEntity(field.getName(), String.valueOf(field.get(oClass)), UomTypes.findByAbbr(uomType), description);
            uom.setDescription(description);

            if (display == true)
                uom.setDisplay(true);
            if (basic == true)
                uom.setBasic(true);

            try {
                uomEntityRepository.save(uom);
            } catch (Exception e) {
                e.printStackTrace();

            }

            String logMessage = System.getProperty("line.separator")
                    + MessageFormat.format(template, arguments);

            logEntries.put(field.getName(), logMessage);
        }

        SortedSet<String> sortedLog = new TreeSet<>(logEntries.keySet());

        StringBuilder sb = new StringBuilder("Class properties:");

        Iterator<String> it = sortedLog.iterator();
        while (it.hasNext()) {
            String key = it.next();
            sb.append(logEntries.get(key));
        }
        System.out.println(uomTypes.toString());
        System.out.println(sb.toString());

    }

    @Test
    public void n2_getAllFromRepo() {
        List<UomEntity> uomList = (List<UomEntity>) uomEntityRepository.findAll();
        uomList.forEach(item -> System.out.println(item.getId() + "|" + item.name + "|" + item.description + "|" + item.symbol));
    }

    @Test
    public void n3_getAllForDisplay() {
        Map<Integer, String> uomForDisplay = new LinkedHashMap<>();
        List<UomEntity> uomList = (List<UomEntity>) uomEntityRepository.getAllForDisplay();
        uomList.forEach(item -> {

            uomForDisplay.put(item.toData().getId(), item.toData().getText());
//            System.out.println(uomForDisplay);

//            if("C".equals(item)){
//                System.out.println(item);
        });
        byte a = 1;
        assertTrue("error getting UOM", uomForDisplay.size() > 0);
    }

}
