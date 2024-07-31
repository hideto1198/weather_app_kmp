# Yahoo天気APIを使用した天気アプリ

## 初めに
- local.propertiesにYahooAPIの`YAHOO_API_KEY`をセットしてください
> yahoo_api_key=/* API_KEY */
- API_KEYをセットしたらBuildKonfigの自動生成を行います
```
./gradlew generateBuildKonfig
```

## TIPS
### SQLDelgiht
#### データベース自動生成
```
./gradlew generateCommonMainAppDatabaseInterface
```