package com.ccjeng.weather.view.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ccjeng.weather.R;
import com.ccjeng.weather.view.base.BaseDialog;

/**
 * Created by andycheng on 2016/10/10.
 */

public class PhotoInfoDialog extends BaseDialog implements View.OnClickListener {

    private TextView tvUsername;
    private TextView tvUrl;
    private Button btnClose;

    public static PhotoInfoDialog newInstance(String username, String url) {
        PhotoInfoDialog dialog = new PhotoInfoDialog();
        Bundle args = new Bundle();
        args.putString("username", username);
        args.putString("url", url);
        dialog.setArguments(args);

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_photo_info, container);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        tvUsername = (TextView) view.findViewById(R.id.username);
        tvUrl = (TextView) view.findViewById(R.id.url);
        btnClose = (Button) view.findViewById(R.id.bt_close);
        btnClose.setOnClickListener(this);

        //Set Original Name
        String userName = getArguments().getString("username");
        tvUsername.setText(getString(R.string.photoinfo_username, userName));
        String url =  getArguments().getString("url");
        tvUrl.setText(url);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_close: {
                dismiss();
                break;
            }
        }
    }
}
