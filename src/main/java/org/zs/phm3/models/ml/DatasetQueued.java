package org.zs.phm3.models.ml;

import java.util.ArrayDeque;

/**
 * Dataset creation queue class.
 * @author Pavel Chuvak
 */
public class DatasetQueued {

    /**
     * Dataset queue
     */
    private static ArrayDeque<Dataset> datasetJobs = new ArrayDeque<>();

    /**
     * Getting dataset queue
     * @return the dataset queue
     */
    public static ArrayDeque<Dataset> getDatasetJobs() {
        return datasetJobs;
    }
}
