package com.example.dichvugiacsay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dichvugiacsay.Model.User_CardView;
import com.example.dichvugiacsay.R;

import java.util.List;

public class User_CardViewAdapter extends RecyclerView.Adapter<User_CardViewAdapter.UserViewHolder>{

    private Context context;
    private List<User_CardView> mList;

    private User_CardViewAdapter.UserListener UserListener;

    public interface User_CardViewITF{void xuli(int pos);}

    public interface UserListener{
        void onItemClick( int pos);
    }

    public void setListener(User_CardViewAdapter.UserListener UserListener) {
        this.UserListener = UserListener;
    }

    User_CardViewITF userCardViewITF;
    public User_CardViewAdapter(Context context, User_CardViewITF userCardViewITF) {
        this.context = context;
        this.userCardViewITF = userCardViewITF;
    }


    public void setData(List<User_CardView> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User_CardView userCartView = mList.get(position);
        if(userCartView == null) {
            return;
        }
        holder.imgUser.setImageResource(userCartView.getResourceImage());
        holder.txtName.setText(userCartView.getName());
        holder.itemView.setOnClickListener(v->{
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION && UserListener != null) {
                UserListener.onItemClick(adapterPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList != null) {
            return mList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgUser;
        private TextView txtName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUser = itemView.findViewById(R.id.imgCartView);
            txtName = itemView.findViewById(R.id.textViewName);

        }
    }
}
