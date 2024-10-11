package org.zs.phm3.models.ml;

/**
 * Helper class for operations with enumeration MlAlgorithm.
 * @author Pavel Chuvak
 */
public class MlAlgorithmType {

    /**
     * Displaying the name of the task type for a given algorithm.
     * @param algorithm algorithm
     * @return name of the task type
     */
    static public String getType(MlAlgorithm algorithm) {
        return switch (algorithm) {
            case SIX_SIGMA, THREE_SIGMA, A_LOF, A_IF -> "Anomaly detection";
            case F_KNN, F_MLP, F_DT, F_AB, F_SVM, F_RF, F_MLBOX, F_ENSEMBLE -> "Fault Detection";
            case R_KNN, R_LGBM, R_SVR, R_MLP, R_DT, R_RF, R_LR, R_ET, R_ADA, R_GB, R_GASVR, R_GASVR1, R_KERAS, R_ENSEMBLE, R_MLBOX -> "RUL prediction";
        };
    }

    /**
     * Displaying the number of the task type according to the specified algorithm for the ml service.
     * @param mlAlgorithm algorithm
     * @return number of the task type
     */
    static public Integer getTaskNumber(MlAlgorithm mlAlgorithm) {
        return switch (mlAlgorithm) {
            case SIX_SIGMA, THREE_SIGMA, A_LOF, A_IF -> 1;
            case F_KNN, F_MLP, F_DT, F_AB, F_SVM, F_RF, F_MLBOX, F_ENSEMBLE -> 2;
            case R_KNN, R_LGBM, R_SVR, R_MLP, R_DT, R_RF, R_LR, R_ET, R_ADA, R_GB, R_GASVR, R_GASVR1, R_KERAS, R_ENSEMBLE, R_MLBOX -> 3;
        };
    }

    /**
     * Obtaining the name of the model according to the given algorithm for the ml service (part of training).
     * @param algorithm algorithm
     * @return algorithm model name for ml service (train)
     */
    static public String getModelNameFromMlAlgorithm(MlAlgorithm algorithm) {
        return switch(algorithm) {
            case SIX_SIGMA -> "SIGMA-6";
            case THREE_SIGMA -> "SIGMA-3";
            case A_IF -> "IF";
            case A_LOF -> "LOF";
            case F_SVM -> "SVM";
            case F_DT -> "DT";
            case F_AB -> "AB";
            case F_KNN -> "KNN";
            case F_MLP -> "MLP";
            case F_RF -> "RF";
            case F_MLBOX -> "MLBOX";
            case F_ENSEMBLE -> "ENSEMBLE";
            case R_KNN -> "KNN";
            case R_LGBM -> "LGBM";
            case R_SVR -> "SVR";
            case R_MLP -> "MLP";
            case R_DT -> "DT";
            case R_RF -> "RF";
            case R_LR -> "LR";
            case R_ET -> "ET";
            case R_ADA -> "ADA";
            case R_GB -> "GB";
            case R_GASVR -> "GASVR";
            case R_GASVR1 -> "GASVR1";
            case R_KERAS -> "KERAS";
            case R_MLBOX -> "MLBOX";
            case R_ENSEMBLE -> "ENSEMBLE";
        };
    }

    /**
     * Returns the name of the algorithm by its code
     * @param algorithm algorithm
     * @return algorithm name abbr
     */
    static public String getAlgorithm(MlAlgorithm algorithm) {
        return switch (algorithm) {
            case SIX_SIGMA -> "6 Sigma";
            case THREE_SIGMA -> "3 Sigma";
            case A_IF, A_LOF -> algorithm.toString().replace("A_", "");
            case F_KNN, F_MLP, F_DT, F_AB, F_SVM, F_RF, F_MLBOX, F_ENSEMBLE -> algorithm.toString().replace("F_", "");
            case R_KNN, R_LGBM, R_SVR, R_MLP, R_DT, R_RF, R_LR, R_ET, R_ADA, R_GB, R_GASVR, R_GASVR1, R_KERAS, R_ENSEMBLE, R_MLBOX ->
                    algorithm.toString().replace("R_", "");
        };
    }

    /**
     * Return full name by ml type.
     * @param mlType algorithm type
     * @return full name by type
     */
    static public String getTypeName(MlType mlType) {
        return switch (mlType) {
            case ANOMALY -> "Anomaly detection";
            case FAULT -> "Fault Detection";
            case RUL -> "RUL prediction";
        };
    }

    /**
     * Return ml type by algorithm
     * @param algorithm algorithm
     * @return ml type
     */
    static public MlType getTypeFromAlgorithm(MlAlgorithm algorithm) {
        return switch(algorithm) {
            case SIX_SIGMA, THREE_SIGMA, A_LOF, A_IF -> MlType.ANOMALY;
            case F_KNN, F_MLP, F_DT, F_AB, F_SVM, F_RF, F_MLBOX, F_ENSEMBLE -> MlType.FAULT;
            case R_KNN, R_LGBM, R_SVR, R_MLP, R_DT, R_RF, R_LR, R_ET, R_ADA, R_GB, R_GASVR, R_GASVR1, R_KERAS,  R_ENSEMBLE, R_MLBOX -> MlType.RUL;
        };
    }

    /**
     * Return full name algorithm
     * @param algorithm algorithm
     * @return full name algorithm
     */
    static public String getFullNameAlgorithm(MlAlgorithm algorithm) {
        return switch(algorithm) {
            case SIX_SIGMA -> "6 Sigma";
            case THREE_SIGMA -> "3 Sigma";
            case A_IF -> "Isolation Forest";
            case A_LOF -> "Local Outlier Factor";
            case F_SVM -> "Support Vector Machine";
            case F_DT -> "Decision trees ";
            case R_KNN -> "KNeighbors Regressor";
            case R_LGBM -> "LGBM Regressor";
            case R_SVR -> "SVR";
            case R_MLP -> "MLP Regressor";
            case R_DT -> "Decision Tree Regressor";
            case R_RF -> "Random Forest Regressor";
            case R_LR -> "Linear Regression";
            case R_ET -> "Extra Trees Regressor";
            case R_ADA -> "Ada Boost Regressor";
            case R_GB -> "Gradient Boosting Regressor";
            default -> "other";
        };
    }
}
