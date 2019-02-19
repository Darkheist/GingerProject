package com.example.projectkiva;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;


public class JsonAdapter extends ArrayAdapter<JSONObject>{

    public JsonAdapter(Context context, int resource, JSONObject[] objects){
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.activity_detalle, null);
        }
        JSONObject p = getItem(position);
        if (p != null){
            TextView titulo = (TextView) v.findViewById(R.id.titulo);
            TextView subtitulo = (TextView) v.findViewById(R.id.subtitulo);
            NetworkImageView imagen = (NetworkImageView) v.findViewById(R.id.imagen);

            try {
                titulo.setText(p.get("name").toString());
                subtitulo.setText(p.get("use").toString());
                String UrlImagen = String .format("https://www-kiva-org-1.global.ssl.fastly.net/img/w960/%d.jpg", p.getJSONObject("image").getInt("id"));

                imagen.setImageUrl(UrlImagen,VolleySingleton.getInstance().getImageLoader());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        return v;
    }

}
