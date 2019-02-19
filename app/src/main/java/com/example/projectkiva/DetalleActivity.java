package com.example.projectkiva;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

public class DetalleActivity extends AppCompatActivity {

    TextView t;
    int idimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Intent intent = getIntent();
        String objeto =  intent.getExtras().getString("elemento");

        try{
            JSONObject obj = new JSONObject(objeto);
            idimg = obj.getJSONObject("image").getInt("id");
            String UrlImage = String.format("https://www-kiva-org-1.global.ssl.fastly.net/img/w960/%d.jpg", idimg);
            NetworkImageView niv  = (NetworkImageView) findViewById(R.id.imagen);
            niv.setImageUrl(UrlImage, VolleySingleton.getInstance().getImageLoader());

            TextView nombre = (TextView)findViewById(R.id.textViewNombre);
            nombre.setText(obj.get("name").toString());

            TextView sector = (TextView)findViewById(R.id.textViewSector);
            sector.setText(obj.get("sector").toString());

            TextView monto = (TextView)findViewById(R.id.textViewMonto);
            monto.setText(obj.get("loan_amount").toString());

            TextView uso = (TextView)findViewById(R.id.textViewUso);
            uso.setText(obj.get("use").toString());

            TextView fecha = (TextView)findViewById(R.id.textViewFecha);
            fecha.setText(obj.get("posted_date").toString());
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

}
