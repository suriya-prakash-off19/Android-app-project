package com.example.admininterface.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admininterface.Model.meet;
import com.example.admininterface.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class meetAdapter extends RecyclerView.Adapter<meetAdapter.ViewHolder>{
    public Context mContext;

    public meetAdapter(Context mContext, List<meet> meetList) {
        this.mContext = mContext;
        this.meetList = meetList;
    }

    public List<meet> meetList;

    FirebaseUser firebaseUser;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.meeting_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final meet meet=meetList.get(position);

        holder.meeting_date.setText(meet.getMeeting_());
        holder.meeting_depart.setText(meet.getDepartment());
        holder.meeting_description.setText(meet.getMeeting_description());
        holder.meeting_guest.setText(meet.getMeeting_guest());
        holder.meeting_host.setText(meet.getMeeting_host());
        holder.meeting_time.setText(meet.getMeeting_time());
        holder.meeeting_name.setText(meet.getMeeting_name());
        holder.meeting_venue.setText(meet.getMeeting_venue());
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(v.getContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.delete,popupMenu.getMenu());
//
//            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    FirebaseDatabase.getInstance().getReference("meeting").child(meet.getId()).removeValue();
//                    return false;
//                }
//            });
                Toast.makeText(mContext, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return meetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView meeeting_name,meeting_host,meeting_guest,meeting_time,meeting_description,meeting_venue,meeting_date,meeting_depart;
        CardView cardView;

        RelativeLayout relativeLayout;

        ImageView more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            meeting_depart=itemView.findViewById(R.id.Depart);
//            cardView=itemView.findViewById(R.id.meeting_card);
            meeting_date=itemView.findViewById(R.id.Date);
            relativeLayout=itemView.findViewById(R.id.meeting);
            meeeting_name=itemView.findViewById(R.id.eventName);
            meeting_host=itemView.findViewById(R.id.Host);
            meeting_guest=itemView.findViewById(R.id.Guest);
            meeting_time=itemView.findViewById(R.id.Time);
            meeting_description=itemView.findViewById(R.id.Description);
            meeting_venue=itemView.findViewById(R.id.Venue);
            more=itemView.findViewById(R.id.more2);
        }
    }
}
