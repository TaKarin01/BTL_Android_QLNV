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
//        String strSQL_admin ="CREATE TABLE tbAdmin (maAd TEXT PRIMARY KEY,ten TEXT,namSinh TEXT,gioiTinh TEXT, email TEXT,banQL TEXT, pass TEXT)";
//        db.execSQL(strSQL_admin);
//        String strSQL_employee = "CREATE TABLE tbEmployee (maE TEXT PRIMARY KEY, ten TEXT, namSinh TEXT, gioiTinh TEXT, banQL TEXT, email TEXT)";
//        db.execSQL(strSQL_employee);

        String strSQL = "CREATE TABLE tbAdmin (maAd TEXT PRIMARY KEY,ten TEXT,namSinh TEXT,gioiTinh TEXT, email TEXT,banQL TEXT, pass TEXT)" + "\n"
                + "CREATE TABLE tbEmployee (maE TEXT PRIMARY KEY, ten TEXT, namSinh TEXT, gioiTinh TEXT, banQL TEXT, email TEXT)" + "\n"
                + "CREATE TABLE tbMission (maE TEXT , tenCV TEXT, trangThai TEXT, CONSTRAINT mission_pk PRIMARY KEY(maE,tenCV))";

        for(String s : strSQL.split("\n"))
        {
            db.execSQL(s);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSQL_admin = "DROP TABLE IF EXISTS tbAdmin";
        db.execSQL(strSQL_admin);

        String strSQL_employee = "DROP TABLE IF EXISTS tbEmployee";
        db.execSQL(strSQL_employee);
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

    // Thêm thông tin nhân viên
    public Boolean addEmployee(String id, String name, String birth, String gender, String dept, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("maE",id);
        values.put("ten",name);
        values.put("namSinh",birth);
        values.put("gioiTinh",gender);
        values.put("banQL",dept);
        values.put("email",email);
        long result = db.insert("tbEmployee",null,values);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    // Kiểm tra ID của nhân viên
    /** tồn tại mã nhân viên thì trả về true ngược lại là false **/
    public Boolean checkIDEmployee(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM tbEmployee WHERE maE = ?",new String[]{id});
        if(cr.getCount()>0) return true;
        else return false;
    }
     // Lay thong tin của nhan vien trong phòng ban mình quản lý

    public ArrayList<ArrayList<String>> getListE(String dept)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String strSql = "SELECT maE, ten FROM tbEmployee WHERE banQL = ?";
        Cursor cr = db.rawQuery(strSql,new String[]{dept});
        ArrayList<ArrayList<String>> listE = new ArrayList<>();
        while(cr.moveToNext())
        {
            ArrayList<String> E = new ArrayList<>();
            for(int i=0; i<2;i++)
            {
                String s = cr.getString(i).toString();
                E.add(s);
            }
            listE.add(E);
        }
        return listE;
    }

    // Lay thong tin nhan vien
    public ArrayList<String> getE(String maE)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String strSql = "SELECT ten, namSinh, gioiTinh, email FROM tbEmployee WHERE maE = ?";
        Cursor cr = db.rawQuery(strSql,new String[]{maE});
        cr.moveToNext();
        ArrayList<String> e = new ArrayList<>();
        for(int i=0; i<4; i++)
        {
            String s;
            s = cr.getString(i);
            e.add(s);
        }
        return e;
    }

    //Thay đổi thông tin nhân viên
    public Boolean changeE(String id, String name, String dob, String gender, String mail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", name);
        values.put("namSinh", dob);
        values.put("gioiTinh", gender);
        values.put("email", mail);

        long result = db.update("tbEmployee",values,"maE = ?",new String[]{id});
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    // Xóa nhân viên
    public Boolean delE(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("tbEmployee","maE = ?",new String[]{id});
        if(result == -1) return false;
        else return true;
    }
}
