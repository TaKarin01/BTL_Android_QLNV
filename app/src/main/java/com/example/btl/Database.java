package com.example.btl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public final static String nameDB = "QLNV";
    public Database(@Nullable Context context) {
        super(context, nameDB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSQL ="CREATE TABLE tbAdmin (maAd TEXT PRIMARY KEY,ten TEXT,namSinh TEXT,gioiTinh TEXT, email TEXT,banQL TEXT, pass TEXT)";
        db.execSQL(strSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSQL = "DROP TABLE IF EXISTS tbAdmin";
        db.execSQL(strSQL);
    }
    // Các hàm để đăng kí tài khoản admin

    //Hàm check thêm mới tài khoản
    public Boolean insertAdmin(String ma,String ten ,String namSinh,String gioiTinh,String email, String ban, String mk)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put("maAd",ma);
        values.put("ten",ten);
        values.put("namSinh",namSinh);
        values.put("gioiTinh",gioiTinh);
        values.put("email",email);
        values.put("banQL",ban);
        values.put("pass",mk);
        long result = db.insert("tbAdmin",null,values);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    // Hàm check tồn tại của tài khoản
    /** Hàm check có tồn tại ID thì trả về true **/
    public Boolean check_ID(String ma)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur =db.rawQuery("SELECT * FROM tbAdmin WHERE maAd = ?",new String[]{ma});
        if(cur.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //check mã nhân viên khớp với login hay không
    public Boolean checkLogin(String ma, String pass)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT pass FROM tbAdmin WHERE maAd = ?",new String[]{ma});

        cr.moveToNext();
        String passTrue = cr.getString(0).toString();
        if(passTrue.equals(pass))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    // check id với gmail
    public Boolean checkMail(String ma, String mail)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT email FROM tbAdmin WHERE maAd = ?", new String[]{ma});
        cr.moveToNext();
        String mailTrue = cr.getString(0).toString();
        if(mailTrue.equals(mail))
        {
            return true;
        }
        else {
            return false;
        }
    }

    //Update lại mật khẩu
    public Boolean updatePass(String ma, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pass",pass);
        long result = db.update("tbAdmin",values,"maAd = ?",new String[]{ma});

        if( result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    // Lấy tên
    public String getAdminName(String ma)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT ten FROM tbAdmin WHERE maAD = ?",new String[]{ma});
        cr.moveToNext();
        String s = cr.getString(0).toString();
        return s;
    }
    //Lấy thông tin lưu được
    public ArrayList<String> getAdmin(String ma)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM tbAdmin WHERE maAD = ?",new String[]{ma});
        cr.moveToNext();
        ArrayList<String> admin = new ArrayList<>();
        for(int i=0; i<6; i++)
        {
            String s = cr.getString(i).toString();
            admin.add(s);
        }
        return admin;
    }

}
