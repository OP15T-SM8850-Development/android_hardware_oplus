package com.oplus.wrapper.os.storage;

public class StorageManager {
    private final android.os.storage.StorageManager mStorageManager;

    public StorageManager(android.os.storage.StorageManager storageManager) {
        this.mStorageManager = storageManager;
    }

    public android.os.storage.StorageVolume[] getVolumeList() {
        return new android.os.storage.StorageVolume[0];
    }
}
