package com.oplus.widget;

/**
 * Class-closure stub for com.oplus.widget.OplusPagerAdapter.
 *
 * Real class (OOS): public abstract class OplusPagerAdapter (extends Object),
 * a PagerAdapter-style base. Referenced by the gallery as the parameter type of
 * OplusViewPager.setAdapter(Lcom/oplus/widget/OplusPagerAdapter;)V and as the
 * .super of OplusResolverPagerAdapter.
 *
 * Kept as an abstract class with NO abstract methods: the gallery never invokes
 * adapter callbacks on it directly (our OplusViewPager stub is a no-op that
 * never drives the adapter), so a bare instantiable-by-subclass base is the
 * minimal, AbstractMethodError-proof closure.
 */
public abstract class OplusPagerAdapter {

    public OplusPagerAdapter() {
    }
}
