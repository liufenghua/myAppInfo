package com.example.myappinfo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Map;
//注释
public class MyBaseAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Map<String,Object>>Appinfolist;   
    public MyBaseAdapter(Context mContext,ArrayList<Map<String,Object>>Appinfolist){
        this.mContext=mContext;
        this.Appinfolist=Appinfolist;
        Log.v("myappinfo", this.Appinfolist.size()+""+"MyBaseAdapter");
    }
    @Override
    public int getCount() {        
        return Appinfolist.size();
    }

    @Override
    public Object getItem(int position) {
       return Appinfolist.get(position);
    }

    @Override
    public long getItemId(int position) {        
        return position;
    }
    public class ViewHolder{
        public TextView myTextView1;
        public TextView myTextView2;       
        public ImageView imageview;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       
       ViewHolder viewholder=null;
       final Map<String,Object>appinfoitem=(Map<String,Object>)this.getItem(position);
       Log.v("myappinfo", "**************");
       if(convertView==null){
           convertView =LayoutInflater.from(mContext).inflate(R.layout.item, null);
           viewholder=new ViewHolder();
           viewholder.myTextView1=(TextView)convertView.findViewById(R.id.mytextview1);
           viewholder.myTextView2=(TextView)convertView.findViewById(R.id.mytextview2);
           viewholder.imageview = (ImageView)convertView.findViewById(R.id.myimageview);
           convertView.setTag(viewholder);
       }else{
           viewholder = (ViewHolder)convertView.getTag();   
        }
       viewholder.myTextView1.setText(appinfoitem.get("name").toString());
       viewholder.myTextView2.setText((boolean) appinfoitem.get("isSys")?"非系统应用":"系统应用");
       viewholder.imageview.setImageDrawable(((Drawable)appinfoitem.get("drawable")));
             
       viewholder.myTextView1.setOnClickListener(new OnClickListener(){
        @Override
            public void onClick(View v) {
               Toast.makeText(mContext, ((TextView)v).getText().toString(), 100).show();
         }           
       });
       
      /* convertView.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
               TextView tx=(TextView)v.findViewById(R.id.mytextview5);
               Toast.makeText(mContext, tx.getText().toString()+"这一行", 100).show();
            }           
       });  */    
       Log.v("myappinfo", convertView.toString());
        return convertView;
    }

}
