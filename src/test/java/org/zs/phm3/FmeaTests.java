package org.zs.phm3;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.fmea.*;
import org.zs.phm3.models.fmea.FailureConsequenceScore;
import org.zs.phm3.models.fmea.FailureProbabilityScore;
import org.zs.phm3.models.ptl.UnitTypePTL;
import org.zs.phm3.service.fmea.CrudCommonService;
import org.zs.phm3.service.fmea.CrudCommonServiceWithStringId;
import org.zs.phm3.service.ptl.UnitTypePTLService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("postgrestest")*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

//@EnableConfigurationProperties
//@Transactional

public class FmeaTests extends AbstractPostgreTestProfile {

    @Autowired
    @Qualifier("failureDetectionMethodService")
    private CrudCommonService<FailureDetectionMethod> failureDetectionMethodService;
    @Autowired
    @Qualifier("failureConsequenceService")
    private CrudCommonService<FailureConsequence> failureConsequenceService;
    @Autowired
    @Qualifier("failureModeService")
    private CrudCommonService<FailureMode> failureModeService;
    @Autowired
    @Qualifier("failureReasonService")
    private CrudCommonService<FailureReason> failureReasonService;
    @Autowired
    @Qualifier("recommendationService")
    private CrudCommonService<Recommendation> recommendationService;
    @Autowired
    @Qualifier("failureSeverityService")
    private CrudCommonServiceWithStringId<FailureSeverity> failureSeverityService;
    @Autowired
    @Qualifier("detectionScoreScaleService")
    private CrudCommonServiceWithStringId<DetectionScoreScale> detectionScoreScaleService;
    @Autowired
    @Qualifier("significanceScoreScaleService")
    private CrudCommonServiceWithStringId<SignificanceScoreScale> significanceScoreScaleService;
    @Autowired
    @Qualifier("occurrenceScoreScaleService")
    private CrudCommonServiceWithStringId<OccurrenceScoreScale> occurrenceScoreScaleService;
    @Autowired
    @Qualifier("failureProbabilityScoreService")
    private CrudCommonServiceWithStringId<FailureProbabilityScore> failureProbabilityScoreService;
    @Autowired
    @Qualifier("failureConsequenceScoreService")
    private CrudCommonServiceWithStringId<FailureConsequenceScore> failureConsequenceScoreService;

    @Autowired
    UnitTypePTLService unitTypePTLService;

    private static int intId;
    private static String stringId;
    private static int unitTypeId;
    private static int unitTypeId2;
    private static int failureModeId;// is used for creating all failure's entities
    private static int failureModeIdWithUnitTypes;// is used for creating all failure's entities
    private static FailureMode fmSavedMemored;
    private static FailureConsequence fcSaveddMemored;
    private static OccurrenceScoreScale ossSavedMemored;
    private static Set<Integer> categoryTypeIdsSet;

    //    FailureDetectionMethod Section --------------------------------------------------- //
/*    @Test
    public void n11SaveFailureDetectionMethod() throws PhmException {
        FailureDetectionMethod fdm = failureDetectionMethodService.save(new FailureDetectionMethod("fdm_name", "fdm_descr"));
        intId = fdm.getId();
        System.out.println(intId);
        assertNotNull("error saving object", fdm);
    }

    @Test
    public void n12Read() throws PhmException {
        FailureDetectionMethod fdm = failureDetectionMethodService.getById(intId);
        assertNotNull("error reading object", fdm);
    }

    @Test
    public void n13ReadAll() throws PhmException {
        FailureDetectionMethod fdm = failureDetectionMethodService.save(new FailureDetectionMethod("fdm_else", "fdm_else_descr"));
        intId = fdm.getId();
        assertTrue(failureDetectionMethodService.getAll().size() == 2, "error reading all objects");
    }

    @Test
    public void n14Delete() throws PhmException {
        FailureDetectionMethod fdm = failureDetectionMethodService.save(new FailureDetectionMethod("fdm_for_delete", "fdm_for_delete_descr"));
        failureDetectionMethodService.deleteById(intId);
        assertTrue(failureDetectionMethodService.getAll().size() == 2, "error reading all objects");
    }*/

