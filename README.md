# Brute-force-Skyline-Path

## 需求

JDK_VERSION: 1.8.0_77 或更新版(程式碼內有使用JAVA 8語法，請務必使用1.8或以上JDK版本)

## 實作

使用暴力法找出所有的Skyline Path

實作步驟

1. 輸入Edge資料
1. 輸入起終點
1. 找出起終點所有路徑
1. 找出路徑上累計的維度
1. 將累計的維度資料做Skyline Qurey(依然暴力法)
1. 找出對應的Path並做成HashMap(key: Path, value: Path Attrs)
1. 輸入至result資料夾

## 參數設定

以下參數皆於在Main.java檔的main函式中做設定

- Edge檔放於data資料夾中，並於FILE_PATH中輸入對印的檔名

  ```java
  final String FILE_PATH = "data/your_data_set";
  ```

- 起點與終點請於SRC與DST中做設定

  ```java
  final int SRC = your_src;
  final int DST = your_dst;
  ```

- 最後結果會輸出於Result資料夾下，可做檔名輸入(若無輸入即為執行日期)

  ```java
  OutputUtil outputUtil = new OutputUtil("your_file_name");
  ```

  以上設定成輸出成`今日日期_your_file_name.txt`

## 其他
  - 若有其他錯誤訊息會於Terminal中提示
