package com.oplus.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Class-closure stub for com.oplus.widget.OplusResolverDialogViewPager.
 *
 * Real class (OOS): public class OplusResolverDialogViewPager extends OplusViewPager.
 * Instantiated by the gallery via <init>(Context, AttributeSet); every other
 * member the gallery invokes on it is inherited and needs no re-declaration:
 *   setAdapter / setCurrentItem / setDisableTouchEvent / setOnPageChangeListener
 *                                                       -> OplusViewPager
 *   getChildAt(int) / getChildCount()                  -> android.view.ViewGroup
 *   performHapticFeedback(int)                          -> android.view.View
 * Both constructors are provided so the (Context) super-chain is available too.
 */
public class OplusResolverDialogViewPager extends OplusViewPager {

    public OplusResolverDialogViewPager(Context context) {
        super(context);
    }

    public OplusResolverDialogViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
