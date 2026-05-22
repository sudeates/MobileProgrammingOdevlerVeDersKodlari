package com.example.loginapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper (context: Context):
    SQLiteOpenHelper(context,DATABASE_NAME,null,1){
    companion object {
        const val DATABASE_NAME = "USER.DB"
        const val TABLE_NAME = "USERS"
        const val COL_ID = "ID"
        const val COL_USERNAME = "USERNAME"
        const val COL_NAME = "NAME"
        const val COL_MAIL = "EMAIL"
        const val COL_PASSWORD = "PASSWORD"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery="""
            CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY,$COL_USERNAME TEXT NOT NULL,
            NAME TEXT NOT NULL,EMAIL TEXT NOT NULL,PASSWORD TEXT NOT NULL)""".trimIndent()
        db?.execSQL(createTableQuery)
    }
    override fun onUpgrade(db: SQLiteDatabase?,oldVersion:Int,newVersion :Int){
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun insertUser(userName:String,name:String,email:String,password:String): Boolean{
        val db=writableDatabase
        val cursor=db.rawQuery("SELECT * FROM $TABLE_NAME",null)
        val id=cursor.count
        cursor.close()
        val record=ContentValues().apply {
            put(COL_ID,id+1)
            put(COL_USERNAME,userName)
            put(COL_NAME,name)
            put(COL_MAIL,email)
            put(COL_PASSWORD,password)
        }
        val result=db.insert(TABLE_NAME,null,record)
        db.close()
        return result!=-1L
    }
    fun viewAllUsers(): ArrayList<String>{
        val db=readableDatabase
        val cursor=db.rawQuery("SELECT * FROM $TABLE_NAME",null)
        val userNames=ArrayList<String>()
        if (cursor.moveToFirst()){
            do{
                userNames.add(cursor.getString(2))
            } while(cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userNames
    }
    fun findUser(userName: String,password: String): Boolean{
        val db=readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $COL_USERNAME = ? AND $COL_PASSWORD=?",
            arrayOf(userName,password)
        )
        var flag=false
        if(cursor.moveToFirst()){
            flag=true
        }
        else{
            flag=false
        }
        cursor.close()
        db.close()
        return flag
    }
}