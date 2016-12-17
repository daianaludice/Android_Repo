package a2016.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends Activity{
    Intent i;
    Button detailBtn, stopBtn;
    EditText detailText01;
    TextView workdetail;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailBtn = (Button)findViewById(R.id.detailBtn);
        stopBtn = (Button)findViewById(R.id.stopBtn);

        detailText01 = (EditText)findViewById(R.id.detailText01);

        workdetail = (TextView)findViewById(R.id.workdetail);

        i = getIntent();
        final int _id = i.getExtras().getInt("_id");

        getInfoForCursorAdapter(_id);

        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = updateInfo(_id);

                i.putExtra("updateResult", flag);
                setResult(RESULT_OK, i);
                finish();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean updateInfo(int _id){
        String work = detailText01.getText().toString();
        String sql  = "UPDATE location SET work='" +work+"' WHERE _id='"+_id+"'";
        try{
            sqlite.execSQL(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void getInfoForCursorAdapter(int _id){

        String sql = "SELECT * FROM location WHERE _id = " + _id;

        cursor = sqlite.rawQuery(sql, null);

        String work = "";

        if(cursor.getCount() > 0){

            cursor.moveToNext();
            work = cursor.getString(4);

        }

        detailText01.setText(work);
        cursor.close();

    }
}