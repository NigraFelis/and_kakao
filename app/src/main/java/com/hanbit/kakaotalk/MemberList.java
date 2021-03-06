package com.hanbit.kakaotalk;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MemberList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_list);

        final Context context=MemberList.this;
        ListView listView=(ListView) findViewById(R.id.listView);
        final FriendList friendList = new FriendList(context);
        ArrayList<Member> friends =(ArrayList<Member>) new Service.iList(){
            @Override
            public ArrayList<?> execute(Object o) {
                return null;
            }
        }.execute(null);

        listView.setAdapter(new MemberAdapter(context, friends));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });




        findViewById(R.id.DetailBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"클 릭!!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(context,MemberDetail.class));
            }
        });
        findViewById(R.id.Add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"클 릭!!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(context,MemberAdd.class));
            }
        });
        findViewById(R.id.LogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"클 릭!!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(context,Index.class));
            }
        });
    }

    private abstract class ListQuery extends Index.QueryFactory {
        SQLiteOpenHelper helper;
        public ListQuery(Context context) {
            super(context);
            helper=new Index.SqLiteHelper(context);
        }
        @Override
        public SQLiteDatabase getDatabase() {
            return helper.getReadableDatabase();
        }
    }
    private class FriendList extends ListQuery{
        public FriendList (Context context) {
            super(context);
        }
        public ArrayList<Member> execute(){
            ArrayList<Member> list = new ArrayList<>();
            String sql=String.format("select * from %s ;",Cons.MEM_TBL);
            Cursor cursor=super.getDatabase().rawQuery(sql,null);
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do{
                        Member member = null;
                        member = new Member();
                        member.setSeq(cursor.getString(cursor.getColumnIndex(Cons.SEQ)));
                        member.setName(cursor.getString(cursor.getColumnIndex(Cons.NAME)));
                        member.setEmail(cursor.getString(cursor.getColumnIndex(Cons.EMAIL)));
                        member.setPass(cursor.getString(cursor.getColumnIndex(Cons.PASS)));
                        member.setAddr(cursor.getString(cursor.getColumnIndex(Cons.ADDR)));
                        member.setPhone(cursor.getString(cursor.getColumnIndex(Cons.PHONE)));
                        member.setProfimg(cursor.getString(cursor.getColumnIndex(Cons.PROF_IMG)));
                        list.add(member);
                    }while(cursor.moveToNext());
                }
            }

            return list;
        }
    }
    class MemberAdapter extends BaseAdapter{
        ArrayList<Member> list;
        LayoutInflater inflater;
        public MemberAdapter(Context context, ArrayList<Member>list) {
            this.list=list;
            this.inflater=LayoutInflater.from(context);
        }
        private int[] photos={
                R.drawable.cupcake,
                R.drawable.donut,
                R.drawable.eclair,
                R.drawable.froyo,
                R.drawable.gingerbread,
                R.drawable.honeycomb
        };

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View v, ViewGroup g) {
            ViewHolder viewHolder;
            if(v==null){
                v=inflater.inflate(R.layout.member_adapter,null);
                viewHolder = new ViewHolder();
                viewHolder.imageView= (ImageView) v.findViewById(R.id.imageView);
                viewHolder.name= (TextView) v.findViewById(R.id.name);
                /*viewHolder.phone= (TextView) v.findViewById(R.id.);*/
                v.setTag(viewHolder);
            }else {
                viewHolder=(ViewHolder) v.getTag();
            }
            viewHolder.imageView.setImageResource(photos[i]);
            viewHolder.name.setText(list.get(i).getName());
            viewHolder.phone.setText(list.get(i).getPhone());
            return null;
        }
    }
    static class ViewHolder{
        ImageView imageView;
        TextView name;
        TextView phone;
    }

}
