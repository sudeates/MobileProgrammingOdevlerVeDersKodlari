package com.example.sqliteodev

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context,DATABASE_NAME,null,1){
    companion object{
        const val DATABASE_NAME = "contact.db"
        const val TABLE_NAME="contacts"
        const val COL_ID="id"
        const val COL_NAME="name"
        const val COL_PHONE="phone"
    }
    override fun onCreate(db: SQLiteDatabase){
        val createTableQuery="""
            CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COL_NAME TEXT NOT NULL,$COL_PHONE TEXT NOT NULL)""".trimIndent()
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun insertContact(adSoyad: String, telefon: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_NAME, adSoyad)
            put(COL_PHONE, telefon)
        }
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result != -1L
    }
    fun getALlNames():ArrayList<String>{
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT $COL_NAME FROM $TABLE_NAME", null)
        val list = ArrayList<String>()
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }
    fun getAllIds(): ArrayList<Int> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT $COL_ID FROM $TABLE_NAME", null)
        val list = ArrayList<Int>()
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getInt(0))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }
    fun updateContact(id:Int,name: String,phone: String): Boolean{
        val db=writableDatabase
        val values=ContentValues().apply {
            put(COL_NAME,name)
            put(COL_PHONE,phone)
        }
        val result=db.update(TABLE_NAME,values,"$COL_ID=?", arrayOf(id.toString()))
        db.close()
        return result>0
    }
    fun deleteContact(id:Int): Boolean{
        val db=writableDatabase
        val result=db.delete(TABLE_NAME,"$COL_ID=?", arrayOf(id.toString()))
        db.close()
        return result>0
    }

    fun getPhoneNumber(id:Int): String{
        val db=readableDatabase
        val cursor=db.rawQuery("SELECT $COL_PHONE FROM $TABLE_NAME WHERE $COL_ID=?",arrayOf(id.toString()))
        var phone=""
        if(cursor.moveToFirst()){
            phone=cursor.getString(0)
        }
        cursor.close()
        db.close()
        return phone
    }
}