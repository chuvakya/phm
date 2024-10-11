package org.zs.phm3.ftamodel;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.ftamodel.ftaconverter.FtaConverter;
import org.zs.phm3.util.GetNullProperties;
import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import org.springframework.web.bind.annotation.*;

import org.xml.sax.SAXException;
import org.zs.phm3.service.user.UserServiceImpl;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


@RestController
@RequestMapping({"api/fta/diagram/"})
public class FtaDiagramController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    FtaModelService ftaModelService;

    @Autowired
    FtaDiagramService ftaDiagramService;

    public FtaDiagramController() {
    }

    @Deprecated
    @GetMapping
    public Iterable<FtaDiagramEntity> getAllRoot() {
        return ftaDiagramService.findByParentIdIsNull();
    }

    @GetMapping("get-all-root-by-project-id/{projectId}")
    public Iterable<FtaDiagramEntity> getAllRoot2(@PathVariable Long projectId) {
        return this.ftaDiagramService.findByParentIdIsNullAndProjectId(projectId);
    }

    @Deprecated
    @GetMapping({"find-all/"})
    public Iterable<FtaDiagramEntity> getAll() {
        return ftaDiagramService.findAll();
    }

    @GetMapping({"{id}"})
    public FtaDiagramEntity getOne(@PathVariable("id") Long id) {
        return ftaDiagramService.findById(id);
    }

    @PostMapping
    public FtaDiagramEntity save(@RequestBody FtaDiagramEntity ftaDiagramEntity) throws PhmException {
        ftaDiagramEntity.setUser(userService.getOneById(1L));
        ftaDiagramEntity.setStatus(FtaDiagramStatus.CREATED);
        return ftaDiagramService.save(ftaDiagramEntity);
    }

    @PutMapping("{id}")
    public FtaDiagramEntity updateAll(@PathVariable("id") Long id, @RequestBody FtaDiagramEntity ftaDiagramEntityForSave){
        FtaDiagramEntity ftaDiagramEntity = ftaDiagramService.findById(id);
        BeanUtils.copyProperties(ftaDiagramEntityForSave, ftaDiagramEntity);
        ftaDiagramEntity.setStatus(FtaDiagramStatus.CREATED);
        return ftaDiagramService.save(ftaDiagramEntity);
    }

    @PatchMapping("{id}")
    public FtaDiagramEntity update(@PathVariable("id") Long id, @RequestBody FtaDiagramEntity ftaDiagramEntityForSave){
        FtaDiagramEntity ftaDiagramEntity = ftaDiagramService.findById(id);
        BeanUtils.copyProperties(ftaDiagramEntityForSave, ftaDiagramEntity, GetNullProperties.getNullPropertyNames(ftaDiagramEntityForSave));
        ftaDiagramEntity.setStatus(FtaDiagramStatus.CREATED);
        return ftaDiagramService.save(ftaDiagramEntity);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        ftaDiagramService.delete(id);
    }

    @GetMapping("is-deploy/{id}")
    public boolean isDeployFtaDiagram(@PathVariable("id") Long ftaDiagramId){
        boolean result = false;
        FtaDiagramEntity ftaDiagramEntity = ftaDiagramService.findById(ftaDiagramId);
        if(ftaDiagramEntity.isActive()){
            result = true;
        }
        return result;
    }

    @GetMapping(value = "calculate/{projectId}", produces = "application/json")
    public String calculate(@PathVariable("projectId") Long projectId, @RequestParam("missionTime") int missionTime,
                            @RequestParam("limitOrder") int limitOrder) throws IOException, ParserConfigurationException, SAXException, TransformerException, ParseException {
        return ftaModelService.calculate(projectId, missionTime, limitOrder);
    }

    @PostMapping(value = "activate/{ftaDiagramId}", produces = "application/json")
    public String activateFtaModel(@PathVariable("ftaDiagramId") Long ftaDiagramId) throws ParseException {
        FtaDiagramEntity ftaDiagramEntity = ftaDiagramService.findById(ftaDiagramId);
        FtaConverter ftaConverter = new FtaConverter(ftaDiagramEntity);
        String ftaModelXml = ftaConverter.getXmlFta();

        if(ftaConverter.isValidate()){
            ftaModelService.save(new FtaModelEntity(ftaDiagramEntity.getProjectId(), ftaModelXml, ftaConverter.createMapping()));
            ftaDiagramEntity.setActive(true);
            ftaDiagramEntity.setStatus(FtaDiagramStatus.ACTIVATED);
            ftaDiagramService.save(ftaDiagramEntity);
            return "true";
        }
        ftaDiagramEntity.setStatus(FtaDiagramStatus.HAS_ERROR);
        ftaDiagramService.save(ftaDiagramEntity);
        return ftaModelXml;
    }

    @PostMapping("deactivate/{ftaDiagramId}")
    public void deactivateFtaModel(@PathVariable("ftaDiagramId") Long ftaDiagramId){
        FtaDiagramEntity ftaDiagramEntity = ftaDiagramService.findById(ftaDiagramId);
        ftaModelService.deleteByProjectId(ftaDiagramEntity.getProjectId());
        ftaDiagramEntity.setActive(false);
        ftaDiagramEntity.setStatus(FtaDiagramStatus.DEACTIVATED);
        ftaDiagramService.save(ftaDiagramEntity);
    }

    @PostMapping(value = "validate/{ftaDiagramId}", produces = "application/json")
    public String validateFtaModel(@PathVariable("ftaDiagramId") Long ftaDiagramId) throws ParseException {
        FtaDiagramEntity ftaDiagramEntity = ftaDiagramService.findById(ftaDiagramId);
        FtaConverter ftaConverter = new FtaConverter(ftaDiagramEntity);
        String ftaModelXml = ftaConverter.getXmlFta();
        if(ftaConverter.isValidate()){
            ftaDiagramEntity.setStatus(FtaDiagramStatus.VERIFIED);
            ftaDiagramService.save(ftaDiagramEntity);
            return "true";
        }
        ftaDiagramEntity.setStatus(FtaDiagramStatus.HAS_ERROR);
        ftaDiagramService.save(ftaDiagramEntity);
        return ftaModelXml;
    }

    @Deprecated
    @PostMapping("validate-scram/{projectId}")
    public boolean validateScramFtaModel(@PathVariable("projectId") Long projectId) throws IOException {
        FtaModelEntity ftaModelEntity = ftaModelService.findByProjectId(projectId);
        return ftaModelEntity.scramValidate();
    }

    @GetMapping(value = "get-short-list/{projectId}")
    public Iterable<FtaDiagramEntity> getShortList(@PathVariable("projectId") Long projectId){
        return ftaDiagramService.getList1(projectId);
    }
}