    //    FailureMode Section --------------------------------------------------- //
    /*
    @Test
    public void n21Save_FailureMode() throws PhmException {
        unitTypeId = unitTypePTLService.save(new UnitTypePTL("type1", null, null, "test_user", null)).getId();
        unitTypeId2 = unitTypePTLService.save(new UnitTypePTL("type2", null, null, "test_user", null)).getId();
//        FailureMode fmSaved = failureModeService.save(new FailureMode(unitTypeId, "zzz", "fm_name", "fm_descr"));
        categoryTypeIdsSet = new HashSet<>();
        categoryTypeIdsSet.add(unitTypeId);
        categoryTypeIdsSet.add(unitTypeId2);
        FailureMode fmSaved = failureModeService.save(new FailureMode(categoryTypeIdsSet, "zzz", "fm_name", "fm_descr"));

        failureModeIdWithUnitTypes = fmSaved.getId();
        assertNotNull("error saving object", fmSaved);
    }*/

    @Test
    public void n22SaveWitoutUnitType() throws PhmException {
        FailureMode fmSaved = failureModeService.save(new FailureMode(null, "zzz", "fm_name_WitoutUnitType", "fm_descr"));
        failureModeId = fmSaved.getId();
        fmSavedMemored = fmSaved;
        assertNotNull("error saving object", fmSaved);
    }

    @Test
    public void n23Patch() throws PhmException {
        fmSavedMemored.setName(fmSavedMemored.getName() + "_Patched");
        FailureMode fmSaved = failureModeService.save(fmSavedMemored);
        failureModeId = fmSaved.getId();
        System.out.println(fmSaved.getName());
        assertNotNull("error saving object", fmSaved);
    }

    @Test
    public void n24Read() throws PhmException {
        FailureMode fm = failureModeService.getById(failureModeIdWithUnitTypes);
//        assertNotNull("error reading object", fm);
        Set<Integer> categoryTypeIdsSetForCheck = new HashSet<>();
        categoryTypeIdsSetForCheck.add(1);
        categoryTypeIdsSetForCheck.add(2);
        assertEquals(fm.getCategoryTypeIdsSet(), categoryTypeIdsSetForCheck);
    }

    @Test
    public void n25ReadAll() throws PhmException {
        FailureMode fdm = failureModeService.save(new FailureMode(categoryTypeIdsSet, "zzz", "fm_else_name", "fm_else_descr"));
        intId = fdm.getId();
//        assertTrue(failureModeService.getAll().size() == 3, "error reading all objects");
        assertEquals(failureModeService.getAll().size(), 3);
    }

    @Test
    public void n26Delete() throws PhmException {
        FailureMode fdm = failureModeService.save(new FailureMode(categoryTypeIdsSet, "zzz", "fm_for_delete_name", "fm__for_delete_descr"));
        failureModeService.deleteById(intId);
//        assertTrue(failureModeService.getAll().size() == 2, "error reading all objects");
        assertEquals(failureModeService.getAll().size(), 3);
    }

    @Test
    public void n27FindAllByNameAfter() throws PhmException {
//        return failureModeService.findAllByNameAfter("fm_name");
        List<FailureMode> failureModesByName = new ArrayList();
        failureModesByName = failureModeService.findAllByName("fm_name");
        assertEquals(failureModesByName.size(), 2);
    }

