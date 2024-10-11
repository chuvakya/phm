/**
 * Copyright © 2016-2019 The Thingsboard Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zs.phm3.validation;

import org.zs.phm3.dto.Dto;
import org.zs.phm3.PhmInstallService;
import org.zs.phm3.models.basic.IdEntity;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zs.phm3.exception.DataValidationException;
import org.zs.phm3.exception.IncorrectParameterException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class DataValidator<D extends Dto> extends IdEntity {
//    public abstract class DataValidator1<D extends BaseSqlEntity> extends IdEntity {
    private static final Logger logger = LoggerFactory.getLogger(PhmInstallService.class);

    public void validate(D data) {
        try {
            if (data == null) {
                throw new DataValidationException("Data object can't be null!");
            }
//            TenantId tenantId = tenantIdFunction.apply(data);
            validateDataImpl(data);
            if (data.getId() == null) {
                validateCreate(data);
            } else {
                validateUpdate(data);
            }
        } catch (DataValidationException e) {
            logger.error("Data object is invalid: [{}]", e.getMessage());
            throw e;
        }
    }

    protected void validateDataImpl(D data) {
    }

    protected void validateCreate(D data) {
    }

    protected void validateUpdate(D data) {
    }

    protected boolean isSameData(D existentData, D actualData) {
//        return actualData.getId() != null && existentData.getId().equals(actualData.getId());
        return false;
    }

    protected static void validateJsonStructure(JsonNode expectedNode, JsonNode actualNode) {
        Set<String> expectedFields = new HashSet<>();
        Iterator<String> fieldsIterator = expectedNode.fieldNames();
        while (fieldsIterator.hasNext()) {
            expectedFields.add(fieldsIterator.next());
        }

        Set<String> actualFields = new HashSet<>();
        fieldsIterator = actualNode.fieldNames();
        while (fieldsIterator.hasNext()) {
            actualFields.add(fieldsIterator.next());
        }

        if (!expectedFields.containsAll(actualFields) || !actualFields.containsAll(expectedFields)) {
            throw new DataValidationException("Provided json structure is different from stored one '" + actualNode + "'!");
        }
    }

    public static void validateEntityId(Integer entityId, String errorMessage) {
        if (entityId == null) {
            throw new IncorrectParameterException(errorMessage);
        }
    }

    public static void validatePositiveNumber(long val, String errorMessage) {
        if (val <= 0) {
            throw new IncorrectParameterException(errorMessage);
        }
    }

    public static void validateString(String val, String errorMessage) {
        if (val == null || val.isEmpty()) {
            throw new IncorrectParameterException(errorMessage);
        }
    }
}
