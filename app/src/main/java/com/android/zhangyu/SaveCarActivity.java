package com.android.zhangyu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class SaveCarActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请输入品牌
     */
    private TextView mTvPinpai;
    /**
     * 请输入品牌
     */
    private TextView mTvType;
    /**
     * 请输入品牌
     */
    private TextView mTvYear;
    /**
     * 请输入品牌
     */
    private TextView mTvPrice;
    /**
     * 请输入品牌
     */
    private TextView mTvContent;
    /**
     * 保存
     */
    private Button mBtnCommit;
    HistoryBean car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_car);
        initView();
    }

    private void initView() {
        mTvPinpai = (TextView) findViewById(R.id.tv_pinpai);
        mTvType = (TextView) findViewById(R.id.tv_type);
        mTvYear = (TextView) findViewById(R.id.tv_year);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mBtnCommit = (Button) findViewById(R.id.btn_commit);
        mBtnCommit.setOnClickListener(this);
        car = (HistoryBean) getIntent().getSerializableExtra("car");
        mTvContent.setText(car.getContent());
        mTvPinpai.setText(car.getPinpai());
        mTvYear.setText(car.getYear());
        mTvPrice.setText(car.getPrice());
        mTvType.setText(car.getType());
        ImageView image = findViewById(R.id.image);

        if (!car.getIamge().equals("-1")) {
            Bitmap bitmap = BitmapFactory.decodeFile(car.getIamge());
            image.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_commit:
                new HistoryDao(SaveCarActivity.this).insertHistory(car);
                startActivity(new Intent(SaveCarActivity.this, ShowCarActivity.class));
                break;
        }
    }
}