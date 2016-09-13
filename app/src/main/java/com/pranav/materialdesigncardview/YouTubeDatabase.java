package com.pranav.materialdesigncardview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Pranav on 9/7/2016.
 */
public class YouTubeDatabase {
    SQLHelper sqlHelper;
    public YouTubeDatabase(Context context){
        sqlHelper=new SQLHelper(context);
    }
    public void insertData(ArrayList<singleRowYouTube_normal> videoList, boolean clearPrevious,int Table_Number){
        if(clearPrevious){
            deleteAll(Table_Number);
        }
        SQLiteDatabase db=sqlHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        for(int i=0;i<videoList.size();i++){
            contentValues.put(SQLHelper.TITLE,videoList.get(i).getTitle());
            contentValues.put(SQLHelper.ID,videoList.get(i).getId());
            contentValues.put(SQLHelper.THUMBNAIL,videoList.get(i).getThumbnail());
            if(Table_Number==0){
                db.insert(SQLHelper.TABLE1_NAME,null,contentValues);
            }else{
                db.insert(SQLHelper.TABLE2_NAME,null,contentValues);
            }
        }

    }
    public ArrayList<singleRowYouTube_normal> getAllVideos(int Table_Number){
        ArrayList<singleRowYouTube_normal> SQLVideoList=new ArrayList<>();
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        String[] columns = {SQLHelper.ID,SQLHelper.THUMBNAIL,SQLHelper.TITLE};
        Cursor cursor=null;
        if(Table_Number==0){
            cursor = db.query(SQLHelper.TABLE1_NAME, columns,null, null, null, null, null);
        }else{
            cursor = db.query(SQLHelper.TABLE2_NAME, columns,null, null, null, null, null);
        }
        if(cursor.moveToFirst() && cursor.getCount() != 0){
            do{
                singleRowYouTube_normal single_video=new singleRowYouTube_normal(cursor.getString(cursor.getColumnIndex(SQLHelper.ID)),cursor.getString(cursor.getColumnIndex(SQLHelper.TITLE)),cursor.getString(cursor.getColumnIndex(SQLHelper.THUMBNAIL)));
//                single_video.getItems().get(0).setId(cursor.getString(cursor.getColumnIndex(SQLHelper.ID)));
//                single_video.getItems().get(0).getSnippet().setTitle(cursor.getString(cursor.getColumnIndex(SQLHelper.TITLE)));
//                single_video.getItems().get(0).getSnippet().getThumbnails().getDefaultX().setUrl(cursor.getString(cursor.getColumnIndex(SQLHelper.THUMBNAIL)));
                SQLVideoList.add(single_video);
            }while(cursor.moveToNext());
        }

        return SQLVideoList;
    }

    private void deleteAll(int Table_Number) {
        SQLiteDatabase db=sqlHelper.getWritableDatabase();
//        db.execSQL("DELETE * FROM "+SQLHelper.TABLE_NAME);
        if(Table_Number==0){
            db.delete(SQLHelper.TABLE1_NAME,null,null);
        }else{
            db.delete(SQLHelper.TABLE2_NAME,null,null);
        }

    }

    static class SQLHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "VideoDB";
        private static final String TABLE1_NAME = "DownloadedVideos";
        private static final String TABLE2_NAME = "Top50Videos";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "sid";
        private static final String TITLE = "VideoTitle";
        private static final String THUMBNAIL="VideoThumbnail";
        private static final String ID = "VideoID";

        private static final String CREATE_TABLE1="CREATE TABLE "+ TABLE1_NAME +" ("+ UID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ TITLE +" VARCHAR(255), "+ THUMBNAIL +" VARCHAR, "+ ID +" VARCHAR(255))";
        private static final String CREATE_TABLE2="CREATE TABLE "+ TABLE2_NAME +" ("+ UID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ TITLE +" VARCHAR(255), "+ THUMBNAIL +" VARCHAR, "+ ID +" VARCHAR(255))";

        Context context;


        public SQLHelper(Context context) {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE1);
            sqLiteDatabase.execSQL(CREATE_TABLE2);
//            Toast.makeText(context, "DB created",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
