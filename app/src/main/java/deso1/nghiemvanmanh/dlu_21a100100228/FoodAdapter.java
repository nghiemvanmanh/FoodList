package deso1.nghiemvanmanh.dlu_21a100100228;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private List<Food> foodList;
    private Context context;
    private OnFoodClickListener listener;

    public interface OnFoodClickListener {
        void onEditClick(Food food);
    }

    public FoodAdapter(Context context,List<Food> foodList, OnFoodClickListener listener) {
        this.context = context;
        this.foodList = foodList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.foodName.setText(food.getName());
        holder.foodPrice.setText(String.format("%.2f , VND", food.getPrice()));
        Glide.with(context).load(food.getImageResourceId()).into(holder.foodImage);
        int imageResId = context.getResources().getIdentifier(food.getImageResourceId(), "drawable", context.getPackageName());
        holder.foodImage.setImageResource(imageResId);
        holder.editButton.setOnClickListener(v -> listener.onEditClick(food));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName, foodPrice;
        ImageButton editButton;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.foodImage);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            editButton = itemView.findViewById(R.id.editButton);

        }
    }
}
