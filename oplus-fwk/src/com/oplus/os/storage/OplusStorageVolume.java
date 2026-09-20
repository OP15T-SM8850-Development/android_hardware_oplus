package com.oplus.os.storage;

import android.os.storage.StorageVolume;

/**
 * Closure stub for the OEM com.oplus.os.storage.OplusStorageVolume.
 *
 * Hard-referenced (class-load + invoke) by OplusCamera and OppoGallery2:
 *   <init>(Landroid/os/storage/StorageVolume;)V
 *   getOplusReadOnlyType()I
 *
 * Ported from OOS16 oplus-framework.jar: a plain public class extending Object
 * (NOT a Parcelable / interface / enum). The three VOLUME_TYPE_* constants are
 * ported faithfully by value.
 *
 * The real getOplusReadOnlyType() delegates to
 * StorageVolume.mStorageVolumeExt.getReadOnlyType(), an OEM-only field/interface
 * absent on the AOSP StorageVolume. Dereferencing it would not compile against
 * the AOSP framework and could NPE at runtime, so the stub returns the safe
 * default VOLUME_TYPE_DEFAULT (0 = normal read/write, no restriction). This is
 * not a feature-enable query, so per the oracle the safe non-restricting default
 * is correct: no consumer is ever denied access and no linkage error can occur.
 */
public class OplusStorageVolume {
    public static final int VOLUME_TYPE_DEFAULT = 0;
    public static final int VOLUME_TYPE_READONLY_DIRECTLY = 1;
    public static final int VOLUME_TYPE_BLOCK_EXCEPTION = 2;

    private final StorageVolume mStorageVolume;

    public OplusStorageVolume(StorageVolume storageVolume) {
        mStorageVolume = storageVolume;
    }

    public int getOplusReadOnlyType() {
        return VOLUME_TYPE_DEFAULT;
    }
}
