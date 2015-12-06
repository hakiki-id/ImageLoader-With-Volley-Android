package com.hakiki95.volleyimageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Network;
import android.util.LruCache;

import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by HAKIKI PC on 06/12/2015.
 */
public class CustomVolleyRequestQueue {

    private static CustomVolleyRequestQueue  mlstance ;
    private static Context mCtx;
    private RequestQueue mRequestQueue ;
    private ImageLoader  mImageLoader ;

    private  CustomVolleyRequestQueue (Context context) {
        mCtx = context;
        mRequestQueue  = getRequestQueue() ;

        mImageLoader = new ImageLoader( mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache =  new LruCache<String, Bitmap> (20) ;
                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                            cache.put(url, bitmap);
                    }

               } );
    }

    public static synchronized CustomVolleyRequestQueue getInstance (Context context) {

        if (mlstance == null) {
            mlstance = new CustomVolleyRequestQueue(context);
        }

        return  mlstance;
    }

    public RequestQueue getRequestQueue () {
        if (mRequestQueue == null ) {
            Cache cache = new DiskBasedCache(mCtx.getCacheDir(), 10 * 1024 * 1024 ) ;
            com.android.volley.Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache,network);

            //start volley reques nya
            mRequestQueue.start();

        }

        return mRequestQueue;
    }



    public  ImageLoader getImageLoader() {

        return  mImageLoader ;
    }
}
