package id.ac.ub.room;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    LayoutInflater inflater;
    Context _context;
    ArrayList<Item> data;


    public MahasiswaAdapter(Context _context, ArrayList<Item> data) {
        this._context = _context;
        this.data = data;
        this.inflater = LayoutInflater.from(this._context);

    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        Item mhs = data.get(position);
        Log.d(MainActivity.TAG,"data "+position);
        Log.d(MainActivity.TAG,"nim "+mhs.getNim());
        Log.d(MainActivity.TAG,"nama "+mhs.getNama());
        holder.tvNim.setText(mhs.getNim());
        holder.tvNama.setText(mhs.getNama());
    }

    @Override
    public int getItemCount() {
        Log.d(MainActivity.TAG,"Jumlah data "+data.size());
        return data.size();
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNim;
        TextView tvNama;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tvNim);
            tvNama = itemView.findViewById(R.id.tvNama);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent intent = new Intent(_context.getApplicationContext(),MahasiswaDetail.class);
            intent.putExtra("NIM",tvNim.getText().toString());
            intent.putExtra("Nama",tvNama.getText().toString());
            _context.startActivity(intent);
        }
    }
}