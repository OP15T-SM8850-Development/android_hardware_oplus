package com.oplus.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Class-closure stub for com.oplus.widget.OplusGridView.
 *
 * Real class (OOS): public class OplusGridView extends android.view.View.
 * Gallery references: <init>(Context) and setAppInfo([[Lcom/oplus/widget/OplusItem;)V.
 *
 * NOTE ON OplusItem: com.oplus.widget.OplusItem is DEFINED BY OppoGallery2.apk
 * itself (self-defined; also has inner $Builder and $a). It MUST NOT be shipped
 * on the boot classpath -- doing so would shadow the gallery-local class and
 * NoSuchFieldError on its mBackground/mIcon/... fields. Therefore OplusItem is a
 * COMPILE-ONLY companion for this file: it is only needed so javac emits the
 * exact descriptor [[Lcom/oplus/widget/OplusItem;. At runtime setAppInfo is a
 * no-op that never touches the array elements, so OplusItem is never loaded from
 * the boot classpath; the gallery-side invoke resolves OplusItem against the
 * gallery`s own classloader. Keep any compile-only companions repo-local.
 */
public class OplusGridView extends View {

    public OplusGridView(Context context) {
        super(context);
    }

    public OplusGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OplusGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAppInfo(OplusItem[][] appInfo) {
    }
}
