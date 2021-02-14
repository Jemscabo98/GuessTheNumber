package montoya.eduardo.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var maxValue = 100
    var minValue = 0
    var num = 0
    var won = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessings: TextView = findViewById(R.id.guessings)
        val butDown: Button = findViewById(R.id.butDown)
        val butUp: Button = findViewById(R.id.butUp)
        val butGenerate: Button = findViewById(R.id.butGenerator)
        val butGuessed: Button = findViewById(R.id.butGuessed)

        butGenerate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            guessings.setText(num.toString())
            butGuessed.visibility = View.VISIBLE
            butGenerate.visibility = View.GONE
        }

        butUp.setOnClickListener{
            minValue = num
            if (checkingLimit()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText("Baka na... ore wa mou shinde iru")
                butGuessed.visibility = View.GONE
                butGenerate.visibility = View.VISIBLE
                resetLimit()
            }
        }

        butDown.setOnClickListener{
            maxValue = num
            if (checkingLimit()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText("Baka na... ore wa mou shinde iru")
                butGuessed.visibility = View.GONE
                butGenerate.visibility = View.VISIBLE
                resetLimit()
            }
        }

        butGuessed.setOnClickListener{
            if(!won) {
                guessings.setText("I guessed it right, your number is " + num)
                butGuessed.setText(" Play Again")
                won = true
            } else{
                guessings.setText("Tap on generate to start")
                butGuessed.setText("Guessed")
                butGuessed.visibility = View.GONE
                butGenerate.visibility = View.VISIBLE
                resetLimit()
            }
        }


    }
    fun resetLimit()
    {
        maxValue = 100
        minValue = 0
        num = 0
        won = false
    }

    fun checkingLimit(): Boolean
    {
        return minValue != maxValue
    }
}