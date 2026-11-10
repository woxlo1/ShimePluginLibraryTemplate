# ShimePluginLibraryTemplate

ShimePluginLibrary 1.1.0 を利用して Paper プラグインを開発するための GitHub Template Repository です。

このリポジトリに ShimePluginLibrary の実装ソースは含まれていません。GitHub Packages から Maven 依存関係として取得します。

## Features

- ShimePluginLibrary / ShimePluginCommand / ShimePluginNMS 1.1.0 を利用
- Paper 1.20.6 / Java 21 / Kotlin 2.0.21
- Groovy Gradle DSL
- Shadow JAR に Kotlin ランタイムを同梱
- GitHub Actions によるビルドと成果物アップロード
- 起動確認用 `/mypaperplugin` コマンド

## 1. Use this template

1. GitHub でこのリポジトリを開きます。
2. **Use this template** → **Create a new repository** を選択します。
3. 作成先のリポジトリ名を入力して新規作成します。
4. 作成したリポジトリを Clone して開発を開始します。

## 2. Requirements

- JDK 21
- Paper 1.20.6 以降（API 互換性は利用する ShimePluginLibrary の対応範囲に従います）
- ShimePluginLibrary 1.1.0 を取得できる GitHub Packages 認証情報

## 3. GitHub Packages authentication

GitHub Packages の Maven リポジトリから依存関係を取得するため、`read:packages` 権限を持つ Personal Access Token (classic) を用意してください。

環境変数を設定します。

### Windows PowerShell

```powershell
$env:GITHUB_ACTOR = "あなたのGitHubユーザー名"
$env:GITHUB_TOKEN = "read:packages権限を持つトークン"
```

### macOS / Linux

```bash
export GITHUB_ACTOR="your-github-username"
export GITHUB_TOKEN="your-token-with-read-packages"
```

トークンをソースコード、`build.gradle`、Git にコミットしないでください。

## 4. Build

### Windows

```powershell
.\gradlew.bat clean build
```

### macOS / Linux

```bash
./gradlew clean build
```

成果物:

```text
build/libs/MyPaperPlugin-1.0.0.jar
```

## 5. Server setup

1. サーバーに ShimePluginLibrary 1.1.0 の配布プラグイン JAR（`ShimePlugin` の Shadow JAR）を `plugins/` へ配置します。
2. このプロジェクトで生成した `MyPaperPlugin` JAR を `plugins/` へ配置します。
3. サーバーを起動します。
4. `/mypaperplugin` を実行して動作を確認します。

`plugin.yml` は `ShimePluginLibrary` を `depend` に指定しています。ライブラリ本体のプラグインが存在しない場合、このプラグインはロードされません。

## 6. Customize

テンプレートから作成したら、次の項目を自分のプラグイン名に合わせて変更してください。

| File | Change |
| --- | --- |
| `settings.gradle` | `rootProject.name` |
| `build.gradle` | `group` と `version` |
| `plugin.yml` | `name`, `main`, `authors`, commands, permissions |
| `MyPaperPlugin.kt` | メインクラス・パッケージ |
| `TemplateCommands.kt` | コマンド名と処理 |

パッケージを変更する場合は Kotlin ファイルの配置ディレクトリと `package` 宣言、`plugin.yml` の `main` を揃えてください。

### ShimePluginLibrary version

デフォルトは `1.1.0` です。変更する場合は次のように指定できます。

```bash
./gradlew build -PshimeVersion=1.1.0
```

## Project structure

```text
ShimePluginLibraryTemplate/
├── .github/
│   └── workflows/
│       └── build.yml
├── gradle/wrapper/
├── src/main/
│   ├── kotlin/com/woxloi/template/
│   │   ├── MyPaperPlugin.kt
│   │   └── command/TemplateCommands.kt
│   └── resources/plugin.yml
├── build.gradle
├── gradle.properties
├── settings.gradle
├── gradlew
└── gradlew.bat
```

## License

MIT License. Update the license and author information when publishing your own project if needed.
