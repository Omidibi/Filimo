package com.omid.filimo.ui.customView.customFonts

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.omid.filimo.utils.configuration.AppConfiguration

class BYekanPlusTextView: AppCompatTextView {

    constructor(context: Context?) : super(context!!) {
        extracted()
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context!!, attrs) {
        extracted()
    }

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        extracted()
    }

    private fun extracted() {
        Typeface.createFromAsset(AppConfiguration.getContext().assets, "fonts/BYekan+/BYekan+.ttf").apply {
            setTypeface(this@apply)
        }
    }
}