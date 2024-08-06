package com.example.admobs.adsContanier.bannerAds

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdmobBanner(modifier: Modifier){
    AndroidView(modifier = Modifier.padding(top = 50.dp, start = 15.dp), factory = {
        AdView(it).apply {

            setAdSize(AdSize.BANNER)
            adUnitId = "ca-app-pub-3940256099942544/9214589741"
            loadAd(AdRequest.Builder().build())

        }
    })

}