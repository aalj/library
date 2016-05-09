package com.bestsnail.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestsnail.R;
import com.bestsnail.fragment.HomeFragment;
import com.bestsnail.fragment.OtherFragment;
import com.bestsnail.fragment.PersonFragment;
import com.bestsnail.fragment.SousuoFragment;
import com.bestsnail.view.HomeView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements HomeView {


    Fragment homeFragment = null;
    Fragment sousuoFragment = null;
    Fragment otherFragment = null;
    Fragment personFragment = null;
    Fragment[] allFragment = new Fragment[4];
    ImageView[] allIma = new ImageView[4];
    TextView[] alltv = new TextView[4];
    //碎片提交事务
    FragmentTransaction transaction = null;
    //用于记录当前选择的是那个tab
    int temp = -1;
    //用于记录上一次选择的tab
    int currentTabIndex = -1;
    //首页标题栏的内容
//    String[] titleName = new String[]{"首页","快速检索","个人中心","其他"};
    String[] titleName = new String[]{"齐齐哈尔大学图书馆", "齐齐哈尔大学图书馆", " ", "齐齐哈尔大学图书馆"};
    private FrameLayout infofragment;
    private ImageView imahome;
    private TextView tvhome;
    private LinearLayout homelinear;
    private ImageView imasousuo;
    private TextView tvsousuo;
    private LinearLayout sousuolinear;
    private ImageView imaperson;
    private TextView tvperson;
    private LinearLayout personlinear;
    private ImageView imaother;
    private TextView tvother;
    private LinearLayout otherlinear;
    private Toolbar toolbar;
    private TextView titleActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    @Override
    public void switchTab(Fragment hideFragment, Fragment addFragment) {
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        fts.hide(hideFragment);
        if (!addFragment.isAdded()) {
            fts.add(R.id.info_fragment, addFragment);

        }
        fts.show(addFragment).commit();

    }

    @Override
    public void changeStatus(TextView selectorText, TextView noSelectorText,
                             ImageView selectorIma, ImageView noSelectorIma) {
        //2、显示点击加载的fragment，改变字体颜色
        noSelectorText.setTextColor(0xff8f8f8f);
        noSelectorIma.setSelected(false);
        selectorText.setTextColor(0xff4AB5E8);
        selectorIma.setSelected(true);

    }


    @Override
    public void submitLogin() {

    }

    @Override
    public void changeTitleText(int titleName) {
        titleActionBar.setText(this.titleName[titleName]);
    }


    @OnClick({R.id.home_linear, R.id.sousuo_linear, R.id.person_linear, R.id.other_linear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_linear://选择首页
                temp = 0;

                break;
            case R.id.sousuo_linear://选择快速检索
                temp = 1;

                break;
            case R.id.person_linear://选择个人中心
                temp = 2;
                break;
            case R.id.other_linear://选择其他
                temp = 3;
                break;
        }

        //点击以后需要做的事，1、改变选中的fragment 2、隐藏之前的fragment 3、改变字体颜色
        //1、隐藏当前fragment，同时改变字体颜色
        if (temp != currentTabIndex) {
            switchTab(allFragment[currentTabIndex], allFragment[temp]);

        }
        changeStatus(alltv[temp], alltv[currentTabIndex], allIma[temp], allIma[currentTabIndex]);
        changeTitleText(temp);
        //保存当前选中的页面
        currentTabIndex = temp;

    }


    public void initView() {

        ButterKnife.bind(this);
        this.otherlinear = (LinearLayout) findViewById(R.id.other_linear);
        this.tvother = (TextView) findViewById(R.id.tv_other);
        this.imaother = (ImageView) findViewById(R.id.ima_other);
        this.personlinear = (LinearLayout) findViewById(R.id.person_linear);
        this.tvperson = (TextView) findViewById(R.id.tv_person);
        this.imaperson = (ImageView) findViewById(R.id.ima_person);
        this.sousuolinear = (LinearLayout) findViewById(R.id.sousuo_linear);
        this.tvsousuo = (TextView) findViewById(R.id.tv_sousuo);
        this.imasousuo = (ImageView) findViewById(R.id.ima_sousuo);
        this.homelinear = (LinearLayout) findViewById(R.id.home_linear);
        this.tvhome = (TextView) findViewById(R.id.tv_home);
        this.imahome = (ImageView) findViewById(R.id.ima_home);
        this.infofragment = (FrameLayout) findViewById(R.id.info_fragment);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.titleActionBar = (TextView) findViewById(R.id.title_actionbar);


        //初始化第一次进入以后，页面显示问题
        imahome.setSelected(true);
        tvhome.setTextColor(0xff4AB5E8);
        //页面第一次进入时候对页面进行初始化操作
        changeTitleText(0);

        //把imageView放置于数组中用于管理底部导航栏的状态
        allIma[0] = imahome;
        allIma[1] = imasousuo;
        allIma[2] = imaperson;
        allIma[3] = imaother;
        //把TextView放置于数组中用于管理底部导航栏的状态
        alltv[0] = tvhome;
        alltv[1] = tvsousuo;
        alltv[2] = tvperson;
        alltv[3] = tvother;

        //Faragment初始化
        homeFragment = new HomeFragment();
        sousuoFragment = new SousuoFragment();
        otherFragment = new OtherFragment();
        personFragment = new PersonFragment();
        //使用数组承装许所有的fragment
        allFragment[0] = homeFragment;
        allFragment[1] = sousuoFragment;
        allFragment[2] = personFragment;
        allFragment[3] = otherFragment;

        //加载全部的fragment，同时隐藏不现实的fragmeng
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.info_fragment, homeFragment)
                .add(R.id.info_fragment, sousuoFragment)
                .add(R.id.info_fragment, otherFragment)
                .add(R.id.info_fragment, personFragment)
                .hide(sousuoFragment)
                .hide(otherFragment)
                .hide(personFragment)
                .show(homeFragment)
                .commit();
        currentTabIndex = 0;


    }


}
