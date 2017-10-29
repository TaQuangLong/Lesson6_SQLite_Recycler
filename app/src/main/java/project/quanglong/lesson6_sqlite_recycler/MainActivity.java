package project.quanglong.lesson6_sqlite_recycler;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvContact;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList;
    private DBManager dbManager;
    EditText etName, etPhoneNumber;
    Button btnAdd, btnSave, btnClose;
    TextView tvName, tvPhoneNumber;
    Dialog dialog;
    String name, phonumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvContact = (RecyclerView) findViewById(R.id.rvContact);

        contactList = new ArrayList<Contact>();

        btnAdd = (Button) findViewById(R.id.btnAdd);
        tvName = (TextView) findViewById(R.id.tvName);
        tvPhoneNumber = (TextView) findViewById(R.id.tvPhoneNumber);


        dbManager = new DBManager(MainActivity.this);

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        dbManager.addContact(new Contact(1, "Ravi", "9100000000"));
        dbManager.addContact(new Contact(2, "Srinivas", "9199999999"));
        dbManager.addContact(new Contact(3, "Tommy", "9522222222"));
        dbManager.addContact(new Contact(4, "Karthik", "9533333333"));

//        // Reading all contacts
//        Log.d("Reading: ", "Reading all contacts..");
//        List<Contact> contact = dbManager.getAllContact();
//        Log.e("etName", "Name");
//        Log.e("etPhone", "PhoneNumber");
//
//
//        for (Contact cn : contact) {
//            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " +
//                    cn.getPhonenumber();
//            // Writing Contacts to log
//            Log.d("Name: ", log);
//        }
//    }
//}


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    private void showDialog() {
        Dialog d = new Dialog(this);

        //NO TITLE
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);


        //layout of dialog
        d.setContentView(R.layout.dialog_layout);
        d.show();

        etName = (EditText) d.findViewById(R.id.etName);
        etPhoneNumber = (EditText) d.findViewById(R.id.etPhoneNumber);
        btnSave = (Button) d.findViewById(R.id.btnSave);
        btnClose = (Button) d.findViewById(R.id.btnClose);
        dbManager = new DBManager(MainActivity.this);

        //ONCLICK LISTENERS
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });
    }

    private void Save() {

        name = etName.getText().toString();
        phonumber = etPhoneNumber.getText().toString();
        if (name.isEmpty() && phonumber.isEmpty()) {

            Toast.makeText(MainActivity.this, "please fill details", Toast.LENGTH_SHORT).show();
        } else {


            //if(tvName!=null)
            etName.setText("");
            etPhoneNumber.setText("");
            dbManager.insertdata(name, phonumber);


            //display Data to RecyclerView
            displayData();

        }
    }

    private void displayData() {

        contactList = dbManager.getAllContact();
        Log.e("list","1");
        contactAdapter = new ContactAdapter(contactList);
        Log.e("list","2");

        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getApplicationContext());
        Log.e("list","3");
        rvContact.setLayoutManager(reLayoutManager);
        Log.e("list","4");
        rvContact.setItemAnimator(new DefaultItemAnimator());
        Log.e("list","5");
        rvContact.setAdapter(contactAdapter);
        Log.e("list","6");
    }
}
