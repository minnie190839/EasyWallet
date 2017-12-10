package com.example.win8.easywallet;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.win8.easywallet.Adapter.WalletAdapter;
import com.example.win8.easywallet.DbHelper.DbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DbHelper mHelper;
    private SQLiteDatabase mDb;

    private ArrayList<BudgetItem> mBugetList = new ArrayList<>();
    private WalletAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DbHelper(this);
        mDb = mHelper.getReadableDatabase();

        loadDataFromDb();

        mAdapter = new WalletAdapter(
                this,
                R.layout.item,
                mBugetList
        );

        ListView lv = findViewById(R.id.list_view);
        lv.setAdapter(mAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BudgetItem item = mBugetList.get(position);
                String moneyItem = item.money;

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("จำนวนเงิน:" + moneyItem));
                startActivity(intent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                String[] items = new String[]{"แก้ไขข้อมูล", "ลบข้อมูล"};

                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) { // แก้ไขข้อมูล
                            BudgetItem item = mBugetList.get(position);
                            int phoneId = item.id;

                            ContentValues cv = new ContentValues();
                            cv.put(DbHelper.COL_MONEY, "12345");

                            mDb.update(
                                    DbHelper.TABLE_NAME,
                                    cv,
                                    DbHelper.COL_ID + "=?",
                                    new String[]{String.valueOf(phoneId)}
                            );
                            loadDataFromDb();
                            mAdapter.notifyDataSetChanged();

                        } else if (i == 1) { // ลบข้อมูล
                            BudgetItem item = mBugetList.get(position);
                            int phoneId = item.id;

                            mDb.delete(
                                    DbHelper.TABLE_NAME,
                                    DbHelper.COL_ID + "=?",
                                    new String[]{String.valueOf(phoneId)}
                            );
                            loadDataFromDb();

                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                loadDataFromDb();
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    private void loadDataFromDb() {
        Cursor cursor = mDb.query(
                DbHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        mBugetList.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DbHelper.COL_ID));
            String type = cursor.getString(cursor.getColumnIndex(DbHelper.COL_TYPE));
            String title = cursor.getString(cursor.getColumnIndex(DbHelper.COL_TITLE));
            String number = cursor.getString(cursor.getColumnIndex(DbHelper.COL_MONEY));
            String picture = cursor.getString(cursor.getColumnIndex(DbHelper.COL_PICTURE));


            BudgetItem item = new BudgetItem(id,type, title, number, picture);
            mBugetList.add(item);
        }
    }
   /* public ArrayList<BudgetItem> checkType(String currentType){
        ArrayList<BudgetItem> currentPictureItem = new ArrayList<>();

        for(int index = 0 ; index < BudgetItem.size(); ++index) {
            if(BudgetItem.get(index).type.equals(currentType)) {
                currentPictureItem.add(BudgetItem.get(index));
            }
        }

        return currentPictureItem;
    }*/
}