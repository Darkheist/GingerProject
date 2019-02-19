package com.example.projectkiva;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton mInstance = null;
    private RequestQueue mRQ;
    private ImageLoader mIL;

    private VolleySingleton(){
        mRQ = Volley.newRequestQueue(MainActivity.getAppContext());
        mIL = new ImageLoader(this.mRQ, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache <String, Bitmap>(10);
            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
        });
    }

    public static VolleySingleton getInstance(){
        if (mInstance == null){
            mInstance = new VolleySingleton();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRQ;
    }

    public ImageLoader getImageLoader(){
        return mIL;
    }

}
