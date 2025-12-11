package com.example.task5;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

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

        YoYo.with(Techniques.SlideInUp)
                .duration(700)
                .playOn(findViewById(R.id.imgAvatar));

        YoYo.with(Techniques.SlideInUp)
                .duration(900)
                .playOn(findViewById(R.id.name));

        YoYo.with(Techniques.SlideInUp)
                .duration(1100)
                .playOn(findViewById(R.id.email));

        finishBtn.setOnClickListener(finishClick);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
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
    }
    private final View.OnClickListener finishClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            YoYo.with(Techniques.FadeOut)
                    .duration(300)
                    .onEnd(animator -> finish())
                    .playOn(findViewById(android.R.id.content));
        }
    };
}
