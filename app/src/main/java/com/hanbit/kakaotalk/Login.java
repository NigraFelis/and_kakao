package com.hanbit.kakaotalk;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.hanbit.kakaotalk.R.layout.login;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(login);
        final Context context=Login.this;
        final EditText inputid=(EditText) findViewById(R.id.inputId);
        final EditText inputPass=(EditText) findViewById(R.id.inputPass);
        final MemberLogin login = new MemberLogin(context);

        findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = String.valueOf(inputid.getText());
                final String pass = String.valueOf(inputPass.getText());
                Toast.makeText(context,"입력된 ID : "+id,Toast.LENGTH_LONG).show();
                Log.d("입력된 ID : ",id);
                Log.d("입력된 Pass : ",pass);

                new Service.iPredicate() {
                    @Override
                    public void execute() {
                        if(login.execute(id,pass)){
                            Toast.makeText(context,"클 릭",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(context,MemberList.class));
                        }else{
                            Toast.makeText(context,"클 릭",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(context,Login.class));
                        }
                    }
                }.execute();

            }
        });
        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

    }

    private abstract class LoginQuery extends Index.QueryFactory {
        SQLiteOpenHelper helper;
        public LoginQuery(Context context) {
            super(context);
            helper=new Index.SqLiteHelper(context);
        }
        @Override
        public SQLiteDatabase getDatabase() {
            return helper.getReadableDatabase();
        }
    }
    private class MemberLogin extends LoginQuery{
        public MemberLogin(Context context) {
            super(context);
        }
        public boolean execute(String id, String pass){
            return super.getDatabase().rawQuery(String.format(
                    " SELECT * FROM %s WHERE %s ='%s' AND %s ='%s' ;",
                    Cons.MEM_TBL,Cons.SEQ,id,Cons.PASS,pass),null)
                    .moveToNext();
        }
    }
}