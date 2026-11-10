package com.woxloi.template.command

import com.woxloi.template.MyPaperPlugin
import shimeserver.shimepluginlibrary.shimecommand.ShimeCommandManager
import shimeserver.shimepluginlibrary.shimecommand.ShimeCommandResult
import shimeserver.shimepluginlibrary.shimecommand.ShimeCommandRegistration
import shimeserver.shimepluginlibrary.shimecommand.shimeCommand

/**
 * テンプレートに含めるコマンド定義です。
 * 実際のプラグインを作成するときは、このクラスを機能ごとに分割してください。
 */
class TemplateCommands(
    private val plugin: MyPaperPlugin,
    private val manager: ShimeCommandManager
) {

    /** `/mypaperplugin` コマンドを登録します。 */
    fun register(): ShimeCommandRegistration {
        val root = shimeCommand("mypaperplugin") {
            permission("mypaperplugin.use")
            description("MyPaperPlugin のメインコマンドです。")
            executes { context, _ ->
                context.sender.sendMessage("§6[§bMyPaperPlugin§6] §rプラグインは正常に動作しています。")
                ShimeCommandResult.SilentSuccess
            }
        }

        val registration = manager.register(root)
        plugin.logger.info("/mypaperplugin を登録しました。")
        return registration
    }
}
