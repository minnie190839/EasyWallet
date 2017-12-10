/**
 * Created by Win8 on 10/12/2560.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.win8.easywallet.DbHelper.DbHelper;
import com.example.win8.easywallet.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

class IncomeActivity extends AppCompatActivity {

    private ImageView mImageviewAdd;
    private EditText mEditText;
    private EditText mEditTextMoney;
    private Button mSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        Intent intent = getIntent();
        String type = intent.getStringExtra("1");
        final String pictureFileName = "ic_income.png";
        mImageviewAdd = findViewById(R.id.image_view);

        AssetManager am = this.getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            mImageviewAdd.setImageDrawable(drawable);

        } catch (IOException e) {
            e.printStackTrace();

            File pictureFile = new File(this.getFilesDir(), pictureFileName);
            Drawable drawable = Drawable.createFromPath(pictureFile.getAbsolutePath());
            mImageviewAdd.setImageDrawable(drawable);
        }

        mEditText = findViewById(R.id.title_edit_text);
        mEditTextMoney = findViewById(R.id.money_edit_text);
        mSave = findViewById(R.id.save_button);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = String.valueOf(mEditText.getText());
                String mon = String.valueOf(mEditTextMoney.getText());

                ContentValues cv = new ContentValues();
                cv.put(DbHelper.COL_PICTURE, pictureFileName);
                cv.put(DbHelper.COL_MONEY, mon);
                cv.put(DbHelper.COL_TITLE,title );


                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });

    }
}
