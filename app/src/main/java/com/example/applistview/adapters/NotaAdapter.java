package com.example.applistview.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.applistview.R;
import com.example.applistview.database.SQLiteHelper;
import com.example.applistview.models.NotaModel;

import java.util.ArrayList;


public class NotaAdapter extends BaseAdapter {

    private final String NOMBRE_DB = "agenda.db";
    private final int VERSION = 1;
    public static final String NOMBRE_TABLA = "notas";

    public static final String SCRIPT_NUEVA_TABLA = "CREATE TABLE " + NOMBRE_TABLA +
            "(" +
            "id integer primary key autoincrement, " +
            "titulo text, " +
            "descripcion text" +
            ");"
            ;

    private static SQLiteDatabase database;
    private  static SQLiteHelper helper;
    private final Context context;
    private NotaModel model;
    private ArrayList<NotaModel> list;


    public NotaAdapter(Context context) {
        this.context = context;
        helper = new SQLiteHelper(context,NOMBRE_DB,null,VERSION);
    }

    public NotaAdapter(Context context, ArrayList<NotaModel> list) {
        this.context = context;
        this.list = list;
    }

    public void openRead(){
        database = helper.getReadableDatabase();
    }

    public void openWrite(){
        database = helper.getWritableDatabase();
    }

    public void close(){
        database.close();
    }


    public long insert(NotaModel model){
        ContentValues values = new ContentValues();
        values.put("titulo",model.get_titulo());
        values.put("descripcion",model.get_descripcion());

        return database.insert(NOMBRE_TABLA,null,values);
    }

    public ArrayList <NotaModel> selectNotasById (){


        Cursor cursor = database.query(NOMBRE_TABLA, null, null, null,null,null,null);
        if(cursor.getCount() < 1){
            return  null;
        }else{
            list = new ArrayList<>();
            cursor.moveToFirst();
            do{
                model = new NotaModel();

                model.set_titulo(cursor.getString(cursor.getColumnIndex("titulo")));
                model.set_descripcion(cursor.getString(cursor.getColumnIndex("descripcion")));

                list.add(model);
            }while(cursor.moveToNext());
            return list;
        }

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItemNota = view;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewItemNota = inflater.inflate(R.layout.item_notas, viewGroup,false);
        }
        model = (NotaModel) getItem(i);


        TextView tv_item_nota_titulo, tv_item_nota_descripcion;

        tv_item_nota_titulo = viewItemNota.findViewById(R.id.tv_item_nota_titulo);
        tv_item_nota_descripcion = viewItemNota.findViewById(R.id.tv_item_nota_descripcion);

        tv_item_nota_titulo.setText(model.get_titulo());
        tv_item_nota_descripcion.setText(model.get_descripcion());

        return viewItemNota;
    }
}
