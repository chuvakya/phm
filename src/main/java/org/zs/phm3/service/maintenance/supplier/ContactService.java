package org.zs.phm3.service.maintenance.supplier;

import org.zs.phm3.models.maintenance.supplier.Contact;

import java.util.List;

public interface ContactService {

    void saveAllContact(List<Contact> contacts, Long supplierId);
    void deleteAllBySupplierId(Long supplierId);
}
