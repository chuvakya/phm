/* Users */
/*==============================================================*/
/* Table: user_entity                                       */
/*==============================================================*/

INSERT INTO user_entity(email, first_name, is_access, is_deleted, last_name, last_seen, login, name, organization, password, picture_extention)
VALUES ('admin@gmail.com', 'Super', true, false, 'User', null, 'Admin', 'Super User', 'Current', '12345678', null);



/*==============================================================*/
/* Table: user_role                                       */
/*==============================================================*/
INSERT INTO user_role(name)
VALUES ('Admin'); --1
INSERT INTO user_role(name)
VALUES ('Operator'); --2
INSERT INTO user_role(name)
VALUES ('ML Engineer'); --3
INSERT INTO user_role(name)
VALUES ('System Designer'); --4
INSERT INTO user_role(name)
VALUES ('Maintenance Engineer'); --5


/*==============================================================*/
/* Table: user_entity_user_role                                       */
/*==============================================================*/
INSERT INTO user_entity_user_role(user_entity_id, user_role_id)
VALUES (1, 1);



/*==============================================================*/
/* Table: user_permission_group                                       */
/*==============================================================*/
INSERT INTO user_permission_group(name)
VALUES ('All Rights'); --1
INSERT INTO user_permission_group(name)
VALUES ('Read Only'); -- 2
INSERT INTO user_permission_group(name)
VALUES ('Dashboard All Rights'); -- 3
INSERT INTO user_permission_group(name)
VALUES ('Dashboard Read Only'); -- 4
INSERT INTO user_permission_group(name)
VALUES ('User All Rights'); -- 5
INSERT INTO user_permission_group(name)
VALUES ('User Read Only'); -- 6
INSERT INTO user_permission_group(name)
VALUES ('Unit All Rights'); -- 7
INSERT INTO user_permission_group(name)
VALUES ('Unit Read Only'); -- 8
INSERT INTO user_permission_group(name)
VALUES ('System Settings All Rights'); -- 9
INSERT INTO user_permission_group(name)
VALUES ('System Settings Read Only'); -- 10
INSERT INTO user_permission_group(name)
VALUES ('FTA All Rights'); -- 11
INSERT INTO user_permission_group(name)
VALUES ('FTA Read Only'); -- 12
INSERT INTO user_permission_group(name)
VALUES ('RBD All Rights'); -- 13
INSERT INTO user_permission_group(name)
VALUES ('RBD Read Only'); -- 14
INSERT INTO user_permission_group(name)
VALUES ('FMEA All Rights'); -- 15
INSERT INTO user_permission_group(name)
VALUES ('FMEA Read Only'); -- 16
INSERT INTO user_permission_group(name)
VALUES ('RPM All Rights'); -- 17
INSERT INTO user_permission_group(name)
VALUES ('RPM Read Only'); -- 18
INSERT INTO user_permission_group(name)
VALUES ('ML All Rights'); -- 19
INSERT INTO user_permission_group(name)
VALUES ('ML Read Only'); -- 20
INSERT INTO user_permission_group(name)
VALUES ('Maintenance All Rights'); -- 21
INSERT INTO user_permission_group(name)
VALUES ('Maintenance Read Only'); -- 22
INSERT INTO user_permission_group(name)
VALUES ('Parts All Rights'); -- 23
INSERT INTO user_permission_group(name)
VALUES ('Parts Read Only'); -- 24
INSERT INTO user_permission_group(name)
VALUES ('FDM All Rights'); -- 25
INSERT INTO user_permission_group(name)
VALUES ('FDM Read Only'); -- 26
INSERT INTO user_permission_group(name)
VALUES ('Rules All Rights'); -- 27
INSERT INTO user_permission_group(name)
VALUES ('Rules Read Only'); -- 28
INSERT INTO user_permission_group(name)
VALUES ('Report All Rights'); -- 29
INSERT INTO user_permission_group(name)
VALUES ('Report Read Only'); -- 30
INSERT INTO user_permission_group(name)
VALUES ('KB All Rights'); -- 31
INSERT INTO user_permission_group(name)
VALUES ('KB Read Only'); -- 32


/*==============================================================*/
/* Table: user_permission                                       */
/*==============================================================*/
INSERT INTO user_permission(description, name)
VALUES ('Full Access to System Settings', 'SYSTEM_ALL'); --1
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to System Settings', 'SYSTEM_READ'); --2
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Project Management and Units', 'UNIT_ALL'); --3
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Projects and Units', 'UNIT_READ'); --4
INSERT INTO user_permission(description, name)
VALUES ('Full Access to User Management', 'USER_ALL'); --5
INSERT INTO user_permission(description, name)
VALUES ('Read-only Access to User Management', 'USER_READ'); --6
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Dashboard Management', 'DASHBOARD_ALL'); --7
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Dashboard Management', 'DASHBOARD_READ'); --8
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Fault Tree Analysis', 'FTA_ALL'); --9
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Fault Tree Analysis Management', 'FTA_READ'); --10
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Reliability Block Diagram Management', 'RBD_ALL'); --11
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Reliability Block Diagram management', 'RBD_READ'); --12
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Failure Modes and Effects Analysis Management', 'FMEA_ALL'); --13
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Failure Modes and Effects Analysis Management', 'FMEA_READ'); --14
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Reliability Prediction Management', 'RPM_ALL'); --15
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Reliability Prediction Management', 'RPM_READ'); --16
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Machine Learning Management', 'ML_ALL'); --17
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Machine Learning Management', 'ML_READ'); --18
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Maintenance Management', 'MAINTENANCE_ALL'); --19
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Maintenance Management', 'MAINTENANCE_READ'); --20
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Spare Parts Management', 'PARTS_ALL'); --21
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Spare Parts Management', 'PARTS_READ'); --22
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Fault Diagnosis Management', 'FDM_ALL'); --23
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Fault Diagnosis Management', 'FDM_READ'); --24
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Rule Management', 'RULES_ALL'); --25
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Rule Management', 'RULES_READ'); --26
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Report Management', 'REPORT_ALL'); --27
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Report Management', 'REPORT_READ'); --28
INSERT INTO user_permission(description, name)
VALUES ('Full Access to Knowledge Base Management', 'KB_ALL'); --29
INSERT INTO user_permission(description, name)
VALUES ('Read-Only Access to Knowledge Base Management', 'KB_READ'); --30


