package com.hromadske.tv.ck.application;

import android.app.Application;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.utils.SystemUtils;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import static com.hromadske.tv.ck.utils.SystemUtils.*;

/**
 * Created by cheb on 12/27/14.
 */
public class HromadskeCkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    private void initImageLoader() {
        if (IMAGELOADER == null) {
            IMAGELOADER = ImageLoader.getInstance();
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .showImageForEmptyUri(R.drawable.ic_launcher)
                    .showImageOnFail(R.drawable.ic_launcher)
                    .showImageOnLoading(R.drawable.ic_launcher)
                    .build();
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                    .diskCacheExtraOptions(1024, 1024, null)//CompressFormat.PNG, 75
                    .denyCacheImageMultipleSizesInMemory()
                    .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                    .memoryCacheSize(2 * 1024 * 1024)
                    .diskCacheSize(50 * 1024 * 1024)
                    .diskCacheFileCount(100)
                    .defaultDisplayImageOptions(options)
                    .build();
            IMAGELOADER.init(config);

        }
    }
}
