package sistemas.jmg.com.crpapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class segundapantalla extends AppCompatActivity{

    TextView venta,total,bloq,foliods,estatus,impresion,city,esped,client;
    private JsonObjectRequest jsonRespuesta;
    private RequestQueue respuestaRq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundapantalla);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        venta=(TextView)findViewById(R.id.SVenta);
        total=(TextView)findViewById(R.id.Stotal);
        bloq=(TextView)findViewById(R.id.Sbloqueado);
        foliods=(TextView)findViewById(R.id.Sdesbloqueo);
        estatus=(TextView)findViewById(R.id.Sestatus);
        impresion=(TextView)findViewById(R.id.Shoradeimpresion);
        city=(TextView)findViewById(R.id.Sciudad);
        esped=(TextView)findViewById(R.id.Sestadoped);
        client=(TextView)findViewById(R.id.Scliente);
        respuestaRq= Volley.newRequestQueue(getBaseContext());
        Pintar();
        comentario();
    }

    public void Pintar()
    {
        venta.setText(getIntent().getExtras().getString("Venta"));
        total.setText(getIntent().getExtras().getString("Total"));
        bloq.setText(getIntent().getExtras().getString("Bloqueado"));
        foliods.setText(getIntent().getExtras().getString("FolioD"));
        estatus.setText(getIntent().getExtras().getString("Esta"));
        impresion.setText(getIntent().getExtras().getString("HraImpresio"));
        city.setText(getIntent().getExtras().getString("City"));
        esped.setText(getIntent().getExtras().getString("EsPed"));
        client.setText(getIntent().getExtras().getString("Client"));
    }

    public void comentario()
    {
        String URL="http://pruebasjmg.sytes.net/Welcome/Comentario/";
        jsonRespuesta= new JsonObjectRequest(Request.Method.POST,URL,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonarray=response.optJSONArray("datos");
                try {
                    JSONArray lista=jsonarray.getJSONArray(0);
                    for(int i=0;i < lista.length();i++){
                        JSONObject TEMP= (JSONObject) lista.get(i);
                        Toast.makeText(getBaseContext(),""+TEMP.getString("DATO1"),Toast.LENGTH_SHORT).show();
                        //ListaDatosClientes.add(new ListaClientes(TEMP.getString("DetCort_NumeroCliente"),TEMP.getString("DetCort_NombreCliente"),RUTA.getText().toString(),SemanaVer.getText().toString(),1,Csemana));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(),"Error de conexión"+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        })
        ;
        respuestaRq.add(jsonRespuesta);
    }
}
