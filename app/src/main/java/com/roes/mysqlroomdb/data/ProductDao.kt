package com.roes.mysqlroomdb.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Insert
    fun insertProduct(p:Product)

    @Query("delete from product_table")
    fun clear()

    @Query("select * from product_table")
    fun getAll() : List<Product>

    @Query("select * from product_table where price < :price")
    fun getPriceLessThan(price:Double) : List<Product>

}