/*==============================================================*/
/* Table: user_group_permission                                       */
/*==============================================================*/
-- All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (1, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (3, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (5, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (7, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (9, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (11, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (13, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (15, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (17, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (19, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (21, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (23, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (25, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (27, 1);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (29, 1);

-- Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (2, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (4, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (6, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (8, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (10, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (12, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (14, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (16, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (18, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (20, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (22, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (24, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (26, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (28, 2);
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (30, 2);

-- Dashboard All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (7, 3);

-- Dashboard Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (8, 4);

-- User All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (5, 5);

-- User Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (6, 6);

-- Unit All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (3, 7);

-- Unit Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (4, 8);

-- System Settings All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (1, 9);

-- System Settings Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (2, 10);

-- FTA All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (9, 11);

-- FTA Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (10, 12);

-- RBD All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (11, 13);

-- RBD Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (12, 14);

-- FMEA All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (13, 15);

-- FMEA Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (14, 16);

-- RPM All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (15, 17);

-- RPM Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (16, 18);

-- ML All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (17, 19);

-- ML Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (18, 20);

-- Maintenance All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (19, 21);

-- Maintenance Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (20, 22);

--Parts All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (21, 23);

-- Parts Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (22, 24);

-- FDM All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (23, 25);

-- FDM Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (24, 26);

-- Rules All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (25, 27);

-- Rules Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (26, 28);

-- Report All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (27, 29);

-- Report Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (28, 30);

-- KB All Rights
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (29, 31);

-- KB Read Only
INSERT INTO user_group_permission(user_permission_id, user_permission_group_id)
VALUES (30, 32);




/*==============================================================*/
/* Table: user_role_user_permission_group                                       */
/*==============================================================*/
-- Admin
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (1, 1);

-- Operator
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (8, 2);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (4, 2);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (12, 2);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (14, 2);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (16, 2);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (18, 2);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (22, 2);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (24, 2);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (26, 2);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (28, 2);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (30, 2);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (32, 2);

-- ML Engineer
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (19, 3);

-- System Designer
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (7, 4);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (3, 4);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (11, 4);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (13, 4);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (15, 4);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (17, 4);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (21, 4);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (23, 4);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (25, 4);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (27, 4);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (29, 4);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (31, 4);

-- Maintenance Engineer
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (21, 5);
INSERT INTO user_role_user_permission_group(user_permission_group_id, user_role_id)
VALUES (23, 5);

/* UOM */
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Ampere', true, 'AMPERE', 'A', 'ElectricCurrent');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Candela', true, 'CANDELA', 'cd', 'LuminousIntensity');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Kelvin', true, 'KELVIN', 'K', 'Temperature');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Kilogram', true, 'KILOGRAM', 'kg', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Metre', true, 'METRE', 'm', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Mole', true, 'MOLE', 'mol', 'AmountOfSubstance');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Second', true, 'SECOND', 's', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Gram', true, 'GRAM', 'g', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Radian', true, 'RADIAN', 'rad', 'Angle');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Steradian', true, 'STERADIAN', 'sr', 'SolidAngle');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Hertz', true, 'HERTZ', 'Hz', 'Frequency');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Newton', true, 'NEWTON', 'N', 'Force');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Pascal', true, 'PASCAL', 'Pa', 'Pressure');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Joule', true, 'JOULE', 'J', 'Energy');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Watt', true, 'WATT', 'W', 'Power');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Coulomb', true, 'COULOMB', 'C', 'ElectricCharge');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Volt', true, 'VOLT', 'V', 'ElectricPotential');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Farad', true, 'FARAD', 'F', 'ElectricCapacitance');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Ohm', true, 'OHM', 'Ω', 'ElectricResistance');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Siemens', true, 'SIEMENS', 'S', 'ElectricConductance');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Weber', true, 'WEBER', 'Wb', 'MagneticFlux');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Tesla', true, 'TESLA', 'T', 'MagneticFluxDensity');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Henry', true, 'HENRY', 'H', 'ElectricInductance');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Celsius', true, 'CELSIUS', '℃', 'Temperature');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Lumen', true, 'LUMEN', 'lm', 'LuminousFlux');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Lux', true, 'LUX', 'lx', 'Illuminance');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Becquerel', true, 'BECQUEREL', 'Bq', 'Radioactivity');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Gray', true, 'GRAY', 'Gy', 'RadiationDoseAbsorbed');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Sievert', true, 'SIEVERT', 'Sv', 'RadiationDoseEffective');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Katal', true, 'KATAL', 'kat', 'CatalyticActivity');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Metre per second', true, 'METRE_PER_SECOND', 'm/s', 'Speed');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Metre per square second', true, 'METRE_PER_SQUARE_SECOND', 'm/s²', 'Acceleration');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Square metre', true, 'SQUARE_METRE', 'm²', 'Area');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Cubic metre', true, 'CUBIC_METRE', '㎥', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Kilometre per hour', true, 'KILOMETRE_PER_HOUR', 'km/h', 'Speed');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Percent', true, 'PERCENT', '%', 'Dimensionless');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Minute', true, 'MINUTE', 'min', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Hour', true, 'HOUR', 'h', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Day', true, 'DAY', 'day', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Week', true, 'WEEK', 'week', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Year', true, 'YEAR', 'year', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (true, 'Litre', true, 'LITRE', 'l', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Meter', true, 'METER', 'm', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Millimeter', true, 'MILLIMETER', 'mm', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Centimeter', true, 'CENTIMETER', 'cm', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Kilometer', true, 'KILOMETER', 'km', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Foot', true, 'FOOT', '(m*3048)/10000', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Furlong', true, 'FURLONG', '((m*3048)/10000)*660', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Yard', true, 'YARD', '((m*3048)/10000)*3', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Inch', true, 'INCH', '((m*3048)/10000)/12', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Mile', true, 'MILE', '(m*1609344)/1000', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Astronomical unit', true, 'ASTRONOMICAL_UNIT', 'm*149597870691', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Fathom', true, 'FATHOM', '((m*3048)/10000)*6', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Mile scandinavian', true, 'MILE_SCANDINAVIAN', 'dakm', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Revolution angle', true, 'REVOLUTION_ANGLE', 'rev', 'Angle');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Pi', true, 'PI', 'one*3.1415926535897932384626433832795028', 'Dimensionless');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Meter of mercury column', true, 'METER_OF_MERCURY_COLUMN', '(kPa*1333220)/10000', 'Pressure');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Inch hg', true, 'INCH_HG', '(kPa*1333220)/10000·((m*3048)/10000)/12/m', 'Pressure');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Degree angle', true, 'DEGREE_ANGLE', '[rad?]', 'Angle');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Minute angle', true, 'MINUTE_ANGLE', '[rad?]', 'Angle');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Second angle', true, 'SECOND_ANGLE', '[rad?]', 'Angle');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Degree', true, 'DEGREE', '[rad?]', 'Angle');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Arc minute', true, 'ARC_MINUTE', '[rad?]', 'Angle');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Arc second', true, 'ARC_SECOND', '[rad?]', 'Angle');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Square foot', true, 'SQUARE_FOOT', 'sft', 'Area');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Are', true, 'ARE', 'm²*100', 'Area');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Hectare', true, 'HECTARE', '(m²*100)*100', 'Area');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Acre', true, 'ACRE', 'sft*43560', 'Area');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Square inch', true, 'SQUARE_INCH', '((m*3048)/10000)/12²', 'Area');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Square yard', true, 'SQUARE_YARD', '((m*3048)/10000)*3²', 'Area');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Liter', true, 'LITER', 'l', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Cubic meter', true, 'CUBIC_METER', '㎥', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Cubic inch', true, 'CUBIC_INCH', '((m*3048)/10000)/12³', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Gallon', true, 'GALLON', '(((m*3048)/10000)/12³)*231', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Gallon imperial', true, 'GALLON_IMPERIAL', '(l*454609)/100000', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Cubic foot', true, 'CUBIC_FOOT', '(((m*3048)/10000)/12³)*1728', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Cubic mile', true, 'CUBIC_MILE', '((((m*3048)/10000)/12³)*1728)*147197952000', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Cubic yard', true, 'CUBIC_YARD', '((((m*3048)/10000)/12³)*1728)*27', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Fluid ounce', true, 'FLUID_OUNCE', '((((m*3048)/10000)/12³)*231)/128', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Acre foot', true, 'ACRE_FOOT', '((((m*3048)/10000)/12³)*1728)*43560', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Bushel', true, 'BUSHEL', '((((m*3048)/10000)/12³)*215042)/100', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Cup', true, 'CUP', '(((((m*3048)/10000)/12³)*231)/128)*8', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Cup metric', true, 'CUP_METRIC', 'ml*250', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Pint', true, 'PINT', 'pt', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Pint metric', true, 'PINT_METRIC', 'metr. pt', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Quart', true, 'QUART', 'qt', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Minim', true, 'MINIM', 'µl*61.61152', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Teaspoon', true, 'TEASPOON', '(µl*61.61152)*80', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Tablespoon', true, 'TABLESPOON', '((µl*61.61152)*80)*3', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Year julian', true, 'YEAR_JULIAN', 'day*365.25', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Month', true, 'MONTH', '(day*365.25)/12', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Century', true, 'CENTURY', '(day*365.25)*100', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Bar', true, 'BAR', 'b', 'Pressure');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Tonne', true, 'TONNE', 'kg*1000', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Grain', true, 'GRAIN', '(mg*6479891)/100000', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Pound', true, 'POUND', 'lb', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Ounce', true, 'OUNCE', 'oz', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Pennyweight troy', true, 'PENNYWEIGHT_TROY', '((mg*6479891)/100000)*24', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Ounce troy', true, 'OUNCE_TROY', '(((mg*6479891)/100000)*24)*20', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Stone', true, 'STONE', 'lb*14', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'C', true, 'C', '(m/s)*299792458', 'Speed');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'G force', true, 'G_FORCE', '(m/s²)*9.80665', 'Acceleration');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Light year', true, 'LIGHT_YEAR', '(m/s)*299792458·day*365.25', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Inch international', true, 'INCH_INTERNATIONAL', '(cm*254)/100', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Foot international', true, 'FOOT_INTERNATIONAL', '((cm*254)/100)*12', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Nautical mile', true, 'NAUTICAL_MILE', 'm*1852', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Knot', true, 'KNOT', 'm*1852/h', 'Speed');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Meter per second', true, 'METER_PER_SECOND', 'm/s', 'Speed');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Mile per hour', true, 'MILE_PER_HOUR', 'mph', 'Speed');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Meter per second squared', true, 'METER_PER_SECOND_SQUARED', 'm/s²', 'Acceleration');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Carat', true, 'CARAT', 'ct', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Karat', true, 'KARAT', 'kt', 'Dimensionless');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Pound force', true, 'POUND_FORCE', 'lb·(m/s²)*9.80665', 'Force');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Parsec', true, 'PARSEC', 'pc', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Line', true, 'LINE', '((cm*254)/100)/12', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Fahrenheit', true, 'FAHRENHEIT', '((K*5)/9)+459.67', 'Temperature');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Foodcalorie', true, 'FOODCALORIE', 'J*4186.8', 'Energy');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Calorie thermochemical', true, 'CALORIE_THERMOCHEMICAL', '(J*4184)/1000', 'Energy');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Calorie', true, 'CALORIE', '(J*4184)/1000', 'Energy');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Horsepower', true, 'HORSEPOWER', 'lb·((cm*254)/100)*12·(m/s²)*9.80665/s', 'Power');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Pound per square inch', true, 'POUND_PER_SQUARE_INCH', 'lb·(m/s²)*9.80665/(cm*254)/100²', 'Pressure');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Sphere', true, 'SPHERE', 'one*3.1415926535897932384626433832795028·sr*4', 'SolidAngle');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Bit', true, 'BIT', 'bit', 'Information');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Byte', true, 'BYTE', 'byte', 'Information');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Bit per second', true, 'BIT_PER_SECOND', 'bit/s', 'InformationRate');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Octet', true, 'OCTET', 'byte', 'Information');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Frame per second', true, 'FRAME_PER_SECOND', '1/s', 'Frequency');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Milligram per deciliter', true, 'MILLIGRAM_PER_DECILITER', 'mg/dl', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Liter per 100kilometers', true, 'LITER_PER_100KILOMETERS', 'km*100/l', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Liter per kilometer', true, 'LITER_PER_KILOMETER', 'km/l', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Mile per gallon', true, 'MILE_PER_GALLON', '(m*1609344)/1000/(((m*3048)/10000)/12³)*231', 'Volume');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Electron volt', null, 'ELECTRON_VOLT', 'eV', 'Energy');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Unified atomic mass', null, 'UNIFIED_ATOMIC_MASS', 'u', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Dalton', null, 'DALTON', 'Da', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Atom', null, 'ATOM', 'mol/602214199000000000000000', 'AmountOfSubstance');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Angstrom', null, 'ANGSTROM', 'Å', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Bohr radius', null, 'BOHR_RADIUS', 'a0', 'Length');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Barn', null, 'BARN', 'fm²*100', 'Area');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Day sidereal', null, 'DAY_SIDEREAL', 's*86164.09', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Year calendar', null, 'YEAR_CALENDAR', 'day*365', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Year sidereal', null, 'YEAR_SIDEREAL', 's*31558149.54', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Year julien', null, 'YEAR_JULIEN', 's*31557600', 'Time');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Atomic mass', null, 'ATOMIC_MASS', 'kg*1.6605387280149467E-27', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Electron mass', null, 'ELECTRON_MASS', 'kg*9.10938188E-31', 'Mass');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'E', null, 'E', 'e', 'ElectricCharge');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Faraday', null, 'FARADAY', 'C*96485.3414719984', 'ElectricCharge');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Franklin', null, 'FRANKLIN', 'C*3.3356E-10', 'ElectricCharge');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Rankine', null, 'RANKINE', '(K*5)/9', 'Temperature');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Revolution', null, 'REVOLUTION', 'rev', 'Angle');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Standard gravity', null, 'STANDARD_GRAVITY', 'g\u2099', 'Acceleration');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Gal', null, 'GAL', 'cm/s²', 'Acceleration');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Gilbert', null, 'GILBERT', 'one*3.141592653589793·daA/4', 'ElectricCurrent');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Erg', null, 'ERG', 'J/10000000', 'Energy');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Stilb', null, 'STILB', 'cd/cm²', 'Luminance');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Lambert', null, 'LAMBERT', 'cd/(one*3.141592653589793·cm²)', 'Luminance');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Phot', null, 'PHOT', 'ph', 'Illuminance');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Maxwell', null, 'MAXWELL', 'Wb/100000000', 'MagneticFlux');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Gauss', null, 'GAUSS', 'T/10000', 'MagneticFluxDensity');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Oersted', null, 'OERSTED', 'Oe', 'MagneticFieldStrength');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Dyne', null, 'DYNE', 'dyn', 'Force');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Kilogram force', null, 'KILOGRAM_FORCE', 'kgf', 'Force');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Millimetre of mercury', null, 'MILLIMETRE_OF_MERCURY', 'Pa*133.322', 'Pressure');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Inch of mercury', null, 'INCH_OF_MERCURY', 'Pa*3386.388', 'Pressure');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Rad', null, 'RAD', 'rd', 'RadiationDoseAbsorbed');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Rem', null, 'REM', 'Sv/100', 'RadiationDoseEffective');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Curie', null, 'CURIE', 'Bq*37000000000', 'Radioactivity');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Rutherford', null, 'RUTHERFORD', 'Bq*1000000', 'Radioactivity');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Poise', null, 'POISE', 'g/(cm·s)', 'DynamicViscosity');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Stokes', null, 'STOKES', 'cm²/s', 'KinematicViscosity');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Frames per second', null, 'FRAMES_PER_SECOND', '1/s', 'Frequency');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Roentgen', null, 'ROENTGEN', 'R', 'IonizingRadiation');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Bel', null, 'BEL', 'B', 'Dimensionless');
INSERT INTO uom (basic, description, display, name, symbol, type) VALUES (null, 'Neper', null, 'NEPER', 'Np', 'Dimensionles');

