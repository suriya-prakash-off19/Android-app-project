package com.example.admininterface.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.admininterface.Model.Post;
import com.example.admininterface.Model.User;
import com.example.admininterface.R;
import com.example.admininterface.comments;
import com.example.admininterface.fragment.postDetail_fragment;
import com.example.admininterface.fragment.profile_fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class PostAdapter extends  RecyclerView .Adapter<PostAdapter.ViewHolder>{

    public Context mContext;
    public List<Post> mPost;

    FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

    public PostAdapter(Context mContext, List<Post> mPost) {
        this.mContext = mContext;
        this.mPost = mPost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.post_item,parent,false);
        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        Post post=mPost.get(position);
        Glide.with(mContext).load(post.getPostImage()).apply(new RequestOptions().placeholder(R.drawable.profileuser)).into(holder.post_image);
        if(post.getDescription().equals("")){
            holder.Descripion.setVisibility(View.GONE);
        }else{
            holder.Descripion.setVisibility(View.VISIBLE);
            holder.Descripion.setText(post.getDescription());
        }
        publishInfo(holder.image_profile,holder.username,holder.publisher,post.getPublisher());
        isLikes(post.getPostID(),holder.like);
        nrLikes(holder.likes, post.getPostID());
        getComments(post.getPostID(),holder.comments);
        holder.image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("profileid",post.getPublisher());
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.admin_fragmentContainer,new profile_fragment()).commit();
            }
        });
        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("profileid",post.getPublisher());


                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.admin_fragmentContainer,new profile_fragment()).commit();
            }
        });
        holder.publisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("profileid",post.getPublisher());


                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragmentContainer,new profile_fragment()).commit();
            }
        });
        holder.post_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("postid",post.getPostID());


                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragmentContainer,new postDetail_fragment()).commit();
            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.like.getTag().equals("like")){
                    FirebaseDatabase.getInstance().getReference().child("Likes").child(post.getPostID())
                            .child(firebaseUser.getUid()).setValue(true);
                    addNotification(post.getPublisher(),post.getPostID());
                }else{
                    FirebaseDatabase.getInstance().getReference().child("Likes").child(post.getPostID())
                            .child(firebaseUser.getUid()).removeValue();
                }
            }
        });

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(view.getContext(),view);
                popupMenu.getMenuInflater().inflate(R.menu.delete_item,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        FirebaseDatabase.getInstance().getReference("Notification").child(post.getPostID()).removeValue();
                        FirebaseDatabase.getInstance().getReference("Posts").child(post.getPostID()).removeValue();
                        FirebaseDatabase.getInstance().getReference("Likes").child(post.getPostID()).removeValue();
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, comments.class);
                intent.putExtra("postid",post.getPostID());
                intent.putExtra("publisherid",post.getPublisher());
                mContext.startActivity(intent);
            }
        });
        holder.comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, comments.class);
                intent.putExtra("postid",post.getPostID());
                intent.putExtra("publisherid",post.getPublisher());
                mContext.startActivity(intent);
            }
        });
//        holder.share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               Intent intent=new Intent(Intent.ACTION_SEND);
//               intent.setType("text/plain");
//               intent.putExtra(Intent.EXTRA_SUBJECT,"Check out this");
//               intent.putExtra(Intent.EXTRA_TEXT,"Your Application Link Here");
//
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        public ImageView image_profile,post_image,like,comment,share,more;
        public TextView username,likes,publisher,Descripion,comments;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            more=itemView.findViewById(R.id.more);
            image_profile=itemView.findViewById(R.id.imageProflie_23);
            post_image=itemView.findViewById(R.id.post_image);
            like=itemView.findViewById(R.id.like);
            comment=itemView.findViewById(R.id.comments);
//            share=itemView.findViewById(R.id.share);
            username=itemView.findViewById(R.id.UserName);
            likes=itemView.findViewById(R.id.likes);
            publisher=itemView.findViewById(R.id.publisher);
            Descripion=itemView.findViewById(R.id.description);
            comments=itemView.findViewById(R.id.Comments);
        }
    }
    private  void getComments(String postid,TextView comments){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Comments").child(postid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                comments.setText("View All "+snapshot.getChildrenCount()+" Comments");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void isLikes(String postid,ImageView imageView){

        final FirebaseUser firebaseUser1=FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Likes").child(postid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(firebaseUser1.getUid()).exists()){
                    imageView.setImageResource(R.drawable.like);
                    imageView.setTag("liked");
                }else{
                    imageView.setImageResource(R.drawable.like_2);
                    imageView.setTag("like");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private  void nrLikes(TextView likes,String postid){
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Likes").child(postid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                likes.setText(snapshot.getChildrenCount()+" Likes");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void addNotification(String userid,String postid){
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Notification").child(userid).child(postid);
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("userid",firebaseUser.getUid());
        hashMap.put("text","liked your post");
        hashMap.put("postid",postid);
        hashMap.put("ispost",true);

        reference.push().setValue(hashMap);
    }
    private void publishInfo(ImageView image_profile,TextView username,TextView pulisher,String userId){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users").child(userId);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user=snapshot.getValue(User.class);
                Glide.with(mContext).asBitmap().load(user.getImageURL()).into(image_profile);
                username.setText(user.getName());
                pulisher.setText(user.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
