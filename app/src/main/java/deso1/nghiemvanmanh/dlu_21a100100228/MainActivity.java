package deso1.nghiemvanmanh.dlu_21a100100228;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FoodAdapter.OnFoodClickListener {
    private List<Food> foodList;
    private FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnAddFood = findViewById(R.id.btnAddFood);
        RecyclerView rvFoodList = findViewById(R.id.rvFoodList);
        foodList = new ArrayList<>();
        foodList.add(new Food("2a","Gỏi gà", 120000, "image"));
        foodList.add(new Food("2a","Bò lúc lắc", 150000, "image"));
        foodList.add(new Food("2a","Tôm hấp", 100000,"image"));
        foodList.add(new Food("2a","Súp cua", 80000, "image"));
        foodList.add(new Food("2a","Lẩu hải sản", 200000,"image"));
        foodAdapter = new FoodAdapter(this,foodList, this);
        rvFoodList.setLayoutManager(new LinearLayoutManager(this));
        rvFoodList.setAdapter(foodAdapter);

        btnAddFood.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddFoodActivity.class);
            startActivityForResult(intent, 2);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String foodId = data.getStringExtra("foodId");
            String newName = data.getStringExtra("newName");
            String addImg = data.getStringExtra("foodImage");
            double newPrice = data.getDoubleExtra("newPrice", 0.0);
            double addPrice = data.getDoubleExtra("addPrice", 0.0);
            String addName = data.getStringExtra("addName");
            if (requestCode == 2) {
                foodList.add(new Food("id",addName, addPrice, addImg));
                foodAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Món ăn đã được thêm", Toast.LENGTH_SHORT).show();
            } else if (requestCode == 1) {
                // Tìm món ăn trong danh sách và cập nhật
                for (Food food : foodList) {
                    if (food.getId().equals(foodId)) {
                        food.setName(newName);
                        food.setPrice(newPrice);
                        break;
                    }
                }
                foodAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Đã cập nhật: " + newName + " - Giá: " + newPrice, Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onEditClick(Food food) {
        Intent intent = new Intent(this, EditFoodActivity.class);
        intent.putExtra("foodId", food.getId());
        intent.putExtra("foodName", food.getName());
        intent.putExtra("foodPrice", food.getPrice());

        startActivityForResult(intent, 1);  // Request code là 1
    }
}