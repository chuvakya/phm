package org.zs.phm3.util.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zs.phm3.data.UUIDConverter;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.*;

public abstract class PhmUtil {

    public static String toShortUUID(String longUUID) throws IllegalArgumentException{
        return UUIDConverter.fromTimeUUID(UUID.fromString(longUUID));
    }

    private PhmUtil() {
    }

    public static String toLongUUID(String shortUUID) {
        return UUIDConverter.fromString(shortUUID).toString();
    }

    public static void logProperties(Object oClass) throws IllegalArgumentException, IllegalAccessException {

        Class<?> aClass = oClass.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        Map<String, String> logEntries = new HashMap<>();

        for (Field field : declaredFields) {
            field.setAccessible(true);

            Object[] arguments = new Object[]{
                    field.getName(),
                    field.getType().getSimpleName(),
                    String.valueOf(field.get(oClass))//(this))
            };

            String template = "- Property: {0} (Type: {1}, Value: {2})";
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

        System.out.println(sb.toString());
    }

    <IdTextReturn> IdTextReturn castTo(Class<IdTextReturn> cls) {
        Object o = null;
        // snip
        return cls.cast(o);
    }

    public static String objectToJsonString(Object obj){
        String result = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e ) {
            e.printStackTrace();
        }
        return result;
    }
}