package com.tesseract.quiz20.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.tesseract.quiz20.Model.Question;
import com.tesseract.quiz20.Model.Ranking;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

//https://blog.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
//deze link gebruikt om mijn eigen database data te gebruiken

public class DbHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "Database.db";
    private static String DB_PATH = "";
    private SQLiteDatabase mDatabase;
    private Context mContext = null;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, 2);
        DB_PATH= context.getApplicationInfo().dataDir+"/databases/";
        this.mContext = context;
    }

    public void openDataBase(){
        String myPath = DB_PATH+DB_NAME;
        mDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void copyDatabase() throws IOException{
        try{
            InputStream myInput = mContext.getAssets().open(DB_NAME);
            String outputFileName = DB_PATH+DB_NAME;
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int lenght;
            while((lenght = myInput.read(buffer))>0)
                myOutput.write(buffer,0,lenght);

            myOutput.flush();
            myOutput.close();
            myInput.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private boolean checkDatabase(){
        SQLiteDatabase tempDB = null;
        try{
            String myPath = DB_PATH+DB_NAME;
            tempDB = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
        }catch(SQLiteException e){
            e.printStackTrace();
        }
        if(tempDB!=null)
            tempDB.close();
        return tempDB!=null?true:false;

    }

    public void createDatabase() throws IOException{
        boolean isDBExists = checkDatabase();
        if(isDBExists){

        }else{
            this.getReadableDatabase();
            try{
                copyDatabase();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public synchronized void close() {
        if(mDatabase != null){
            mDatabase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //CRUD for table
    public List<Question> getAllQuestion(){
        List<Question> listQuestion = new ArrayList<Question>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try{
            c =db.rawQuery("SELECT * FROM Question ORDER BY Random() LIMIT 30",null);
            if(c==null) return null;
            c.moveToFirst();
            do{
                int id =c.getColumnIndex("ID");
                String Image = c.getString(c.getColumnIndex("Image"));
                String AnswerA = c.getString(c.getColumnIndex("AnswerA"));
                String AnswerB= c.getString(c.getColumnIndex("AnswerB"));
                String AnswerC = c.getString(c.getColumnIndex("AnswerC"));
                String AnswerD = c.getString(c.getColumnIndex("AnswerD"));
                String CorrectAnswer = c.getString(c.getColumnIndex("CorrectAnswer"));

                Question question = new Question(id, Image,AnswerA,AnswerB,AnswerC,AnswerD,CorrectAnswer);
                listQuestion.add(question);
            }while(c.moveToNext());
            c.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        db.close();
        return listQuestion;
    }

    //Insert score to ranking table
    public void insertScore(int Score,String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Score",Score);
        contentValues.put("Username",username);
        db.insert("Ranking",null,contentValues);

    }

    //Get score and sort ranking
    public List<Ranking> getRanking(){
        List<Ranking> listRanking = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try{
            c = db.rawQuery("SELECT * FROM Ranking ORDER BY Score DESC",null);
            if(c==null) return null;
            c.moveToNext();
            do{
                int ID = c.getInt(c.getColumnIndex("ID"));
                int Score = c.getInt(c.getColumnIndex("Score"));
                String Username = c.getString(c.getColumnIndex("Username"));

                Ranking ranking = new Ranking(ID,Score,Username);
                listRanking.add(ranking);

            }while (c.moveToNext());
            c.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        db.close();
        return listRanking;
    }

}