/* TsKvAttributeType */
INSERT INTO ts_kv_attribute_type (name, project_id) VALUES ('Status', null);
INSERT INTO ts_kv_attribute_type (name, project_id) VALUES ('Environment', null);
INSERT INTO ts_kv_attribute_type (name, project_id) VALUES ('Signal', null);
INSERT INTO ts_kv_attribute_type (name, project_id) VALUES ('Calibration', null);
INSERT INTO ts_kv_attribute_type (name, project_id) VALUES ('Test/BIT', null);
INSERT INTO ts_kv_attribute_type (name, project_id) VALUES ('Business', null);
INSERT INTO ts_kv_attribute_type (name, project_id) VALUES ('Logs', null);

/* Calculation Attribute Type */
INSERT INTO calculation_attr_type (name) VALUES ('ML Services');
INSERT INTO calculation_attr_type (name) VALUES ('Reliability Service');

/* UnitTypePTL */
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Function', 1, null, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('System of system', 2, null, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('System', 3, null, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Subsystem', 4, null, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Equipment group', 5, null, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Equipment', 6, null, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Module', 7, null, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Board', 8, null, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Electronic components', 9, null, true, 1, null);

INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Microcircuit', 10, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Diode', 11, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Transistor', 12, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Resistor', 13, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Capacitor', 14, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Potentiometer', 15, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Optoelectronic devices', 16, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Vacuum electronic devices (tubes)', 17, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Connector', 18, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Inductive elements', 19, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Relay', 20, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Magnetic devices', 21, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Resonator and oscillator', 22, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Filter', 23, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Battery', 24, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Laser', 25, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Optical fiber connector', 26, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('PCB, solder joints and SMT', 27, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Switch', 28, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Rotating appliances', 29, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Light', 30, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Gyroscope', 31, 9, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Other components', 32, 9, true, 1, null);

INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Mechanical components', 33, null, true, 1, null);
INSERT INTO unit_type_ptl (name, picture_id, unit_type_ptl_id, is_default_type, modified_by_id, modified_time) VALUES ('Software', 34, null, true, 1, null);


/* insert sub group ptl */
/*10-Microcircuit*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (200, 'Monolithic digital circuit bipolar', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (201, 'Monolithic digital circuit MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (202, 'Monolithic analog circuit bipolar', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (203, 'Monolithic analog circuit MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (204, 'Microprocessor bipolar', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (205, 'Microprocessor MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (206, 'ROM memory bipolar', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (207, 'ROM memory MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (208, 'SRAM memory bipolar', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (209, 'SRAM memory MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (210, 'DRAM memory bipolar', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (211, 'DRAM memory MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (212, 'PROM memory bipolar', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (213, 'PROM memory MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (214, 'EPROM memory MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (215, 'EEPROM memory MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (216, 'UVEPROM memory MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (217, 'FLASH memory MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (218, 'FIFO memory MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (219, 'CCD memory MOS', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (220, 'GaAs MMIC', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (221, 'Hybrid integrated circuits', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (259, 'Surface acoustic wave device', 10);

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (260, 'Single chip digital integrated circuit bipolar (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (261, 'Single chip digital integrated circuit MOS (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (262, 'Monolithic analog integrated circuit (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (263, 'Microprocessor circuit bipolar (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (264, 'Microprocessor circuit MOS (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (265, 'SRAM memory bipolar (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (266, 'SRAM memory MOS (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (267, 'DRAM memory (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (268, 'FIFO memory (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (269, 'ROM memory bipolar (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (270, 'ROM memory MOS (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (271, 'PROM memory bipolar (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (272, 'PROM memory MOS (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (273, 'EPROM memory bipolar (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (274, 'EPROM memory MOS (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (275, 'UVEPROM memory bipolar (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (276, 'UVEPROM memory MOS (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (277, 'EEPROM memory bipolar (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (278, 'EEPROM memory MOS (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (279, 'FLASH memory bipolar (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (280, 'FLASH memory MOS (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (281, 'GaAs MMIC (import)', 10);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (282, 'Hybrid integrated circuits (import)', 10);


/*11-Diode*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (1, 'Silicon ordinary diode', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (2, 'Germanium ordinary diode', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (10, 'Voltage adjustment, voltage reference and current adjustment diode', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (11, 'Microwave silicon detector diode', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (12, 'Microwave germanium detector diode', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (13, 'Microwave silicon mixing diode', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (14, 'Microwave germanium mixing diode', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (15, 'Varactor', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (16, 'PIN diode', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id) 
VALUES (17, 'Breakaway diode', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (18, 'Tunnel diode', 11);

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (291, 'Silicon diode (import)', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (292, 'Germanium diode (import)', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (293, 'Voltage adjustment, voltage reference and current adjustment diod', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (294, 'Silicon detector diode (import)', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (295, 'Silicon mixing diode (import)', 11);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (296, 'Variable capacitance, step recovery, tunnel, PIN, body effect, collapse diode', 11);



/*12-Transistor*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (3, 'Silicon bipolar transistor', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (4, 'Germanium bipolar transistor', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (5, 'High power bipolar transistor', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (6, 'Silicon field effect transistor', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (7, 'Controlled field effect transistor', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (8, 'Single junction transistor', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (9, 'Thyristor', 12);

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (283, 'Ordinary bipolar transistor: Silicon bipolar transistor NPN (import)', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (284, 'Ordinary bipolar transistor: Silicon bipolar transistor PNP (import)', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (285, 'Field effect transistor: Silicon field-effect transistor (import)', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (286, 'Field effect transistor: Gallium field effect transistor (import)', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (287, 'Single junction transistor (import)', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (288, 'Microwave transistor: Microwave transistor pulse amplifier (import)', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (289, 'Microwave transistor: Continuous wave microwave transistor (import)', 12);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (290, 'Thyristor (import)', 12);


/*13-Resistor*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (62, 'Synthetic resistor', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (63, 'Metal Film Resistor', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (64, 'Carbon Film Resistor', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (65, 'Wirewound Resistor', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (66, 'Power Wirewound Resistor', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (67, 'Power Non-Wirewound Resistor', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (75, 'Thermistor', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (76, 'Varistor', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (77, 'Photoresistor', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (78, 'Chip film resistors', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (79, 'Resistance network', 13);

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (323, 'Synthetic resistor (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (324, 'Conventional thin film resistor (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (325, 'Power thin film resistor (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (326, 'Varistor (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (327, 'Photoresistor (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (328, 'Ordinary wire wound resistors (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (329, 'Precision wire wound resistor (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (330, 'Power wire wound resistor (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (331, 'Thermistor (ball shape) (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (332, 'Thermistor (disc shape) (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (333, 'Thermistor (stick shape) (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (334, 'Thermistor (PTC polymer device) (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (335, 'Chip film resistor (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (336, 'Resistance network with discrete components (import)', 13);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (337, 'Resistance network with thick film (import)', 13);



/*14-Capasitor*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (71, 'Paper and film capacitors', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (72, 'Glass glaze capacitor', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (73, 'Mica capacitor', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (74, 'Ceramic capacitor', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (80, 'Class 2 Ceramic capacitor', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (81, 'Class 3 Ceramic capacitor', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (82, 'Solid Xuan Electrolytic Capacitor', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (100, 'Non-solid electrolytic capacitor', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (101, 'Aluminum electrolytic capacitor', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (102, 'Variable capacitor', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (103, 'Capacitor network', 14);

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (349, 'Paper capacitor (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (350, 'Capacitor composite carrier paper-film (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (351, 'Organic film capacitor (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (352, 'Mica capacitor (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (353, 'Glass glaze capacitor (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (354, 'Sealed solid electrolytic capacitor (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (355, 'Unsealed solid electrolytic capacitor (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (356, 'Non-solid electrolytic capacitor (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (357, 'Porcelain dielectric capacitor (including 1, 2, 3 types of porcelain) (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (358, 'Axial pin aluminum electrolytic capacitor (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (359, 'Frame mounted aluminum electrolytic capacitor (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (360, 'Variable capacitor with fine tuning (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (361, 'Variable capacitor in glass glaze (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (362, 'Variable porcelain capacitor (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (363, 'Variable vacuum capacitor (import)', 14);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (364, 'Capacitor network (import)', 14);



/*15-Potentiometer*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (58, 'Ordinary wire wound potentiometer', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (59, 'Precision wirewound potentiometer', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (60, 'Fine-tuning wire wound potentiometer', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (61, 'Power wire wound potentiometer', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (68, 'Organic solid potentiometer', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (69, 'Synthetic carbon film potentiometer', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (70, 'Glass glaze potentiometer', 15);

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (338, 'Thin film potentiometer (import)', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (339, 'Low precision non-wire wound potentiometer (import)', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (340, 'Precision carbon film potentiometer (import)', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (341, 'Trimming non-wire wound potentiometer (import)', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (342, 'Organic solid potentiometer (import)', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (343, 'Glass glaze potentiometer (import)', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (344, 'Power wire wound potentiometer (import)', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (345, 'Screw push wire wound potentiometer(import)', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (346, 'Precision wire wound potentiometer (import)', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (347, 'Semi-precision wire wound potentiometer (import)', 15);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (348, 'Trimming wire wound potentiometer (import)', 15);



/*16-Optoelectronic devices*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (19, 'Light-emitting diode', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (20, 'Infrared LED', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (21, 'Photodiode', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (22, 'Phototransistor', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (23, 'Optocoupler', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (24, 'Digital tube', 16);

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (297, 'Optocoupler (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (298, 'Single light emitting diode (LED) (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (299, 'Photodiode (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (300, 'Photodiode detector (single) (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (301, 'Phototransistor (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (302, 'Phototransistor detector (single) (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (303, 'Photoelectric filter (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (304, 'Fiber laser module (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (305, 'Optical fiber light emitting diode module (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (306, 'Optical fiber detector module (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (307, 'Fiber coupler (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (308, 'Wavelength Division Multiplexer (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (309, 'Digital Tube (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (310, 'Unit digital tube/with logic chip (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (311, 'Two-digit digital tube (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (312, 'Two-digit digital tube /with logic chip (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (313, 'Three-digit digital tube (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (314, 'Three-digit digital tube / with logic chip (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (315, 'Four-digit digital tube (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (316, 'Five-digit digital tube (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (317, 'Six-digit digital tube (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (318, 'Seven-digit digital tube (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (319, 'Eight-digit digital tube (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (320, 'Nine-digit digital tube (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (321, 'Ten-digit digital tube (import)', 16);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (322, 'Multi-digit digital tube (import)', 16);



/*17-Vacuum electronic devices (tubes)*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (25, 'Launch tube: Ultra short wave', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (26, 'Launch tube: Pulse', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (27, 'Launch tube: Decimeter wave', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (28, 'Launch tube: Centimeter wave', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (29, 'Modulating tube: Continuous wave', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (30, 'Modulating tube: Pulse', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (31, 'Discharge tube: Resonance', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (32, 'Discharge tube: Non-resonance', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (33, 'Voltage-stabilized tube', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (34, 'Flow regulator', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (35, 'Rectifier', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (36, 'Look at sluice flow', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (37, 'Receive magnifying tubes', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (38, 'Counter', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (39, 'X-ray tube', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (40, 'Digital tube', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (41, 'Fluorescent tube', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (42, 'Photomultiplier tube', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (43, 'Trigger tube', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (44, 'Continuous Wave Speed Tubing', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (45, 'Pulse Speed Control Tubes', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (46, 'Quadrature Field Magnifier', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (47, 'Electron beam tube: Indicator tube', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (48, 'Electron beam tube: Black and white cathode ray tube', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (49, 'Electron beam tube: Oscilloscope', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (50, 'Electron beam tube: Color cathode ray tube', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (51, 'Electron beam tube: Camera tube', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (52, 'Reverberation tube: Succession', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (53, 'Reverberation tube: Pulse', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (54, 'Inflatable microwave switch tube: Active pipe', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (55, 'Inflatable microwave switch tube: Passive tube', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (56, 'Traveling wave tube (electronics)', 17);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (57, 'Magnetron', 17);



/*18-Connector*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (83, 'Connector', 18);

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (388, 'Universal power connector (import)', 18);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (389, 'Electrical coaxial connector (import)', 18);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (390, 'Optical fiber connector (import)', 18);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (391, 'Plug (import)', 18);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (392, 'Connectors for printed circuit boards (import)', 18);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (393, 'Ribbon cable connector (import)', 18);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (394, 'IC socket (import)', 18);



/*19-Inductive elements*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (105, 'Transformers: Low-power pulse transformer', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (106, 'Transformers: Intermediate frequency transformer', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (107, 'Transformers: Audio transformer', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (108, 'Transformers: Power Transformer', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (109, 'Transformers: High-power pulse transformer', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (110, 'Transformers: High voltage transformer', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (111, 'Transformers: RF transformer', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (112, 'Coils and inductors: Fixed and variable small coils and inductors', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (113, 'Coils and inductors: Low-power radio frequency coils, deflection coils', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (114, 'Coils and inductors: High power radio frequency coil', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (115, 'Chip coils and chip inductors: Chip coils and chip inductors', 19);

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (365, 'Low power pulse transformer (import)', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (366, 'High-power pulse transformer, power transformer (import)', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (367, 'Power pulse transformer (import)', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (368, 'Audio transformer (import)', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (369, 'Intermediate frequency transformer (import)', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (370, 'Power transformer (>1W) (import)', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (371, 'RF transformer (import)', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (372, 'High voltage transformer (import)', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (373, 'Load coil (import)', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (374, 'Power filter (import)', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (375, 'Fixed RF coil (import)', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (376, 'Variable radio frequency coil (import)', 19);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (377, 'Chip coils and chip inductors (import)', 19);



/*20-Relay*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (116, 'Electromechanical relay', 20);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (117, 'Solid state relay', 20);

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (378, 'General relay (import)', 20);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (379, 'Contact relay (import)', 20);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (380, 'Latch relay (import)', 20);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (381, 'Reed relay (import)', 20);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (382, 'Thermal delay, bimetal relay (import)', 20);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (383, 'Wet reed relay (import)', 20);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (384, 'Solid state relay (import)', 20);



/*21-Magnetic devices*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (118, 'High Power Magnetic Devices', 21);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (119, 'Isolator', 21);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (120, 'Sound Amplifier', 21);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (121, 'Gyrator', 21);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (122, 'Modulator', 21);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (123, 'Circulator', 21);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (124, 'Phase Shifter', 21);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (125, 'Coupler', 21);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (126, 'Low-power magnetic device (for receiver)', 21);



/*22-Resonator and oscillator*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (127, 'Resonator', 22);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (128, 'Oscillator', 22);

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (405, 'Resonator (import)', 22);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (406, 'Oscillator (import)', 22);



/*23-Filter*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (129, 'Electronic filter: Discrete inductance capacitor', 23);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (130, 'Electronic filter: Discrete inductors, capacitors and crystals', 23);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (131, 'Piezoelectric Ceramics', 23);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (132, 'Quartz crystal', 23);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (133, 'Mechanical filter', 23);


/*24-Battery*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (134, 'Silicon solar cell', 24);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (135, 'Cadmium silver battery', 24);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (136, 'Lead-acid batteries', 24);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (137, 'Lithium battery', 24);


/*25-Laser*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (140, 'Nitrogen Atmosphere Laser', 25);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (141, 'Nitrogen cadmium laser', 25);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (142, 'Nitrogen ion laser', 25);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (143, 'Sealed carbon dioxide', 25);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (144, 'Rod laser', 25);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (145, 'Ruby Rod Laser', 25);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (146, 'Semiconductor laser', 25);


/*26-Optical fiber connector*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (148, 'Optical fiber connector', 26);


/*27-PCB, solder joints and SMT*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (150, 'Printed board (PCB)', 27);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (151, 'Welding point', 27);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (152, 'SMT interconnect', 27);



/*28-Switch*/

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (385, 'Toggle-button switch (import)', 28);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (386, 'Rocker switch (import)', 28);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (387, 'Rotary switch (import)', 28);



