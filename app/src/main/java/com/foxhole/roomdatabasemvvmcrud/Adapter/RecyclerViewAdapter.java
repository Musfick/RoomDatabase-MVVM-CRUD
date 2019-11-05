package com.foxhole.roomdatabasemvvmcrud.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foxhole.roomdatabasemvvmcrud.Model.Player;
import com.foxhole.roomdatabasemvvmcrud.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Player> players = new ArrayList<>();
    private OnPlayerClickListner onPlayerClickListner;

    public void setPlayers(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    public void setItemOnClick(OnPlayerClickListner onPlayerClickListner){
        this.onPlayerClickListner = onPlayerClickListner;
    }

    public Player getPlayerAt(int position)
    {
        Player player = players.get(position);
        player.setId(players.get(position).getId());
        return player;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout,null);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,onPlayerClickListner);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        Player currentPlayer = players.get(position);
        holder.mName.setText("Name: "+currentPlayer.getName());
        holder.mAge.setText("Age: " +currentPlayer.getAge());
        holder.mPosition.setText("Position: " +currentPlayer.getPosition());

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mName;
        private TextView mAge;
        private TextView mPosition;
        private OnPlayerClickListner mListener;

        public RecyclerViewHolder(@NonNull View itemView, OnPlayerClickListner onPlayerClickListner) {
            super(itemView);
            this.mListener = onPlayerClickListner;
            itemView.setOnClickListener(this);
            mName = itemView.findViewById(R.id.tv_name);
            mAge = itemView.findViewById(R.id.tv_age);
            mPosition = itemView.findViewById(R.id.tv_position);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Player currentPlayer = players.get(position);
            Player player = new Player(currentPlayer.getName(),currentPlayer.getPosition(),currentPlayer.getAge());
            player.setId(currentPlayer.getId());
            mListener.onPlayerClick(player);
        }
    }

    public interface OnPlayerClickListner{
        void onPlayerClick(Player player);
    }
}
