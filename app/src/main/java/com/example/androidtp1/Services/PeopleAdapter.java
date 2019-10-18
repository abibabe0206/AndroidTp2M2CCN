package com.example.androidtp1.Services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtp1.R;
import com.example.androidtp1.model.PersonDB;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleHolder> {

    private List<PersonDB> personDBList = new ArrayList<>();
    private List<PersonDB> personListFiltered;
    private PeopleAdapterListener listener;


    @NonNull
    @Override
    public PeopleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.peopleitem, parent, false);
        return new PeopleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleHolder holder, int position){
        PersonDB currentPerson = personDBList.get(position);
        holder.pplNom.setText(currentPerson.nom);
        holder.pplPernom.setText(currentPerson.prenom);
        holder.pplPhono.setText(currentPerson.phone);
        holder.pplDateBirth.setText(currentPerson.ddn);
        holder.pplVille.setText(currentPerson.vdn);
        holder.pplDep.setText(currentPerson.depdn);
    }

    @Override
    public int getItemCount(){
        return personDBList.size();
    }

    public void setPersonDBList(List<PersonDB> People){
        this.personDBList = People;
        this.personListFiltered = People;
        notifyDataSetChanged();
    }

    public PersonDB getPersonAt(int adapterPosition) {
        return personDBList.get(adapterPosition);
    }


    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    personListFiltered = personDBList;
                } else {
                    List<PersonDB> filteredList = new ArrayList<>();
                    for (PersonDB row : personDBList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getNom().toLowerCase().contains(charString.toLowerCase()) || row.getPrenom().toLowerCase().contains(charString.toLowerCase())
                                || row.getDdn().toLowerCase().contains(charString.toLowerCase()) || row.getDepdn().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getVdn().toLowerCase().contains(charString.toLowerCase()) || row.getPhone().contains(charSequence)) {
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
                personListFiltered = (ArrayList<PersonDB>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class PeopleHolder extends RecyclerView.ViewHolder {
        private TextView pplNom;
        private TextView pplPernom;
        private TextView pplPhono;
        private TextView pplDateBirth;
        private TextView pplVille;
        private TextView pplDep;


        public PeopleHolder(View itemView) {
            super(itemView);
            pplNom = itemView.findViewById(R.id.pplNom);
            pplPernom = itemView.findViewById(R.id.pplPrenom);
            pplPhono = itemView.findViewById(R.id.pplPhono);
            pplDateBirth = itemView.findViewById(R.id.pplDateBirth);
            pplVille = itemView.findViewById(R.id.pplVille);
            pplDep = itemView.findViewById(R.id.pplDep);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // send selected contact in callback
                    listener.onContactSelected(personListFiltered.get(getAdapterPosition()));
                }
            });

        }

    }

    public interface PeopleAdapterListener {
        void onContactSelected(PersonDB personDB);
    }
}
