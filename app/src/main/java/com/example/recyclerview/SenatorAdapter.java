package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.domain.Political;
import com.example.recyclerview.domain.PoliticalParty;
import com.example.recyclerview.domain.enumeration.ElectivePositionType;

import java.util.ArrayList;

public class SenatorAdapter extends RecyclerView.Adapter<SenatorAdapter.ViewHolder>{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private SenatorAdapter.OnItemClickListener clickListener;

    private Context context;

    public interface OnItemClickListener{
        void  onItemClick(View view, int position);
    }

    public void setOnItemClickListener(SenatorAdapter.OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

public class ViewHolder extends RecyclerView.ViewHolder{
    TextView textView1;
    TextView textView2;
    public  ViewHolder(View itemView){
        super(itemView);
        textView1 = itemView.findViewById(R.id.textView1);
        textView2 = itemView.findViewById(R.id.textView2);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener != null){
                    clickListener.onItemClick(view, getAdapterPosition());
                }
            }
        });
    }
}

    @NonNull
    @Override
    public SenatorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(
                R.layout.recycler_view_item,
                parent,
                false
        );
        return new SenatorAdapter.ViewHolder(itemView);
    }

//    @Override
//    public void onBindViewHolder(@NonNull SenatorAdapter.ViewHolder holder, int position) {
//        ArrayList<Political> politicals;
//        if(name == null){
//            politicals = Data.getInstance().getPoliticalByType(ElectivePositionType.SENADOR);
//        }else{
//            politicals = Data.getInstance().getPoliticalByTypeAndName(ElectivePositionType.SENADOR, name);
//        }
//
//        Political political = getPolitical(position, politicals);
//        PoliticalParty politicalParty = Data.getInstance().getPoliticalPartyFromId(political.getPoliticalParty());
//        holder.textView1.setText(political.getName());
//        holder.textView2.setText(politicalParty.getAcronym());
//    }
//
//    @Override
//    public int getItemCount() {
//
//        if(name == null){
//            return Data.getInstance().getPoliticalByType(ElectivePositionType.SENADOR).size();
//        }else{
//            return Data.getInstance().getPoliticalByTypeAndName(ElectivePositionType.SENADOR, name).size();
//        }
//    }
//
//    public Political getPolitical(int pos, ArrayList<Political> politicals){
//        return politicals.get(pos);
//    }
//
//}
//


    @Override
    public void onBindViewHolder(@NonNull SenatorAdapter.ViewHolder holder, int position) {
        Political political = Data.getInstance().getSenator(position);
        PoliticalParty politicalParty = Data.getInstance().getPoliticalPartyFromId(political.getPoliticalParty());
        holder.textView1.setText(political.getName());
        holder.textView2.setText(politicalParty.getAcronym());
    }

    @Override
    public int getItemCount() {
        return Data.getInstance().getSenators().size();
    }}

