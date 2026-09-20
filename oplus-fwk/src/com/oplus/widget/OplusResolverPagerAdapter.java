package com.oplus.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.CheckBox;

import java.util.List;

/**
 * Class-closure stub for com.oplus.widget.OplusResolverPagerAdapter.
 *
 * Real class (OOS):
 *   public class OplusResolverPagerAdapter extends OplusPagerAdapter
 *       implements com.oplus.resolver.IOplusResolverGridItemClickListener
 * The IOplusResolverGridItemClickListener interface is intentionally NOT
 * implemented here: our shipped OppoGallery2 never references that interface
 * (verified: zero refs in the gallery dex), so implementing it would only
 * enlarge the closure with an unreferenced companion type. The two click
 * callbacks are declared directly instead -- exactly the descriptors the
 * gallery invokes.
 *
 * Constructor descriptor matches the gallery reference verbatim:
 *   (Landroid/content/Context;Ljava/util/List;Ljava/util/List;ILandroid/content/Intent;Landroid/widget/CheckBox;Landroid/app/Dialog;Z)V
 * (raw List params -> Ljava/util/List; erasure, matching the invoke).
 */
public class OplusResolverPagerAdapter extends OplusPagerAdapter {

    public OplusResolverPagerAdapter(Context context, List resolveInfos, List targetInfos,
            int columnCount, Intent intent, CheckBox alwaysCheck, Dialog dialog,
            boolean isInMultiWindow) {
        super();
    }

    public void OnItemClick(int position) {
    }

    public void OnItemLongClick(int position) {
    }
}
