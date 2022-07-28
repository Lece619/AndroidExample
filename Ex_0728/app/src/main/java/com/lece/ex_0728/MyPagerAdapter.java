package com.lece.ex_0728;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter {


    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Page fragment = new Page();
        fragment.setPosition(i);
        //넘어온 포지션을 받은 프래그먼트가 참조하고 있는 레이아웃을 리턴한다.
        return fragment;
    }

    @Override
    public int getCount() {
        return 4; //총 페이지 수를 지정해주는 메소드
    }
}
