package sg.edu.rp.c346.id19029863.ourndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText etID, edTitle, edSinger, edYear;
    Button btnUpdate, btnDel, btnCancel;
    RatingBar rb;
    //RadioButton r1, r2, r3, r4, r5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etID=findViewById(R.id.etID);
        edTitle=findViewById(R.id.etTitle);
        edSinger=findViewById(R.id.etSingers);
        edYear=findViewById(R.id.etYear);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDel=findViewById(R.id.btnDel);
        btnCancel=findViewById(R.id.btnCancel);
        rb = findViewById(R.id.rb);
       /* r1 = findViewById(R.id.rgStar1);
        r2 = findViewById(R.id.rgStar2);
        r3 = findViewById(R.id.rgStar3);
        r4 = findViewById(R.id.rgStar4);
        r5 = findViewById(R.id.rgStar5);*/

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText(String.valueOf(data.get_id()));
        edTitle.setText(data.getTitle());
        edSinger.setText(data.getSingers());
        edYear.setText(String.valueOf(data.getYear()));

        /*if (data.getStars() == 1)
        {
            r1.setChecked(true);
        }
        else if (data.getStars() == 2)
        {
            r2.setChecked(true);
        }
        else if (data.getStars() == 3)
        {
            r3.setChecked(true);
        }
        else if (data.getStars() == 4)
        {
            r4.setChecked(true);
        }
        else if (data.getStars() == 5)
        {
            r5.setChecked(true);
        }
*/
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.set_id(Integer.parseInt(etID.getText().toString()));
                data.setTitle(edTitle.getText().toString());
                data.setSingers(edSinger.getText().toString());
                data.setYear(Integer.parseInt(edYear.getText().toString()));
                String star = String.valueOf(getStars());
                String s = String.valueOf(star.charAt(0));
                int stars = Integer.parseInt(s);
                data.setStars(stars);

                /*int stars = 0;
                if (r1.isChecked())
                {
                    stars = 1;
                }
                else if (r2.isChecked())
                {
                    stars = 2;
                }
                else if (r3.isChecked())
                {
                    stars = 3;
                }
                else if (r4.isChecked())
                {
                    stars = 4;
                }
                else if (r5.isChecked())
                {
                    stars = 5;
                }*/

                data.setStars(stars);
                dbh.updateSong(data);
                Toast.makeText(EditActivity.this, "Update successfully!", Toast.LENGTH_SHORT).show();
                dbh.close();
                Intent i = new Intent(EditActivity.this, ListActivity.class);
                startActivity(i);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.get_id());
                Toast.makeText(EditActivity.this, "Delete successfully!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditActivity.this, ListActivity.class);
                startActivity(i);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditActivity.this, "Edit Cancel", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditActivity.this,
                        ListActivity.class);
                startActivity(i);
            }
        });
    }
    private float getStars()
    {
        float stars = rb.getRating();
        return stars;
    }
}