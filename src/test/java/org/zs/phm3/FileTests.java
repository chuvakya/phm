package org.zs.phm3;

import org.zs.phm3.models.FileEntity;
import org.zs.phm3.models.FileType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.FileService;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Base64;

import static junit.framework.Assert.assertNotNull;

//import org.zs.phm3.repository.project.ProjectUnitSQLRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileTests {
//    @TestConfiguration
//    static class ProjectUnitTestContextConfiguration {
//        @Bean
//        public UnitService unitService() {
//            return new UnitServiceImpl();
//        }

//        @Bean
//        public ProjectService projectService() {
//            return new ProjectServiceImpl();
//        }
//    }

    @Autowired
    private FileService fileService;


    @Test
    public void n1Save() throws PhmException, IOException {
        long start = System.currentTimeMillis()/ 1000;
        File pic_file = new File("G:\\2306260.jpg");
        FileEntity filePicSaved = fileService.save(new FileEntity(getBytesFromFile(pic_file),"2306260.jpg " +
                LocalDateTime.now(), "G:\\2306260.jpg.jpg", FileType.IMAGE));
//        FileEntity newPhmFile = new FileEntity(getBytesFromFile(pic_file), "2306260.jpg " +
//                LocalDateTime.now(), "G:\\2306260.jpg.jpg", FileType.IMAGE);
//        FileEntity filePicSaved = fileService.save(newPhmFile);
        assertNotNull("error saving project", filePicSaved);

//        File doc_file=new File("C:\\tmp\\testDb.properties");

        File doc_file = new File("E:\\Employees.txt");
        FileEntity fileDocSaved = fileService.save(new FileEntity(getBytesFromFile(doc_file), "Employees.txt " +
                LocalDateTime.now(), "E:\\Employees.txts", FileType.DOC));
        long finish = System.currentTimeMillis()/ 1000;

        System.out.println("Time read (sec): " + (finish - start));
        assertNotNull("error saving project", fileDocSaved);
    }

    @Test
    public void n2FindById() throws PhmException {
        FileEntity fileFinded = fileService.findById(1);
        System.out.println(fileFinded.getFile());
        byte[] bytes = fileFinded.getFile();
        String s = Base64.getEncoder().encodeToString(bytes);

        System.out.println(s);

//        assertNotNull("error reading project", projectFinded);
    }

    @Test
    public void n2ReadFileAndSaveIconToFile() throws PhmException, IOException {
        FileEntity fileFinded = fileService.findById(1);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("G:\\2306260icon.png"), fileFinded.getSmallImg());
    }

    @Test
    public void n3FindAllImg() {
        Iterable<Integer> ptcAll = fileService.findAllImg();
    }

    @Test
    public void n4FindAll() {
        Iterable<FileEntity> filesAll = fileService.findAll();
    }


    private static byte[] getBytesFromFile(File file) throws IOException {
        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File is too large!");
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;

        InputStream is = new FileInputStream(file);
        try {
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        return bytes;
    }
}
