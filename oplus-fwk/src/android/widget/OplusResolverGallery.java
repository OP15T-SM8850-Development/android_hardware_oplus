package android.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Class-closure stub for OxygenOS android.widget.OplusResolverGallery.
 *
 * Real class (OOS system/framework/oplus-framework.jar) is a fork of
 * android.widget.Gallery:
 *   public class OplusResolverGallery extends android.widget.AbsSpinner
 *       implements android.view.GestureDetector.OnGestureListener
 * Consumer: OppoGallery2 (through its com.heytap.addon.widget.OplusResolverGallery
 * wrapper).
 *
 * Extending the real superclass AbsSpinner is REQUIRED so the following
 * consumer-referenced members resolve to genuine framework implementations
 * (a NoSuchMethodError / hard crash otherwise):
 *   setAdapter(SpinnerAdapter)                  -> AbsSpinner
 *   setSelection(int)                           -> AbsSpinner
 *   setOnItemClickListener(OnItemClickListener) -> AdapterView
 *   getChildCount() / getChildAt(int)           -> ViewGroup
 *   getLeft() / getRight() / getHeight()        -> View
 * Declared explicitly below (Gallery-specific, absent from AbsSpinner):
 *   setSpacing(int)
 *   setOnScrollListener(OnGalleryScrollListener)
 *
 * AbsSpinner supplies concrete implementations of every AdapterView abstract
 * method (getAdapter/setAdapter/getSelectedView/setSelection), so this direct
 * subclass is instantiable with only a constructor -- matching the real class,
 * which overrides none of them.
 */
public class OplusResolverGallery extends AbsSpinner {

    private int mSpacing;
    private OnGalleryScrollListener mOnScrollListener;

    public OplusResolverGallery(Context context) {
        this(context, null);
    }

    public OplusResolverGallery(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OplusResolverGallery(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public OplusResolverGallery(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setSpacing(int spacing) {
        mSpacing = spacing;
    }

    public void setOnScrollListener(OnGalleryScrollListener listener) {
        mOnScrollListener = listener;
    }

    /**
     * Real inner type:
     *   public interface OplusResolverGallery.OnGalleryScrollListener {
     *       void onScroll(OplusResolverGallery gallery);
     *   }
     * Compiles to android.widget.OplusResolverGallery$OnGalleryScrollListener,
     * exactly the descriptor the consumer references.
     */
    public interface OnGalleryScrollListener {
        void onScroll(OplusResolverGallery gallery);
    }

    void layout(int delta, boolean animate) {
    }
}
