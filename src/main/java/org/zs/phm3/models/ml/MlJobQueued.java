package org.zs.phm3.models.ml;

import java.util.ArrayDeque;

/**
 * Training task queue class.
 * @author Pavel Chuvak
 */
public class MlJobQueued {

    /** Ml job queue */
    private static ArrayDeque<MlJob> mlJobQueue = new ArrayDeque<>();

    /**
     * Gets ml job queue/
     * @return ml job queue
     */
    public static ArrayDeque<MlJob> getMlJobQueue() {
        return mlJobQueue;
    }
}
