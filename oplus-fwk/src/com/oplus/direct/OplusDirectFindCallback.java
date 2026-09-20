package com.oplus.direct;

/**
 * Closure stub for the OEM abstract callback adapter subclassed by OppoGallery2.
 * Extends the AIDL Stub (Binder) so the subclass hierarchy loads/links; the
 * default onDirectInfoFound is a safe no-op (feature not exercised on this build).
 */
public abstract class OplusDirectFindCallback extends IOplusDirectFindCallback.Stub {

    public OplusDirectFindCallback() {
        super();
    }

    @Override
    public void onDirectInfoFound(OplusDirectFindResult result) {
    }
}
