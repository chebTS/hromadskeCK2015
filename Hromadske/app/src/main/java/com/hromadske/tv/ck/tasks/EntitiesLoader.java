package com.hromadske.tv.ck.tasks;

import android.content.Context;
import android.content.Loader;

/**
 * Created by cheb on 12/30/14.
 */
public class EntitiesLoader extends Loader<String> {
    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public EntitiesLoader(Context context) {
        super(context);
    }


}
