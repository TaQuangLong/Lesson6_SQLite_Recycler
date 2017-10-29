package project.quanglong.lesson6_sqlite_recycler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by long on 10/27/2017.
 */

public class DBManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="student_list";
    private static final String TABLE_NAME ="student";
    private static final String ID ="id";
    private static final String NAME ="name";
    private static final String PHONENUMBER = "phonenumber";


    private Context context;

    public DBManager(Context context) {
        super(context, DATABASE_NAME,null, 1);
        Log.d("DBManager", "DBManager: ");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                ID +" integer primary key, "+
                NAME + " TEXT, "+
                PHONENUMBER +" TEXT)";
        db.execSQL(sqlQuery);
        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }



    /*
    Select a contact by ID
     */

    public Contact getContactById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { ID,
                        NAME}, ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact();
        cursor.close();
        db.close();
        return contact;
    }

    /*
    Update name of contact
     */

    public int Update(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME,contact.getName());

        return db.update(TABLE_NAME,values,ID +"=?",new String[] { String.valueOf(contact.getId())});


    }

    /*
     Getting All Contact
      */

    public List<Contact> getAllContact() {
        List<Contact> listContact = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhonenumber(cursor.getString(2));
                listContact.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listContact ;
    }
    /*
    Delete a contact by ID
     */
    public void deleteStudent(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }
    /*
    Get Count Student in Table Contact
     */
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public void insertdata(String name,String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();


        contentValues.put(NAME, name);
        contentValues.put(PHONENUMBER, phonenumber);
        db.insert(TABLE_NAME,null,contentValues);

        db.close();
    }

    //Add new a contact
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, contact.getName());
        values.put(PHONENUMBER,contact.getPhonenumber());

        //Neu de null thi khi value bang null thi loi

        db.insert(TABLE_NAME,null,values);

        db.close();
    }
}