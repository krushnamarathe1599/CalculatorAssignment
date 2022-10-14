package com.example.calculator_kotlin

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    lateinit var calculatorEditorLayout: LinearLayout
    lateinit var calculatorHistoryLayout: LinearLayout

    lateinit var button1: android.widget.Button
    lateinit var button2: android.widget.Button
    lateinit var button3: android.widget.Button
    lateinit var button4: android.widget.Button
    lateinit var button5: android.widget.Button
    lateinit var button6: android.widget.Button
    lateinit var button7: android.widget.Button
    lateinit var button8: android.widget.Button
    lateinit var button9: android.widget.Button
    lateinit var button0: android.widget.Button
    lateinit var buttonHistory: android.widget.Button
    lateinit var buttonPercent: android.widget.Button
    lateinit var buttonClear: android.widget.Button
    lateinit var buttonDot: android.widget.Button
    lateinit var buttonEqual: android.widget.Button
    lateinit var buttonAddition: android.widget.Button
    lateinit var buttonSubtraction: android.widget.Button
    lateinit var buttonMultiplication: android.widget.Button
    lateinit var buttonDivide: android.widget.Button
    lateinit var buttonBackspace: android.widget.Button
    lateinit var inputText: EditText
    lateinit var resultText: EditText
    lateinit var recyclerViewHistory: RecyclerView
    lateinit var historyAdapter: HistoryAdapter


    var check = 0

    var historyQueue: Queue<String> = LinkedList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        buttonAddition = findViewById(R.id.buttonadd)
        buttonSubtraction = findViewById(R.id.buttonminus)
        buttonMultiplication = findViewById(R.id.buttonmultiply)
        buttonDivide = findViewById(R.id.buttondivide)
        buttonDot = findViewById(R.id.buttondot)
        buttonBackspace = findViewById(R.id.buttonbackspace)
        buttonClear = findViewById(R.id.clear)
        buttonPercent = findViewById(R.id.buttonpercent)
        buttonEqual = findViewById(R.id.buttonequal)
        buttonHistory = findViewById(R.id.buttonHistory)
        resultText = findViewById(R.id.result)
        inputText = findViewById(R.id.inputnumber)
        recyclerViewHistory = findViewById(R.id.recyclerViewHistory)
        calculatorEditorLayout = findViewById(R.id.calculator_view_edittext_layout)
        calculatorHistoryLayout = findViewById(R.id.calculator_view_history_layout)
        recyclerViewHistory.layoutManager = LinearLayoutManager(this)
        historyAdapter = HistoryAdapter(ArrayList(historyQueue),this)
        recyclerViewHistory.adapter = historyAdapter

        inputText.movementMethod = ScrollingMovementMethod()
        inputText.isActivated
        inputText.isPressed

        var text:String

        button0.setOnClickListener {
            text = inputText.text.toString()+"0"
            inputText.setText(text)
            result(text)
        }

        button1.setOnClickListener {
            text = inputText.text.toString()+"1"
            inputText.setText(text)
            result(text)
        }

        button2.setOnClickListener {
            text = inputText.text.toString()+"2"
            inputText.setText(text)
            result(text)
        }

        button3.setOnClickListener {
            text = inputText.text.toString()+"3"
            inputText.setText(text)
            result(text)
        }

        button4.setOnClickListener {
            text = inputText.text.toString()+"4"
            inputText.setText(text)
            result(text)
        }

        button5.setOnClickListener {
            text = inputText.text.toString()+"5"
            inputText.setText(text)
            result(text)
        }

        button6.setOnClickListener {
            text = inputText.text.toString()+"6"
            inputText.setText(text)
            result(text)
        }

        button7.setOnClickListener {
            text = inputText.text.toString()+"7"
            inputText.setText(text)
            result(text)
        }

        button8.setOnClickListener {
            text = inputText.text.toString()+"8"
            inputText.setText(text)
            result(text)
        }

        button9.setOnClickListener {
            text = inputText.text.toString()+"9"
            inputText.setText(text)
            result(text)
        }

        buttonHistory.setOnClickListener {
            Log.d(TAG,"List Queue = $historyQueue")
            historyAdapter.setList(ArrayList(historyQueue))
            historyAdapter.notifyDataSetChanged()
            enableHistoryView(true)
        }

        buttonDot.setOnClickListener {
            text = inputText.text.toString()
            if (!text.endsWith(".") && !text.endsWith("+")
                && !text.endsWith("*") && !text.endsWith("/")
                && !text.endsWith("-") && !text.endsWith("%")){
                text = inputText.text.toString() + "."
                inputText.setText(text)
                check += 1
            }
        }

        buttonAddition.setOnClickListener {
            text = inputText.text.toString()
            if (!text.endsWith(".") && !text.endsWith("+")
                && !text.endsWith("*") && !text.endsWith("/")
                && !text.endsWith("-") && !text.endsWith("%")){
                text = inputText.text.toString() + "+"
                inputText.setText(text)
                check += 1
                enableHistoryView(false)
            }

        }

        buttonSubtraction.setOnClickListener {
            text = inputText.text.toString()
            if (!text.endsWith(".") && !text.endsWith("+")
                && !text.endsWith("*") && !text.endsWith("/")
                && !text.endsWith("-") && !text.endsWith("%")){
                text = inputText.text.toString() + "-"
                inputText.setText(text)
                check += 1
                enableHistoryView(false)
            }
        }

        buttonMultiplication.setOnClickListener {
            text = inputText.text.toString()
            if (!text.endsWith(".") && !text.endsWith("+")
                && !text.endsWith("*") && !text.endsWith("/")
                && !text.endsWith("-") && !text.endsWith("%")){
                text = inputText.text.toString() + "*"
                inputText.setText(text)
                check += 1
                enableHistoryView(false)
            }
        }

        buttonDivide.setOnClickListener {kunal@
            text = inputText.text.toString()
            if (!text.endsWith(".") && !text.endsWith("+")
                && !text.endsWith("*") && !text.endsWith("/")
                && !text.endsWith("-") && !text.endsWith("%")){
                text = inputText.text.toString() + "/"
                inputText.setText(text)
                check += 1
                enableHistoryView(false)
            }
        }

        buttonPercent.setOnClickListener {
            text = inputText.text.toString()
            if (!text.endsWith(".") && !text.endsWith("+")
                && !text.endsWith("*") && !text.endsWith("/")
                && !text.endsWith("-") && !text.endsWith("%")){
                text = inputText.text.toString() + "%"
                inputText.setText(text)
                check += 1
                enableHistoryView(false)
            }
        }

        buttonEqual.setOnClickListener {
            text = resultText.text.toString()
            inputText.setText(text)
            resultText.text = null
            enableHistoryView(false)

        }

        buttonClear.setOnClickListener {
            inputText.text = null
            resultText.text = null
            historyQueue.clear()
            enableHistoryView(false)
        }

        buttonBackspace.setOnClickListener {
            var backspace: String?=null
            if (inputText.text.isNotEmpty()){
                val stringBuilder: StringBuilder= StringBuilder(inputText.text)
                val find = inputText.text.toString()
                val find2 = find.last()

                if (find2 == '+' || find2 == '-' || find2 == '*' || find2 == '/' || find2 == '%'){
                    check -= 1
                }

                stringBuilder.deleteCharAt(inputText.text.length-1)
                backspace = stringBuilder.toString()
                inputText.setText(backspace)
                result(backspace)
            }
        }
    }


    private fun result(text: String) {
        enableHistoryView(false)
        val engine = ScriptEngineManager().getEngineByName("rhino")
        try {
            val result = engine.eval(text).toString()
            if (check==0){
                resultText.text = null
            }
            else{
                addHistoryItem("$text = $result")
                resultText.setText(result)
            }
        }catch (e:ScriptException){
            Log.d("Tag","Error")
        }
    }

    fun addHistoryItem(historyItem: String?) {
        if (historyQueue.size >= 10) {
            historyQueue.remove()
        }
        historyQueue.add(historyItem)
    }


    fun enableHistoryView(enable: Boolean){
        if(enable){
            calculatorHistoryLayout.visibility = View.VISIBLE
            calculatorEditorLayout.visibility = View.GONE
        }else{
            calculatorHistoryLayout.visibility = View.GONE
            calculatorEditorLayout.visibility = View.VISIBLE
        }
    }
}