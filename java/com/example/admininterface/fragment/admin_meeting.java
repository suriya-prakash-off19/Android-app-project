package com.example.admininterface.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admininterface.Adapter.meetAdapter;
import com.example.admininterface.Model.meet;
import com.example.admininterface.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class admin_meeting extends Fragment {
    RecyclerView recyclerView;
    List<meet> postLists;
    meetAdapter meetAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meeting, container, false);
        recyclerView = view.findViewById(R.id.meeting_recycleView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        postLists = new ArrayList<>();
        meetAdapter = new meetAdapter(getContext(), postLists);
        recyclerView.setAdapter(meetAdapter);
        meeting();
        return view;
    }

    private void meeting() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meeting");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postLists.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    meet Meet = snapshot1.getValue(meet.class);
                    postLists.add(Meet);
                }
                meetAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//        @Override
//        public void handleOnBackPressed() {
//                get().popBackStack();
//        }
//    };
}