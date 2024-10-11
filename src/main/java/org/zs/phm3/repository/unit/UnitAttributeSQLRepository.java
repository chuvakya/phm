package org.zs.phm3.repository.unit;

import org.zs.phm3.models.unit.UnitAttribute;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UnitAttributeSQLRepository extends JpaRepository<UnitAttribute, UnitAttributeKey> {

    @Query(value = "SELECT t1.attribute_key, t1.unit_id, t1.name, t1.bool_v, t1.dbl_v, t1.long_v, t1.str_v, \n" +
            "            t2.symbol AS uomi, t3.symbol as uomo FROM unit_attribute AS t1 \n" +
            "            LEFT JOIN uom AS t2 ON t1.uom_input_id = t2.id \n" +
            "            LEFT JOIN uom AS t3 ON t1.uom_output_id = t3.id \n" +
            "            WHERE unit_id=?1", nativeQuery = true)
    List<Object[]> getAllForTableByUnitId(String unitId);

    @Query(value = "SELECT t1.unit_id AS id, t1.long_v AS value FROM unit_attribute AS t1 \n" +
            "            WHERE t1.attribute_key='MTBF'", nativeQuery = true)
    List<Object[]> getAllMTBF();


    @Query(value = "SELECT a.long_v AS value FROM unit_attribute AS a \n" +
            "            WHERE a.attribute_key='MTBF' AND a.unit_id=?1", nativeQuery = true)
    Long getUnitMTBF(String unitId);


    // If the MS_task has already been created, its date is in the future and it isn't closed

/*    @Query(value = "SELECT a.unit_id AS id, a.long_v AS value, r.planned_date_operation_start AS start, \n" +
            "r.status AS status FROM unit_attribute AS a LEFT JOIN ms_register AS r ON a.unit_id = r.unit_id \n" +
            "WHERE a.attribute_key='MTBF'", nativeQuery = true)*/

    @Query(value = "SELECT a.unit_id AS id, a.long_v AS value, r.planned_date_operation_start AS start, \n" +
            "r.status AS status FROM unit_attribute AS a \n" +
            "LEFT JOIN ms_register AS r ON a.unit_id=r.unit_id WHERE a.attribute_key='MTBF' AND \n" +
            "r.planned_date_operation_start>now() OR r.planned_date_operation_start isnull AND \n" +
            "a.long_v is not null", nativeQuery = true)
    List<Object[]> getAllMTBFandLastOpenMsTask();

/*    @Query(value = "SELECT DISTINCT a.unit_id AS id, a.long_v AS value, h.status \n" +
            "FROM unit_attribute AS a \n" +
            "LEFT JOIN ms_taskheaders AS h ON a.unit_id=h.unit_id WHERE a.attribute_key='MTBF' AND \n" +
            "h.status NOT in (1,0) OR h.status isnull AND \n" +
            "a.long_v is not null", nativeQuery = true)*/

    @Query(value = "SELECT t.unit_id, t.date_created, a.long_v AS mtbf, t.status " +
            "FROM ms_taskheaders t " +
            "INNER JOIN " +
            "(SELECT unit_id, MAX(date_created) as last_date FROM ms_taskheaders " +
            "GROUP BY unit_id) d " +
            "ON d.unit_id = t.unit_id AND d.last_date = t.date_created " +
            "INNER JOIN unit_attribute a " +
            "ON t.unit_id=a.unit_id " +
            "WHERE t.status NOT in (0,1)  AND a.attribute_key='MTBF' " +
            "ORDER BY t.unit_id", nativeQuery = true)

    List<Object[]> getUnitsWithClosedMsTasks();


    @Query(value = "SELECT a.unit_id AS id, a.long_v AS value, t.status FROM unit_attribute AS a "+
            "LEFT JOIN ms_taskheaders AS t ON a.unit_id=t.unit_id " +
            "WHERE a.attribute_key='MTBF' is NOT null AND t.status is null AND a.long_v is NOT null", nativeQuery = true)
    List<Object[]> getUnitsWithoutMsTasks();


}