/*29-Rotating appliances*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (84, 'Ordinary motor', 29);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (85, 'Low speed and low load motor', 29);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (86, 'Timer', 29);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (87, 'Blower', 29);

/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (395, 'Blower (import)', 29);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (396, 'Dryer (import)', 29);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (397, 'Fan (import)', 29);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (398, 'High speed motor (import)', 29);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (399, 'Medium speed motor (import)', 29);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (400, 'Low speed motor (import)', 29);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (401, 'Rectifier motor (import)', 29);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (402, 'AC timer (import)', 29);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (403, 'Switch Driver Timer (import)', 29);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (404, 'Timer DC Switch (import)', 29);


/*30-Light*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (138, 'Atmosphere lamp', 30);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (139, 'Incandescent lamp', 30);


/*31-Gyroscope*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (147, 'Piezoelectric gyroscope', 31);



/*32-Other components*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (222, 'Microwave components: Attenuator (fixed or adjustable)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (223, 'Microwave components: Fixed element', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (224, 'Microwave components: Directional coupler', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (225, 'Microwave components: Fixed short line', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (226, 'Microwave components: Resonant cavity', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (227, 'Microwave components: Adjustable stub or adjustable resonator', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (228, 'Microwave components: Waveguide switch', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (229, 'Microwave components: Waveguide cross-connect high power', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (230, 'Microwave components: Waveguide cross-connect low power', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (231, 'Microwave components: Microwave integrated circuit module high power', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (232, 'Microwave components: Microwave integrated circuit module low power', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (240, 'Electroacoustic device: Microphone', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (241, 'Electroacoustic device: Electric bell', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (242, 'Electroacoustic device: Speaker', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (251, 'Switch: Protection (each electrode)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (252, 'Switch: For power switch (each electrode)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (233, 'Depression component', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (234, 'Meter head', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (235, 'Heater', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (236, 'Fuse', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (237, 'LC delay line (<= 0.5 μs)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (238, 'LC delay line (> 0.5 μs)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (239, 'Cable (each•km)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (243, 'Whip antenna', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (244, 'Loop antenna', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (245, 'IC socket', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (246, 'Simulation load (< 100W)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (247, 'Simulation load (100 W ~ <= 1000 W)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (248, 'Simulation load (> 1000W)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (249, 'Emulation terminal (thick, film load)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (250, 'Ceramic resonator', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (253, 'Fiber laser module', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (254, 'Optical fiber light emitting diode module', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (255, 'Optical fiber detector module', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (256, 'Fiber coupler', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (257, 'Wavelength Division Multiplexer', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (258, 'Photoelectric filter', 32);


/*import*/
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (407, 'Piezoelectric gyroscope (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (408, 'Resonator: 60Hz (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (409, 'Resonator: 120Hz (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (410, 'Resonator: 400Hz (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (411, 'Obese porcelain resonator (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (412, 'Switch: Safety switch (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (413, 'Switch: Switch for source switch (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (414, 'Fuse: <=30A (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (415, 'Fuse: >30A (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (416, 'Light: Atmosphere lamp (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (417, 'Light: Incandescent lamp 5VDC (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (418, 'Light: Incandescent lamp 12VDC (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (419, 'Light: Incandescent lamp 48VDC (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (420, 'Dashboard (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (421, 'Heater (crystal stove) (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (422, 'Microwave components: Coaxial line and waveguide load (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (423, 'Microwave components: Attenuator (fixed or adjustable) (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (424, 'Microwave components: Fixed element (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (425, 'Microwave components: Directional coupler (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (426, 'Microwave components: Short line (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (427, 'Microwave components: Cavity (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (428, 'Microwave components: Variable element (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (429, 'Microwave components: Adjustable short line (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (430, 'Microwave components: Adjustable cavity (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (431, 'Ferrite device (transmission) (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (432, 'Ferrite device (receiving) (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (433, 'Thermoelectric cooling (＜2W) (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (434, 'Delay line (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (435, 'Battery: Cadmium Battery (import)', 32);
INSERT INTO sub_group_ptl (class_code, class_name, unit_type_ptl_id)
VALUES (436, 'Battery: Lithium battery (import)', 32);