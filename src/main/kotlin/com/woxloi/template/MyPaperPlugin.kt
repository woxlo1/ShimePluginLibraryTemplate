package com.woxloi.template

import com.woxloi.template.command.TemplateCommands
import org.bukkit.plugin.java.JavaPlugin
import shimeserver.shimepluginlibrary.shimeapi.ShimeLibrary
import shimeserver.shimepluginlibrary.shimecommand.ShimeCommandManager
import shimeserver.shimepluginlibrary.shimecommand.ShimeCommandRegistration

/**
 * ShimePluginLibrary を利用する Paper プラグインのエントリーポイントです。
 *
 * ShimePluginLibrary 本体の初期化は依存プラグイン側が担当します。
 * このクラスでは初期化済みのライブラリを取得し、利用側の機能を登録します。
 */
class MyPaperPlugin : JavaPlugin() {

    /** 登録したコマンドを管理するマネージャーです。 */
    private var commandManager: ShimeCommandManager? = null

    /** 登録したコマンドの解除ハンドルです。 */
    private var commandRegistration: ShimeCommandRegistration? = null

    /**
     * プラグイン有効化時に呼び出されます。
     * ライブラリの取得とコマンド登録を行います。
     */
    override fun onEnable() {
        val library = ShimeLibrary.getOrNull()
        if (library == null) {
            logger.severe("ShimePluginLibrary が初期化されていません。プラグインを無効化します。")
            server.pluginManager.disablePlugin(this)
            return
        }

        val manager = ShimeCommandManager(this, library.logger, library.scheduler)
        commandManager = manager

        try {
            commandRegistration = TemplateCommands(this, manager).register()
            library.logger.info("MyPaperPlugin が有効になりました。")
        } catch (exception: Exception) {
            logger.severe("コマンドの登録に失敗しました: ${exception.message}")
            exception.printStackTrace()
            server.pluginManager.disablePlugin(this)
        }
    }

    /**
     * プラグイン無効化時に呼び出されます。
     * 登録済みコマンドなどのリソースを解放します。
     */
    override fun onDisable() {
        commandRegistration?.unregister()
        commandRegistration = null
        commandManager = null
    }
}
