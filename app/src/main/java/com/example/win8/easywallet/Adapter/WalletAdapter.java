package com.example.win8.easywallet.Adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.win8.easywallet.BudgetItem;
import com.example.win8.easywallet.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Win8 on 10/12/2560.
 */

public class WalletAdapter extends ArrayAdapter<BudgetItem>{


        private Context mContext;
        private int mLayoutResId;
        private ArrayList<BudgetItem> mBudgetItemList;

    public WalletAdapter(@NonNull Context context, int resource, @NonNull List<BudgetItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View itemLayout = inflater.inflate(mLayoutResId, null);

            BudgetItem item =  mBudgetItemList.get(position);

            ImageView ImageView = itemLayout.findViewById(R.id.image_view);
            TextView TitleTextView = itemLayout.findViewById(R.id.title_text_view);
            TextView NumberTextView = itemLayout.findViewById(R.id.number_text_view);

            TitleTextView.setText(item.title);
            NumberTextView.setText(item.money);

            String pictureFileName = item.picture;

            AssetManager am = mContext.getAssets();
            try {
                InputStream stream = am.open(pictureFileName);
                Drawable drawable = Drawable.createFromStream(stream, null);
                ImageView.setImageDrawable(drawable);

            } catch (IOException e) {
                e.printStackTrace();

                File pictureFile = new File(mContext.getFilesDir(), pictureFileName);
                Drawable drawable = Drawable.createFromPath(pictureFile.getAbsolutePath());
                ImageView.setImageDrawable(drawable);
            }

            return itemLayout;
        }
    }
