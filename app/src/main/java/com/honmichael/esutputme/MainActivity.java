package com.honmichael.esutputme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.honmichael.esutputme.fragments.ChooseSubjectFragment;
import com.honmichael.esutputme.fragments.DepartmentsFragment;
import com.honmichael.esutputme.fragments.HomeFragment;
import com.honmichael.esutputme.fragments.MiddleFragment;
import com.honmichael.esutputme.model.FragmentTag;
import com.honmichael.esutputme.model.HomeItemModel;
import com.honmichael.esutputme.model.Subjects;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainActivity,
        BottomNavigationViewEx.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationViewEx mBottomNavigationViewEx;
    private ImageView mHeaderImage;
    private DrawerLayout mDrawlayout;


    //constants
    private static final int HOME_FRAGMENT = 0;
    private static final int MIDDlE_FRAGMENT = 1;
    private static final int USER_FRAGMENT = 2;

    FrameLayout frameLayout;


    //fragments
    private HomeFragment mHomeFragment;
    private MiddleFragment mMiddleFragment;
    private DepartmentsFragment mDepartmentsFragment;
    private ChooseSubjectFragment mChooseSubjectFragment;


    //vars
    private ArrayList<String> mFragmentsTags = new ArrayList<>();
    private ArrayList<FragmentTag> mFragments = new ArrayList<>();
    private int mExitCount = 0;


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.main_content_frame2);
        mBottomNavigationViewEx = findViewById(R.id.bottom_nav_view);
        mBottomNavigationViewEx.setOnNavigationItemSelectedListener(this);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        mHeaderImage = headerView.findViewById(R.id.header_image);
        mDrawlayout = findViewById(R.id.drawer_layout);

        setHeaderImage();
        initBottomNavigationView();
        setNavigationViewListener();
        init();
    }

    private void initBottomNavigationView() {
        //mBottomNavigationViewEx = findViewById(R.id.bottom_nav_view);
        //mBottomNavigationViewEx.enableAnimation(false);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        int backStackCount = mFragmentsTags.size();
        if (backStackCount > 1) {
            // nav backwards
            String topFragmentTag = mFragmentsTags.get(backStackCount - 1);
            String newTopFragmentTag = mFragmentsTags.get(backStackCount - 2);

            setFragmentVisibilities(newTopFragmentTag);
            mFragmentsTags.remove(topFragmentTag);

            mExitCount = 0;
        } else if (backStackCount == 1) {
            mExitCount++;
            Toast.makeText(this, "1 more click to exit", Toast.LENGTH_SHORT).show();
        }
        if (mExitCount >= 2) {
            super.onBackPressed();
        }
    }

    private void setNavigationIcon(String tagname) {
        Menu menu = mBottomNavigationViewEx.getMenu();
        MenuItem menuItem = null;


        if (tagname.equals(getString(R.string.tag_fragment_home))) {
            Log.d(TAG, "setNavigationIcon: home fragment is visible");
            menuItem = menu.getItem(HOME_FRAGMENT);
            menuItem.setChecked(true);
        } else if (tagname.equals(getString(R.string.tag_departments_list))) {
            Log.d(TAG, "setNavigationIcon: home fragment is visible");
            menuItem = menu.getItem(MIDDlE_FRAGMENT);
            menuItem.setChecked(true);
        } else if (tagname.equals(getString(R.string.tag_fragment_choose))) {
            Log.d(TAG, "setNavigationIcon: home fragment is visible");
            menuItem = menu.getItem(USER_FRAGMENT);
            menuItem.setChecked(true);
        }
    }

    private void setFragmentVisibilities(String tagName) {

        if (tagName.equals(getString(R.string.tag_fragment_home))) {
            showBottomNavigation();
            frameLayout.setVisibility(View.VISIBLE);
        }
        if (tagName.equals(getString(R.string.tag_departments_list)))
            showBottomNavigation();
        if (tagName.equals(getString(R.string.tag_fragment_choose)))
            hideBottomNavigation();


        for (int i = 0; i < mFragments.size(); i++) {
            if (tagName.equals(mFragments.get(i).getTag())) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.show(mFragments.get(i).getFragment());
                transaction.commit();
            } else {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.hide(mFragments.get(i).getFragment());
                transaction.commit();
            }
            setNavigationIcon(tagName);
        }
    }

    private void setHeaderImage() {
        Log.d(TAG, "setHeaderImage: Setting header image for navigation ");
        Glide.with(this)
                .load(R.drawable.ic_launcher_background)
                .into(mHeaderImage);
    }

    private void hideBottomNavigation() {
        if (mBottomNavigationViewEx != null) {
            mBottomNavigationViewEx.setVisibility(View.GONE);
        }
    }

    private void showBottomNavigation() {
        if (mBottomNavigationViewEx != null) {
            mBottomNavigationViewEx.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.home: {
                mFragmentsTags.clear();
                mFragmentsTags = new ArrayList<>();
                init();
                break;
            }


            case R.id.settings: {
                Log.d(TAG, "onNavigationItemSelected: Settings");
                if (mChooseSubjectFragment == null) {
                    mChooseSubjectFragment = new ChooseSubjectFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mChooseSubjectFragment, getString(R.string.tag_fragment_choose));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_choose));
                    mFragments.add(new FragmentTag(mChooseSubjectFragment, getString(R.string.tag_fragment_choose)));

                } else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_choose));
                    mFragmentsTags.add(getString(R.string.tag_fragment_choose));
                }
                frameLayout.setVisibility(View.GONE);
                setFragmentVisibilities(getString(R.string.tag_fragment_choose));

                break;
            }
            case R.id.agreement: {

                if (mDepartmentsFragment == null) {
                    mDepartmentsFragment = new DepartmentsFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mDepartmentsFragment, getString(R.string.tag_departments_list));
                    transaction.commit();

                    mFragmentsTags.add(getString(R.string.tag_departments_list));
                    mFragments.add(new FragmentTag(mDepartmentsFragment, getString(R.string.tag_departments_list)));

                } else {
                    mFragmentsTags.remove(getString(R.string.tag_departments_list));
                    mFragmentsTags.add(getString(R.string.tag_departments_list));
                }
                frameLayout.setVisibility(View.GONE);
                setFragmentVisibilities(getString(R.string.tag_departments_list));

                break;
            }


            case R.id.bottom_nav_home: {

                if (mHomeFragment == null) {

                    mHomeFragment = new HomeFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mHomeFragment, getString(R.string.tag_fragment_home));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_home));
                    mFragments.add(new FragmentTag(mHomeFragment, getString(R.string.tag_fragment_home)));

                } else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_home));
                    mFragmentsTags.add(getString(R.string.tag_fragment_home));
                }
                menuItem.setChecked(true);
                frameLayout.setVisibility(View.VISIBLE);
                setFragmentVisibilities(getString(R.string.tag_fragment_home));
                break;
            }

            case R.id.bottom_results: {

                if (mDepartmentsFragment == null) {

                    mDepartmentsFragment = new DepartmentsFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mDepartmentsFragment, getString(R.string.tag_departments_list));
                    transaction.commit();

                    menuItem.setChecked(true);
                    mFragmentsTags.add(getString(R.string.tag_departments_list));
                    mFragments.add(new FragmentTag(mDepartmentsFragment, getString(R.string.tag_departments_list)));

                } else {
                    mFragmentsTags.remove(getString(R.string.tag_departments_list));
                    mFragmentsTags.add(getString(R.string.tag_departments_list));
                }

                menuItem.setChecked(true);
                frameLayout.setVisibility(View.GONE);
                setFragmentVisibilities(getString(R.string.tag_departments_list));

                break;
            }

            case R.id.bottom_user_profile: {

                if (mChooseSubjectFragment == null) {

                    mChooseSubjectFragment = new ChooseSubjectFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mChooseSubjectFragment, getString(R.string.tag_fragment_choose));
                    transaction.commit();
                    frameLayout.setVisibility(View.GONE);
                    mFragmentsTags.add(getString(R.string.tag_fragment_choose));
                    mFragments.add(new FragmentTag(mChooseSubjectFragment, getString(R.string.tag_fragment_choose)));

                } else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_choose));
                    mFragmentsTags.add(getString(R.string.tag_fragment_choose));
                }
                menuItem.setChecked(true);
                frameLayout.setVisibility(View.GONE);
                setFragmentVisibilities(getString(R.string.tag_fragment_choose));

                break;
            }
        }
        mDrawlayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void setNavigationViewListener() {
        Log.d(TAG, "setNavigationViewListener: initializing navigation drawer listener");
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void init() {

        if (mHomeFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(mHomeFragment).commitAllowingStateLoss();
            getSupportFragmentManager().beginTransaction().remove(mMiddleFragment).commitAllowingStateLoss();
        }

        mHomeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_content_frame, mHomeFragment, getString(R.string.tag_fragment_home));
        transaction.commit();
        mFragmentsTags.add(getString(R.string.tag_fragment_home));
        mFragments.add(new FragmentTag(mHomeFragment, getString(R.string.tag_fragment_home)));

/*
        if (mHomeFragment == null) {

            mHomeFragment = new HomeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_content_frame, mHomeFragment, getString(R.string.tag_fragment_home));
            transaction.commit();

            mFragmentsTags.add(getString(R.string.tag_fragment_home));
            mFragments.add(new FragmentTag(mHomeFragment, getString(R.string.tag_fragment_home)));

        } else {
            mFragmentsTags.remove(getString(R.string.tag_fragment_home));
            mFragmentsTags.add(getString(R.string.tag_fragment_home));
        }*/
        frameLayout.setVisibility(View.VISIBLE);
        setFragmentVisibilities(getString(R.string.tag_fragment_home));


        mMiddleFragment = new MiddleFragment();
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        transaction2.add(R.id.main_content_frame2, mMiddleFragment, getString(R.string.tag_fragment_middle));
        transaction2.commit();


        /*if (mMiddleFragment == null) {

            mMiddleFragment = new MiddleFragment();
            FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
            transaction2.add(R.id.main_content_frame2, mMiddleFragment, getString(R.string.tag_fragment_middle));
            transaction2.commit();
        }*/
    }

    @Override
    public void inflateChooseSubjectsFragment(HomeItemModel model) {

        if (mChooseSubjectFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(mChooseSubjectFragment).commitAllowingStateLoss();
        }

        mChooseSubjectFragment = new ChooseSubjectFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_content_frame, mChooseSubjectFragment, getString(R.string.tag_fragment_choose));
        transaction.commit();


        mFragmentsTags.add(getString(R.string.tag_fragment_choose));
        mFragments.add(new FragmentTag(mChooseSubjectFragment, getString(R.string.tag_fragment_choose)));

        frameLayout.setVisibility(View.GONE);

        setFragmentVisibilities(getString(R.string.tag_fragment_choose));
    }

    @Override
    public void onSelectSubjectsAction(int position) {

    }

    @Override
    public void inflateExamBoardActivity(ArrayList<Subjects> selectedsubjects) {


        String[] selectedSubj = new String[selectedsubjects.size()];
        for (int i = 0; i < selectedsubjects.size(); i++) {
            selectedSubj[i] = String.valueOf(selectedsubjects.get(i).getName());

        }

        Intent openExamBoardActivity = new Intent(MainActivity.this, ExamBoaredActivity.class);
        openExamBoardActivity.putExtra("KEY", selectedSubj);
        startActivity(openExamBoardActivity);





    }


}
