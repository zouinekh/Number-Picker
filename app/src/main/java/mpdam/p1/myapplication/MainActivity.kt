package mpdam.p1.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerview:RecyclerView
    private lateinit var historyArrayList:ArrayList<String>
    private lateinit var ResultView : TextView
    private lateinit var editText: EditText
    lateinit var restart:ImageButton
    lateinit var checkButton: ImageButton

    private var random:Int=nextInt(1,100)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ResultView = findViewById(R.id.textView)
        editText=findViewById(R.id.EditText)
        checkButton=findViewById(R.id.imageButton)
        restart=findViewById(R.id.imageButton2)


        ResultView.text="Please enter your guess "
        checkButton.setOnClickListener{
            var number:Int=editText.text.toString().toInt()
            var result :String

            if(number < random){
                result= " Wrong \uD83D\uDE14 your number $number is to low ! "
                ResultView.text=result
                historyArrayList.add(result)
                editText.text.clear()
            }else if (number> random){
                result= " Wrong \uD83D\uDE14 your number $number is to high ! "
                ResultView.text=result
                historyArrayList.add(result)
                editText.text.clear()
            }else{
                result= " \uD83C\uDF89  Congratulation, Your number is right \uD83C\uDF89 "
                ResultView.text=result
                historyArrayList.add(result)
                editText.text.clear()
            }
        }
        restart.setOnClickListener{
            reset()
        }

        newRecyclerview = findViewById(R.id.history)
        newRecyclerview.layoutManager = LinearLayoutManager(this).apply {

        }
        newRecyclerview.setHasFixedSize(true)
        historyArrayList = arrayListOf<String>()

        newRecyclerview.adapter=MyAdapter(historyArrayList)

        val nextModeButton= findViewById<Button>(R.id.button)
        nextModeButton.setOnClickListener {
            val intent =Intent(this,NextMode::class.java)
            startActivity(intent)
        }

    }

    private fun reset(){
        random= nextInt(1,100)
        ResultView.text="Please enter your guess"
        editText.text.clear()
        historyArrayList.clear()
        newRecyclerview.adapter=MyAdapter(historyArrayList)
    }
}