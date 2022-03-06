package com.encoders.coroutinesinkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.encoders.coroutinesinkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var number_: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        CoroutineScope(Dispatchers.Main).launch {
            Runblocking()
        }


//        CoroutineScope(Dispatchers.Main).launch {
//            Task_One()
//        }
//
//        CoroutineScope(Dispatchers.Main).launch {
//            Task_Two()
//        }


//        binding.updateNumber.setOnClickListener {
//            binding.number.text = (number_++).toString()
//        }
//
        binding.updateNumber.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                execute()
            }
        }

        binding.startLoop.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Task_One()
            }

            CoroutineScope(Dispatchers.IO).launch {
                Task_Two()
            }
        }

//        binding.startLoop.setOnClickListener {
//
//            CoroutineScope(Dispatchers.Default).launch{
//                executeLongRunningTask()
//            }
//
//        }


//        binding.startLoop.setOnClickListener {
//            GlobalScope.launch(Dispatchers.IO){
//                executeLongRunningTask()
//            }
//
//
//        }


//        binding.startLoop.setOnClickListener {
//            thread(start = true) {
//                executeLongRunningTask()
//            }
//
//        }

    }

    suspend fun print_followers() {
        val job = CoroutineScope(Dispatchers.Main).async {
         async {  binding.number.text = Return_Number().toString() }

        }
        job.await()


    }

    suspend fun Runblocking(){
        runBlocking {
            launch {
                Log.e("Start","Start")
                delay(10000)
                Log.e("Middle","Middle")
            }
        }
        Log.e("End","End")
    }

    suspend fun WithContext(){
        Log.e("BEfore","Coroutine is Launch")
        withContext(Dispatchers.IO){
            delay(3000)
            Log.e("Inside","Coroutine is Launch")
        }

        Log.e("After","Coroutine is Launch")
    }

    suspend fun execute(){
        val parentJob = GlobalScope.launch(Dispatchers.Main) {
          Log.e("PARENT_STARTED","STARTED")
            delay(5000)
            Log.e("PARENT_ENDED","ENDED")

            val child_Job = GlobalScope.launch {
                Log.e("Child is Started","STARTED")
                delay(10000)
                Log.e("Child is Ended","ENDED")

            }
            delay(1000)
            child_Job.cancel()

        }
//        delay(1000)
//        parentJob.cancel()
        parentJob.join()
        Log.e("PARENT_COMPLETED","COMPLETED")

    }

//    suspend fun print_followers() {
//        var number: Int = 0
//        val job = CoroutineScope(Dispatchers.Main).launch {
//            number = Return_Number()
//            binding.number.text = number.toString()
//        }
//        job.join()
//        binding.number.text = number.toString()
//
//    }

    suspend fun Return_Number(): Int {
        delay(2000)
        return 54
    }

    fun executeLongRunningTask() {
        for (i in 1..100000000000L) {

        }
    }


    suspend fun Task_One() {
        Log.e("TASK_ONE_START", "Task one is start")
        yield()
        Log.e("TASK_ONE_STOP", "Task one is stop")
    }

    suspend fun Task_Two() {
        Log.e("TASK_TWO_START", "Task two is start")
        yield()
        Log.e("TASK_TWO_STOP", "Task two is stop")
    }
}