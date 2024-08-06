package com.example.admobs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.admobs.adsContanier.bannerAds.AdmobBanner
import com.example.admobs.adsContanier.interstitialAds.interstitialAdsContainer
import com.example.admobs.adsContanier.rewardedAds.rewardedAdsContainer
import com.example.admobs.ui.theme.AdMobsTheme
import com.farimarwat.composenativeadmob.nativead.BannerAdAdmobMedium
import com.farimarwat.composenativeadmob.nativead.BannerAdAdmobSmall
import com.farimarwat.composenativeadmob.nativead.rememberNativeAdState
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.nativead.NativeAd

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MobileAds.initialize(this)
        setContent {

            val adState: NativeAd ? = rememberNativeAdState(
                context = this@MainActivity,
                adUnitId = "ca-app-pub-3940256099942544/2247696110"
            )

            AdMobsTheme {

                var loading by remember { mutableStateOf(false) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                        Column {

                            AdmobBanner(modifier = Modifier.padding(padding))

                            Spacer(modifier = Modifier.height(20.dp))

                            if (loading) {
                                CircularProgressIndicator(modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.CenterHorizontally))
                            }

                            Button(onClick = {

                                loading = true

                                interstitialAdsContainer(activity = this@MainActivity) {
                                    // This is a callback to be called when ad is closed or failed to load
                                    loading = false
                                }
                            }, modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 70.dp, end = 70.dp, top = 20.dp)) {
                                Text(text = "Show Interstitial Ad")
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(onClick = {

                                loading = true

                                rewardedAdsContainer(activity = this@MainActivity) {
                                    // This is a callback to be called when ad is closed or failed to load
                                    loading = false
                                }
                            }, modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 70.dp, end = 70.dp, top = 20.dp)) {
                                Text(text = "Show Rewarded Ad")
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            BannerAdAdmobSmall(loadedAd = adState)

                            Spacer(modifier = Modifier.height(20.dp))

                            BannerAdAdmobMedium(loadedAd = adState)

                        }
                    }
                }
            }
        }
    }
}
