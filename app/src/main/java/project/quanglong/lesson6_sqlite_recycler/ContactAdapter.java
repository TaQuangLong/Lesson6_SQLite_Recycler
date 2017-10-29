package project.quanglong.lesson6_sqlite_recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by long on 10/27/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    List<Contact> contactList;
    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }



    public class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPhoneNumber;
        public ContactViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPhoneNumber = (TextView) itemView.findViewById(R.id.tvPhoneNumber);
        }
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_line, parent, false);
        ContactViewHolder vh = new ContactViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.tvName.setText(contact.getName());
        holder.tvPhoneNumber.setText(contact.getPhonenumber());
    }

    @Override
    public int getItemCount() {
        return null!=contactList?contactList.size():0;
    }
}

