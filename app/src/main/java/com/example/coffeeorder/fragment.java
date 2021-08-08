package com.example.coffeeorder;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.security.PrivateKey;

public class fragment extends FragmentPagerAdapter {
    private int tab;
    public fragment(@NonNull FragmentManager fm, int behavior, int tab) {
        super(fm, behavior);
        this.tab = tab;
    }



    public fragment(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Home();
            case 1:
                return new AboutUS();
            case 2:
                return new Menu();
            case 3:
                return new CofeePayOrder();
            case 4:
                return new Call();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tab;
    }
}
