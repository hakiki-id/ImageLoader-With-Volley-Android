package com.hakiki95.volleyimageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NetworkImageView mNetworkImageView;
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNetworkImageView = (NetworkImageView) findViewById(R.id.networkimageView);



    }

    @Override
    protected void onStart() {
        super.onStart();
        //Initializer Request RequesQueue


        mImageLoader = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getImageLoader();

        final String url = "https://goo.gl/iUE7he";
        mImageLoader.get(String.valueOf(url), ImageLoader.getImageListener(mNetworkImageView,R.mipmap.ic_launcher, R.drawable.alert));
        mNetworkImageView.setImageUrl(String.valueOf(url), mImageLoader);


    }
}
