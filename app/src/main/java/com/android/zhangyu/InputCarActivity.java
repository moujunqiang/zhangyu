package com.android.zhangyu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;

public class InputCarActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请输入品牌
     */
    private EditText mEtPinpai;
    /**
     * 请输入品牌
     */
    private EditText mEtType;
    /**
     * 请输入品牌
     */
    private EditText mEtYear;
    /**
     * 请输入品牌
     */
    private EditText mEtPrice;
    /**
     * 请输入品牌
     */
    private EditText mEtContent;
    /**
     * 提交
     */
    private Button mBtnCommit;
    String filePathByUri = "-1";
    private ImageView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mEtPinpai = (EditText) findViewById(R.id.et_pinpai);
        mEtType = (EditText) findViewById(R.id.et_type);
        mEtYear = (EditText) findViewById(R.id.et_year);
        mEtPrice = (EditText) findViewById(R.id.et_price);
        mEtContent = (EditText) findViewById(R.id.et_content);
        mBtnCommit = (Button) findViewById(R.id.btn_commit);
        mBtnCommit.setOnClickListener(this);
        viewById = findViewById(R.id.image);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });
    }

    /**
     * 打开相册
     */
    private void choosePhoto() {
        //这是打开系统默认的相册(就是你系统怎么分类,就怎么显示,首先展示分类列表)
        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picture, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == Activity.RESULT_OK
                && null != data) {
            try {
                filePathByUri = FileUtil.getFilePathByUri(InputCarActivity.this, data.getData());
                Bitmap bitmap = BitmapFactory.decodeFile(filePathByUri);
                viewById.setImageBitmap(bitmap);

            } catch (Exception e) {
                //"上传失败");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_commit:
                Intent intent = new Intent(InputCarActivity.this, SaveCarActivity.class);
                HistoryBean historyBean = new HistoryBean();
                historyBean.setPinpai(mEtPinpai.getText().toString());
                historyBean.setYear(mEtYear.getText().toString());
                historyBean.setType(mEtType.getText().toString());
                historyBean.setPrice(mEtPrice.getText().toString());
                historyBean.setContent(mEtContent.getText().toString());
                historyBean.setIamge(filePathByUri);
                intent.putExtra("car", historyBean);
                startActivity(intent);
                break;
        }
    }
}