package org.zs.phm3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.UomEntity;
import org.zs.phm3.repository.UomEntityRepository;
import org.zs.phm3.util.uom.UomUnits;
import si.uom.quantity.*;
import systems.uom.quantity.Information;
import systems.uom.quantity.InformationRate;

import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.quantity.*;
import java.util.List;

@Service
public class UomServiceImpl implements UomService {

    @Autowired
    private UomEntityRepository uomEntityRepository;

    @Override
    public List<UomEntity> getAll() {
        return (List<UomEntity>) uomEntityRepository.findAll();
    }

    @Override
    public Double convert(UomEntity uomInput, UomEntity uomOutput, Double value) {
        UnitConverter converter = null;

        switch(uomInput.getType()) {
            case Acceleration -> {
                Unit<Acceleration> currentUnit = (Unit<Acceleration>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Acceleration> targetUnit = (Unit<Acceleration>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case AmountOfSubstance -> {
                Unit<AmountOfSubstance> currentUnit = (Unit<AmountOfSubstance>) UomUnits.getUnitByName(uomInput.getName());
                Unit<AmountOfSubstance> targetUnit = (Unit<AmountOfSubstance>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Angle -> {
                Unit<Angle> currentUnit = (Unit<Angle>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Angle> targetUnit = (Unit<Angle>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Area -> {
                Unit<Area> currentUnit = (Unit<Area>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Area> targetUnit = (Unit<Area>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case CatalyticActivity -> {
                Unit<CatalyticActivity> currentUnit = (Unit<CatalyticActivity>) UomUnits.getUnitByName(uomInput.getName());
                Unit<CatalyticActivity> targetUnit = (Unit<CatalyticActivity>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Dimensionless -> {
                Unit<Dimensionless> currentUnit = (Unit<Dimensionless>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Dimensionless> targetUnit = (Unit<Dimensionless>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case ElectricCapacitance -> {
                Unit<ElectricCapacitance> currentUnit = (Unit<ElectricCapacitance>) UomUnits.getUnitByName(uomInput.getName());
                Unit<ElectricCapacitance> targetUnit = (Unit<ElectricCapacitance>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case ElectricCharge -> {
                Unit<ElectricCharge> currentUnit = (Unit<ElectricCharge>) UomUnits.getUnitByName(uomInput.getName());
                Unit<ElectricCharge> targetUnit = (Unit<ElectricCharge>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case ElectricConductance -> {
                Unit<ElectricConductance> currentUnit = (Unit<ElectricConductance>) UomUnits.getUnitByName(uomInput.getName());
                Unit<ElectricConductance> targetUnit = (Unit<ElectricConductance>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case ElectricCurrent -> {
                Unit<ElectricCurrent> currentUnit = (Unit<ElectricCurrent>) UomUnits.getUnitByName(uomInput.getName());
                Unit<ElectricCurrent> targetUnit = (Unit<ElectricCurrent>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case ElectricInductance -> {
                Unit<ElectricInductance> currentUnit = (Unit<ElectricInductance>) UomUnits.getUnitByName(uomInput.getName());
                Unit<ElectricInductance> targetUnit = (Unit<ElectricInductance>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case ElectricPotential -> {
                Unit<ElectricPotential> currentUnit = (Unit<ElectricPotential>) UomUnits.getUnitByName(uomInput.getName());
                Unit<ElectricPotential> targetUnit = (Unit<ElectricPotential>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case ElectricResistance -> {
                Unit<ElectricResistance> currentUnit = (Unit<ElectricResistance>) UomUnits.getUnitByName(uomInput.getName());
                Unit<ElectricResistance> targetUnit = (Unit<ElectricResistance>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Energy -> {
                Unit<Energy> currentUnit = (Unit<Energy>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Energy> targetUnit = (Unit<Energy>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Force -> {
                Unit<Force> currentUnit = (Unit<Force>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Force> targetUnit = (Unit<Force>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Frequency -> {
                Unit<Frequency> currentUnit = (Unit<Frequency>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Frequency> targetUnit = (Unit<Frequency>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Illuminance -> {
                Unit<Illuminance> currentUnit = (Unit<Illuminance>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Illuminance> targetUnit = (Unit<Illuminance>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Information -> {
                Unit<Information> currentUnit = (Unit<Information>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Information> targetUnit = (Unit<Information>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case InformationRate -> {
                Unit<InformationRate> currentUnit = (Unit<InformationRate>) UomUnits.getUnitByName(uomInput.getName());
                Unit<InformationRate> targetUnit = (Unit<InformationRate>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Length -> {
                Unit<Length> currentUnit = (Unit<Length>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Length> targetUnit = (Unit<Length>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case LuminousFlux -> {
                Unit<LuminousFlux> currentUnit = (Unit<LuminousFlux>) UomUnits.getUnitByName(uomInput.getName());
                Unit<LuminousFlux> targetUnit = (Unit<LuminousFlux>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case LuminousIntensity -> {
                Unit<LuminousIntensity> currentUnit = (Unit<LuminousIntensity>) UomUnits.getUnitByName(uomInput.getName());
                Unit<LuminousIntensity> targetUnit = (Unit<LuminousIntensity>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case MagneticFlux -> {
                Unit<MagneticFlux> currentUnit = (Unit<MagneticFlux>) UomUnits.getUnitByName(uomInput.getName());
                Unit<MagneticFlux> targetUnit = (Unit<MagneticFlux>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case MagneticFluxDensity -> {
                Unit<MagneticFluxDensity> currentUnit = (Unit<MagneticFluxDensity>) UomUnits.getUnitByName(uomInput.getName());
                Unit<MagneticFluxDensity> targetUnit = (Unit<MagneticFluxDensity>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Mass -> {
                Unit<Mass> currentUnit = (Unit<Mass>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Mass> targetUnit = (Unit<Mass>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Power -> {
                Unit<Power> currentUnit = (Unit<Power>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Power> targetUnit = (Unit<Power>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Pressure -> {
                Unit<Pressure> currentUnit = (Unit<Pressure>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Pressure> targetUnit = (Unit<Pressure>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case RadiationDoseAbsorbed -> {
                Unit<RadiationDoseAbsorbed> currentUnit = (Unit<RadiationDoseAbsorbed>) UomUnits.getUnitByName(uomInput.getName());
                Unit<RadiationDoseAbsorbed> targetUnit = (Unit<RadiationDoseAbsorbed>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case RadiationDoseEffective -> {
                Unit<RadiationDoseEffective> currentUnit = (Unit<RadiationDoseEffective>) UomUnits.getUnitByName(uomInput.getName());
                Unit<RadiationDoseEffective> targetUnit = (Unit<RadiationDoseEffective>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Radioactivity -> {
                Unit<Radioactivity> currentUnit = (Unit<Radioactivity>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Radioactivity> targetUnit = (Unit<Radioactivity>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case SolidAngle -> {
                Unit<SolidAngle> currentUnit = (Unit<SolidAngle>) UomUnits.getUnitByName(uomInput.getName());
                Unit<SolidAngle> targetUnit = (Unit<SolidAngle>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Speed -> {
                Unit<Speed> currentUnit = (Unit<Speed>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Speed> targetUnit = (Unit<Speed>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Temperature -> {
                Unit<Temperature> currentUnit = (Unit<Temperature>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Temperature> targetUnit = (Unit<Temperature>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Time -> {
                Unit<Time> currentUnit = (Unit<Time>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Time> targetUnit = (Unit<Time>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Volume -> {
                Unit<Volume> currentUnit = (Unit<Volume>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Volume> targetUnit = (Unit<Volume>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case Luminance -> {
                Unit<Luminance> currentUnit = (Unit<Luminance>) UomUnits.getUnitByName(uomInput.getName());
                Unit<Luminance> targetUnit = (Unit<Luminance>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case KinematicViscosity -> {
                Unit<KinematicViscosity> currentUnit = (Unit<KinematicViscosity>) UomUnits.getUnitByName(uomInput.getName());
                Unit<KinematicViscosity> targetUnit = (Unit<KinematicViscosity>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case IonizingRadiation -> {
                Unit<IonizingRadiation> currentUnit = (Unit<IonizingRadiation>) UomUnits.getUnitByName(uomInput.getName());
                Unit<IonizingRadiation> targetUnit = (Unit<IonizingRadiation>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case DynamicViscosity -> {
                Unit<DynamicViscosity> currentUnit = (Unit<DynamicViscosity>) UomUnits.getUnitByName(uomInput.getName());
                Unit<DynamicViscosity> targetUnit = (Unit<DynamicViscosity>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }
            case MagneticFieldStrength -> {
                Unit<MagneticFieldStrength> currentUnit = (Unit<MagneticFieldStrength>) UomUnits.getUnitByName(uomInput.getName());
                Unit<MagneticFieldStrength> targetUnit = (Unit<MagneticFieldStrength>) UomUnits.getUnitByName(uomOutput.getName());
                converter = currentUnit.getConverterTo(targetUnit);
            }

        }
        return converter.convert(value.doubleValue());
    }

    @Override
    public UomEntity getById(Integer id) {
        return uomEntityRepository.findById(id).get();
    }
}
