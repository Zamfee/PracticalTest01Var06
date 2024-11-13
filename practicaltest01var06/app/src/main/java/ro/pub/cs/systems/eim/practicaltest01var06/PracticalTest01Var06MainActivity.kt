package ro.pub.cs.systems.eim.practicaltest01var06

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.compareTo

class PracticalTest01Var06MainActivity : AppCompatActivity() {
    var gain = 0
    var check_started = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var06_main)

        var button_1 = findViewById<Button>(R.id.playButton)

        var text_1 = findViewById<EditText>(R.id.textBox1)
        var text_2 = findViewById<EditText>(R.id.textBox2)
        var text_3 = findViewById<EditText>(R.id.textBox3)

        var check_1 = findViewById<CheckBox>(R.id.firstBox)
        var check_2 = findViewById<CheckBox>(R.id.secondBox)
        var check_3 = findViewById<CheckBox>(R.id.thirdBox)

        button_1.setOnClickListener {
            val values = listOf(1, 2, 3, '*')
            val randomValues = List(3) { values.random() }

            var toast_1 = "empty"
            var toast_2 = "empty"
            var toast_3 = "empty"

            if (!check_1.isChecked) {
                text_1.setText(randomValues[0].toString())
                toast_1 = randomValues[0].toString()
            }
            if (!check_2.isChecked) {
                text_2.setText(randomValues[1].toString())
                toast_2 = randomValues[1].toString()
            }
            if (!check_3.isChecked) {
                text_3.setText(randomValues[2].toString())
                toast_3 = randomValues[2].toString()
            }

            Toast.makeText(this, "Text Status: $toast_1, $toast_2, $toast_3", Toast.LENGTH_LONG).show()
        }

        var send_button = findViewById<Button>(R.id.goToButton)

        send_button.setOnClickListener() {
            val intent = Intent(this, PracticalTest01Var06SecondaryActivity::class.java)
            intent.putExtra("text1", text_1.text.toString())
            intent.putExtra("text2", text_2.text.toString())
            intent.putExtra("text3", text_3.text.toString())

            var check_nr = 0
            if (check_1.isChecked) {
                check_nr += 1
            }
            if (check_2.isChecked) {
                check_nr += 1
            }
            if (check_3.isChecked) {
                check_nr += 1
            }

            intent.putExtra("check_nr", check_nr)
            startActivityForResult(intent, 1)
        }
    }

    fun checkWin() : Boolean {
        var text_1 = findViewById<EditText>(R.id.textBox1)
        var text_2 = findViewById<EditText>(R.id.textBox2)
        var text_3 = findViewById<EditText>(R.id.textBox3)

        var first_number = text_1.text.toString()
        var second_number = text_2.text.toString()
        var third_number = text_3.text.toString()

        if((first_number == second_number || first_number == "*" || second_number == "*") &&
                (second_number == third_number || second_number == "*" || third_number == "*") &&
                (first_number == third_number || first_number == "*" || third_number == "*")) {
            return true
        } else
            return false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("gain", gain)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        gain = savedInstanceState.getInt("gain")

        Toast.makeText(this, "Current gain: $gain", Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val gain_second = data?.getIntExtra("gain", 0) ?: 0
            gain += gain_second
            Toast.makeText(this, "Gain: $gain", Toast.LENGTH_LONG).show()
        }
    }

    class checkThread(private val activity: PracticalTest01Var06MainActivity) : Thread() {
        override fun run() {
            if (activity.check_started == false) {
                activity.check_started = true
                if (activity.gain > 300) {
                    var intent = Intent(activity, PracticalTest01Var06Service::class.java)

                    intent.putExtra("gain", activity.gain)

                    activity.startService(intent)
                }
            }
        }
    }
}