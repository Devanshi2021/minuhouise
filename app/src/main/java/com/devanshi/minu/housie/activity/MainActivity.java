package com.devanshi.minu.housie.activity;

import android.*;
import android.annotation.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.widget.*;

import androidx.core.app.*;
import androidx.core.content.*;
import androidx.databinding.*;
import androidx.fragment.app.*;
import androidx.recyclerview.widget.*;

import com.devanshi.minu.housie.R;
import com.devanshi.minu.housie.adapters.*;
import com.devanshi.minu.housie.customui.*;
import com.devanshi.minu.housie.databinding.*;
import com.devanshi.minu.housie.fragments.ui.home.*;
import com.devanshi.minu.housie.fragments.ui.menu.*;
import com.devanshi.minu.housie.fragments.ui.mycontest.*;
import com.devanshi.minu.housie.interfaces.*;
import com.devanshi.minu.housie.models.Menu;
import com.devanshi.minu.housie.utils.*;

import java.util.*;


public class MainActivity extends BaseActivity implements ItemClickListener {

    private ActivityMainBinding binding;
    private ArrayList<Menu> menuArrayList;
    private ItemClickListener itemClickListener;
    private MenuAdapter menuAdapter;
    private Fragment selectedFragment;
    private Preference preference;
    private boolean doubleBackToExitPressedOnce = false;
    private int selectedItem = 0;
    private String resumeFragment;
    private static final int ALL_PERMISSIONS_REQUEST_CODE = 200;


    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isAllPermissionsGranted(this)) {
            askForAllRequiredPermissions();
        }

        preference = new Preference(getApplicationContext());
        if (preference.getUserName().isEmpty()){
            startActivity(new Intent(this, LoginSignupActivity.class));
            finish();
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupUI(binding.rootView);
        itemClickListener = this;
        if (getIntent().hasExtra(getString(R.string.fragment_to_be_opened))) {
            resumeFragment = getIntent().getStringExtra(getString(R.string.fragment_to_be_opened));
        } else {
            resumeFragment = getString(R.string.menu_home);
        }
        initUI();
        toast = Toast.makeText(this, "Click again to exit", Toast.LENGTH_SHORT);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {

        menuArrayList = new ArrayList<>();

        Menu menu = new Menu();
        menu.setMenu_id(1);
        menu.setMenu_name(getString(R.string.menu_home));
        menu.setIcon_id(R.drawable.ic_home_active);
        menu.setIcon_path("ic_home");
        menu.setSelected(false);
        menuArrayList.add(menu);

        menu = new Menu();
        menu.setMenu_id(1);
        menu.setMenu_name(getString(R.string.menu_my_contest));
        menu.setIcon_id(R.drawable.ic_mycontest_inactive);
        menu.setIcon_path("ic_mycontest");
        menu.setSelected(false);
        menuArrayList.add(menu);

        menu = new Menu();
        menu.setMenu_id(1);
        menu.setMenu_name(getString(R.string.menu_winners));
        menu.setIcon_id(R.drawable.ic_winner_inactive);
        menu.setIcon_path("ic_winner");
        menu.setSelected(false);
        menuArrayList.add(menu);

        menu = new Menu();
        menu.setMenu_id(1);
        menu.setMenu_name(getString(R.string.menu_menu));
        menu.setIcon_id(R.drawable.ic_menu_inactive);
        menu.setIcon_path("ic_menu");
        menu.setSelected(false);
        menuArrayList.add(menu);

        menuAdapter = new MenuAdapter(menuArrayList, itemClickListener, MainActivity.this);
        binding.menuRecyclerView.setAdapter(menuAdapter);

        SpanningLinearLayoutManager spanningLinearLayoutManager = new SpanningLinearLayoutManager(this, menuAdapter.getItemCount());
        spanningLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        spanningLinearLayoutManager.setScrollHorizontally(true);
        spanningLinearLayoutManager.setMaxItemsToShowInScreen(4);
        binding.menuRecyclerView.setLayoutManager(spanningLinearLayoutManager);

        binding.fragmentContainer.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeLeft() {
                clickLeft();
                super.onSwipeLeft();
            }

            @Override
            public void onSwipeRight() {
                clickRight();
                super.onSwipeRight();
            }
        });

        onItemClick(resumeFragment);
    }

    public void clickLeft() {
        if (selectedItem < menuArrayList.size()-1){
            onItemClick(menuArrayList.get(selectedItem+1).getMenu_name());
        }
    }

    public void clickRight() {
        if (selectedItem != 0){
            onItemClick(menuArrayList.get(selectedItem-1).getMenu_name());
        }
    }

    /**
     * Item Selected Color Change
     *
     * @param index selected item index
     */
    private void setSelected(int index) {
        if (menuArrayList != null && menuArrayList.size() > 0) {
            selectedItem = index;
            for (int i = 0; i < menuArrayList.size(); i++) {
                Menu menu = menuArrayList.get(i);
                if (i == index) {
                    menu.setSelected(true);
                } else {
                    menu.setSelected(false);
                }
                menuArrayList.set(i, menu);
            }

            if (menuAdapter != null) {
                menuAdapter.refreshList(menuArrayList);
            }
        }
    }


    @Override
    public void onItemClick(String menu_name) {
        if (menu_name.equals(getString(R.string.menu_home))){
            if (!(selectedFragment instanceof HomeFragment)) {
                openFragment(new HomeFragment(), false);
            }
            setSelected(0);
        } else if (menu_name.equals(getString(R.string.menu_my_contest))){
            if (preference.getUserId().isEmpty()){
                startActivity(new Intent(MainActivity.this, LoginSignupActivity.class));
                finish();
            } else {
                if (!(selectedFragment instanceof MyContestFragment)) {
                    openFragment(new MyContestFragment(), false);
                }
                setSelected(1);
            }
        } else if (menu_name.equals(getString(R.string.menu_winners))){
            if (preference.getUserId().isEmpty()){
                startActivity(new Intent(MainActivity.this, LoginSignupActivity.class));
                finish();
            } else {
                if (!(selectedFragment instanceof MenuFragment)) {
                    openFragment(new MenuFragment(), false);
                }
                setSelected(2);
            }
        } else if (menu_name.equals(getString(R.string.menu_menu))){
            if (preference.getUserId().isEmpty()){
                startActivity(new Intent(MainActivity.this, LoginSignupActivity.class));
                finish();
            } else {
                preference.clearAllPreferenceData();
                recreate();
            }
        }
    }

    /**
     * Open Fragment
     *
     * @param fragment Fragment whichever has to be opened
     * @param addToStack boolean whether fragment has to be added to back-stack
     */
    public void openFragment(Fragment fragment, boolean addToStack) {
        selectedFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (addToStack) {
            transaction.addToBackStack(fragment.getClass().getName());
        }
        transaction.commit();

    }

    Toast toast;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            toast.cancel();
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        toast.show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
    }

    public static boolean isAllPermissionsGranted(Context context) {
        boolean writeStoragePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        boolean readStoragePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        return readStoragePermission && writeStoragePermission;
    }

    public void askForAllRequiredPermissions() {

        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                ALL_PERMISSIONS_REQUEST_CODE);
    }
}
