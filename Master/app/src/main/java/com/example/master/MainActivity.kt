package com.example.master

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.master.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var b:ActivityMainBinding
    var rez:Int=0
    var rez2:Int=0
    var count:Int=0
    val znak= listOf<String>("+","-","*")
    val num1= (0..10).toList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b=ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.start.setOnClickListener { Time() }
        b.proverka.setOnClickListener { Checked() }
    }

    fun randomMe () {
        val ch1=num1.random()
        val ch2=num1.random()
        val zn=znak.random()
        b.ch1.setText(ch1.toString())
        b.ch2.setText(ch2.toString())
        b.zn.setText(zn)
        val rez2=determinant(ch1,ch2,zn)
    }
    fun determinant(num1:Int,num2:Int,oper:String):Int {
        when(oper){
            "+" -> rez=num1+num2
            "*" -> rez=num1*num2
            "-" -> rez=num1-num2
        }
        return rez
    }
    fun Checked(){
        val prov = Integer.parseInt(b.rez.text.toString())
        if(prov==rez2){
            count++
            Toast.makeText(this,"ВЕРНО!",Toast.LENGTH_SHORT).show()
                    b.rez.setText("")
                    randomMe()
        }
        else
            Toast.makeText(this,"ПОПРОБУЙ ЕЩЕ РАЗ!",Toast.LENGTH_SHORT).show()
    }
    fun Time() {
        count=0
        b.rez.isEnabled=true
        b.count.text=getString(R.string.rez,count)
        randomMe()
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timepad=millisUntilFinished /
                        1000
                b.time.text=getString(R.string.timing,timepad)
            }
            override fun onFinish() {
                b.time.setText("done!")
                b.rez.isEnabled=false
                b.count.text=getString(R.string.rez,count)
            }
        }.start()
    }
}