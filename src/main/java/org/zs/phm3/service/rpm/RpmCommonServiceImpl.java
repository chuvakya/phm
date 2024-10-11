package org.zs.phm3.service.rpm;

import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.rpm.*;
import org.zs.phm3.repository.rpm.ProductCalcRepository;
import org.zs.phm3.repository.rpm.TotalCalcRepository;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.util.mapping.PhmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RpmCommonServiceImpl implements RpmCommonService {

    @Autowired
    UnitService unitService;
    @Autowired
    ProductCalcRepository productCalcRepository;
    @Autowired
    TotalCalcRepository totalCalcRepository;
    @Autowired
    GjbRestClient gjbRestClient;
    @Autowired
    GjbFeignProxyClient gjbFeignProxyClient;

    @Override
    public RpmDtoOutput getData(String unitId) throws IllegalArgumentException{

        List<TotalCalc> totalCalcList = totalCalcRepository.findAllByUnitId(PhmUtil.toShortUUID(unitId));
        List<ProductCalc> productCalcList = productCalcRepository.findAllByParentIdIs(PhmUtil.toShortUUID(unitId));
        return new RpmDtoOutput(totalCalcList, productCalcList);
    }

    @Override
    public void saveDataAll(List<ProductCalc> productCalcList) {//(RpmDto data) {
        productCalcList.forEach(productCalc -> {

            CalcId id = productCalc.getCalcId();
            id.setUnitId(PhmUtil.toShortUUID(id.getUnitId()));
            productCalc.setCalcId(id);
            productCalc.setParentId(PhmUtil.toShortUUID(productCalc.getParentId()));
            productCalc.setTotalValue(productCalc.getValue() * productCalc.getQuantity());
        });
        productCalcRepository.saveAll(productCalcList);//(data.getProductCalcList());
        totalCalcRepository.saveAll(totalCalcUpdate(productCalcList));
    }

    @Override
    public RpmDtoOutput saveData(RpmDtoIntput rpmDtoIntput) throws PhmException {
        double actualLambda = 0.0;

        if (rpmDtoIntput.getProductCalc().getCalculatedType().equals(CalculatedType.MANUAL_TYPE)) {
            actualLambda = rpmDtoIntput.getProductCalc().getValue();
        } else if (rpmDtoIntput.getProductCalc().getCalculatedType().equals(CalculatedType.CALCULATED_TYPE)) {
            if (rpmDtoIntput.getInputParametersValuesList() == null) {
                throw new PhmException("Calculated type's Data must contained input parameters.",
                        PhmErrorCode.BAD_REQUEST_PARAMS);
            }
            if (rpmDtoIntput.getProductCalc().getProductId()==0)
                throw new PhmException("Enter classcode into ProductId.",PhmErrorCode.BAD_REQUEST_PARAMS);
            actualLambda = gjbRestClient.getGjbResult(rpmDtoIntput.getProductCalc().getProductId(),1,rpmDtoIntput.getInputParametersValuesList());
        }

        double lambdaTotal = 0.0;

        List<ProductCalc> productCalcList =
                productCalcRepository.findAllByParentIdIs(PhmUtil.toShortUUID(rpmDtoIntput.getProductCalc().getParentId()));

        CalcId id = rpmDtoIntput.getProductCalc().getCalcId();
        id.setUnitId(PhmUtil.toShortUUID(id.getUnitId()));
        rpmDtoIntput.getProductCalc().setCalcId(id);
        rpmDtoIntput.getProductCalc().setParentId(PhmUtil.toShortUUID(rpmDtoIntput.getProductCalc().getParentId()));

        for (ProductCalc productCalcItem : productCalcList) {

            if (productCalcItem.equals(rpmDtoIntput.getProductCalc())){
                throw new PhmException("Duplicated data for unit with id = " +
                        PhmUtil.toLongUUID(rpmDtoIntput.getProductCalc().getCalcId().getUnitId()),
                        PhmErrorCode.TOO_MANY_UPDATES);
            }
            if (!productCalcItem.getCalcId().getUnitId().equals(rpmDtoIntput.getProductCalc().getCalcId().getUnitId())) {
                lambdaTotal = lambdaTotal + productCalcItem.getTotalValue();
            }
        }
        lambdaTotal = lambdaTotal + actualLambda * rpmDtoIntput.getProductCalc().getQuantity();

//        CalcId id = rpmDtoIntput.getProductCalc().getCalcId();
//        id.setUnitId(UnitEntity.toShortUUID(id.getUnitId()));
//        rpmDtoIntput.getProductCalc().setCalcId(id);
//        rpmDtoIntput.getProductCalc().setParentId(UnitEntity.toShortUUID(rpmDtoIntput.getProductCalc().getParentId()));
        rpmDtoIntput.getProductCalc().setValue(actualLambda);
        rpmDtoIntput.getProductCalc().setTotalValue(actualLambda * rpmDtoIntput.getProductCalc().getQuantity());

        List<TotalCalc> totalCalcList = new ArrayList<>();
        TotalCalc newTCalcLambda = new TotalCalc(new CalcId(rpmDtoIntput.getProductCalc().getParentId(), ParamCode.Λ),
                1, lambdaTotal);
        totalCalcList.add(newTCalcLambda);
        TotalCalc newTCalcMtbf = new TotalCalc(new CalcId(rpmDtoIntput.getProductCalc().getParentId(), ParamCode.MTBF), 1,
                1 / lambdaTotal);
        totalCalcList.add(newTCalcMtbf);

        productCalcRepository.save(rpmDtoIntput.getProductCalc());
        totalCalcRepository.saveAll(totalCalcList);
        return getData(PhmUtil.toLongUUID(rpmDtoIntput.getProductCalc().getParentId()));
    }

    @Override
    public List<TotalCalc> saveTotalCalcManually(TotalCalc totalCalcData) {
        List<TotalCalc> totalCalcList = new ArrayList<>();

        CalcId id = totalCalcData.getCalcId();
        id.setUnitId(PhmUtil.toShortUUID(id.getUnitId()));
        totalCalcList.add(totalCalcData);

//        id.setParamCode(ParamCode.MTBF);
        TotalCalc newTCalcMtbf = new TotalCalc( new CalcId(id.getUnitId(), ParamCode.MTBF),1,1/totalCalcData.getValue());
//        TotalCalc newTCalcMtbf = new TotalCalc(id,1, 1/totalCalcData.getValue());
        totalCalcList.add(newTCalcMtbf);
        return (List<TotalCalc>) totalCalcRepository.saveAll(totalCalcList);
    }

    @Override
    public List<String[]> getAllUnitChildsPtlData(String parentId) {
        return productCalcRepository.getAllUnitChildsPtlData(PhmUtil.toShortUUID(parentId));
    }

    @Override
    public void testFeign() {
/*        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

        logger.info("{}", response);

        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());*/


        List<String> status=gjbFeignProxyClient.getGjbStatus();
    }

    private List<TotalCalc> totalCalcUpdate(List<ProductCalc> productCalcList) {

        Double lambdaTotal = 0.0;
        String parentId = "";
        for (ProductCalc productCalc : productCalcList) {
            lambdaTotal = productCalc.getTotalValue();
            parentId = productCalc.getParentId();
        }
        List<TotalCalc> totalCalcList = new ArrayList<>();
        TotalCalc newTCalcLambda = new TotalCalc(new CalcId(parentId, ParamCode.Λ), 1, lambdaTotal);
        totalCalcList.add(newTCalcLambda);
        TotalCalc newTCalcMtbf = new TotalCalc(new CalcId(parentId, ParamCode.MTBF), 1, 1 / lambdaTotal);
        totalCalcList.add(newTCalcMtbf);

        return totalCalcList;
    }
}
