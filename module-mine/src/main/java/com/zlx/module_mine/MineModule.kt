package com.zlx.module_mine

import com.kunpeng.component.annotation.api.Event
import com.kunpeng.component.annotation.module.ModuleExecutor
import com.kunpeng.component.module.ApiEvent
import com.kunpeng.component.module.Module
import com.kunpeng.component.module.SafeModuleProvider
import com.kunpeng.component.module.SelfModuleProvider
import com.zlx.module_mine.bean.LanguageBean

@Event
interface MineEvent : ApiEvent {
    val languageChanged: LanguageBean
}

@ModuleExecutor
class MineModule: Module {

    override fun onEvaluate(provider: SelfModuleProvider) {
        // Call here when evaluating, only call self by provider.getSelf().
    }

    override fun onExecute(provider: SafeModuleProvider) {
        // Now can call other module by provider.getModule().
    }
}