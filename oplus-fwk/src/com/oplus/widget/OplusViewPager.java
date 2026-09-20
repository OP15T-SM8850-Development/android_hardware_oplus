package com.oplus.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Class-closure stub for com.oplus.widget.OplusViewPager.
 *
 * Real class (OOS system/framework/oplus-framework.jar):
 *   public class OplusViewPager extends android.view.ViewGroup
 * Not referenced directly by OppoGallery2, but pulled in TRANSITIVELY as the
 * superclass of OplusResolverDialogViewPager (its .super), so it must be
 * loadable or the subclass NoClassDefFoundErrors at load time.
 *
 * ViewGroup declares abstract onLayout(boolean,int,int,int,int); overriding it
 * is REQUIRED for this concrete class to be instantiable. The setter surface
 * below is the subset invoked (via the OplusResolverDialogViewPager subtype) by
 * the gallery: setAdapter/setCurrentItem/setDisableTouchEvent/
 * setOnPageChangeListener -- these resolve through the hierarchy on the subclass.
 */
public class OplusViewPager extends ViewGroup {

    public OplusViewPager(Context context) {
        super(context);
    }

    public OplusViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    public void setAdapter(OplusPagerAdapter adapter) {
    }

    public OplusPagerAdapter getAdapter() {
        return null;
    }

    public void setCurrentItem(int item) {
    }

    public int getCurrentItem() {
        return 0;
    }

    public void setDisableTouchEvent(boolean disable) {
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
    }

    /**
     * Real inner interface com.oplus.widget.OplusViewPager$OnPageChangeListener.
     * Compiles to exactly the descriptor the gallery references in
     * setOnPageChangeListener(Lcom/oplus/widget/OplusViewPager$OnPageChangeListener;)V.
     * Method set matches the OOS abstract declaration verbatim.
     */
    public interface OnPageChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }
}
