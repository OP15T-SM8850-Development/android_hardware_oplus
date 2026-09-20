package com.oplus.wrapper.graphics;

public class BitmapFactory {
    public static class Options {
        private final android.graphics.BitmapFactory.Options mOptions;

        public Options(android.graphics.BitmapFactory.Options options) {
            this.mOptions = options;
        }

        public void setInPostProc(boolean inPostProc) {
        }
    }
}
