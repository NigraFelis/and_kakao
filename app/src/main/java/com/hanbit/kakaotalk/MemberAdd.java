package com.hanbit.kakaotalk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MemberAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_add);

        final Context context=MemberAdd.this;
        findViewById(R.id.AddConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"클 릭!!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(context,MemberList.class));
            }
        });
        findViewById(R.id.AddCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"클 릭!!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(context,MemberList.class));
            }
        });

    }
}
