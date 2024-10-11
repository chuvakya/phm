package org.zs.phm3.ftamodel;


import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface FtaModelService {
    FtaModelEntity save(FtaModelEntity ftaModelEntity);
    FtaModelEntity update(FtaModelEntity ftaModelEntity);
    void deleteByProjectId(Long projectId);
    FtaModelEntity findByProjectId(Long projectId);
    String calculate(Long projectId, int missionTime, int limitOrder) throws IOException, SAXException, ParserConfigurationException, TransformerException, ParseException;
//    void writeProbabilityInFtaModel(Long projectId) throws ParserConfigurationException, IOException, SAXException, TransformerException;
}
