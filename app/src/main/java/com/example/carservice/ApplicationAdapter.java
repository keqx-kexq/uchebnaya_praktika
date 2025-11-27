package com.example.carservice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.carservice.data.Application;
import java.util.List;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ViewHolder> {
    private List<Application> applications;
    private OnCompleteListener listener;

    public interface OnCompleteListener {
        void onComplete(Application application);
    }

    public ApplicationAdapter(List<Application> applications, OnCompleteListener listener) {
        this.applications = applications;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_application, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Application app = applications.get(position);

        holder.tvName.setText(app.getName().isEmpty() ? "—" : app.getName());
        holder.tvNumber.setText("Номер: " + app.getNumber());
        holder.tvCarModel.setText("Модель: " + app.getCarModel());
        holder.tvDescription.setText("Описание: " + app.getDescription());
        holder.tvDate.setText("Дата: " + app.getDate());
        holder.tvStatus.setText(app.isTask() ? "Выполнено" : "Не выполнено");

        if (app.isTask()) {
            holder.btnComplete.setVisibility(View.GONE);
        } else {
            holder.btnComplete.setVisibility(View.VISIBLE);
            holder.btnComplete.setOnClickListener(v -> listener.onComplete(app));
        }
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumber, tvCarModel, tvDescription, tvDate, tvStatus;
        Button btnComplete;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvCarModel = itemView.findViewById(R.id.tvCarModel);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            btnComplete = itemView.findViewById(R.id.btnComplete);
        }
    }
}
