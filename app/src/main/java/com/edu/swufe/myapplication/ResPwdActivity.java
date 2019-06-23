package com.edu.swufe.myapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.swufe.dao.DBOpenHelper;
import com.edu.swufe.util.pubFun;

public class ResPwdActivity extends AppCompatActivity {

    private EditText editPhone;
    private EditText editPwd;
    private EditText editResPwd;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_res_pwd );
        editPhone = (EditText) findViewById(R.id.editPhone);
        editPwd = (EditText) findViewById(R.id.editPwd);
        editResPwd = (EditText) findViewById(R.id.editResPwd);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
    }
    public void OnMyConfirmClick(View v) {
        confirmInfo();
    }


    private void confirmInfo() {
        if(pubFun.isEmpty(editPhone.getText().toString()) || pubFun.isEmpty(editPwd.getText().toString()) || pubFun.isEmpty(editResPwd.getText().toString())){
            Toast.makeText(this, "手机号或密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!editPwd.getText().toString().equals(editResPwd.getText().toString())){
            Toast.makeText(this, "输入密码不正确！", Toast.LENGTH_SHORT).show();
            return;
        }


        DBOpenHelper helper = new DBOpenHelper(this,"qianbao.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.query("user_tb",null,"userID=?",new String[]{editPhone.getText().toString()},null,null,null);
        if(c!=null && c.getCount() >= 1){
            ContentValues cv = new ContentValues();
            cv.put("pwd", editPwd.getText().toString());
            String[] args = {String.valueOf(editPhone.getText().toString())};
            long rowid = db.update("user_tb", cv, "userID=?",args);
            c.close();
            db.close();
            Toast.makeText(this, "密码重置成功！", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else{
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("该用户不存在，请到注册界面进行注册！")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            setResult(RESULT_OK);
                            Intent intent=new Intent(ResPwdActivity.this,RegistActivity.class);
                            ResPwdActivity.this.startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            return;
                        }
                    })
                    .show();
        }
    }
}
