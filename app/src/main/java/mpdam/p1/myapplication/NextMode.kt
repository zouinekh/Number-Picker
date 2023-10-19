package mpdam.p1.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class NextMode : AppCompatActivity() {
    val START_TIME_IN_MILLIS:Long= 1 *60 * 1000
    var remainingTime:Long=START_TIME_IN_MILLIS
    var timer:CountDownTimer?=null
    lateinit var startBtn : Button
    lateinit var restartBtn : Button
    lateinit var timerTV :TextView
    lateinit var editText: EditText
    lateinit var checkButton: ImageButton
    var random:Int= Random.nextInt(1, 100)
    lateinit var goToPrevMode :Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_mode)
        startBtn=findViewById(R.id.startBTN)
        restartBtn=findViewById(R.id.reset)
        timerTV=findViewById(R.id.timerTV)
        goToPrevMode=findViewById(R.id.prev)
        checkButton=findViewById(R.id.checkButton)
        editText=findViewById(R.id.editTextText)

        checkButton.setOnClickListener {
           var result =checkValue()
            Toast.makeText(applicationContext,result,Toast.LENGTH_SHORT).show()
        }

        startBtn.setOnClickListener { startTimer() }
        restartBtn.setOnClickListener { resetTimer() }
        goToPrevMode.setOnClickListener { val intent = Intent(this,MainActivity::class.java)
            startActivity(intent) }
    }
    private fun startTimer(){
        editText.isEnabled=true
         timer= object : CountDownTimer(START_TIME_IN_MILLIS,1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTime=millisUntilFinished
                updateTimerText()
            }
            override fun onFinish() {
                Toast.makeText(applicationContext,"time's UP !! ",Toast.LENGTH_SHORT).show()
                editText.isEnabled=false
            }
        }.start()
    }
    private fun resetTimer(){
        timer?.cancel()
        remainingTime=START_TIME_IN_MILLIS
        updateTimerText()
        editText.isEnabled=false
    }
    private fun updateTimerText(){
        var minute = remainingTime.div(1000).div(60)
        var second=remainingTime.div(1000) % 60
        var formattedTimed=String.format("%02d:%02d",minute,second)
        timerTV.text=formattedTimed
    }
    private fun checkValue():String{
        var number:Int=editText.text.toString().toInt()
        var result :String

        if(number < random){
            result= " Wrong \uD83D\uDE14 your number $number is to low ! "
            editText.text.clear()
        }else if (number> random){
            result= " Wrong \uD83D\uDE14 your number $number is to high ! "
            editText.text.clear()
        }else{
            result= " \uD83C\uDF89  Congratulation, Your number is right \uD83C\uDF89 "
            editText.text.clear()
            resetTimer()
        }
        return result
    }

}