package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.domain.Political;
import com.example.recyclerview.domain.Project;
import com.example.recyclerview.domain.Vote;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder>{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OnItemClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private DetailsAdapter.OnItemClickListener clickListener;

    public interface OnItemClickListener{
        void  onItemClick(View view, int position);
    }

    public void setOnItemClickListener(DetailsAdapter.OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        public  ViewHolder(View itemView){
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView7);
            textView4 = itemView.findViewById(R.id.textView8);


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
    public DetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(
                R.layout.recycler_view_item2,
                parent,
                false
        );
        return new DetailsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.ViewHolder holder, int position) {
        ArrayList<Vote> votes = Data.getInstance().getVotesByPolitical(id);
        Vote vote = getVote(position, votes);
        Project project = Data.getInstance().getProjectFromID(vote.getProject()) ;
        holder.textView1.setText(project.getNumberOfProject() + " - "+ project.getName());
        holder.textView2.setText(vote.voteToPortuguese());
        holder.textView3.setText(project.getBrief());
        holder.textView4.setText(project.isWasApproved() == false ? "Rejeitado." : "Aprovado.");

    }

    public Vote getVote(int pos, ArrayList<Vote> votes){
        return votes.get(pos);
    }


    @Override
    public int getItemCount() {
        return Data.getInstance().getVotesByPolitical(id).size();
    }
}

