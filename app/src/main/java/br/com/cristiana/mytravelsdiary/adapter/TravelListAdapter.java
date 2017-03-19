package br.com.cristiana.mytravelsdiary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.cristiana.mytravelsdiary.R;
import br.com.cristiana.mytravelsdiary.listener.OnClickListener;
import br.com.cristiana.mytravelsdiary.model.Travel;

/**
 * Created by Cristiana on 19/03/2017.
 */

public class TravelListAdapter extends
        RecyclerView.Adapter<TravelListAdapter.TravelsViewHolder> {

    private Context context;
    private List<Travel> travels;
    private OnClickListener clickListener;

    public TravelListAdapter(Context context, List<Travel> travels, OnClickListener clickListener) {
        this.context = context;
        this.travels = travels;
        this.clickListener = clickListener;
    }

    @Override
    public TravelsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.travel_item, parent, false);

        return new TravelsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final TravelsViewHolder holder, final int position) {

        holder.tvDestiny.setText(travels.get(position).getDestiny());
        holder.tvValue.setText(travels.get(position).getValue());
        holder.tvHotel.setText(travels.get(position).getHotel());

        if (clickListener !=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(holder.itemView, position);
                }
            });
        }
    }

    public Travel getItem(int position){
        return travels.get(position);
    }

    @Override
    public int getItemCount() {
        return travels.size();
    }

    public static class TravelsViewHolder extends RecyclerView.ViewHolder {
        TextView tvDestiny;
        TextView tvValue;
        TextView tvHotel;


        public TravelsViewHolder(View itemView) {
            super(itemView);
            tvDestiny = (TextView) itemView.findViewById(R.id.tv_destiny);
            tvValue = (TextView) itemView.findViewById(R.id.tv_value);
            tvHotel = (TextView) itemView.findViewById(R.id.tv_hotel);
        }
    }
}
