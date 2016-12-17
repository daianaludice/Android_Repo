package a2016.androidproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ViewActivity extends Activity implements View.OnClickListener {
    Button closeBt, listBt1, listBt2, listBt3, listBt4, listBt5;
    ListView infoList;
    InformationAdapter InfoAdapter;
    Cursor cursor;
    Intent i;
    TextView countT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        closeBt = (Button)findViewById(R.id.closeBt);
        infoList = (ListView)findViewById(R.id.List1);
        listBt1 = (Button)findViewById(R.id.ListBt1);
        listBt2 = (Button)findViewById(R.id.ListBt2);
        listBt3 = (Button)findViewById(R.id.ListBt3);
        listBt4 = (Button)findViewById(R.id.ListBt4);
        listBt5 = (Button)findViewById(R.id.ListBt5);
        countT = (TextView)findViewById(R.id.count);

        i = new Intent(this, DetailActivity.class);

        getInfoForCursorAdapter();

        closeBt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listBt1.setOnClickListener(this);
        listBt2.setOnClickListener(this);
        listBt3.setOnClickListener(this);
        listBt4.setOnClickListener(this);
        listBt5.setOnClickListener(this);

        infoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int _id = (int)view.getTag();


                AlertDialog.Builder alertDlg = new AlertDialog.Builder(ViewActivity.this);
                alertDlg.setTitle(R.string.alert_title_question);
                alertDlg.setMessage(R.string.alert_msg_delete);

                alertDlg.setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteInfo(_id);
                        dialog.dismiss();
                        refresh();
                    }
                });

                alertDlg.setNegativeButton(R.string.button_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        i.putExtra("_id", _id);

                        startActivityForResult(i, 1);
                        dialog.dismiss();
                    }
                });

                alertDlg.show();
            }
        });
    }

    public void deleteInfo(int _id){
        String sql = "DELETE FROM location WHERE _id = " + _id;
        sqlite.execSQL(sql);
        sqlite.close();
    }

    public void refresh(){
        getInfoForCursorAdapter();
    }

    public void onClick(View v){
        String sql;
        switch (v.getId()){
            case R.id.listbtn01:
                sql = "SELECT * FROM location WHERE etc=1";
                cursor = sqlite.rawQuery(sql, null);

                if(cursor.getCount() > 0){
                    countT.setText("Num of Study : " + cursor.getCount());
                    startManagingCursor(cursor);
                    InfoAdapter = new InformationAdapter(this, cursor);
                    infoList.setAdapter(InfoAdapter);
                }
                if(cursor.getCount() == 0){
                    countT.setText("No data of Study");
                    startManagingCursor(cursor);
                    InfoAdapter = new InformationAdapter(this, cursor);
                    infoList.setAdapter(InfoAdapter);
                }
                break;
            case R.id.listbtn02:
                sql = "SELECT * FROM location WHERE etc=2";
                cursor = sqlite.rawQuery(sql, null);

                if(cursor.getCount() > 0){
                    countT.setText("Num of Work : " + cursor.getCount());
                    startManagingCursor(cursor);
                    InfoAdapter = new InformationAdapter(this, cursor);
                    infoList.setAdapter(InfoAdapter);
                }
                if(cursor.getCount() == 0){
                    countT.setText("No data of Work");
                    startManagingCursor(cursor);
                    InfoAdapter = new InformationAdapter(this, cursor);
                    infoList.setAdapter(InfoAdapter);
                }
                break;
            case R.id.listbtn03:
                sql = "SELECT * FROM location WHERE etc=3";
                cursor = sqlite.rawQuery(sql, null);

                if(cursor.getCount() > 0){
                    countT.setText("Num of Rest : " + cursor.getCount());
                    startManagingCursor(cursor);
                    InfoAdapter = new InformationAdapter(this, cursor);
                    infoList.setAdapter(InfoAdapter);
                }
                if(cursor.getCount() == 0){
                    countT.setText("No data of Rest");
                    startManagingCursor(cursor);
                    InfoAdapter = new InformationAdapter(this, cursor);
                    infoList.setAdapter(InfoAdapter);
                }
                break;
            case R.id.listbtn04:
                sql = "SELECT * FROM location WHERE etc=4";
                cursor = sqlite.rawQuery(sql, null);

                if(cursor.getCount() > 0){
                    countT.setText("Num of Eat : " + cursor.getCount());
                    startManagingCursor(cursor);
                    InfoAdapter = new InformationAdapter(this, cursor);
                    infoList.setAdapter(InfoAdapter);
                }
                if(cursor.getCount() == 0){
                    countT.setText("No data of Eat");
                    startManagingCursor(cursor);
                    InfoAdapter = new InformationAdapter(this, cursor);
                    infoList.setAdapter(InfoAdapter);
                }
                break;
            case R.id.listbtn05:
                sql = "SELECT * FROM location WHERE etc=5";
                cursor = sqlite.rawQuery(sql, null);

                if(cursor.getCount() > 0){
                    countT.setText("Num of Move : " + cursor.getCount());
                    startManagingCursor(cursor);
                    InfoAdapter = new InformationAdapter(this, cursor);
                    infoList.setAdapter(InfoAdapter);
                }
                if(cursor.getCount() == 0){
                    countT.setText("No data of Move");
                    startManagingCursor(cursor);
                    InfoAdapter = new InformationAdapter(this, cursor);
                    infoList.setAdapter(InfoAdapter);
                }
                break;
        }
    }


    public void getInfoForCursorAdapter(){

        String sql = "SELECT * FROM location";
        cursor = sqlite.rawQuery(sql, null);



        if(cursor.getCount() > 0) {
            countT.setText("Num of List : " + cursor.getCount());
            startManagingCursor(cursor);
            InfoAdapter = new InformationAdapter(this, cursor);
            infoList.setAdapter(InfoAdapter);

        }

        if(cursor.getCount() == 0){
            countT.setText("No data of List");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        sqlite.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                if(data.getBooleanExtra("updateResult", false)){
                    Log.d("update =>", "sucessed");
                }
                else{
                    Log.d("update => ", "failed");
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}