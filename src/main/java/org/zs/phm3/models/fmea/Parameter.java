package org.zs.phm3.models.fmea;

import org.zs.phm3.models.basic.IdStringEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fmea_parameters")
public class Parameter extends IdStringEntity {
/*
    RPN - Risk Priority Number
    S - significance score
    O is the score of occurrence
    D - detection score
    RPNMIN - lower bound
    RPNMAX - upper limit
    С - criticality of failure
    B1 - the score of the assessment of the probability of failure
    B2 - score for evaluating the consequences of failures
    B3 - score for the evaluation of failure detection
    Pm is the expected probability of failure
    PD - probability of failure detection
    */
private Integer riskPriorityNumber;
    private Integer significanceScore;
    private Integer scoreOfOccurrence;
    private Integer detectionScore;
    private Integer lowerBound;

    private Integer upperLimit;
    private Integer criticalityOfFailure;
    private Integer scoreAssessmentProbability;

}
