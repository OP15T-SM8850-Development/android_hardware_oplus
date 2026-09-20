package com.oplus.wrapper.view;

public class DragEvent {
    private final android.view.DragEvent mDragEvent;

    public DragEvent(android.view.DragEvent dragEvent) {
        this.mDragEvent = dragEvent;
    }

    public android.view.SurfaceControl getDragSurface() {
        return null;
    }
}
