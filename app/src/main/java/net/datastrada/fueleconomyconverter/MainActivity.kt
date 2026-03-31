package net.datastrada.fueleconomyconverter

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.iterator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun convertFE(view: View) {
        var inputValue = findViewById<EditText>(R.id.inputValue).text.toString()
        var inputDouble: Double = inputValue.toDouble()

        var inputRadioGroup = findViewById<RadioGroup>(R.id.radioFromGroup)
        var inputSelection = findViewById<RadioButton>(inputRadioGroup.checkedRadioButtonId)

        var usMpgResult: Double? = null
        var impMpgResult: Double? = null
        var kplResult: Double? = null
        var lP100KmResult: Double? = null

        val usMpgResultText = findViewById<TextView>(R.id.usMpgResultText)
        val imperialMpgResultText = findViewById<TextView>(R.id.imperialMpgResultText)
        val kplResultText = findViewById<TextView>(R.id.kplResultText)
        val lP100KmResultText = findViewById<TextView>(R.id.lP100KmResultText)

        // From US MPG
        if (inputSelection == findViewById(R.id.fromMpgUS)) {
            // to US MPG
            usMpgResult = inputDouble

            // to Imperial MPG
            impMpgResult =  inputDouble * 1.2009499241541

            // to KPL
            kplResult = inputDouble * 0.425

            // to L/100 KM
            lP100KmResult = 100/((inputDouble * 1.609) / 3.785)

        // From Imperial MPG
        } else if (inputSelection == findViewById(R.id.fromMpgImperial)) {
            //To US MPG
            usMpgResult = inputDouble / 1.2009499241541

            // to Imperial MPG
            impMpgResult =  inputDouble

            // to KPL
            kplResult = inputDouble * 0.354

            // to L/100 KM
            lP100KmResult = 100 /((inputDouble * 1.609) / 4.546)

        // From KPL
        } else if (inputSelection == findViewById(R.id.fromKPL)) {
            //To US MPG
            usMpgResult = inputDouble * 2.352

            // to Imperial MPG
            impMpgResult =  inputDouble * 2.825

            // to KPL
            kplResult = inputDouble

            // to L/100 KM
            lP100KmResult = 100 / inputDouble


        // from L/100 KM
        } else if (inputSelection == findViewById(R.id.fromLP100KM)) {
            //To US MPG
            usMpgResult = (3.785 * (100 / inputDouble)) / 1.609

            // to Imperial MPG
            impMpgResult =  (4.546 * (100 / inputDouble)) / 1.609

            // to KPL
            kplResult = 100 / inputDouble

            // to L/100 KM
            lP100KmResult = inputDouble

        }

        usMpgResultText.text = usMpgResult.toString()
        imperialMpgResultText.text = impMpgResult.toString()
        kplResultText.text = kplResult.toString()
        lP100KmResultText.text = lP100KmResult.toString()

    }
}