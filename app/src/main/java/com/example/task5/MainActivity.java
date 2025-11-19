package com.example.task5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class MainActivity extends AppCompatActivity {

    private ImageView imgAvatar;
    private Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtName = findViewById(R.id.txtName);
        EditText txtEmail = findViewById(R.id.txtEmail);
        Button btnView = findViewById(R.id.btnView);
        Button btnChooseImage = findViewById(R.id.btnChooseImage);
        imgAvatar = findViewById(R.id.imgAvatar);

        btnChooseImage.setOnClickListener(v -> {
            ImagePicker.with(this)
                    .crop()
                    .compress(1024) //1MB
                    .maxResultSize(1080, 1080)
                    .start();
        });

        btnView.setOnClickListener(v -> {
            String name = txtName.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();

            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("email", email);
            if (imageUri != null) {
                bundle.putString("imageUri", imageUri.toString());
            }

            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            Glide.with(this)
                    .load(imageUri)
                    .circleCrop()
                    .into(imgAvatar);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, "Lỗi khi chọn ảnh!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bạn chưa chọn ảnh nào!", Toast.LENGTH_SHORT).show();
        }
    }
}
