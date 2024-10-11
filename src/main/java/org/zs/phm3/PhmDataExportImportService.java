package org.zs.phm3;

import org.zs.phm3.exception.PhmException;

import java.io.IOException;

public interface PhmDataExportImportService {
    String exportUnit(String UnitId) throws PhmException, IOException;

    String importUnit(String filePath, Integer projectId) throws PhmException, IOException;

    String exportInitialData() throws PhmException, IOException;

    String importInitialData(String filePath) throws PhmException, IOException;

    String exportProjectData(Integer projectId) throws PhmException, IOException;

    String importProjectData(String filePath, Integer projectId) throws PhmException, IOException;
}
