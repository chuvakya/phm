package org.zs.phm3.models.init;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The type Init.
 * @author Pavel Chuvak
 */
@Entity
public class Init {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean isInitStartApp;

    /**
     * Instantiates a new Init.
     */
    public Init() {
    }

    /**
     * Instantiates a new Init.
     *
     * @param isInitStartApp the is init start app
     */
    public Init(Boolean isInitStartApp) {
        this.isInitStartApp = isInitStartApp;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets init start app.
     *
     * @return the init start app
     */
    public Boolean getInitStartApp() {
        return isInitStartApp;
    }

    /**
     * Sets init start app.
     *
     * @param initStartApp the init start app
     */
    public void setInitStartApp(Boolean initStartApp) {
        isInitStartApp = initStartApp;
    }
}
