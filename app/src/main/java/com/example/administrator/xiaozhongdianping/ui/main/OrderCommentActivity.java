package com.example.administrator.xiaozhongdianping.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.presenter.Main.OrderCommentPresenterImpl;
import com.example.administrator.xiaozhongdianping.view.main.OrderCommentView;

public class OrderCommentActivity extends AppCompatActivity implements OrderCommentView, View.OnClickListener {

    private RatingBar rbComment;
    private EditText etComment;
    LinearLayout layoutBack;
    Button btnComment;
    OrderCommentPresenterImpl orderCommentPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_comment);

        //初始化控件
        rbComment = (RatingBar) findViewById(R.id.rb_comment);
        etComment = (EditText) findViewById(R.id.et_comment);
        layoutBack = (LinearLayout) findViewById(R.id.layoutBack);
        btnComment = (Button) findViewById(R.id.comment_button);

        //设置数据
        orderCommentPresenter=new OrderCommentPresenterImpl(this);
        //设置监听
        layoutBack.setOnClickListener(this);
        btnComment.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layoutBack:
                finish();
                break;
            case R.id.comment_button:
                orderCommentPresenter.clickCommentButton(etComment.getText().toString(),rbComment.getRating());
                break;
        }
    }

    @Override
    public void showCommentSuccess() {
        Toast.makeText(this,"评价成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
