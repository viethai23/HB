package com.example.hb.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hb.Adapter.CommentAdapter;
import com.example.hb.MainActivity;
import com.example.hb.Model.Comment;
import com.example.hb.databinding.ActivityCongDongBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CongDongActivity extends AppCompatActivity {

    private ActivityCongDongBinding binding;
    private static boolean successSignIn;
    FirebaseUser user;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    CommentAdapter commentAdapter;
    List<Comment> listComment;
    static String COMMENT_KEY = "Comment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCongDongBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        setListener();
    }

    private void showToasts(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    private void setListener() {
        binding.imageBack.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

        binding.textSignIn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), DangNhapActivity.class)));

        if(successSignIn == true) {
            binding.textSignIn.setVisibility(View.GONE);
            binding.inputComment.setVisibility(View.VISIBLE);
            binding.layoutSend.setVisibility(View.VISIBLE);
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        binding.layoutSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference commentReference = firebaseDatabase.getReference(COMMENT_KEY).push();
                String comment_content = binding.inputComment.getText().toString();
                String uid = user.getUid();
                String uname = user.getDisplayName();
                String uimg = user.getPhotoUrl().toString();
                Comment comment = new Comment(comment_content, uid, uname, uimg);
                comment.setTimestamp(ServerValue.TIMESTAMP);
                commentReference.setValue(comment)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                showToasts("Bình luận đã được thêm");
                                binding.inputComment.setText("");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                showToasts("Không thể thêm bình luận" + e.getMessage());
                            }
                        });
            }
        });
        iniRvComment();

    }

    private void iniRvComment() {
        binding.rvComment.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference commentRef = firebaseDatabase.getReference(COMMENT_KEY);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listComment = new ArrayList<>();
                for(DataSnapshot snap:snapshot.getChildren()) {
                    Comment comment = snap.getValue(Comment.class);
                    listComment.add(comment);
                }

                commentAdapter = new CommentAdapter(getApplicationContext(), listComment);
                binding.rvComment.setAdapter(commentAdapter);
                binding.rvComment.smoothScrollToPosition(binding.rvComment.getAdapter().getItemCount()-1);
                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void setSuccesLogin(boolean check) {
        successSignIn = check;
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = mAuth.getCurrentUser();
        if(user != null) {
            binding.textSignIn.setVisibility(View.GONE);
            binding.inputComment.setVisibility(View.VISIBLE);
            binding.layoutSend.setVisibility(View.VISIBLE);
        }
    }
}