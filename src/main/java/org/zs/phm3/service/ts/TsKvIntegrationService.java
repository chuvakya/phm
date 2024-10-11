package org.zs.phm3.service.ts;

import java.io.IOException;

public interface TsKvIntegrationService {
    public String importToCustomTsFromCsvFile() throws IOException, InterruptedException;

    public String copyingCustomTsToTs(String customUnitId, String unitId);
}
