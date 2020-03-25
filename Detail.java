package dehant.rayan.tpfinal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Detail extends AppCompatActivity {
    private Country country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        country=getIntent().getParcelableExtra("personne");
        TextView tvNom=findViewById(R.id.nom);
        tvNom.setText(country.getNom());
        TextView tvCapitale=findViewById(R.id.capitale);
        tvCapitale.setText(country.getCapitale());
        TextView tvHabitants=findViewById(R.id.habitants);
        tvHabitants.setText(country.getHabitants());
        ImageView image=findViewById(R.id.image_detail_pays);
        URL url = null;
        try {
            url = new URL(country.getImage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setImageBitmap(bmp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
