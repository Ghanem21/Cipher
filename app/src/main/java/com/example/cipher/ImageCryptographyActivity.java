package com.example.cipher;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;

public class ImageCryptographyActivity extends AppCompatActivity {

    private Button decrypt_btn;
    private Button clear_btn;
    private Button upload_btn;
    private ImageView img_to_encrypt;
    private TextView resultText;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_cryptography);

        resultText = findViewById(R.id.resultText);
        Button encrypt_btn = findViewById(R.id.encrypt_btn);
        decrypt_btn = findViewById(R.id.decrypt_btn);
        clear_btn = findViewById(R.id.clear_btn);
        upload_btn = findViewById(R.id.upload_btn);
        img_to_encrypt = findViewById(R.id.img_to_encrypt);

        //upload image button function
        upload_btn.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(ImageCryptographyActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(ImageCryptographyActivity.this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE
                }, 100);
            }
            else{
                selectImg();
            }
        });

        //encrypt button function
        encrypt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        EncryptionImg();
                        img_to_encrypt.setImageResource(R.drawable.ic_launcher_foreground);
                    }
                }.run();
            }
        });

        //decrypt button function
        decrypt_btn.setOnClickListener(v -> DecryptionImg());

        //clear button function
        clear_btn.setOnClickListener(v -> {
            img_to_encrypt.setImageResource(R.drawable.ic_launcher_foreground);
            resultText.setText("Results Appear Here");
        });

    }

    private void selectImg() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            selectImg();
        }
        else {
            Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            img_to_encrypt.setImageURI(selectedImageUri);
        }
    }


    private String EncryptionImg() {
        img_to_encrypt.buildDrawingCache();
        Bitmap bitmap = img_to_encrypt.getDrawingCache();
        String image;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        image = Base64.encodeToString(bytes, Base64.DEFAULT);
        resultText.setText(image);
        Toast.makeText(this, "Image Encrypted!", Toast.LENGTH_SHORT).show();
        return image;
    }

    private void DecryptionImg() {
        try {
            byte[] bytes = Base64.decode(resultText.getText().toString(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            img_to_encrypt.setImageBitmap(bitmap);
        }catch (Exception exception){

        }
    }

}