package org.zs.phm3.service.maintenance.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.supplier.Contact;
import org.zs.phm3.repository.maintenance.supplier.ContactRepository;
import org.zs.phm3.service.maintenance.workorder.LaborTaskService;
import org.zs.phm3.service.maintenance.workorder.LaborTaskServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private LaborTaskServiceImpl laborTaskService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ContactRepository contactRepository;

    @Transactional
    @Override
    public void saveAllContact(List<Contact> contacts, Long supplierId) {
        if (!contacts.isEmpty()) {
            StringBuilder builderSQL = new StringBuilder("INSERT INTO contact (contact_name, description, supplier_id) " +
                    "VALUES (" + laborTaskService.get(contacts.get(0).getContactName()) + ", " +
                    laborTaskService.get(contacts.get(0).getDescription()) + ", " + supplierId +  ")");
            for (int i = 1; i < contacts.size(); i++) {
                builderSQL.append(", (" + laborTaskService.get(contacts.get(0).getContactName()) + ", " +
                        laborTaskService.get(contacts.get(0).getDescription()) + ", " + supplierId + ")");
            }
            entityManager.createNativeQuery(builderSQL.toString()).executeUpdate();
        }
    }

    @Override
    public void deleteAllBySupplierId(Long supplierId) {
        contactRepository.deleteAllBySupplierId(supplierId);
    }
}
