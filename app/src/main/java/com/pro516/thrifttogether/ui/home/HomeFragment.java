package com.pro516.thrifttogether.ui.home;

import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.pro516.thrifttogether.R;
import com.pro516.thrifttogether.entity.home.BannerInfo;
import com.pro516.thrifttogether.ui.base.BaseFragment;
import com.pro516.thrifttogether.ui.home.activity.HomeBeautyActivity;
import com.pro516.thrifttogether.ui.home.activity.nav.HomeCityPickerActivity;
import com.pro516.thrifttogether.ui.home.activity.HomeEntertainmentActivity;
import com.pro516.thrifttogether.ui.home.activity.HomeFoodActivity;
import com.pro516.thrifttogether.ui.home.activity.HomeHotelActivity;
import com.pro516.thrifttogether.ui.home.activity.HomeKtvActivity;
import com.pro516.thrifttogether.ui.home.activity.HomeLookingAroundActivity;
import com.pro516.thrifttogether.ui.home.activity.movie.HomeMovieActivity;
import com.pro516.thrifttogether.ui.home.activity.nav.HomeSearchActivity;
import com.pro516.thrifttogether.ui.home.activity.HomeTicketActivity;
import com.stx.xhb.xbanner.XBanner;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hncboy on 2019-03-19.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(View view) {
        AppCompatImageButton mScanQrcodeImgBtn = view.findViewById(R.id.scan_qrcode_btn);
        LinearLayout cityPickerLLayout = view.findViewById(R.id.city_picker_layout);
        LinearLayout homeSearchLLayout = view.findViewById(R.id.home_search_layout);
        AppCompatImageButton enterLookingAroundImgBtn = view.findViewById(R.id.enter_looking_around_img_btn);
        LinearLayout homeFoodLayout = view.findViewById(R.id.home_food_layout);
        LinearLayout homeMovieLayout = view.findViewById(R.id.home_movie_layout);
        LinearLayout homeHotelLayout = view.findViewById(R.id.home_hotel_layout);
        LinearLayout homeKtvLayout = view.findViewById(R.id.home_ktv_layout);
        LinearLayout homeBeautyLayout = view.findViewById(R.id.home_beauty_layout);
        LinearLayout homeEntertainmentLayout = view.findViewById(R.id.home_entertainment_layout);
        LinearLayout homeTicketLayout = view.findViewById(R.id.home_ticket_layout);
        mScanQrcodeImgBtn.setOnClickListener(this);
        cityPickerLLayout.setOnClickListener(this);
        homeSearchLLayout.setOnClickListener(this);
        enterLookingAroundImgBtn.setOnClickListener(this);
        homeFoodLayout.setOnClickListener(this);
        homeMovieLayout.setOnClickListener(this);
        homeHotelLayout.setOnClickListener(this);
        homeKtvLayout.setOnClickListener(this);
        homeBeautyLayout.setOnClickListener(this);
        homeEntertainmentLayout.setOnClickListener(this);
        homeTicketLayout.setOnClickListener(this);
        initBanner(view);
    }

    private void initBanner(View view) {
        List<BannerInfo> bannerInfos = new ArrayList<>();
        String[] imageUrls = getResources().getStringArray(R.array.bannerImageUrls);
        for (String imageUrl : imageUrls) {
            BannerInfo bannerInfo = new BannerInfo();
            bannerInfo.setImageUrl(imageUrl);
            bannerInfos.add(bannerInfo);
        }

        XBanner xBanner = view.findViewById(R.id.xbanner);
        xBanner.setBannerData(bannerInfos);
        xBanner.setOnItemClickListener((banner, model, imageView, position) ->
                toast("点击了第" + position + "图片")
        );

        xBanner.loadImage((banner, model, imageView, position) ->
                Glide.with(imageView.getContext()).load(((BannerInfo)
                        model).getXBannerUrl()).into((ImageView) imageView)
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scan_qrcode_btn: // 二维码扫描
                AndPermission.with(this)
                        .runtime()
                        .permission(Permission.Group.STORAGE, Permission.Group.CAMERA)
                        .onGranted(permissions -> {
                            startActivity(CaptureActivity.class, false);
                        })
                        .onDenied(permissions -> {
                            toast("请开启权限");
                        })
                        .start();
                break;
            case R.id.city_picker_layout: // 城市选择
                startActivity(HomeCityPickerActivity.class);
                break;
            case R.id.home_search_layout: // 搜索
                startActivity(HomeSearchActivity.class);
                break;
            case R.id.enter_looking_around_img_btn: // 随便看看
                startActivity(HomeLookingAroundActivity.class);
                break;
            case R.id.home_food_layout: // 美食
                startActivity(HomeFoodActivity.class);
                break;
            case R.id.home_movie_layout: // 电影
                startActivity(HomeMovieActivity.class);
                break;
            case R.id.home_hotel_layout: // 酒店
                startActivity(HomeHotelActivity.class);
                break;
            case R.id.home_beauty_layout: // 丽人
                startActivity(HomeBeautyActivity.class);
                break;
            case R.id.home_ktv_layout: // KTV
                startActivity(HomeKtvActivity.class);
                break;
            case R.id.home_entertainment_layout: // 休闲娱乐
                startActivity(HomeEntertainmentActivity.class);
                break;
            case R.id.home_ticket_layout: // 车票
                startActivity(HomeTicketActivity.class);
                break;
            default:
                break;
        }
    }
}