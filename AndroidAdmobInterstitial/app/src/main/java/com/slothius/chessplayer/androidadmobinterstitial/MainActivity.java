

package com.slothius.chessplayer.androidadmobinterstitial;
//written by Masaki Nakamura

/*
  step1: add "compile 'com.google.android.gms:play-services-ads:8.4.0"' in the graddle and sync it
  step2: in manifest add these lines:

      *out of <application

       <uses-permission android:name="android.permission.INTERNET" />
       <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

       *inside <application but outside <activity :

       <meta-data
            android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version" />


       * inside <application as an activity

            <activity
            android:name="com.google.android.gms.ads.AdActivity"
            android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"
            android:theme="@android:style/Theme.Translucent" />

   step 3:  the script of this page


*/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


//needed imports for the ads

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    Button button;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button1);  // declaring button1 from content_main


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");  //this number was given by Admob just as an example, u should change for yours

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                beginPlayingGame();
            }
        });

        requestNewInterstitial();


        button.setOnClickListener(new View.OnClickListener() {     //with this button the ad will show up and after that the 2nd activity.
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {     //if the ad is loaded show it, if not begin the game
                    mInterstitialAd.show();
                } else {
                    beginPlayingGame();    //this method is written below in this page
                }
            }
        });


    }


    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);

    }

    public void beginPlayingGame() {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }




}