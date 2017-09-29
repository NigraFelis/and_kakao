package com.hanbit.kakaotalk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MemberUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_update);

        final Context context=MemberUpdate.this;
        findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"클 릭!!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(context,MemberDetail.class));
            }
        });
        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"클 릭!!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(context,MemberList.class));
            }
        });
    }
}
