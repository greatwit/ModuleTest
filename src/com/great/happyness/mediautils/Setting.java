package com.great.happyness.mediautils;

import com.great.happyness.wise.GApplication;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;


public class Setting 
{
		public static int DADDR = 1, DPORT = 2, DFILE = 3;
	
		private static String dataBaseName 	= "ModuleTestDatabase";
		private static String tableName 	= "ModuleTestTable";
		private Context mContext = GApplication.mContext;
		public String readData(int keyindex) {
			return readData(mContext, keyindex);
		}
		
		public void InsertOrUpdate(int keyindex, String value) {
			InsertOrUpdate(mContext, keyindex, value);
		}
		
		public String readData(Context context,int keyindex) 
		{
			String Keycounter = "";
			SQLiteDatabase adobeDB = null;
			try 
			{  
				adobeDB = context.openOrCreateDatabase(dataBaseName,Context.MODE_PRIVATE, null);
				adobeDB.execSQL("CREATE TABLE IF NOT EXISTS '" + tableName + "' (" + 
		    						"id integer primary key autoincrement, " +
									    "Keyindex tinyint, " +
									         "Keycounter varchar(20) ); ");
				Cursor mcursor = adobeDB.rawQuery("SELECT * FROM '" + tableName + "' where Keyindex = " + keyindex + "", null);
				//Log.e("..","mcursor count*********:"+mcursor.getCount());
				if (mcursor != null) 
				{
					if(mcursor.moveToFirst())
					    //mcursor.moveToFirst();
						Keycounter=mcursor.getString(mcursor.getColumnIndex("Keycounter"));
 				else 
 				{
						Keycounter="";
						adobeDB.execSQL("INSERT INTO '" + tableName + "' (Keyindex,Keycounter) VALUES (" +
									""+ keyindex +"," +
										 "'" + Keycounter +"');");
 				}
			    }
				if(mcursor!=null)
					mcursor.close();
				return Keycounter;
			} 
			catch (SQLiteException se)
			{
				Log.e(dataBaseName,"Could not create or open the database");
			} 
			finally 
			{
				if (adobeDB != null) 
				{
					adobeDB.close();
				} 
			}
			return Keycounter;
			
		}

		
		public void InsertOrUpdate(Context context,int keyindex, String keycounter) 
		{
			SQLiteDatabase adobeDB = null;
			try 
			{
				adobeDB = context.openOrCreateDatabase(dataBaseName,Context.MODE_PRIVATE, null);
				adobeDB.execSQL("CREATE TABLE IF NOT EXISTS '" + tableName + "' (" + 
	    						"id integer primary key autoincrement, " +
								    "Keyindex tinyint, " +
								         "Keycounter varchar(20) ); ");

				adobeDB.execSQL("UPDATE '" + tableName + "' SET " +
					    "Keycounter = '" + keycounter + "' " + 
					             " WHERE Keyindex = " + keyindex + ";");
			} 
			catch (SQLiteException se) 
			{
				Log.e(dataBaseName,"Could not create or open the database " + se.getMessage());
			} 
			finally 
			{
				if (adobeDB != null)
					adobeDB.close();
			}
		}
 }


