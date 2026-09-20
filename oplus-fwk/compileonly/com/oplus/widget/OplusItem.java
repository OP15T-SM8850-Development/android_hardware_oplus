package com.oplus.widget;

/**
 * COMPILE-ONLY companion -- DO NOT PACKAGE INTO oplus-fwk.jar (boot classpath).
 *
 * The real com.oplus.widget.OplusItem (plus its inner $Builder and $a) is
 * DEFINED BY OppoGallery2.apk itself. This placeholder exists solely so that
 * OplusGridView.setAppInfo(OplusItem[][]) compiles to the exact runtime
 * descriptor [[Lcom/oplus/widget/OplusItem;. Ship this class on the boot
 * classpath and it would shadow the gallery`s own OplusItem, breaking its field
 * access (mBackground/mContext/mIcon/mLabel/mText/mOnItemClickListener) with
 * NoSuchFieldError. Add it to the framework module as a compile-only (libs / not
 * static_libs) dependency, or otherwise exclude com/oplus/widget/OplusItem.class
 * from the final jar/dex.
 */
public class OplusItem {
}
