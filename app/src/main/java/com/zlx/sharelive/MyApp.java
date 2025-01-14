package com.zlx.sharelive;

import android.content.Intent;
import android.util.Log;

import com.p2m.core.P2M;
import com.p2m.module.api.Mine;

import com.zlx.module_base.BaseApplication;
import com.zlx.module_base.base_manage.ActivityUtil;
import com.zlx.sharelive.activity.MainActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


/**
 * @date: 2019\3\8 0008
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */
public class MyApp extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
         configP2M();
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    private void configP2M() {
        P2M.initializer(this)
                .logger((level, msg, throwable) -> {
                    Log.i("P2M", msg);
                })
                .onEvaluate(taskRegister -> {
                    taskRegister.registers(BTask.class, CTask.class);
                    taskRegister.register(ATask.class, 100);

                    // 任务的执行顺序一定是[C -> B]， B的onExecute()可以获得 C的output
                    taskRegister.find(CTask.class).dependOn(BTask.class);
                })
                .onExecuted((taskOutputProvider, moduleProvider) -> {
                    String outputOfATask = taskOutputProvider.getOutputOf(ATask.class);
                    Log.i("P2M", "outputOfATask: " + outputOfATask);

                    // 语言改变时切换到主界面
                    moduleProvider.moduleOf(Mine.class)
                            .getEvent()
                            .getLanguageChanged()
                            .observeForeverNoSticky((language) -> {
                                String desc = language.getDesc();
                                ActivityUtil.finishAllActivity();
                                Intent intent = new Intent(MyApp.this, MainActivity.class);
                                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                                MyApp.this.startActivity(intent);
                            });
                })
                .start();
    }

}