    @Test
    public void n28FindByCategoryTypeId() throws PhmException {
//        return failureModeService.findAllByNameAfter("fm_name");
        List<FailureMode> failureModesByType = new ArrayList();
        Set<Integer> dataSet = new HashSet();
        dataSet.add(1);
        failureModesByType = failureModeService.findByCategoryTypeId(1);
        assertEquals(failureModesByType.size(), 2);
    }


/*
    //    FailureConsequence Section --------------------------------------------------- //
    @Test
    public void n31Save_FailureConsequence() throws PhmException {
        System.out.println("failureModeId: " + failureModeId);
        FailureConsequence fcSaved = failureConsequenceService.save(new FailureConsequence(failureModeId, "zzz", "fc_name"));
        fcSaveddMemored = fcSaved;
        intId = fcSaved.getId();
        System.out.println(intId);
        System.out.println(fcSaved.getFailureMode().getName());
        assertNotNull("error saving object", fcSaved);
    }

    @Test
    public void n32Patch() throws PhmException {
        fcSaveddMemored.setName(fcSaveddMemored.getName() + "_Patched");
        FailureConsequence fcSaved = failureConsequenceService.save(fcSaveddMemored);
        intId = fcSaved.getId();
        System.out.println(intId);
        System.out.println(fcSaved.getFailureMode().getName());
//        assertNotNull("error saving object", fcSaved);
        assertEquals(fcSaved.getName(), "fc_name_Patched");
    }

    @Test
    public void n33Read() throws PhmException {
        FailureConsequence fc = failureConsequenceService.getById(intId);
        assertNotNull("error reading object", fc);
    }

    @Test
    public void n34ReadAll() throws PhmException {
        FailureConsequence fdm = failureConsequenceService.save(new FailureConsequence(failureModeId, "zzz", "fc_else_name"));
        intId = fdm.getId();
        assertTrue(failureConsequenceService.getAll().size() == 2, "error reading all objects");
    }

    @Test
    public void n35Delete() throws PhmException {
        FailureConsequence fdm = failureConsequenceService.save(new FailureConsequence(failureModeId, "zzz", "fc_for_del_name"));
        failureConsequenceService.deleteById(intId);
        assertTrue(failureConsequenceService.getAll().size() == 2, "error reading all objects");
    }

    //    FailureReason Section --------------------------------------------------- //
    @Test
    public void n41SaveFailureReason() throws PhmException {
        System.out.println("failureModeId: " + failureModeId);
        FailureReason frSaved = failureReasonService.save(new FailureReason(failureModeId, "zzz", "fr_name"));
        intId = frSaved.getId();
        System.out.println(intId);
        assertNotNull("error saving object", frSaved);
    }

    @Test
    public void n42Read() throws PhmException {
        FailureReason fc = failureReasonService.getById(intId);
        assertNotNull("error reading object", fc);
    }

    @Test
    public void n43ReadAll() throws PhmException {
        FailureReason fdm = failureReasonService.save(new FailureReason(failureModeId, "zzz", "fc_else_name"));
        intId = fdm.getId();
        assertTrue(failureReasonService.getAll().size() == 2, "error reading all objects");
    }

    @Test
    public void n44Delete() throws PhmException {
        FailureReason fdm = failureReasonService.save(new FailureReason(failureModeId, "zzz", "fc_for_del_name"));
        failureReasonService.deleteById(intId);
        assertTrue(failureConsequenceService.getAll().size() == 2, "error reading all objects");
    }

    //    FailureSeverity Section --------------------------------------------------- //
    @Test
    public void n51SaveFailureSeverity() throws PhmException {
        FailureSeverity fsSaved = failureSeverityService.save(new FailureSeverity("IV", "Catastrophic",
                "Failure, which quickly and with a high probability can lead to significant damage to " +
                        "the object itself and / or the environment, death or serious injury of people, disruption of the task"));
        stringId = fsSaved.getId();
        System.out.println(intId);
        assertNotNull("error saving object", fsSaved);
    }

    @Test
    public void n52Read() throws PhmException {
        FailureSeverity fs = failureSeverityService.getById(stringId);
        assertNotNull("error reading object", fs);
    }

    @Test
    public void n53ReadAll() throws PhmException {
        FailureSeverity fs = failureSeverityService.save(new FailureSeverity("III A", "Critical",
                "Failure, which quickly and with a high probability can cause significant damage to the " +
                        "facility itself and / or to the environment, disrupting the task being performed, but creates " +
                        "negligible threat to human life and health"));
        stringId = fs.getId();
        assertTrue(failureSeverityService.getAll().size() == 2, "error reading all objects");
    }

    @Test
    public void n54Delete() throws PhmException {
        FailureSeverity fs = failureSeverityService.save(new FailureSeverity("II", "Insignificant",
                "Failure that may lead to a decrease in the quality of the functioning of the facility, " +
                        "but does not pose a danger to the environment, the facility itself and human health"));
        failureSeverityService.deleteById(stringId);
        assertTrue(failureSeverityService.getAll().size() == 2, "error reading all objects");
    }

    //    DetectionScoreScale Section --------------------------------------------------- //
    @Test
    public void n61SaveDetectionScoreScale() throws PhmException {
        DetectionScoreScale dssSaved = detectionScoreScaleService.save(new DetectionScoreScale("Almost certainly",
                "Almost certainly The projected action (control) will almost certainly reveal a " +
                        "potential cause and subsequent type of defect.", 1));
        stringId = dssSaved.getId();
        System.out.println(intId);
        assertNotNull("error saving object", dssSaved);
    }

    @Test
    public void n62Read() throws PhmException {
        DetectionScoreScale dss = detectionScoreScaleService.getById(stringId);
        assertNotNull("error reading object", dss);
    }

    @Test
    public void n63ReadAll() throws PhmException {
        DetectionScoreScale dss = detectionScoreScaleService.save(new DetectionScoreScale("Very good",
                "Very high odds", 2));
        stringId = dss.getId();
        assertTrue(detectionScoreScaleService.getAll().size() == 2, "error reading all objects");
    }

    @Test
    public void n64Delete() throws PhmException {
        DetectionScoreScale dssSaved = detectionScoreScaleService.save(new DetectionScoreScale("Good",
                "High chances", 3));
        detectionScoreScaleService.deleteById(stringId);
        assertTrue(detectionScoreScaleService.getAll().size() == 2, "error reading all objects");
    }

    //    FailureProbabilityScore Section --------------------------------------------------- //
    @Test
    public void n81SaveFailureProbabilityScore() throws PhmException {
        FailureProbabilityScore fpsSaved =
                failureProbabilityScoreService.save(new FailureProbabilityScore("Failure is almost unbelievabley",
                        "from 0.00005", 1));
        stringId = fpsSaved.getId();
        System.out.println(intId);
        assertNotNull("error saving object", fpsSaved);
    }

    @Test
    public void n82Read() throws PhmException {
        FailureProbabilityScore dss = failureProbabilityScoreService.getById(stringId);
        assertNotNull("error reading object", dss);
    }

    @Test
    public void n83ReadAll() throws PhmException {
        FailureProbabilityScore dss =
                failureProbabilityScoreService.save(new FailureProbabilityScore("Failure unlikely",
                        "from 0.00005 to 0.0001", 2));
        stringId = dss.getId();
//        assertTrue(failureProbabilityScoreService.getAll().size() == 2, "error reading all objects");
        assertEquals(failureProbabilityScoreService.getAll().size(), 2);
    }

    @Test
    public void n84Delete() throws PhmException {
        FailureProbabilityScore dssSaved =
                failureProbabilityScoreService.save(new FailureProbabilityScore(
                        "Failure has a low probability, due only to the accuracy of the calculation",
                        " from 0.001 to 0.005", 3));
        failureProbabilityScoreService.deleteById(stringId);
//        assertTrue(failureProbabilityScoreService.getAll().size() == 2, "error reading all objects");
        assertEquals(failureProbabilityScoreService.getAll().size(), 2);
    }

    //TODO Fix Late
    //    OccurrenceScoreScale Section --------------------------------------------------- //
    @Test
    public void n71Save_OccurrenceScoreScale() throws PhmException {
        OccurrenceScoreScale ossSaved = occurrenceScoreScaleService.save(new OccurrenceScoreScale("Almost certainly",
                "Almost certainly The projected action (control) will almost certainly reveal a " +
                        "potential cause and subsequent type of defect.", 1));
        ossSavedMemored = ossSaved;
        stringId = ossSaved.getId();
        System.out.println(intId);
        assertNotNull("error saving object", ossSaved);
    }

    @Test
    public void n72Patch() throws PhmException {
        ossSavedMemored.setDefectFrequence(ossSavedMemored.getDefectFrequence()+"_Patched");
        ossSavedMemored.setOccurrenceScore(10);
        OccurrenceScoreScale ossSaved = occurrenceScoreScaleService.save(ossSavedMemored);
        assertNotNull("error reading object", ossSaved);
    }

    @Test
    public void n73Read() throws PhmException {
        OccurrenceScoreScale oss = occurrenceScoreScaleService.getById(stringId);
        assertNotNull("error reading object", oss);
        assertEquals(occurrenceScoreScaleService.getById(stringId).getDefectFrequence(),
                "Almost certainly The projected action (control) will almost certainly reveal a " +
                "potential cause and subsequent type of defect._Patched");
    }

    @Test
    public void n74ReadAll() throws PhmException {
        OccurrenceScoreScale oss = occurrenceScoreScaleService.save(new OccurrenceScoreScale("Very good",
                "Very high odds", 2));
        stringId = oss.getId();
        assertTrue(occurrenceScoreScaleService.getAll().size() == 2, "error reading all objects");
    }

    @Test
    public void n75Delete() throws PhmException {
        OccurrenceScoreScale ossSaved = occurrenceScoreScaleService.save(new OccurrenceScoreScale("Good",
                "High chances", 3));
        occurrenceScoreScaleService.deleteById(stringId);
        assertTrue(occurrenceScoreScaleService.getAll().size() == 2, "error reading all objects");
    }

    //    FailureConsequenceScore Section --------------------------------------------------- //
    @Test
    public void n91SaveFailureConsequenceScore() throws PhmException {
        FailureConsequenceScore fcsSaved =
                failureConsequenceScoreService.save(new FailureConsequenceScore(
                        "Failure does not lead to noticeable consequences, the consumer probably will not detect the presence of a malfunction",
                        "1"));
        stringId = fcsSaved.getId();
        System.out.println(intId);
        assertNotNull("error saving object", fcsSaved);
    }

    @Test
    public void n92Read() throws PhmException {
        FailureConsequenceScore fcs = failureConsequenceScoreService.getById(stringId);
        assertNotNull("error reading object", fcs);
    }

    @Test
    public void n93ReadAll() throws PhmException {
        FailureConsequenceScore fcs =
                failureConsequenceScoreService.save(new FailureConsequenceScore("The consequences of refusal are minor, but the consumer may express dissatisfaction with its appearance.",
                        "2-3"));
        stringId = fcs.getId();
//        assertTrue(failureProbabilityScoreService.getAll().size() == 2, "error reading all objects");
        assertEquals(failureConsequenceScoreService.getAll().size(), 2);
    }

    @Test
    public void n94Delete() throws PhmException {
        FailureConsequenceScore fcsSaved =
                failureConsequenceScoreService.save(new FailureConsequenceScore(
                        "Failure leads to a noticeable decrease in performance for the consumer and / or to the inconvenience of using the product",
                        "4-6"));
        failureConsequenceScoreService.deleteById(stringId);
//        assertTrue(failureProbabilityScoreService.getAll().size() == 2, "error reading all objects");
        assertEquals(failureConsequenceScoreService.getAll().size(), 2);
    }

    //    Recommendation Section --------------------------------------------------- //
    @Test
    public void o11Save_Recommendation() throws PhmException {
        Recommendation recSaved =
                recommendationService.save(new Recommendation(failureModeId, "RecomCode#1",
                        "Recommendation Text #1"));
        intId = recSaved.getId();
        System.out.println(intId);
        assertNotNull("error saving object", recSaved);
    }

    @Test
    public void o12Read() throws PhmException {
        Recommendation rec = recommendationService.getById(intId);
        assertNotNull("error reading object", rec);
    }

    @Test
    public void o13ReadAll() throws PhmException {
        Recommendation rec =
                recommendationService.save(new Recommendation(failureModeId, "RecomCode#2",
                        "Recommendation Text #2"));
        intId = rec.getId();
//        assertTrue(failureProbabilityScoreService.getAll().size() == 2, "error reading all objects");
        assertEquals(recommendationService.getAll().size(), 2);
    }

    @Test
    public void o14Delete() throws PhmException {
        Recommendation rec =
                recommendationService.save(new Recommendation(failureModeId, "RecomCode#2", "Recommendation Text #3"));
        recommendationService.deleteById(intId);
//        assertTrue(failureProbabilityScoreService.getAll().size() == 2, "error reading all objects");
        assertEquals(recommendationService.getAll().size(), 2);
    }

    //    SignificanceScoreScale Section --------------------------------------------------- //
    @Test
    public void o21SaveSignificanceScoreScale() throws PhmException {
        SignificanceScoreScale recSaved =
                significanceScoreScaleService.save(new SignificanceScoreScale("None", "No consequence", 1));
        stringId = recSaved.getId();
        System.out.println(intId);
        assertNotNull("error saving object", recSaved);
    }

    @Test
    public void o22Read() throws PhmException {
        SignificanceScoreScale rec = significanceScoreScaleService.getById(stringId);
        assertNotNull("error reading object", rec);
    }

    @Test
    public void o23ReadAll() throws PhmException {
        SignificanceScoreScale rec =
                significanceScoreScaleService.save(new SignificanceScoreScale("Very slight",
                        "The finish / noise of the product does not meet the customer's expectations. The defect is noticed by picky consumers", 2));
        stringId = rec.getId();
//        assertTrue(failureProbabilityScoreService.getAll().size() == 2, "error reading all objects");
        assertEquals(significanceScoreScaleService.getAll().size(), 2);
    }

    @Test
    public void o24Delete() throws PhmException {
        SignificanceScoreScale rec =
                significanceScoreScaleService.save(new SignificanceScoreScale("Slight",
                        "The finish noise of the product does not meet the customer's expectations. " +
                                "The defect is noticed by the average consumer", 3));
        significanceScoreScaleService.deleteById(stringId);
//        assertTrue(failureProbabilityScoreService.getAll().size() == 2, "error reading all objects");
        assertEquals(significanceScoreScaleService.getAll().size(), 2);
    }
*/

}
