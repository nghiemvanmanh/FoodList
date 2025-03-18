package deso1.nghiemvanmanh.dlu_21a100100228;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddFoodActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText etFoodName, etFoodPrice, etImageUrl;
    private ImageView ivPreview;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        etFoodName = findViewById(R.id.etFoodName);
        etFoodPrice = findViewById(R.id.etFoodPrice);
        etImageUrl = findViewById(R.id.etImageUrl);
        ivPreview = findViewById(R.id.ivPreview);
        Button btnPickImage = findViewById(R.id.btnPickImage);
        Button btnSave = findViewById(R.id.btnSave);

        btnPickImage.setOnClickListener(v -> openImagePicker());
        btnSave.setOnClickListener(v -> saveFood());
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.getData();
            ivPreview.setImageURI(imageUri);
        }
    }

    private void saveFood() {
        String name = etFoodName.getText().toString().trim();
        String priceText = etFoodPrice.getText().toString().trim();
        String imageUrl = etImageUrl.getText().toString().trim();

        if (name.isEmpty() || priceText.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên và giá", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceText);
        String finalImage = imageUri != null ? imageUri.toString() : imageUrl;

        Intent resultIntent = new Intent();
        resultIntent.putExtra("addName", name);
        resultIntent.putExtra("addPrice", price);
        resultIntent.putExtra("foodImage", finalImage);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}