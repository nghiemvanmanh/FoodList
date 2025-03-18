package deso1.nghiemvanmanh.dlu_21a100100228;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditFoodActivity extends AppCompatActivity {

    private EditText etFoodName, etFoodPrice;
    private Button btnSave;
    private String foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        etFoodName = findViewById(R.id.etFoodName);
        etFoodPrice = findViewById(R.id.etFoodPrice);
        btnSave = findViewById(R.id.btnSave);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            foodId = intent.getStringExtra("foodId");
            String foodName = intent.getStringExtra("foodName");
            double foodPrice = intent.getDoubleExtra("foodPrice", 0.0);

            // Hiển thị dữ liệu nhận được
            etFoodName.setText(foodName);
            etFoodPrice.setText(String.valueOf(foodPrice));
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = etFoodName.getText().toString().trim();
                String priceText = etFoodPrice.getText().toString().trim();

                if (newName.isEmpty() || priceText.isEmpty()) {
                    Toast.makeText(EditFoodActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                double newPrice;
                try {
                    newPrice = Double.parseDouble(priceText);
                } catch (NumberFormatException e) {
                    Toast.makeText(EditFoodActivity.this, "Giá không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Gửi kết quả về Activity trước
                Intent resultIntent = new Intent();
                resultIntent.putExtra("foodId", foodId);
                resultIntent.putExtra("newName", newName);
                resultIntent.putExtra("newPrice", newPrice);
                setResult(RESULT_OK, resultIntent);
                finish();

                finish();
            }
        });
    }
}
