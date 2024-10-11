package org.zs.phm3.models;

public enum UomTypes {
        Acceleration,
        AmountOfSubstance,
        Angle,
        Area,
        CatalyticActivity,
        Dimensionless,
        ElectricCapacitance,
        ElectricCharge,
        ElectricConductance,
        ElectricCurrent,
        ElectricInductance,
        ElectricPotential,
        ElectricResistance,
        Energy,
        Force,
        Frequency,
        Illuminance,
        Information,
        InformationRate,
        Length,
        LuminousFlux,
        LuminousIntensity,
        MagneticFlux,
        MagneticFluxDensity,
        Mass,
        Power,
        Pressure,
        RadiationDoseAbsorbed,
        RadiationDoseEffective,
        Radioactivity,
        SolidAngle,
        Speed,
        Temperature,
        Time,
        Volume,
        MagneticFieldStrength,
        Luminance,
        KinematicViscosity,
        IonizingRadiation,
        DynamicViscosity,
        test;

        public static UomTypes findByAbbr(String abbr){

                for (UomTypes v : values()) {
                    if (v.name().equals(abbr)) {
                        return v;
                    }
                }
                return null;
            }
    }
