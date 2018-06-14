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
import android.widget.Toast;


import java.util.ArrayList;

public class VentasAdapter extends RecyclerView.Adapter<VentasAdapter.ViewHolder>{
    private Context contexto;
    private ArrayList<VentasCortes> Mlista;
    VentasAdapter (Context cont, ArrayList<VentasCortes> lista){
        contexto=cont;
        Mlista=lista;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflado= LayoutInflater.from(contexto);
        View view= inflado.inflate(R.layout.items_data,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView nocliente,venta,bloqueado,foliodesbloqueo,sumtotal,ciudad,estatus,fimpresion,estadoped;
        CardView tarjeta;
        final VentasCortes x= Mlista.get(position);
        nocliente=holder.NoCliente;
        venta=holder.Venta;
        bloqueado=holder.Bloqueado;
        foliodesbloqueo=holder.FolioDesbloqueo;
        sumtotal=holder.SumaTotal;
        ciudad=holder.Ciudad;
        estatus=holder.Estatus;
        fimpresion=holder.FechaImpresion;
        estadoped=holder.EstadoPed;
        tarjeta=holder.tarjeta;

        nocliente.setText(x.getNUM_CLIENTE()+" "+x.getCLIENTE());
        venta.setText(x.getVENTA());
        bloqueado.setText(x.getBLOQUEADO());
        foliodesbloqueo.setText(x.getFOLIODESBLOQUEO());
        sumtotal.setText(x.getSUMATOTAL());
        ciudad.setText(x.getCIUDAD());
        estatus.setText(x.getESTATUS());
        fimpresion.setText(x.getFECHADEIMPRESION());
        estadoped.setText(x.getESTADO_PED());

        tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, segundapantalla.class);
                intent.putExtra("Venta", x.getVENTA().trim());
                intent.putExtra("Total", x.getSUMATOTAL().trim());
                intent.putExtra("Bloqueado", x.getBLOQUEADO().trim());
                intent.putExtra("FolioD", x.getFOLIODESBLOQUEO().trim());
                intent.putExtra("Esta", x.getESTATUS().trim());
                intent.putExtra("HraImpresio", x.getFECHADEIMPRESION().trim());
                intent.putExtra("City", x.getCIUDAD().trim());
                intent.putExtra("EsPed", x.getESTADO_PED().trim());
                intent.putExtra("Client", x.getNUM_CLIENTE()+" "+x.getCLIENTE().trim());
                contexto.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Mlista.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView NoCliente,Venta,Bloqueado,FolioDesbloqueo,SumaTotal,Ciudad,Estatus,FechaImpresion,EstadoPed;
        CardView tarjeta;
        public ViewHolder(View viewItems){
            super(viewItems);
            NoCliente=viewItems.findViewById(R.id.N_Cliente);
            Venta=viewItems.findViewById(R.id.venta);
            Bloqueado=viewItems.findViewById(R.id.TwBloq);
            FolioDesbloqueo=viewItems.findViewById(R.id.TwFolioDesbloq);
            SumaTotal=viewItems.findViewById(R.id.SumaTotal);
            Ciudad=viewItems.findViewById(R.id.TwCiudad);
            Estatus=viewItems.findViewById(R.id.TwEstatus);
            FechaImpresion=viewItems.findViewById(R.id.TwImpresion);
            EstadoPed=viewItems.findViewById(R.id.TwEstadoPed);
            tarjeta=viewItems.findViewById(R.id.VistaCartaMedio);
        }
    }

}
