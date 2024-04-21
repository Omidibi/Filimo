package com.omid.filimo.utils.progressBarStatus

import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.omid.filimo.R
import com.omid.filimo.utils.configuration.AppConfiguration

class ProgressBarStatus {

    companion object {

        fun pbStatus(progressBar: ProgressBar){
            val wrapDrawable = DrawableCompat.wrap(progressBar.indeterminateDrawable)
            DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(AppConfiguration.getContext(), R.color.sunGlow))
            progressBar.indeterminateDrawable = DrawableCompat.unwrap(wrapDrawable)
        }
    }
}