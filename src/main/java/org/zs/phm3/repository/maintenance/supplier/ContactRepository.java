package org.zs.phm3.repository.maintenance.supplier;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.supplier.Contact;

import java.util.List;

@Transactional
@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    @Modifying
    @Query(value = "DELETE FROM contact WHERE supplier_id = ?1", nativeQuery = true)
    void deleteAllBySupplierId(Long supplierId);

    List<Contact> getAllBySupplier_Id(Long supplierId);

}
