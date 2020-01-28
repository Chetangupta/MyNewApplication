package example.com.mynewapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import example.com.mynewapplication.model.Country;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {

    private List<Country> countryList;


    public DetailAdapter(List<Country> countryList) {
        this.countryList = countryList;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     Country country=countryList.get(position);
     holder.tvName.setText(country.getName());
     holder.tvCapitalName.setText(country.getCapital());
     holder.tvLatLng.setText(country.getLatlng().toString());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvCapitalName;
        public TextView tvLatLng;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvCapitalName = (TextView) view.findViewById(R.id.tv_capital_name);
            tvLatLng=(TextView)view.findViewById(R.id.tv_lat_long);
        }


    }


}
