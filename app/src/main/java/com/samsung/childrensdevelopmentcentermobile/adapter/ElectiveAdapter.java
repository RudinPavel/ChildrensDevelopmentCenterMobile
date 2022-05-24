package com.samsung.childrensdevelopmentcentermobile.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.childrensdevelopmentcentermobile.MainActivity;
import com.samsung.childrensdevelopmentcentermobile.R;
import com.samsung.childrensdevelopmentcentermobile.fragment.ChangeElectiveFragment;
import com.samsung.childrensdevelopmentcentermobile.model.Elective;

import java.util.List;

public class ElectiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<Elective> electiveList;
    private Context context;

    public ElectiveAdapter(Context context, List<Elective> electiveList) {

        this.inflater = LayoutInflater.from(context);
        this.electiveList = electiveList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName,
                tvDirection,
                tvTutor;
        private final LinearLayout ll_item;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            ll_item = itemView.findViewById(R.id.ll_item);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDirection = itemView.findViewById(R.id.tv_direction);
            tvTutor = itemView.findViewById(R.id.tv_tutor);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_elective_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder,
            @SuppressLint("RecyclerView") int position
    ) {

        Elective elective = electiveList.get(position);

        ((MyViewHolder) holder).tvName.setText(elective.getName());
        ((MyViewHolder) holder).tvDirection.setText(elective.getDirection().getName());
        ((MyViewHolder) holder).tvTutor.setText(elective.getTutor().getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeElectiveFragment changeClientFragment = new ChangeElectiveFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable(MainActivity.MSG_NAME, electiveList.get(position));
                changeClientFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, changeClientFragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {

        return electiveList.size();
    }
}