package com.imanesaurus.xperiasetting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemProperties;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.widget.Toast;
import miui.app.ActionBar;
import miui.preference.PreferenceActivity;

public class MainActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    private static final String DEV = SystemProperties.get("ro.product.mod_device");
    private static final String NAVIGATION_BAR_SHOW = "navigation_bar_show_key";
    private static final String CAMERA_SWITCH = "camera_switch_key";
    private static final String DENSITY_SWITCH = "density_key";
    private static final String ABOUT_WINDOW = "about_window_key";
    private ListPreference mCameraSwitch;
    private CheckBoxPreference mNavigationBarShow;
    private ListPreference mDensity;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initStyle();
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        if (actionBar !=null) {
                actionBar.setTitle(R.string.app_name);
        }
        addPreferencesFromResource(R.xml.xperiasettings);
        mCameraSwitch = (ListPreference) findPreference(CAMERA_SWITCH);
        mNavigationBarShow = (CheckBoxPreference) findPreference(NAVIGATION_BAR_SHOW);
        mDensity = (ListPreference) findPreference(DENSITY_SWITCH);

        mCameraSwitch.setOnPreferenceChangeListener(this);
        setListPreferenceSummary(mCameraSwitch);

        mDensity.setOnPreferenceChangeListener(this);
        setListPreferenceSummary(mDensity);

        //start popup window activity
        Preference mAboutWindow = findPreference(ABOUT_WINDOW);
        mAboutWindow.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(MainActivity.this,AboutWindowPop.class));
                return true;
            }
        });

        //Show pref screen refer to Patchrom PORT_PRODUCT
        //noinspection AccessStaticViaInstance
        if (!this.DEV.equals("huashan_imanesaurus")) {
            getPreferenceScreen().removeAll(); {
                Toast.makeText(getApplicationContext(), R.string.device_spec_error, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initStyle() {
        setTheme(miui.R.style.Theme_Light_Settings);
    }

    private void setListPreferenceSummary(ListPreference mListPreference) {
        if (mListPreference == mCameraSwitch) {
            if (0 == Integer.parseInt(mListPreference.getValue())){
                mListPreference.setSummary(R.string.camera_switch_cyanogen_summary);
            } else if (1 == Integer.parseInt(mListPreference.getValue())){
                mListPreference.setSummary(R.string.camera_switch_nubia_summary);
            }
        }
        if (mListPreference == mDensity) {
            if (-4 == Integer.parseInt(mListPreference.getValue())){
                mListPreference.setSummary(R.string.density_switch_260_summary);
            } else if (-3 == Integer.parseInt(mListPreference.getValue())) {
                mListPreference.setSummary(R.string.density_switch_270_summary);
            } else if (-2 == Integer.parseInt(mListPreference.getValue())) {
                mListPreference.setSummary(R.string.density_switch_280_summary);
            } else if (-1 == Integer.parseInt(mListPreference.getValue())) {
                mListPreference.setSummary(R.string.density_switch_290_summary);
            } else if (0 == Integer.parseInt(mListPreference.getValue())) {
                mListPreference.setSummary(R.string.density_switch_300_summary);
            } else if (1 == Integer.parseInt(mListPreference.getValue())) {
                mListPreference.setSummary(R.string.density_switch_310_summary);
            } else if (2 == Integer.parseInt(mListPreference.getValue())) {
                mListPreference.setSummary(R.string.density_switch_320_summary);
            } else if (3 == Integer.parseInt(mListPreference.getValue())) {
                mListPreference.setSummary(R.string.density_switch_330_summary);
            } else if (4 == Integer.parseInt(mListPreference.getValue())) {
                mListPreference.setSummary(R.string.density_switch_340_summary);
            }
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (mCameraSwitch == preference) {
            String ValueCameraSwitch = (String) newValue;
            mCameraSwitch.setValue(ValueCameraSwitch);
            int mode = Integer.parseInt(ValueCameraSwitch);
            switch (mode){
                case 0:
                    preference.setSummary(R.string.camera_switch_cyanogen_summary);
                    RootCmd.RunRootCmd("mount -o remount,rw /system");
                    RootCmd.RunRootCmd("rm -rf /system/app/Camera.apk");
                    RootCmd.RunRootCmd("cp -f /system/xperiasettings/CmCamera.apk /system/app/Camera.apk");
                    RootCmd.RunRootCmd("chmod 0644 /system/app/Camera.apk");
                    break;
                case 1:
                    preference.setSummary(R.string.camera_switch_nubia_summary);
                    RootCmd.RunRootCmd("mount -o remount,rw /system");
                    RootCmd.RunRootCmd("rm -rf /system/app/Camera.apk");
                    RootCmd.RunRootCmd("cp -f /system/xperiasettings/NubiaCamera.apk /system/app/Camera.apk");
                    RootCmd.RunRootCmd("chmod 0644 /system/app/Camera.apk");
                    break;
                default:
                    break;
            }
            DialogReboot();
        }
        if (mDensity == preference) {
            String ValueDensityChange = (String) newValue;
            mDensity.setValue(ValueDensityChange);
            int mode = Integer.parseInt(ValueDensityChange);
            switch (mode) {
                case -4:
                    preference.setSummary(R.string.density_switch_260_summary);
                    RootCmd.RunRootCmd("setprop persist.miui.density 260");
                    break;
                case -3:
                    preference.setSummary(R.string.density_switch_270_summary);
                    RootCmd.RunRootCmd("setprop persist.miui.density 270");
                    break;
                case -2:
                    preference.setSummary(R.string.density_switch_280_summary);
                    RootCmd.RunRootCmd("setprop persist.miui.density 280");
                    break;
                case -1:
                    preference.setSummary(R.string.density_switch_290_summary);
                    RootCmd.RunRootCmd("setprop persist.miui.density 290");
                    break;
                case 0:
                    preference.setSummary(R.string.density_switch_300_summary);
                    RootCmd.RunRootCmd("setprop persist.miui.density 300");
                    break;
                case 1:
                    preference.setSummary(R.string.density_switch_310_summary);
                    RootCmd.RunRootCmd("setprop persist.miui.density 310");
                    break;
                case 2:
                    preference.setSummary(R.string.density_switch_320_summary);
                    RootCmd.RunRootCmd("setprop persist.miui.density 320");
                    break;
                case 3:
                    preference.setSummary(R.string.density_switch_330_summary);
                    RootCmd.RunRootCmd("setprop persist.miui.density 330");
                    break;
                case 4:
                    preference.setSummary(R.string.density_switch_340_summary);
                    RootCmd.RunRootCmd("setprop persist.miui.density 340");
                    break;
                default:
                    break;
            }
            DialogReboot();
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mNavigationBarShow) {
            if (mNavigationBarShow.isChecked()) {
                RootCmd.RunRootCmd("mount -o remount,rw /system");
                RootCmd.RunRootCmd("busybox sed -i 's/qemu.hw.mainkeys=.*/qemu.hw.mainkeys=1/g' /system/build.prop");
                DialogRestart();
            } else {
                RootCmd.RunRootCmd("mount -o remount,rw /system");
                RootCmd.RunRootCmd("busybox sed -i 's/qemu.hw.mainkeys=.*/qemu.hw.mainkeys=0/g' /system/build.prop");
                DialogRestart();
            }
        }
        return false;
    }

    private void DialogRestart() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_restart)
                .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RootCmd.RunRootCmd("reboot");
                    }
                })
                .setNeutralButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), R.string.dialog_reboot, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void DialogReboot() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_ok)
                .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RootCmd.RunRootCmd("busybox killall system_server");
                    }
                })
                .setNeutralButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), R.string.dialog_reboot, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }
}