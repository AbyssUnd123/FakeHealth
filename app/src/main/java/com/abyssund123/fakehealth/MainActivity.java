package com.abyssund123.fakehealth;
import android.os.Bundle;
import android.os.Build;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

public class MainActivity extends AppCompatActivity {

    private TextView screenTimeTextView;
    private TextView deviceNameTextView;
    private TextView appUsageTextView;
    private TextView currentTimeTextView;
    private TextView timePeriodTextView;
    private TextView timeRangeTextView;
    private TextView deviceStatusTextView;

    private int customHours = 0;
    private int customMinutes = 0;
    private int randomMinHours = 0;
    private int randomMaxHours = 23;
    private int randomMinMinutes = 0;
    private int randomMaxMinutes = 59;

    private static final int MENU_MANUAL_SET = 1;
    private static final int MENU_RANDOM_TIME = 2;
    private static final int MENU_RANGE_SETTING = 3;
    
    private boolean isLocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenTimeTextView = findViewById(R.id.screenTimeTextView);
        deviceNameTextView = findViewById(R.id.deviceNameTextView);
        appUsageTextView = findViewById(R.id.appUsageTextView);
        currentTimeTextView = findViewById(R.id.currentTimeTextView);
        timePeriodTextView = findViewById(R.id.timePeriodTextView);
        timeRangeTextView = findViewById(R.id.timeRangeTextView);
        deviceStatusTextView = findViewById(R.id.deviceStatusTextView);

        registerForContextMenu(screenTimeTextView);
        updateScreenTime();
        updateDeviceName("iPhone 14 Pro Max");
        updateAppUsage("QQ");
        updateCurrentTime("0000-00-00 00:00:00");
        updateTimePeriod("8:00 - 22:00");
        updateTimeRange("2小时0分钟");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("健康使用手机（客户端）");
        
        deviceStatusTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleDeviceStatus();
                }
            });

        deviceNameTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showDeviceNameDialog();
                    return true;
                }

                private void showDeviceNameDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("修改控制端设备名称");

                    final EditText input = new EditText(MainActivity.this);
                    String currentName = deviceNameTextView.getText().toString();
                    String initialText = currentName.replace("控制端设备名称：", "");
                    input.setText(initialText);
                    builder.setView(input);

                    builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String newName = input.getText().toString();
                                String updatedName = newName;
                                updateDeviceName(updatedName);
                            }
                        });

                    builder.setNegativeButton("取消", null);

                    builder.show();
                }
            });

        appUsageTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showAppUsageDialog();
                    return true;
                }

                private void showAppUsageDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("修改今日使用的应用程序");

                    final EditText input = new EditText(MainActivity.this);
                    String currentApp = appUsageTextView.getText().toString();
                    String initialText = currentApp.replace("今日使用的应用程序：", "");
                    input.setText(initialText);
                    builder.setView(input);

                    builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String newApp = input.getText().toString();
                                String updatedApp = newApp;
                                updateAppUsage(updatedApp);
                            }
                        });

                    builder.setNegativeButton("取消", null);

                    builder.show();
                }
            });

        timePeriodTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showTimePeriodDialog();
                    return true;
                }

                private void showTimePeriodDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("修改允许使用的时间段");

                    final EditText input = new EditText(MainActivity.this);
                    String currentPeriod = timePeriodTextView.getText().toString();
                    String initialText = currentPeriod.replace("允许使用的时间段：", "");
                    input.setText(initialText);
                    builder.setView(input);

                    builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String newPeriod = input.getText().toString();
                                String updatedPeriod = newPeriod;
                                updateTimePeriod(updatedPeriod);
                            }
                        });

                    builder.setNegativeButton("取消", null);

                    builder.show();
                }
            });

        timeRangeTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showTimePeriodDialog();
                    return true;
                }

                private void showTimePeriodDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("修改使用时间的范围");

                    final EditText input = new EditText(MainActivity.this);
                    String currentRange = timeRangeTextView.getText().toString();
                    String initialText = currentRange.replace("使用时间的范围：", "");
                    input.setText(initialText);
                    builder.setView(input);

                    builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String newRange = input.getText().toString();
                                String updatedRange = newRange;
                                updateTimeRange(updatedRange);
                            }
                        });

                    builder.setNegativeButton("取消", null);

                    builder.show();
                }
            });
            
        currentTimeTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showCurrentTimeDialog();
                    return true;
                }

                private void showCurrentTimeDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("修改上一次获取使用时长的时间");

                    final EditText input = new EditText(MainActivity.this);
                    String currentTime = currentTimeTextView.getText().toString();
                    String initialText = currentTime.replace("上一次获取使用时长的时间：", "");
                    input.setText(initialText);
                    builder.setView(input);

                    builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String newTime = input.getText().toString();
                                String updatedTime = newTime;
                                updateCurrentTime(updatedTime);
                            }
                        });

                    builder.setNegativeButton("取消", null);

                    builder.show();
                }
            });
            
        findViewById(R.id.pairingButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToastMessage("这只是个装饰而已啦！！！");
                }
            });
            
        findViewById(R.id.disconnectButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToastMessage("这只是个装饰而已啦！！！");
                }
            });
            
            updateDeviceStatus();
            
    }

    private void updateDeviceName(String name) {
        String deviceName = "控制端设备名称：" + name;
        deviceNameTextView.setText(deviceName);
    }

    private void updateAppUsage(String app) {
        String appUsage = "今日使用的应用程序：" + app;
        appUsageTextView.setText(appUsage);
    }

    private void updateScreenTime() {
        String screenTime = "今日手机使用时间：" + customHours + "小时" + customMinutes + "分钟";
        screenTimeTextView.setText(screenTime);
    }

    private void updateCurrentTime(String time) {
        String currentTime = "上一次获取使用时长的时间：" + time;
        currentTimeTextView.setText(currentTime);
    }

    private void updateTimePeriod(String period) {
        String timePeriod = "允许使用的时间段：" + period;
        timePeriodTextView.setText(timePeriod);
    }

    private void updateTimeRange(String range) {
        String timeRange = "使用时间的范围：" + range;
        timeRangeTextView.setText(timeRange);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, MENU_MANUAL_SET, 0, "手动设置");
        menu.add(0, MENU_RANDOM_TIME, 0, "随机时间");
        menu.add(0, MENU_RANGE_SETTING, 0, "范围设置");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_MANUAL_SET:
                showManualSetDialog();
                return true;
            case MENU_RANDOM_TIME:
                showRandomTime();
                return true;
            case MENU_RANGE_SETTING:
                showRangeSettingDialog();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void showManualSetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("手动设置时间");

        View view = getLayoutInflater().inflate(R.layout.dialog_manual_set, null);
        final EditText hoursEditText = view.findViewById(R.id.hoursEditText);
        final EditText minutesEditText = view.findViewById(R.id.minutesEditText);

        hoursEditText.setText(String.valueOf(customHours));
        minutesEditText.setText(String.valueOf(customMinutes));

        builder.setView(view);

        builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String hoursStr = hoursEditText.getText().toString();
                    String minutesStr = minutesEditText.getText().toString();

                    if (!hoursStr.isEmpty()) {
                        customHours = Integer.parseInt(hoursStr);
                    }

                    if (!minutesStr.isEmpty()) {
                        customMinutes = Integer.parseInt(minutesStr);
                    }

                    updateScreenTime();
                }
            });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

        builder.setNegativeButton("取消", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showRandomTime() {
        customHours = getRandomNumberInRange(randomMinHours, randomMaxHours);
        customMinutes = getRandomNumberInRange(randomMinMinutes, randomMaxMinutes);
        updateScreenTime();
    }

    private void showRangeSettingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("范围设置");

        View view = getLayoutInflater().inflate(R.layout.dialog_range_setting, null);
        final EditText minHoursEditText = view.findViewById(R.id.minHoursEditText);
        final EditText maxHoursEditText = view.findViewById(R.id.maxHoursEditText);
        final EditText minMinutesEditText = view.findViewById(R.id.minMinutesEditText);
        final EditText maxMinutesEditText = view.findViewById(R.id.maxMinutesEditText);

        minHoursEditText.setText(String.valueOf(randomMinHours));
        maxHoursEditText.setText(String.valueOf(randomMaxHours));
        minMinutesEditText.setText(String.valueOf(randomMinMinutes));
        maxMinutesEditText.setText(String.valueOf(randomMaxMinutes));

        builder.setView(view);

        builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String minHoursStr = minHoursEditText.getText().toString();
                    String maxHoursStr = maxHoursEditText.getText().toString();
                    String minMinutesStr = minMinutesEditText.getText().toString();
                    String maxMinutesStr = maxMinutesEditText.getText().toString();

                    if (!minHoursStr.isEmpty()) {
                        randomMinHours = Integer.parseInt(minHoursStr);
                    }

                    if (!maxHoursStr.isEmpty()) {
                        randomMaxHours = Integer.parseInt(maxHoursStr);
                    }

                    if (!minMinutesStr.isEmpty()) {
                        randomMinMinutes = Integer.parseInt(minMinutesStr);
                    }

                    if (!maxMinutesStr.isEmpty()) {
                        randomMaxMinutes = Integer.parseInt(maxMinutesStr);
                    }

                    if (randomMinHours < 0) {
                        randomMinHours = 0;
                    }

                    if (randomMaxHours < randomMinHours) {
                        randomMaxHours = randomMinHours;
                    }

                    if (randomMinMinutes < 0) {
                        randomMinMinutes = 0;
                    }

                    if (randomMaxMinutes < randomMinMinutes) {
                        randomMaxMinutes = randomMinMinutes;
                    }

                    if (randomMaxMinutes == 0 && randomMaxHours == 24) {
                        randomMaxMinutes = 59;
                    }
                }
            });

        builder.setNeutralButton("重置", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    randomMinHours = 0;
                    randomMaxHours = 23;
                    randomMinMinutes = 0;
                    randomMaxMinutes = 59;

                    minHoursEditText.setText(String.valueOf(randomMinHours));
                    maxHoursEditText.setText(String.valueOf(randomMaxHours));
                    minMinutesEditText.setText(String.valueOf(randomMinMinutes));
                    maxMinutesEditText.setText(String.valueOf(randomMaxMinutes));
                }
            });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    
    private void toggleDeviceStatus() {
    isLocked = !isLocked;
    updateDeviceStatus();
    }

    private void updateDeviceStatus() {
        String deviceStatus = isLocked ? "锁定" : "未锁定";
        deviceStatusTextView.setText("设备状态：" + deviceStatus);
    }
}
