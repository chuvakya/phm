package org.zs.phm3.models.dashboard;

import org.zs.phm3.models.ts.TsKvAttribute;

import javax.persistence.*;

/**
 * Widget attribute entity
 * @author Pavel Chuvak
 */
@Entity
public class WidgetAttribute {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Widget attribute name */
    private String name;

    /** Ts kv attribute */
    @ManyToOne
    private TsKvAttribute tsKvAttribute;

    /** Widget */
    @ManyToOne
    private Widget widget;

    /**
     * Constructor
     */
    public WidgetAttribute() {
    }

    /**
     * Constructor
     * @param tsKvAttribute ts kv attribute
     * @param widget widget
     * @param name attribute name
     */
    public WidgetAttribute(TsKvAttribute tsKvAttribute, Widget widget, String name) {
        this.tsKvAttribute = tsKvAttribute;
        this.widget = widget;
        this.name = name;
    }

    /**
     * Gets attribute name
     * @return attribute name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets attribute name
     * @param name attribute name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets ts kv attribute
     * @return ts kv attribute
     */
    public TsKvAttribute getTsKvAttribute() {
        return tsKvAttribute;
    }

    /**
     * Sets ts kv attribute
     * @param tsKvAttribute ts kv attribute
     */
    public void setTsKvAttribute(TsKvAttribute tsKvAttribute) {
        this.tsKvAttribute = tsKvAttribute;
    }

    /**
     * Gets widget
     * @return widget
     */
    public Widget getWidget() {
        return widget;
    }

    /**
     * Sets widget
     * @param widget widget
     */
    public void setWidget(Widget widget) {
        this.widget = widget;
    }
}
