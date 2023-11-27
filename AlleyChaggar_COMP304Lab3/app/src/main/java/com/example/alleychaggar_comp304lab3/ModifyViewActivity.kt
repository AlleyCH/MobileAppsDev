package com.example.alleychaggar_comp304lab3
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.text.SimpleDateFormat
import java.util.*

class ModifyViewActivity : AppCompatTextView {
    private var longDate = false

    constructor(context: Context?) : super(context!!) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            // Retrieve custom attribute 'longDate'
            longDate = attrs.getAttributeBooleanValue("http://schemas.android.com/apk/res-auto", "longDate", false)        }
        updateText()
    }

    fun setLongDate(longDate: Boolean) {
        this.longDate = longDate
        updateText()
    }

    private fun updateText() {
        // Get the current date
        val calendar = Calendar.getInstance()

        // Format the date based on the 'longDate' attribute
        val dateFormat: SimpleDateFormat = if (longDate) {
            SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())
        } else {
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        }
        val formattedDate = dateFormat.format(calendar.time)

        // Set the formatted date as the text for the TextView
        text = formattedDate
    }
}