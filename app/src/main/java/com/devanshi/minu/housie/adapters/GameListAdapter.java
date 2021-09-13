package com.devanshi.minu.housie.adapters;

import android.content.*;
import android.text.*;
import android.text.style.*;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.recyclerview.widget.*;

import com.devanshi.minu.housie.*;
import com.devanshi.minu.housie.interfaces.*;
import com.devanshi.minu.housie.models.*;

import java.util.*;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {

    private ArrayList<GameData> gameArrayList;
    private GameTicketClickInterface gameTicketClickInterface;
    private Context context;

    public GameListAdapter(ArrayList<GameData> gameArrayList, GameTicketClickInterface gameTicketClickInterface, Context context) {

        this.gameArrayList = gameArrayList;
        this.gameTicketClickInterface = gameTicketClickInterface;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_game_recycle, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GameData gameData = gameArrayList.get(position);
        holder.txtGameTitle.setText(gameData.getTitle());
        holder.txtGameDetailDate.setText(gameData.getGameDate());
        holder.txtGameDetailTime.setText(gameData.getGameTime());
        holder.txtExpert.setText(context.getString(R.string.expert, gameData.getExpert()));
        holder.buyTicket.setOnClickListener(v -> gameTicketClickInterface.onButtonClick(gameData));
        String descrips = context.getString(R.string.see_description);
        SpannableString text = new SpannableString(descrips);
        text.setSpan(new UnderlineSpan(), 0, descrips.length(), 0);
        holder.description.setText(text);
        holder.description.setTextColor(context.getColor(R.color.colorPrimary));
        holder.description.setOnClickListener(v->gameTicketClickInterface.onDescriptionClick(gameData.getDescription()));
    }

    @Override
    public int getItemCount() {
        return gameArrayList.size();
    }

    public void refreshList(ArrayList<GameData> menuArrayList) {

        this.gameArrayList = menuArrayList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtGameTitle;
        TextView txtGameDetailDate;
        TextView txtGameDetailTime;
        TextView txtExpert;
        TextView description;
        private TextView buyTicket;

        ViewHolder(View itemView) {
            super(itemView);
            this.txtGameTitle = itemView.findViewById(R.id.txtGameHeaderDate);
            this.txtGameDetailDate = itemView.findViewById(R.id.txtGameDetailDate);
            this.txtGameDetailTime = itemView.findViewById(R.id.txtGameDetailTime);
            this.txtExpert = itemView.findViewById(R.id.expert);
            this.description = itemView.findViewById(R.id.description);
            this.buyTicket = itemView.findViewById(R.id.buyTicket);
        }
    }

}
