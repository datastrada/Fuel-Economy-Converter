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

        var resultDouble: Double? = null

        var inputRadioGroup = findViewById<RadioGroup>(R.id.radioFromGroup)
        var inputSelection = findViewById<RadioButton>(inputRadioGroup.checkedRadioButtonId)

        var outputRadioGroup = findViewById<RadioGroup>(R.id.radioToGroup)
        var outputSelection = findViewById<RadioButton>(outputRadioGroup.checkedRadioButtonId)

        if (inputSelection == findViewById(R.id.fromMpgUS)) {
            print("From US MPG")
            when (outputSelection) {
                findViewById(R.id.toMpgUS) -> resultDouble = inputDouble
                findViewById(R.id.toMpgImperial) -> resultDouble = convertUsMpgToImperialMpg(inputDouble)
                findViewById(R.id.toKPL) -> resultDouble = convertUsMpgToKPL(inputDouble)
                findViewById(R.id.toLP100KM) -> resultDouble = convertUsMpgToLP100KM(inputDouble)
                else -> println("nothing selected")
            }
        } else if (inputSelection == findViewById(R.id.fromMpgImperial)) {
            print("From Imperial MPG")
            when (outputSelection) {
                findViewById(R.id.toMpgUS) -> resultDouble = convertImperialMpgToUsMpg(inputDouble)
                findViewById(R.id.toMpgImperial) -> resultDouble = inputDouble
                findViewById(R.id.toKPL) -> resultDouble = convertImperialMpgToKpl(inputDouble)
                findViewById(R.id.toLP100KM) -> resultDouble = convertImperialMpgToLP100KM(inputDouble)
                else -> println("nothing selected")
            }
        } else if (inputSelection == findViewById(R.id.fromKPL)) {
            print("From KPL")
            when (outputSelection) {
                findViewById(R.id.toMpgUS) -> resultDouble = convertKplToUsMpg(inputDouble)
                findViewById(R.id.toMpgImperial) -> resultDouble = convertKplToImperialMpg(inputDouble)
                findViewById(R.id.toKPL) -> resultDouble = inputDouble
                findViewById(R.id.toLP100KM) -> resultDouble = convertKplToLP100KM(inputDouble)
                else -> println("nothing selected")
            }
        } else if (inputSelection == findViewById(R.id.fromLP100KM)) {
            print("From L/100 KM")
            when (outputSelection) {
                findViewById(R.id.toMpgUS) -> resultDouble = convertLp100KmToUsMpg(inputDouble)
                findViewById(R.id.toMpgImperial) -> resultDouble = convertLp100KmToImperialMpg(inputDouble)
                findViewById(R.id.toKPL) -> resultDouble = convertLp100KmToKpl(inputDouble)
                findViewById(R.id.toLP100KM) -> resultDouble = inputDouble
                else -> println("No output value selected.")
            }
        } else {
            println("No input selected.")
        }

        if (resultDouble != null) {
            val resultString: String = String.format("%.2f", resultDouble)
            findViewById<TextView>(R.id.resultTextView).text = resultString
        }
    }



    /*******************************************************************************
    * From US MPG
    *******************************************************************************/
    // To L/100 KM
    private fun convertUsMpgToLP100KM(inputDouble: Double): Double? {
        var result = 100/((inputDouble * 1.609) / 3.785)
        return result
    }

    // to KPL
    private fun convertUsMpgToKPL(inputDouble: Double): Double? {
        var result = inputDouble * 0.425
        return result
    }

    // to Imperial MPG
    private fun convertUsMpgToImperialMpg(inputDouble: Double): Double {
        var result = inputDouble * 1.2009499241541
        return result
    }

    /*******************************************************************************
     * From Imperial MPG
     *******************************************************************************/
    private fun convertImperialMpgToKpl(inputDouble: Double): Double? {
        var result = inputDouble * 0.354
        return result
    }

    private fun convertImperialMpgToLP100KM(inputDouble: Double): Double? {
        var result = 100 /((inputDouble * 1.609) / 4.546)
        return result
    }

    private fun convertImperialMpgToUsMpg(inputDouble: Double): Double? {
        var result = inputDouble / 1.2009499241541
        return result
    }

    /*******************************************************************************
     * From KPL
     *******************************************************************************/
    private fun convertKplToImperialMpg(inputDouble: Double): Double? {
        var result = inputDouble * 2.825
        return result
    }

    private fun convertKplToLP100KM(inputDouble: Double): Double? {
        var result = 100 / inputDouble
        return result
    }

    private fun convertKplToUsMpg(inputDouble: Double): Double? {
        var result = inputDouble * 2.352
        return result
    }

    /*******************************************************************************
     * From L/100 KM
     *******************************************************************************/
    private fun convertLp100KmToImperialMpg(inputDouble: Double): Double? {
        var result = (4.546 * (100 / inputDouble)) / 1.609
        return result
    }

    private fun convertLp100KmToKpl(inputDouble: Double): Double? {
        var result = 100 / inputDouble
        return result
    }

    private fun convertLp100KmToUsMpg(inputDouble: Double): Double? {
        var result = (3.785 * (100 / inputDouble)) / 1.609
        return result
    }
}