package com.chesteve.last.operators;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.chesteve.last.R;
import java.util.ArrayList;

public class OperatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Activity myActivity;
    private ArrayList<Operators> contentList;

    public OperatorAdapter(Context context, Activity myActivity, ArrayList<Operators> contentList) {
        this.context = context;
        this.myActivity = myActivity;
        this.contentList = contentList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taxis_list, parent, false);
        return new ViewHolder(view, viewType);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView taxisname;
        private TextView location;
        private TextView email;
        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            // Find all views ids
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            taxisname = (TextView) itemView.findViewById(R.id.taxis_name);
            location = (TextView) itemView.findViewById(R.id.taxis_location);
            email = (TextView) itemView.findViewById(R.id.taxis_email);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mainHolder, int position) {
        ViewHolder holder = (ViewHolder) mainHolder;
        final Operators model = contentList.get(position);
        // setting data over views
//        String imgUrl = model.getImageUrl();
//        if (imgUrl != null && !imgUrl.isEmpty()) {
//            Glide.with(mContext)
//                    .load(imgUrl)
//                    .into(holder.imgPost);
//        }
        holder.taxisname.setText(model.getTaxisname());
        holder.location.setText(model.getLocation());
        holder.email.setText(model.getEmail());
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }


}

