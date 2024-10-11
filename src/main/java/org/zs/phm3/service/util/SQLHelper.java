package org.zs.phm3.service.util;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class SQLHelper {

    @PersistenceContext
    private EntityManager entityManager;

    public void deleteAll(String tableName, String idName, List ids) {
        StringBuilder query = new StringBuilder("DELETE FROM " + tableName + " WHERE " + idName + " IN (");
        for (int i = 0; i < ids.size(); i++) {
            if (i == 0 && ids.size() > 1 || (i > 0 && ids.size() - 1 != i)) {
                query.append(ids.get(i).toString() + ",");
            } else if (i == ids.size() - 1) {
                query.append(ids.get(i).toString() + ")");
                break;
            }
        }
        entityManager.createNativeQuery(query.toString()).executeUpdate();
    }

}
