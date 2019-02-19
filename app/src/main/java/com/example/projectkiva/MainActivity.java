package com.example.projectkiva;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static Context context;

    public static Context getAppContext()
    {
        return MainActivity.context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://api.kivaws.org/v1/loans/search.json&country_code=HN";

        //new JsonObjectRequest(metodo , url, null , Response.Listener<JSONObject> , Response.ErrorListener);
        JsonRequest<JSONObject> jor = new JsonObjectRequest(
                Request.Method.GET,url,null

                ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray array = response.getJSONArray("loans");
                    final JSONObject [] objetos = new JSONObject[array.length()];
                    for(int i=0;i<objetos.length;i++){
                        objetos[i]= (JSONObject)array.get(i);
                    }
                    JsonAdapter JA = new JsonAdapter(getAppContext(),R.layout.activity_detalle,objetos);
                    ListView lv = (ListView)findViewById(R.id.listView);
                    lv.setAdapter(JA);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            JSONObject obj = objetos[position];
                            Intent det = new Intent(MainActivity.this, Activity_Detalle.class);
                            det.putExtra("elemento", obj.toString());
                            startActivityForResult(det, 1);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
                ,new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        }
        );

        RequestQueue rq = VolleySingleton.getInstance().getRequestQueue();
        rq.add(jor);

    }
}
