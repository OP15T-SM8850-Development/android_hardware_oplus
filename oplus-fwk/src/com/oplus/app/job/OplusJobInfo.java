package com.oplus.app.job;

import android.app.job.JobInfo;

/* loaded from: classes.dex */
public class OplusJobInfo {
    public static final int PRIORITY_BACKGROUND = 0;
    public static final int PRIORITY_DEFAULT = 1;
    public static final int PRIORITY_USER_INITIALED = 2;
    public static final int PROTECT_FORE_FRAME = 0;
    public static final int PROTECT_FORE_NET = 1;
    public static final int SCENE_MODE_GAME = 4;
    public static final int SCENE_MODE_VIDEO = 1;
    public static final int SCENE_MODE_VIDEO_CALL = 2;
    public static final int WORK_TYPE_CPU = 1;
    public static final int WORK_TYPE_IO = 2;
    public static final int WORK_TYPE_NETWORK = 8;
    public static final int WORK_TYPE_NONE = 0;
    public static final int WORK_TYPE_RAM = 4;

    private OplusJobInfo() {
    }

    public static final class Builder {
        private JobInfo.Builder mBuilder;

        public Builder(JobInfo.Builder builder) {
            this.mBuilder = builder;
        }

        public void setPriority(int priority) {
        }

        public void setWorkType(int workType) {
        }

        public void setRequiresBattIdle(boolean requiresBattIdle) {
        }

        public boolean getRequiresBattIdle() {
            return false;
        }

        public void setRequiresChargingRestriction(boolean requiresChargingRestriction) {
        }

        public void setRequiresProtectFore(boolean requiresProtectFore) {
        }

        public void setRequiresProtectFore(boolean requiresProtectFore, int type) {
        }

        public boolean getRequiresProtectFore() {
            return false;
        }

        public void setRequiresProtectScene(boolean requiresProtectScene, int sceneMode) {
        }

        public boolean getRequiresProtectScene() {
            return false;
        }

        public void setHasCpuConstraint(boolean hasCpuConstraint) {
        }

        public boolean getHasCpuConstraint() {
            return false;
        }

        public void setHasTemperatureConstraint(boolean hasTemperatureConstraint) {
        }

        public boolean getHasTemperatureConstraint() {
            return false;
        }

        public void setExtraJob(boolean extraJob) {
        }

        public boolean isExtraJob() {
            return false;
        }

        public void setExtraStr(String extraStr) {
        }

        public String getExtraStr() {
            return null;
        }
    }
}
