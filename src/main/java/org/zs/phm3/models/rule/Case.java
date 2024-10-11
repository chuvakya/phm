package org.zs.phm3.models.rule;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.fault.Fault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is responsible for the case inside the rule
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "rule_case")
public class Case {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Expressions type */
    @Enumerated(EnumType.STRING)
    private ExpressionsType expressionType;

    /** Faults */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL)
    private List<Fault> faults = new ArrayList<>();

    /** Conditions */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL)
    private List<Condition> conditions = new ArrayList<>();

    /** Rule */
    @ManyToOne
    private Rule rule;

    /** Actions */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL)
    private List<Action> actions = new ArrayList<>();

    /** Constructor */
    public Case() {
    }

    /**
     * Constructor
     * @param expressionType expressions type
     * @param rule rule
     */
    public Case(ExpressionsType expressionType, Rule rule) {
        this.expressionType = expressionType;
        this.rule = rule;
    }

    /**
     * Gets faults
     * @return faults
     */
    public List<Fault> getFaults() {
        return faults;
    }

    /**
     * Sets faults
     * @param faults faults
     */
    public void setFaults(List<Fault> faults) {
        this.faults = faults;
    }

    /**
     * Gets ID
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets expressions type
     * @return expressions type
     */
    public ExpressionsType getExpressionType() {
        return expressionType;
    }

    /**
     * Sets expressions type
     * @param expressionType expressions type
     */
    public void setExpressionType(ExpressionsType expressionType) {
        this.expressionType = expressionType;
    }

    /**
     * Gets conditions
     * @return conditions
     */
    public List<Condition> getConditions() {
        return conditions;
    }

    /**
     * Sets conditions
     * @param conditions conditions
     */
    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    /**
     * Gets rule
     * @return rule
     */
    public Rule getRule() {
        return rule;
    }

    /**
     * Sets rule
     * @param rule rule
     */
    public void setRule(Rule rule) {
        this.rule = rule;
    }

    /**
     * Gets actions
     * @return actions
     */
    public List<Action> getActions() {
        return actions;
    }

    /**
     * Sets actions
     * @param actions actions
     */
    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
