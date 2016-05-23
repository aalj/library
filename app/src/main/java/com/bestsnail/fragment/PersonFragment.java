package com.bestsnail.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.adapter.GridViewAdapter;
import com.bestsnail.bean.PersonItemGridView;
import com.bestsnail.bean.Student;
import com.bestsnail.ui.ApplyLeturesActivity;
import com.bestsnail.ui.BookRecommendActivity;
import com.bestsnail.ui.BorrowBookActivityo;
import com.bestsnail.ui.BorrowBookXuJieActivityo;
import com.bestsnail.ui.BorrowHistroyActivity;
import com.bestsnail.ui.Library;
import com.bestsnail.ui.LoginActivity;
import com.bestsnail.ui.StudentInfoActivity;
import com.bestsnail.view.PersonFragmentView;
import com.bestsnail.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PersonFragment extends Fragment implements PersonFragmentView {


    View view = null;
    @Bind(R.id.grid_item)
    GridView gridItem;

    @Bind(R.id.xiaoxi_ima)
    ImageView xiaoxiIma;
    @Bind(R.id.bianji_ima)
    ImageView bianjiIma;
    @Bind(R.id.name)
    TextView _mName;

    @Bind(R.id.touxiang)
    CircleImageView _mTouxiang;

    private Student student = null;
    int stu_id = 0;

    GridViewAdapter adapter = null;
    List<PersonItemGridView> list = new ArrayList<>();

    public static String PERSON = "person";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_person, container, false);

        ButterKnife.bind(this, view);
//        student = ((Library) getActivity().getApplication()).getStudent();
//        stu_id = student.getStu_id();

        MyOnItemClickListener myOnItemClickListener = new MyOnItemClickListener();
        gridItem.setOnItemClickListener(myOnItemClickListener);
        adapter = new GridViewAdapter(getActivity(), list);
        gridItem.setAdapter(adapter);
        initView();
        return view;
    }


    @Override
    public void onStart() {
        super.onResume();

    }

    @Override
    public void initView() {

        int[] imaRes = {R.mipmap.yijie, R.mipmap.pxujie,
                R.mipmap.jiangou, R.mipmap.lishi,
                R.mipmap.baoming, R.mipmap.shoucang,
                R.mipmap.qingli, /*R.mipmap.lixianxiazai,*/
                R.mipmap.guanyuwomen};
        int[] imaRes_s = {R.mipmap.yijie_s, R.mipmap.pxujie_s,
                R.mipmap.jiangou_s, R.mipmap.lishi_s,
                R.mipmap.baoming_s, R.mipmap.shoucang_s,
                R.mipmap.qingli_s, /*R.mipmap.lixianxiazai_s,*/
                R.mipmap.guanyuwomen_s};
        String[] itemName = {"已借图书", "图书续借"
                , "图书荐购", "借阅历史", "报名讲座", "收藏", "缓存清理"
                , /*"离线下载",*/ "关于我们"};


        for (int i = 0; i < imaRes.length; i++) {
//            list.clear();
            list.add(new PersonItemGridView(imaRes_s[i], itemName[i]));
        }


        adapter.notifyDataSetChanged();


    }




    @Override
    public void intentActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        getActivity().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.touxiang, R.id.xiaoxi_ima, R.id.bianji_ima, R.id.name})
    public void onClick(View view) {
        boolean temp= stu_id>0;

        switch (view.getId()) {
            case R.id.touxiang://点击头像

            case R.id.name://登陆字
                if (stu_id > 0) {

                    Toast.makeText(getActivity(), "已经登陆", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.xiaoxi_ima://点击消息
                if(temp){
                    Intent intent = new Intent(getActivity(), StudentInfoActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(getActivity(), "xiaoxi_ima", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bianji_ima://点击编辑
                if(temp){
                    Intent intent = new Intent(getActivity(), StudentInfoActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);

                }
                Toast.makeText(getActivity(), "bianji_ima", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            stu_id = ((Library) getActivity().getApplication()).getStudent().getStu_id();
            Log.i("tag", "stu_id-----------" + stu_id);
            if (stu_id > 0) {


                switch (position) {
                    case 0://已借图书
                        Intent intentm2 = new
                                Intent(getActivity(), BorrowBookActivityo.class);
                        startActivity(intentm2);
                        break;
                    case 1://图书续借
                        Intent intentm1 = new
                                Intent(getActivity(), BorrowBookXuJieActivityo.class);
                        startActivity(intentm1);
                        break;
                    case 2://图书荐购
                        Intent intentm4 = new Intent(getActivity(), BookRecommendActivity.class);
                        startActivity(intentm4);
//                         Toast.makeText(getActivity(), "图书荐购", Toast.LENGTH_SHORT).show();
                        break;
                    case 3://借阅历史
                        Intent intentm = new
                                Intent(getActivity(), BorrowHistroyActivity.class);
                        startActivity(intentm);
//                        Toast.makeText(getActivity(), "借阅历史", Toast.LENGTH_SHORT).show();
                        break;
                    case 4://报名讲座
                        Intent intentm3 = new Intent(getActivity(), ApplyLeturesActivity.class);
                        startActivity(intentm3);
//                        Toast.makeText(getActivity(), "报名讲座", Toast.LENGTH_SHORT).show();
                        break;
                    case 5://收藏

                        Intent intent = new Intent();
//                            intent.setClass(getActivity(), CollectionActivity.class);
                        startActivity(intent);

                        Toast.makeText(getActivity(), "收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case 6://缓存清理
                        Toast.makeText(getActivity(), "缓存清理", Toast.LENGTH_SHORT).show();
                        break;
//                    case 7://离线下载
//                        Toast.makeText(getActivity(), "离线下载", Toast.LENGTH_SHORT).show();
//                        break;
                    case 8://关于我们
                        Toast.makeText(getActivity(), "关于我们", Toast.LENGTH_SHORT).show();
                        break;
                }
            } else {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("flags", PERSON);
                startActivityForResult(intent, 11);
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        student = ((Library) getActivity().getApplication()).getStudent();
        stu_id = student.getStu_id();

        if (stu_id > 0) {
            _mName.setText(student.getStu_name());
        } else {
            _mName.setText("未登录");
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("tag","fragment的生名周期哦onResume()");
        student = ((Library) getActivity().getApplication()).getStudent();
        stu_id = student.getStu_id();
        if (stu_id > 0) {
            _mName.setText(student.getStu_name());
        }
    }
}
