package org.zs.phm3.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.zs.phm3.InstallApplication;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;

import java.io.IOException;
@Service
public class PhmServiceImpl implements PhmService {
    private static final Logger logger = LoggerFactory.getLogger(InstallApplication.class);
    @Cacheable(value = "phm")
    public JsonNode getDefaultSchemeBody() throws PhmException {
        System.out.println("getDefaultSchemeBody");
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonSchemeBody = mapper.readTree(ResourceUtils.getURL("classpath:phm/DefaultScheme.json").openStream());
            return jsonSchemeBody;
        } catch (
                IOException e) {
            logger.error("Error: "+e.getMessage(), e);
        }
        throw new PhmException("Error reading default scheme body", PhmErrorCode.INVALID_ARGUMENTS);
    }
}
