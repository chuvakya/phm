package org.zs.phm3.service.maintenance.part;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.postgresql.core.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.part.Part;
import org.zs.phm3.models.maintenance.part.PartStock;
import org.zs.phm3.repository.maintenance.part.PartRepository;
import org.zs.phm3.repository.maintenance.part.PartStockRepository;
import org.zs.phm3.service.maintenance.workorder.LaborTaskService;
import org.zs.phm3.service.maintenance.workorder.LaborTaskServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private PartStockRepository partStockRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LaborTaskServiceImpl laborTaskService;

    @Override
    public Part save(Part part) {
        return partRepository.save(part);
    }

    @Override
    public void deleteByIdSQL(Long partId) {

    }

    @Override
    public Part getById(Long partId) {
        return partRepository.findById(partId).get();
    }

    @Override
    public String getListByProjectId(Integer projectId) {
        JSONArray jsonArray = new JSONArray();
        List<List<Object>> parts = partRepository.getAllByProjectId(projectId);
        for (List<Object> part : parts) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", part.get(0));
            jsonObject.put("name", part.get(1));
            jsonObject.put("categoryName", part.get(2));
            jsonObject.put("storage", part.get(4));
            jsonObject.put("totalStock", part.get(3));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public String getListByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit) {
        JSONArray jsonArray = new JSONArray();
        List<List<Object>> parts = partRepository.getAllByProjectIdAndOffsetAndLimit(projectId, offset, limit);
        for (List<Object> part : parts) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", part.get(0));
            jsonObject.put("name", part.get(1));
            jsonObject.put("categoryName", part.get(2));
            jsonObject.put("storage", part.get(4));
            jsonObject.put("totalStock", part.get(3));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public String getByIdJSON(Long partId) {
        List<List<Object>> parts = partRepository.getIdAndNameAndCategoryIdByPartId(partId);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonStocks = new JSONArray();
        jsonObject.put("partId", parts.get(0).get(0));
        jsonObject.put("name", parts.get(0).get(1));
        jsonObject.put("categoryId", parts.get(0).get(2));
        for (PartStock stock : partStockRepository.getAllByPart_Id(partId)) {
            JSONObject jsonStock = new JSONObject();
            jsonStock.put("stockId", stock.getId());
            jsonStock.put("aisle", stock.getAisle());
            jsonStock.put("cell", stock.getCell());
            jsonStock.put("minQuantity", stock.getMinQuantity());
            jsonStock.put("quantity", stock.getQuantity());
            jsonStock.put("row", stock.getRow());
            jsonStock.put("storage", stock.getStorage());
            jsonStocks.add(jsonStock);
        }
        jsonObject.put("stocks", jsonStocks);
        return jsonObject.toJSONString();
    }

    @Transactional
    @Override
    public void addPartStocksForPart(List<PartStock> partStocks, Long partId) {
        if (!partStocks.isEmpty()) {
            StringBuilder builderSQL = new StringBuilder("INSERT INTO part_stock (aisle, cell, min_quantity, quantity, " +
                    "row, storage, part_id) VALUES (" + partStocks.get(0).getAisle() + ", " + partStocks.get(0).getCell() +
                    ", " + partStocks.get(0).getMinQuantity() + ", " + partStocks.get(0).getQuantity() +  ", " +
                    partStocks.get(0).getRow() + ", " +
                    laborTaskService.get(partStocks.get(0).getStorage()) + ", " +
                    partId + ")");
            for (int i = 1; i < partStocks.size(); i++) {
                builderSQL.append(", " + "(" + partStocks.get(i).getAisle() + ", " + partStocks.get(i).getCell() +
                        ", " + partStocks.get(i).getMinQuantity() + ", " + partStocks.get(i).getQuantity() +  ", " +
                                partStocks.get(i).getRow() + ", " +
                                laborTaskService.get(partStocks.get(i).getStorage()) + ", " +
                                partId + ")");
            }
            entityManager.createNativeQuery(builderSQL.toString()).executeUpdate();
        }
    }

    @Override
    public Map getTotalStockAndStorages(List<PartStock> partStocks) {
        Map<String, Object> map = new HashMap<>();
        Long sumQuantity = 0L + partStocks.get(0).getQuantity();
        StringBuilder stringBuilder = new StringBuilder(partStocks.get(0).getStorage());
        for (int i = 1; i < partStocks.size(); i++) {
            sumQuantity += partStocks.get(i).getQuantity();
            stringBuilder.append(", " + partStocks.get(i).getStorage());
        }
        map.put("storages", stringBuilder.toString());
        map.put("totalStock", sumQuantity);
        return map;
    }

    @Override
    public Long getCountByProjectId(Integer projectId) {
        return partRepository.getCountByProjectId(projectId);
    }

    @Override
    public String getListForWorkOrderByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit) {
        List<List<Object>> lists = partRepository.getListForWorkOrderByProjectIdAndOffsetAndLimit(projectId, offset,
                limit);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("partId", list.get(0));
            jsonObject.put("partName", list.get(1));
            jsonObject.put("partStockId", list.get(2));
            jsonObject.put("storage", list.get(3));
            jsonObject.put("aisle", list.get(4));
            jsonObject.put("cell", list.get(5));
            jsonObject.put("quantity", list.get(6));
            jsonObject.put("minQuantity", list.get(7));
            jsonObject.put("row", list.get(8));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }


}
