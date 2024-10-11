package org.zs.phm3.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.zs.phm3.PhmInstallService;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.models.user.UserEntity;
import org.zs.phm3.repository.ptl.UnitTypePTLRepository;
import org.zs.phm3.util.parquet.csv.PhmCSV;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zs.phm3.service.ts.TsKvService;
import org.zs.phm3.service.user.UserServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/initialization")
public class InitializationController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PhmInstallService phmInstallService;

    @Autowired
    private TsKvService tsKvService;

    @PersistenceContext
    private EntityManager entityManager;

    @Value(value = "${ml-service.temp-dir}")
    private String tempDir;

    @PostMapping(value = "/")
    public void Initialization(HttpServletResponse response) throws Exception {

        PrintWriter writer = response.getWriter();
        writer.println("*************************");
        writer.println("*  Data Initialization  *");
        writer.println("*************************");
        String returnResult=phmInstallService.performInstall();
        writer.println(returnResult);
        writer.println("*************************");
//        return returnResult;
    }

    // 800.000 вставок за меньше, чем за 25 сек
    // 4 млн вставок делается за 138 сек
    @Transactional
    @PostMapping(value = "/fastLoadTsKvForUnit")
    public void fastTsKvDatasetNative(@RequestParam String deviceId,
                                      @RequestParam MultipartFile file) {
        // запись файла на диск
        try {
            file.transferTo(new File(tempDir + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // считывание файла
        PhmCSV phmCSV = new PhmCSV(tempDir + file.getOriginalFilename());
        phmCSV.readMap();
        List<Map<String, String>> lists = phmCSV.getKeysValues();

        long start = System.currentTimeMillis();
        // фиксируем время и проходимся по данным
        long time = System.currentTimeMillis();
        String queryInsert = "insert into ts_kv (unit_id,device_id,key,ts,bool_v,dbl_v,long_v,str_v) values ";
        StringBuilder query = new StringBuilder(queryInsert);
        for (int i = 0; i < lists.size(); i++) {
            // если считали 5000 записей из файла (5000 * lists.get(i).keySet().size() инсертов), то записываем в базу всё
            if (i != 0 && i % 5000 == 0) {
                entityManager.createNativeQuery(query.substring(0, query.length() - 1)).executeUpdate();
                query = new StringBuilder(queryInsert);
            }
            // добавляем к запросу значения values для каждой строчки файла csv
            for (String key: lists.get(i).keySet()) {
                query.append("('','" + deviceId + "','" + key + "'," + time + ",null,"
                        + Double.valueOf(lists.get(i).get(key)) + ",null,null),");
            }
            // записываем все оставшиеся значения
            if (i != 0 && i == lists.size() - 1) {
                entityManager.createNativeQuery(query.substring(0, query.length() - 1)).executeUpdate();
            }
            time++;
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @Transactional
    @PostMapping(value = "/fastLoadLast")
    public void fastLoadTelemetryLast(@RequestParam String deviceId, @RequestParam MultipartFile file,
                                  @RequestParam Integer everySecondCount) {
        // запись файла на диск
        try {
            file.transferTo(new File(tempDir + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // считывание файла
        PhmCSV phmCSV = new PhmCSV(tempDir + file.getOriginalFilename());
        phmCSV.readMap();
        List<Map<String, String>> lists = phmCSV.getKeysValues();

        Long timeEnd = System.currentTimeMillis();
        // фиксируем время и проходимся по данным
        long time = timeEnd - lists.size() * everySecondCount * 1000 + everySecondCount * 1000;
        String queryInsert = "insert into ts_kv (unit_id,device_id,key,ts,bool_v,dbl_v,long_v,str_v) values ";
        StringBuilder query = new StringBuilder(queryInsert);
        for (int i = 0; i < lists.size(); i++) {
            // если считали 5000 записей из файла (5000 * lists.get(i).keySet().size() инсертов), то записываем в базу всё
            if (i != 0 && i % 5000 == 0) {
                entityManager.createNativeQuery(query.substring(0, query.length() - 1)).executeUpdate();
                query = new StringBuilder(queryInsert);
            }
            // добавляем к запросу значения values для каждой строчки файла csv
            for (String key: lists.get(i).keySet()) {
                query.append("('','" + deviceId + "','" + key + "'," + time + ",null,"
                        + Double.valueOf(lists.get(i).get(key)) + ",null,null),");
            }
            // записываем все оставшиеся значения
            if (i != 0 && i == lists.size() - 1) {
                entityManager.createNativeQuery(query.substring(0, query.length() - 1)).executeUpdate();
            }
            time += everySecondCount * 1000;
        }
    }

    @Transactional
    @PostMapping(value = "/fastLoadFuture")
    public void fastLoadTelemetryFuture(@RequestParam String deviceId, @RequestParam MultipartFile file,
                                  @RequestParam Integer everySecondCount) {
        // запись файла на диск
        try {
            file.transferTo(new File(tempDir + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // считывание файла
        PhmCSV phmCSV = new PhmCSV(tempDir + file.getOriginalFilename());
        phmCSV.readMap();
        List<Map<String, String>> lists = phmCSV.getKeysValues();

        // фиксируем время и проходимся по данным
        Long time = System.currentTimeMillis();
        String queryInsert = "insert into ts_kv (unit_id,device_id,key,ts,bool_v,dbl_v,long_v,str_v) values ";
        StringBuilder query = new StringBuilder(queryInsert);
        for (int i = 0; i < lists.size(); i++) {
            // если считали 5000 записей из файла (5000 * lists.get(i).keySet().size() инсертов), то записываем в базу всё
            if (i != 0 && i % 5000 == 0) {
                entityManager.createNativeQuery(query.substring(0, query.length() - 1)).executeUpdate();
                query = new StringBuilder(queryInsert);
            }
            // добавляем к запросу значения values для каждой строчки файла csv
            for (String key: lists.get(i).keySet()) {
                query.append("('','" + deviceId + "','" + key + "'," + time + ",null,"
                        + Double.valueOf(lists.get(i).get(key)) + ",null,null),");
            }
            // записываем все оставшиеся значения
            if (i != 0 && i == lists.size() - 1) {
                entityManager.createNativeQuery(query.substring(0, query.length() - 1)).executeUpdate();
            }
            time += everySecondCount * 1000;
        }
    }

    @Transactional
    @GetMapping(value = "/initPTL")
    public String init() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/sql/init.sql");
            String sql = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            entityManager.createNativeQuery(sql).executeUpdate();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Success!";
    }

    @PostMapping(value = "/user-create")
    public UserEntity createUser(){
        return userService.save(new UserEntity(1L, "admin", "Pavel Chuvak"));
    }
}
