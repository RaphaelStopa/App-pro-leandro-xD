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

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DeputyAdapter extends RecyclerView.Adapter<DeputyAdapter.ViewHolder>{

    private OnItemClickListener clickListener;

    public OnItemClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name= "";


    public interface OnItemClickListener{
        void  onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(
                R.layout.recycler_view_item,
                parent,
                false
        );
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Political political = Data.getInstance().getDeputy(position);
        PoliticalParty politicalParty = Data.getInstance().getPoliticalPartyFromId(political.getPoliticalParty());
        holder.textView1.setText(political.getName());
        holder.textView2.setText(politicalParty.getAcronym());
    }


    @Override
    public int getItemCount() {
        return Data.getInstance().getDeputies().size();
    }

}

