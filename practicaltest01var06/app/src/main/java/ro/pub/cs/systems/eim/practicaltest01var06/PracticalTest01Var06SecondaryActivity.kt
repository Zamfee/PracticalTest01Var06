package ro.pub.cs.systems.eim.practicaltest01var06

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var06SecondaryActivity : AppCompatActivity() {

    val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var06_secondary)

        var intent = getIntent()
        var first_number = intent.getStringExtra("text1")
        var second_number = intent.getStringExtra("text2")
        var third_number = intent.getStringExtra("text3")

        var check_nr = intent.getIntExtra("check_nr", 0)

        var textBox = findViewById<EditText>(R.id.gainText)
        var gain = 0

        if ((first_number == second_number || first_number == "*" || second_number == "*") &&
            (second_number == third_number || second_number == "*" || third_number == "*") &&
            (first_number == third_number || first_number == "*" || third_number == "*")) {
            when (check_nr) {
                0 -> { textBox.setText("Gained 100")
                        gain = 100 }
                1 -> {
                    textBox.setText("Gained 50")
                    gain = 50
                }
                2 -> {
                    textBox.setText("Gained 10")
                    gain = 10
                }
                3 -> {
                    textBox.setText("Gained 0 :(")
                    gain = 0
                }
            }
        } else {
            textBox.setText("You lost")
            gain = 0
        }

        var okButton = findViewById<Button>(R.id.okButton)

        okButton.setOnClickListener() {
            val resultIntent = Intent()
            resultIntent.putExtra("gain", gain)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}