package com.bestsnail.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.bean.Student;
import com.bestsnail.widget.CircleImageView;

import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentInfoActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar _mToolbar;
    @Bind(R.id.img_head)
    CircleImageView _mImgHead;
    @Bind(R.id.view_user)
    RelativeLayout _mViewUser;
    @Bind(R.id.text_nicheng)
    TextView _mTextNicheng;//名字
    @Bind(R.id.nicheng)
    RelativeLayout _mNicheng;//昵称布局
    @Bind(R.id.study_gexingqianming)
    TextView _mStudyGexingqianming;//学号
    @Bind(R.id.gexingqianming)
    RelativeLayout _mGexingqianming;//学号布局
    @Bind(R.id.sex)
    TextView _mSex;//性别
    @Bind(R.id.xingbie)
    RelativeLayout _mXingbie;//性别布局
    @Bind(R.id.text_age)
    TextView _mTextAge;//学院
    @Bind(R.id.nianling)
    RelativeLayout _mNianling;//学院布局
    @Bind(R.id.text_grade)
    TextView _mTextGrade;//专业
    @Bind(R.id.nianji_grade)
    RelativeLayout _mNianjiGrade;//专业布局
    @Bind(R.id.text_school)
    TextView _mTextSchool;//年级
    @Bind(R.id.xuexiao)
    RelativeLayout _mXuexiao;//年级布局


    Student student = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        ButterKnife.bind(this);
        setSupportActionBar(_mToolbar);
        //加载右面的突变
        _mToolbar.setNavigationIcon(R.mipmap.back);
        //设置右面的点击事件
        _mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    /**
     * 页面初始化
     */
    private void initView() {
        student = ((Library) getApplication()).getStudent();

        _mTextNicheng.setText(student.getStu_name());
        _mStudyGexingqianming.setText(student.getStu_number());
        _mSex.setText(student.getStu_sex());
        _mTextAge.setText(student.getStu_college());
        _mTextGrade.setText(student.getStu_subject());
        _mTextSchool.setText(student.getStu_grade());

    }

    @OnClick({R.id.view_user, R.id.xingbie})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.xingbie://性别
                shoeAlertDialog(_mSex);
                break;
            case R.id.view_user:

                break;

        }
    }


    public void shoeAlertDialog(final TextView eview) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(StudentInfoActivity.this);
        View view = getLayoutInflater().inflate(R.layout.edittext_dialog, null);
        final EditText ed = (EditText) view.findViewById(R.id.text_dialog);
        builder.setTitle("修改内容");
        builder.setView(view);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = ed.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    eview.setText(text);


                } else {
                    Toast.makeText(StudentInfoActivity.this, "输入的值怎么能为空", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        builder.create().show();


    }


}
