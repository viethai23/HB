package com.example.hb.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.hb.Activities.MainActivity;
import com.example.hb.R;
import com.example.hb.databinding.ActivityDangKyBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class DangKyActivity extends AppCompatActivity {

    private ActivityDangKyBinding binding;
    static int REQUESTCODE = 1;
    Uri chonAnhUri;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangKyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        setListeners();
    }

    private void setListeners() {
        binding.textSignIn.setOnClickListener(v -> onBackPressed());
        binding.buttonDangKy.setOnClickListener(v -> {
            dangKy();
        });
        binding.layoutImage.setOnClickListener(v -> {
            xacNhan();
        });
    }

    private void dangKy() {
        loading(true);
        final String email = binding.inputEmail.getText().toString();
        final String name = binding.inputName.getText().toString();
        final String password = binding.inputPassword.getText().toString();
        final String password2 = binding.inputConfirmPassword.getText().toString();
        if(email.isEmpty() || name.isEmpty() || password.isEmpty() || !password.equals(password2)) {
            showToasts("Vui lòng nhập đầy đủ thông tin");
            loading(false);
        }
        else
            taoTaiKhoan(email, name, password);
    }

    private void showToasts(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    private void taoTaiKhoan(String email, String name, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            showToasts("Tạo tài khoản thành công");
                            updateUserInfo(name, mAuth.getCurrentUser());
//                            updateUI();

                        }
                        else {
                            showToasts(task.getException().getMessage());
                            loading(false);

                        }
                    }
                });
    }

    private void updateUserInfo(String name, FirebaseUser currentUser) {
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child(currentUser.getUid() + ".jpg");
        StorageReference imgFilePath = mStorage.child(chonAnhUri.getLastPathSegment());
        imgFilePath.putFile(chonAnhUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imgFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        updateUI();
                                    }
                                });
                    }
                });
            }
        });
    }

    private void updateUI() {
        CongDongActivity.setSuccesLogin(true);
        startActivity(new Intent(getApplicationContext(), CongDongActivity.class));
        finish();
    }

    private void moThuVienAnh() {
        Intent thuVienIntent = new Intent(Intent.ACTION_GET_CONTENT);
        thuVienIntent.setType("image/*");
        startActivityForResult(thuVienIntent, REQUESTCODE);
    }

    private void xacNhan() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                moThuVienAnh();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                showToasts("Quyền truy cập bị từ chối");
            }

        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("Nếu bạn từ chối, bạn sẽ không truy cập vào dịch vụ này\nVui lòng cấp quyền trong [Cài đặt] -> [Quyền và dịch vụ]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null) {
            chonAnhUri = data.getData();
            binding.imageProfile.setImageURI(chonAnhUri);
            binding.textAddImage.setVisibility(View.GONE);
        }
    }

    private void loading(Boolean isLoading) {
        if(isLoading) {
            binding.buttonDangKy.setVisibility(View.INVISIBLE);
            binding.progessBar.setVisibility(View.VISIBLE);
        }
        else {
            binding.progessBar.setVisibility(View.INVISIBLE);
            binding.buttonDangKy.setVisibility(View.VISIBLE);
        }
    }
}