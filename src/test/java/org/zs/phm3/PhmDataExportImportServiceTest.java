package org.zs.phm3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.PhmDataExportImportService;
import org.zs.phm3.exception.PhmException;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PhmDataExportImportServiceTest {
    @Autowired
    PhmDataExportImportService phmDataExportImportService;

    @Test
    public void m1_exportUnit() throws IOException, PhmException {
//        LocalDateTime now=LocalDateTime.now();
        String unitId="fb1ff956-b527-11ea-9a04-7f3a3b024f37";
        phmDataExportImportService.exportUnit(unitId);
    }

    @Test
    public void m2_importUnit() throws IOException, PhmException {
        phmDataExportImportService.importUnit("target\\unit_e0f2dbb7-aa27-11ea-98dc-7bada0cdcd07.json", 1);
    }
    @Test
    public void exportInitialData() throws IOException, PhmException {
        phmDataExportImportService.exportInitialData();
    }
        @Test
        public void importInitialData() throws IOException, PhmException {
            phmDataExportImportService.importInitialData("target\\initialData.json");
}

    @Test
    public void m2_exportProjectData() throws IOException, PhmException {
        phmDataExportImportService.exportProjectData( 1);
    }

    @Test
    public void m2_importProjectData() throws IOException, PhmException {
        String filePath="target\\Data_for_project_#1_from_2020-06-17T17_58_59.142838100.json3";

        phmDataExportImportService.importProjectData( filePath, 1);
    }
//    todo test for error 1)saving 2)reading 3)finding
//    todo attach phm exception
}
