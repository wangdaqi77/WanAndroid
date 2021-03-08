package com.zlx.sharelive

import com.kunpeng.component.module.SafeModuleProvider
import com.kunpeng.component.module.task.Task
import com.kunpeng.component.module.task.TaskExecutor
import com.tencent.mmkv.MMKV


class MMKVInitTask: Task {
    override fun onExecute(provider: SafeModuleProvider) {
        MMKV.initialize(provider.application)
    }
}