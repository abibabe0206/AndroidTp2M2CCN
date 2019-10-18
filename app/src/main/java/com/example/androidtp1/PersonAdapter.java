package com.example.androidtp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Person> personList;
    private List<Person> personListFiltered;
    private PersonsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nom, prenom, ddn, vdn, pn;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);

            nom = view.findViewById(R.id.textNomitem);
            prenom = view.findViewById(R.id.textPrenomitem);
            ddn = view.findViewById(R.id.textDdnitem);
            vdn = view.findViewById(R.id.textVdnitem);
            pn = view.findViewById(R.id.pnitem);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // send selected contact in callback
                    listener.onContactSelected(personListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public PersonAdapter(Context context, List<Person> personList, PersonsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.personList = personList;
        this.personListFiltered = personList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Person person = personListFiltered.get(position);
        holder.nom.setText(person.getNom());
        holder.prenom.setText(person.getPrenom());
        holder.ddn.setText(person.getDdn());
        holder.vdn.setText(person.getVdn());
        holder.pn.setText(person.getPhone());

        Glide.with(context)
                .load(person.getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return personListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    personListFiltered = personList;
                } else {
                    List<Person> filteredList = new ArrayList<>();
                    for (Person row : personList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getNom().toLowerCase().contains(charString.toLowerCase()) || row.getPrenom().toLowerCase().contains(charString.toLowerCase())
                                || row.getDdn().toLowerCase().contains(charString.toLowerCase()) || row.getVdn().toLowerCase().contains(charString.toLowerCase())
                                || row.getPhone().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    personListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = personListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                personListFiltered = (ArrayList<Person>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface PersonsAdapterListener {
        void onContactSelected(Person person);
    }
}
