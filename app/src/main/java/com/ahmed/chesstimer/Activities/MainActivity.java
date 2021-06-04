package com.ahmed.chesstimer.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ahmed.chesstimer.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private static final String AD_UNIT_ID = "ca-app-pub-7057161373684740/5012791329";

    private Button oneMinButton, fiveMinButton, tenMinButton, fifteenMinButton, twentyMinButton, thirtyMinButton;

    private AdView mAdView;
    private AdView mAdView2;
    private InterstitialAd mInterstitialAd;
    private  AdRequest adRequest;
    private AdRequest bannerRequest;
    private AdRequest bannerRequest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        oneMinButton = findViewById(R.id.oneMinButton);
        fiveMinButton = findViewById(R.id.fiveMinButton);
        tenMinButton = findViewById(R.id.tenMinButton);
        fifteenMinButton = findViewById(R.id.fifteenMinButton);
        twentyMinButton = findViewById(R.id.twentyMinButton);
        thirtyMinButton = findViewById(R.id.thirtyMinButton);

        //Initialize the Mobile Ads SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }

        });

        //Loading the Ads
        loadAd();


        oneMinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Using intent to move from MainActivity to OneMinActivity
                Intent intent = new Intent(MainActivity.this, OneMinActivity.class);
                startActivity(intent);

                //Show the Interstitial ad
                if (mInterstitialAd != null) {

                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d(TAG, "The interstitial ad wasn't ready yet.");
                }
            }
        });

        fiveMinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Using intent to move from MainActivity to FiveMinActivity
                Intent intent = new Intent(MainActivity.this, FiveMinActivity.class);
                startActivity(intent);

                //Show the Interstitial ad
                if (mInterstitialAd != null) {

                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d(TAG, "The interstitial ad wasn't ready yet.");
                }
            }
        });

        tenMinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Using intent to move from MainActivity to TenMinActivity
                Intent intent = new Intent(MainActivity.this, TenMinActivity.class);
                startActivity(intent);

                //Show the Interstitial ad
                if (mInterstitialAd != null) {

                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d(TAG, "The interstitial ad wasn't ready yet.");
                }
            }
        });

        fifteenMinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Using intent to move from MainActivity to FifteenMinActivity
                Intent intent = new Intent(MainActivity.this, FifteenMinActivity.class);
                startActivity(intent);

                //Show the Interstitial ad
                if (mInterstitialAd != null) {

                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d(TAG, "The interstitial ad wasn't ready yet.");
                }
            }
        });

        twentyMinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Using intent to move from MainActivity to TwentyMinActivity
                Intent intent = new Intent(MainActivity.this, TwentyMinActivity.class);
                startActivity(intent);

                //Show the Interstitial ad
                if (mInterstitialAd != null) {

                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d(TAG, "The interstitial ad wasn't ready yet.");
                }
            }
        });

        thirtyMinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Using intent to move from MainActivity to ThirtyMinActivity
                Intent intent = new Intent(MainActivity.this, ThirtyMinActivity.class);
                startActivity(intent);

                //Show the Interstitial ad
                if (mInterstitialAd != null) {

                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d(TAG, "The interstitial ad wasn't ready yet.");
                }
            }
        });


    }


    private void loadAd(){

        //Load a Banner ad
        mAdView = findViewById(R.id.adView);
        bannerRequest = new AdRequest.Builder().build();
        mAdView.loadAd(bannerRequest);


//        //Load a Banner ad
//        mAdView2 = findViewById(R.id.adView2);
//        bannerRequest2 = new AdRequest.Builder().build();
//        mAdView2.loadAd(bannerRequest2);


        adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(MainActivity.this, AD_UNIT_ID , adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i(TAG, "onAdLoaded");


                //Set the FullScreenContentCallback
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.d(TAG, "The ad was dismissed.");
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.d(TAG, "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d(TAG, "The ad was shown.");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i(TAG, loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });
    }
}