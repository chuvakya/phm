package org.zs.phm3.service.maintenance.supplier;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.maintenance.supplier.Contact;
import org.zs.phm3.models.maintenance.supplier.Supplier;
import org.zs.phm3.repository.maintenance.supplier.ContactRepository;
import org.zs.phm3.repository.maintenance.supplier.SupplierRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteByIdSQL(Long supplierId) {
        supplierRepository.deleteByIdSQL(supplierId);
    }

    @Override
    public Supplier getById(Long supplierId) {
        return supplierRepository.findById(supplierId).get();
    }

    @Override
    public String getByIdJSON(Long supplierId) {
        List<List<Object>> lists = supplierRepository.getAllById(supplierId);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonContacts = new JSONArray();

        jsonObject.put("id", lists.get(0).get(0));
        jsonObject.put("name", lists.get(0).get(1));
        jsonObject.put("currency", lists.get(0).get(2).toString());
        jsonObject.put("isManufacturer", lists.get(0).get(3));
        jsonObject.put("isSupplier", lists.get(0).get(4));
        jsonObject.put("isTenant", lists.get(0).get(5));
        jsonObject.put("notes", lists.get(0).get(6));
        jsonObject.put("isManufacturerPTL", lists.get(0).get(7));

        for (Contact contact : contactRepository.getAllBySupplier_Id(supplierId)) {
            JSONObject jsonContact = new JSONObject();
            jsonContact.put("contactName", contact.getContactName());
            jsonContact.put("description", contact.getDescription());
            jsonContacts.add(jsonContact);
        }
        jsonObject.put("contacts", jsonContacts);
        return jsonObject.toJSONString();
    }

    @Override
    public String getListForTableByProjectId(Integer projectId) {
        JSONArray jsonArray = new JSONArray();
        List<List<Object>> lists = supplierRepository.getListForTableByProjectId(projectId);
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            List<String> classification = new ArrayList<>();
            if ((boolean) list.get(1)) {
                classification.add("Manufacturer");
            }
            if ((boolean) list.get(2)) {
                classification.add("Supplier");
            }
            if ((boolean) list.get(3)) {
                classification.add("Tenant");
            }
            StringBuilder stringBuilder = new StringBuilder(classification.isEmpty() ? "" : classification.get(0));
            for (int i = 1; i < classification.size(); i++) {
                stringBuilder.append(", " + classification.get(i));
            }
            jsonObject.put("id", list.get(0));
            jsonObject.put("classification", stringBuilder.toString());
            jsonObject.put("name", list.get(4));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public String getListForTableByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit) {
        JSONArray jsonArray = new JSONArray();
        List<List<Object>> lists = supplierRepository.getListForTableByProjectIdAndOffsetAndLimit(projectId, offset,
                limit);
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            List<String> classification = new ArrayList<>();
            if ((boolean) list.get(1)) {
                classification.add("Manufacturer");
            }
            if ((boolean) list.get(2)) {
                classification.add("Supplier");
            }
            if ((boolean) list.get(3)) {
                classification.add("Tenant");
            }
            StringBuilder stringBuilder = new StringBuilder(classification.isEmpty() ? "" : classification.get(0));
            for (int i = 1; i < classification.size(); i++) {
                stringBuilder.append(", " + classification.get(i));
            }
            jsonObject.put("id", list.get(0));
            jsonObject.put("classification", stringBuilder.toString());
            jsonObject.put("name", list.get(4));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public void updateIsManufacturerPTLByName(Boolean isManufacturerPLT, List<String> names) {
        supplierRepository.updateIsManufacturerPTLByName(isManufacturerPLT, names);
    }

    @Override
    public void updateName(String newName, String oldName) {
        supplierRepository.updateName(newName, oldName);
    }

    @Override
    public Long getCountByProjectId(Integer projectId) {
        return supplierRepository.getCountByProjectId(projectId);
    }
}
