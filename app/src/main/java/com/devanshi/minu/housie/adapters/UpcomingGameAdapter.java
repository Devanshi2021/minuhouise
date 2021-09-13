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

public class UpcomingGameAdapter extends RecyclerView.Adapter<UpcomingGameAdapter.ViewHolder>{
    private ArrayList<ContestList> contestListArrayList;
    private Context context;
    private UpcomingGameClickInterface upcomingGameClickInterface;

    public UpcomingGameAdapter(ArrayList<ContestList> contestListArrayList, Context context, UpcomingGameClickInterface upcomingGameClickInterface) {
        this.contestListArrayList = contestListArrayList;
        this.context = context;
        this.upcomingGameClickInterface = upcomingGameClickInterface;
    }

    public void refreshList(ArrayList<ContestList> contestListArrayList) {
        this.contestListArrayList = contestListArrayList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_upcoming_game_recycle, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int ticketPosition) {
        ContestList contestList = contestListArrayList.get(ticketPosition);
        holder.game_title.setText(contestList.getTitle());
        holder.expert.setText(context.getString(R.string.expert, contestList.getExpert()));
        holder.txtGameDetailDate.setText(contestList.getGameDate());
        holder.txtGameDetailTime.setText(contestList.getGameTime());
        String descrips = context.getString(R.string.see_description);
        SpannableString text = new SpannableString(descrips);
        text.setSpan(new UnderlineSpan(), 0, descrips.length(), 0);
        holder.description.setText(text);
        holder.description.setTextColor(context.getColor(R.color.colorPrimary));
        holder.description.setOnClickListener(v->upcomingGameClickInterface.descriptionClick(contestList.getDescription()));
        holder.download.setOnClickListener(v->upcomingGameClickInterface.downloadClick(contestList.getImage()));
        holder.share.setOnClickListener(v->upcomingGameClickInterface.shareClick(contestList.getImage()));
        if (contestList.getJoinedGameStatus() == 0) {
            holder.whatsapp.setOnClickListener(v -> upcomingGameClickInterface.whatsappClick(contestList));
            holder.txtWhatsapp.setVisibility(View.VISIBLE);
        } else {
            holder.whatsapp.setVisibility(View.INVISIBLE);
            holder.txtWhatsapp.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return contestListArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView expert, game_title, txtGameDetailDate, txtGameDetailTime, description, txtWhatsapp;
        ImageView download, share, whatsapp;

        ViewHolder(View itemView) {
            super(itemView);
            this.game_title = itemView.findViewById(R.id.game_title);
            this.expert = itemView.findViewById(R.id.expert);
            this.description = itemView.findViewById(R.id.description);
            this.txtGameDetailDate = itemView.findViewById(R.id.txtGameDetailDate);
            this.txtGameDetailTime = itemView.findViewById(R.id.txtGameDetailTime);
            this.download = itemView.findViewById(R.id.download);
            this.share = itemView.findViewById(R.id.share);
            this.whatsapp = itemView.findViewById(R.id.whatsapp);
            this.txtWhatsapp = itemView.findViewById(R.id.txtWhatsapp);
        }
    }

}
