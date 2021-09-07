package com.roes.mysqlroomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.roes.mysqlroomdb.data.Product
import com.roes.mysqlroomdb.data.ProductAdapter
import com.roes.mysqlroomdb.data.ProductDB
import com.roes.mysqlroomdb.data.ProductDao
import com.roes.mysqlroomdb.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var dao: ProductDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dao = ProductDB.getInstance(application).productDao

        val products:List<Product> = listOf(
            Product(1,"abc",123.00),
            Product(2,"def",456.00)
        )

        val myAdapter = ProductAdapter(products)
        binding.rvView.adapter = myAdapter
        binding.rvView.setHasFixedSize(true)

        //val price: Double = binding.tbPrice.text.toString().toDouble()
        //val btn : Button = findViewById(R.id.btnInsert)

        binding.btnInsert.setOnClickListener() {

            val p = Product(0, binding.tbName.text.toString(), binding.tbPrice.text.toString().toDouble())

            CoroutineScope(IO).launch{
                dao.insertProduct(p)
            }
        }

        binding.btnGet.setOnClickListener() {

            CoroutineScope(IO).launch{
                var productName: String = ""
                val prodList: List<Product> = dao.getAll()
                //val prodList: List<Product> = dao.getPriceLessThan(binding.tbPrice.text.toString().toDouble())


                for (p: Product in prodList){
                    productName += p.name + "\n"
                }



                CoroutineScope(Main).launch {
                    binding.tvResult.text = productName

                }
            }

        }

    }
}