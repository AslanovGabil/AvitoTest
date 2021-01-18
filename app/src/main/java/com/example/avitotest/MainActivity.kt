package com.example.avitotest

//import kotlin.system.measureTimeMillis
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*



class MainActivity : AppCompatActivity() , ExampleAdapter.OnItemClickListener{
    private val exampleList = ArrayList<ExampleItem>()
    private val removeList = ArrayList<ExampleItem>()
    private val adapter = ExampleAdapter(exampleList,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        generateDummyList(15)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(this,2 )
        recycler_view.setHasFixedSize(true)

        CoroutineScope(Dispatchers.IO).launch {
            async { InsertItemAsync() }

        }


    }



    suspend fun InsertItemAsync() {
        while (true) {
            delay(5000)

            Handler(Looper.getMainLooper()).post {

                if (  removeList.count()>0  ){
                    insertItem(removeList.get(0))
                    removeList.removeAt(0)
                }
                else
                    insertItem(CreateItem())




            }
        }
    }

    fun CreateItem(): ExampleItem {
        val i: Int;
        if (exampleList != null) {
            i = exampleList.size
        } else {
            i = 0
        }
        Log.d("TAG", "exampleList $i")

        val drawble = when (i % 3) {
            0 -> R.drawable.ic_moon
            1 -> R.drawable.ic_eclipse
            else -> R.drawable.ic_sun
        }
        return ExampleItem(drawble, i, "Item $i")

    }



    private fun generateDummyList(size: Int) {

        for (i in 0 until size) {
            val item = CreateItem()
            insertItem(item)
        }

    }

    fun insertItem(item: ExampleItem) {


        exampleList.add(item)
        adapter.notifyItemInserted(exampleList.size)

    }





    override fun onRemoveClick(position: Int) {
        removeList.add(exampleList.get(position))
        exampleList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}