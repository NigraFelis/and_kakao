package com.hanbit.kakaotalk;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import static com.hanbit.kakaotalk.R.layout.index;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(index);
        final Context context=Index.this;
        Handler handler=new Handler();
        SqLiteHelper helper = new SqLiteHelper(context);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(context,Login.class));
                finish();
            }
        },2000);



       /* findViewById(R.id.loginbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"클 릭!!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,login.class);
                startActivity(new Intent(context,login.class));
            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    public static class SqLiteHelper extends SQLiteOpenHelper {

        public SqLiteHelper(Context context) {

            super(context,"hanbit.db",null,1);
            this.getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql=String.format(" CREATE TABLE IF NOT EXISTS "+
                    " %s( " +
                    "         %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "         %s TEXT, %s TEXT, %s TEXT, %s TEXT, " +
                    "         %s TEXT, %s TEXT " +
                    " );",Cons.MEM_TBL,Cons.SEQ,Cons.PASS,Cons.NAME,Cons.EMAIL,Cons.PHONE,Cons.ADDR,Cons.PROF_IMG);
            db.execSQL(sql);

           /* for(int i=0;i<6;i++){
                db.execSQL(String.format(" INSERT INTO %s(%s,%s,%s,%s,%s,%s) " +
                                " VALUES ('%s','%s','%s','%s','%s','%s'); ",Cons.MEM_TBL,Cons.PASS,Cons.NAME,Cons.EMAIL,Cons.PHONE,Cons.ADDR,Cons.PROFILEIMAGE,
                        "1","홍길동"+i,"hong"+i+"@test.com","010-1234-567"+i,"서울"+i,"default_img"));
            }*/
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    public static abstract class QueryFactory{
        Context context;
        //자동 생성자 단축키 alt + insert

        public QueryFactory(Context context) {
            this.context = context;
        }
        public abstract SQLiteDatabase getDatabase();
    }
}