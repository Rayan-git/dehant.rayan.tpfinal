package dehant.rayan.tpfinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private ArrayList<Country> list;
    private LayoutInflater inflater;

    public Adapter(Context con,ArrayList<Country> list){
        super();
        this.list=list;
        inflater=LayoutInflater.from(con);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Country getItem(int arg0){
        return list.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;

        if(convertView==null){
            layoutItem=(LinearLayout) inflater.inflate(R.layout.element_list,parent,false);
        }else{
            layoutItem=(LinearLayout) convertView;
        }

        TextView nom= layoutItem.findViewById(R.id.nom);
        nom.setText(this.list.get(position).getNom());
        TextView continent= layoutItem.findViewById(R.id.continent);
        continent.setText(this.list.get(position).getContinent()+"-"+this.list.get(position).getSousContinent());
        URL url = null;
        try {
            url = new URL(this.list.get(position).getImage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageView image= layoutItem.findViewById(R.id.image_miniature_pays);
        image.setImageBitmap(bmp);
        return layoutItem;
    }
}

