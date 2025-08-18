```
local.propertiesのAPI_BASE_URLがなければlocalhost:3000が指定されます。
実機インストールの場合はPCのlocalhostにリクエストできないので、
バックエンドをデプロイするか、ngrockのようなサービスで外部からリクエストできるようにしましょう。

build.gradle.ktsでバックエンドのURLを指定しています。
val url = props.getProperty("API_BASE_URL") ?: "http://10.0.2.2:3000/"
buildConfigField("String", "BASE_URL", "\"$url\"")
```