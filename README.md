# ImageSearch Android App - Android 15

一個現代化的 Android 圖片搜尋應用程式，使用 Kotlin + Jetpack Compose + Material 3 開發，支援 Android 15 最新功能。

## 🚀 功能特色

- **圖片搜尋** - 使用 Pixabay API 搜尋高品質圖片
- **圖片下載** - 下載並保存圖片到本地存儲
- **下載管理** - 查看和管理已下載的圖片
- **Android 15 支援** - Edge-to-Edge、Predictive Back、Photo Picker

## 🏗️ 技術架構

### 核心技術
- **Kotlin** + **Jetpack Compose** + **Material 3**
- **Multi-Module Architecture** - 模組化設計
- **Version Catalog** - 統一依賴管理
- **Convention Plugins** - 共用建置邏輯
- **Hilt** - 依賴注入
- **Room** - 本地數據庫
- **Retrofit** - 網路請求
- **Coil** - 圖片載入

### 專案模組
```
app/                    # 主應用程式模組
feature/images/         # 圖片功能模組
core/
├── data/              # 資料層 (Repository Pattern)
├── network/           # 網路層 (Retrofit + Kotlin Serialization)
├── files/             # 檔案管理
└── database/          # 資料庫層 (Room)
build-logic/           # Convention Plugins
```

## 🛠️ 建置需求

### 開發環境
- **Android Studio Ladybug** | 2024.2.1+
- **JDK 17** 或更高版本
- **Android SDK 35** (Android 15)
- **Kotlin 2.0.20** 或更高版本

### 系統需求
- **最低支援**: Android 7.0 (API 24)
- **目標版本**: Android 15 (API 35)
- **建議記憶體**: 4GB RAM 以上

## 🚀 開始使用

### 1. 設定 API Key
```kotlin
// 在 core/network/src/main/java/.../PixabayApi.kt 中
const val API_KEY = "YOUR_PIXABAY_API_KEY"
```
⚠️ **重要**: 請到 [Pixabay API](https://pixabay.com/api/docs/) 註冊並獲取免費的 API Key

### 2. 建置專案
```bash
# Clone 專案
git clone https://github.com/siuro514/ImageSearch.git
cd ImageSearch

# 建置專案
./gradlew build
```

### 3. 運行應用程式
```bash
./gradlew installDebug
```

## 📱 應用程式畫面

### 主頁面
- 歡迎介面
- 快速搜尋功能 (Bottom Sheet)
- 導航到搜尋和下載頁面

### 搜尋頁面
- 關鍵字搜尋
- Grid 佈局顯示搜尋結果
- 保持圖片原始比例
- 一鍵下載功能

### 下載頁面
- 管理已下載的圖片
- 刪除單張或全部圖片
- 本地圖片顯示

## 🎯 Android 15 新功能

### Edge-to-Edge UI
```kotlin
// MainActivity.kt
enableEdgeToEdge()
WindowCompat.setDecorFitsSystemWindows(window, false)
```

### Predictive Back Gesture
```kotlin
// AppNavigation.kt
BackHandler(enabled = canNavigateBack) {
    navController.popBackStack()
}
```

### Scoped Storage
- 完整支援 Android 15 的範圍化存儲
- 自動權限處理
- 跨版本相容性

## 🏗️ 架構亮點

### Version Catalog
- 統一版本管理在 `gradle/libs.versions.toml`
- Bundle 群組化依賴
- 型別安全的依賴引用

### Convention Plugins
- `imagesearch.android.application` - App 模組配置
- `imagesearch.android.library` - Library 模組配置
- `imagesearch.android.compose` - Compose 功能配置
- `imagesearch.android.hilt` - 依賴注入配置
- `imagesearch.android.room` - 數據庫配置

### Repository Pattern
```kotlin
interface ImageRepository {
    suspend fun searchImages(query: String): Result<List<ImageItem>>
    suspend fun downloadImage(imageItem: ImageItem): Result<String>
    fun getDownloadedImages(): Flow<List<ImageItem>>
}
```

## 🔧 開發工具

### 建置腳本
```bash
# 清理並建置
./gradlew clean build

# 運行測試
./gradlew test

# 生成 Release 版本
./gradlew assembleRelease
```

### 代碼品質
- **Lint 檢查**: `./gradlew lint`
- **單元測試**: `./gradlew test`
- **UI 測試**: `./gradlew connectedAndroidTest`

## 📊 專案統計

- **語言**: 100% Kotlin
- **模組數量**: 6 個
- **依賴管理**: Version Catalog + Convention Plugins
- **UI 框架**: Jetpack Compose
- **設計系統**: Material 3

## 🤝 貢獻指南

1. Fork 專案
2. 建立功能分支 (`git checkout -b feature/amazing-feature`)
3. 提交變更 (`git commit -m 'Add amazing feature'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 開啟 Pull Request

## 📄 License

這個專案使用 MIT License - 查看 [LICENSE](LICENSE) 檔案了解詳情。

## 🙏 致謝

- [Pixabay](https://pixabay.com/) - 提供免費高品質圖片 API
- [Material Design](https://material.io/) - 設計指南和元件
- [Android Developers](https://developer.android.com/) - 官方文件和最佳實踐
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - 現代化 UI 工具包

---

## 📞 支援

如果您遇到任何問題或有建議，請：
- 開啟 [Issue](https://github.com/siuro514/ImageSearch/issues)
- 查看 [Wiki](https://github.com/siuro514/ImageSearch/wiki)
- 聯繫開發者: [siuro514@gmail.com](mailto:siuro514@gmail.com)

**⭐ 如果這個專案對您有幫助，請給個 Star！**
