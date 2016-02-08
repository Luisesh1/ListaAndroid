package com.reser.luisesh1.lista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.reser.luisesh1.lista.Models.Elements;

import java.lang.annotation.ElementType;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddActivity extends AppCompatActivity {
    Button BTlimpiar;
    Button BTGuardar;
    TextView TXTtitulo;
    TextView TXTnota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        BTlimpiar = (Button) findViewById(R.id.BTNlimpiar);
        BTGuardar = (Button) findViewById(R.id.BTNregistrar);
        TXTnota = (TextView) findViewById(R.id.TBNota);
        TXTtitulo = (TextView) findViewById(R.id.TBTitulo);
        BTGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TXTtitulo.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No se puede dejar el titulo vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                Realm realm = Realm.getInstance(getApplicationContext());
                int id;
                RealmResults<Elements> res = realm.where(Elements.class).findAll();
                if (res.size()!=0)
                    id =  res.get(res.size()-1).getId();
                else
                    id =0;
                realm.beginTransaction();
                Elements e = realm.createObject(Elements.class);
                e.setId(id + 1);
                e.setTitulo(TXTtitulo.getText().toString());
                e.setDescripcion(TXTnota.getText().toString());
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Se a agregado", Toast.LENGTH_SHORT).show();
                AddActivity.this.onBackPressed();
            }
        });
        BTlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TXTtitulo.setText("");
                TXTnota.setText("");
                Realm re = Realm.getInstance(getApplicationContext());
                RealmResults<Elements> res = re.where(Elements.class).findAll();
                re.beginTransaction();
                res.removeAll(res);
                re.commitTransaction();
            }
        });
    }
}
