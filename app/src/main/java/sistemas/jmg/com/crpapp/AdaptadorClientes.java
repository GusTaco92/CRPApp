package sistemas.jmg.com.crpapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorClientes extends RecyclerView.Adapter<AdaptadorClientes.ViewHolder>
{
    private Context contexto;
    private ArrayList<ListaClientes> Mlista;
    AdaptadorClientes(Context cont, ArrayList<ListaClientes> lista){
        contexto=cont;
        Mlista=lista;
    }
    @NonNull
    @Override
    public AdaptadorClientes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflado= LayoutInflater.from(contexto);
        View view= inflado.inflate(R.layout.listaclientesprincipal,parent,false);
        ViewHolder holder=new AdaptadorClientes.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        TextView NUMERO,NOMBRE;
        CardView TARJETA;
        final ListaClientes x= Mlista.get(position);
        NUMERO=holder.numero;
        NOMBRE=holder.nombre;
        TARJETA=holder.tarjeta;

        NUMERO.setText(x.getNUMEROCLIENTE().trim());
        NOMBRE.setText(x.getNOMBRECLIENTE().trim());

        TARJETA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, TerceraPantalla.class);
                intent.putExtra("NUMEROCLIENTE", x.getNUMEROCLIENTE());
                intent.putExtra("RUTAPRINCIPAL", x.getRUTA());
                intent.putExtra("CLAVEPRINCIPAL", x.getCLAVEVENDEDOR());
                intent.putExtra("TIPO", x.getTIPO());
                intent.putExtra("SEMANA", x.getSEMANA());
                contexto.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Mlista.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView numero,nombre;
        CardView tarjeta;
        public ViewHolder(View itemView) {
            super(itemView);
            numero=(TextView)itemView.findViewById(R.id.ListaClientesNumero);
            nombre=(TextView)itemView.findViewById(R.id.ListaClientesNombre);
            tarjeta=(CardView)itemView.findViewById(R.id.VistaCartaCliente);
        }
    }
}
