package com.example.task5;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class EditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView txtName;
        TextView txtEmail;
        ImageView imageAvatar;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        imageAvatar = (ImageView)  findViewById(R.id.imgAvatar);
        Button finishBtn = (Button) findViewById(R.id.btnFinish);

        finishBtn.setOnClickListener(finishClick);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String email = bundle.getString("email");
        String imageUriStr = bundle.getString("imageUri");
        if (imageUriStr != null) {
            Uri imageUri = Uri.parse(imageUriStr);
            Glide.with(this)
                    .load(imageUri)
                    .circleCrop()
                    .into(imageAvatar);
        }
        txtName.setText(name);
        txtEmail.setText(email);
    }
    private final View.OnClickListener finishClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
