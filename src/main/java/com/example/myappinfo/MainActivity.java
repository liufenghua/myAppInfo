
package com.example.myappinfo;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    private ListView listview;
    private ArrayList<Map<String,Object>>ApplistItem=new ArrayList<Map<String,Object>>();
    private MyBaseAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=(ListView)this.findViewById(R.id.mylistview);
        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packages.size();i++){            
            PackageInfo packageInfo = packages.get(i); 
            Map<String,Object> mymap=new HashMap<String,Object>();
            mymap.put("name", packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
            mymap.put("drawable", packageInfo.applicationInfo.loadIcon(getPackageManager()));
            mymap.put("isSys", (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)==0);
            ApplistItem.add(mymap);
        }
        myadapter=new MyBaseAdapter(this,ApplistItem);
        listview.setAdapter(myadapter);
        listview.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String info=(boolean)(ApplistItem.get(position)).get("isSys")?"是非系统的应用":"是系统的应用";
               Toast.makeText(MainActivity.this, ApplistItem.get(position).get("name")+info, Toast.LENGTH_LONG).show();
            }
            
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
