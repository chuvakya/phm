package org.zs.phm3.service;

import org.zs.phm3.exception.PhmException;
import com.fasterxml.jackson.databind.JsonNode;

public interface PhmService {
    JsonNode getDefaultSchemeBody() throws PhmException;
}
