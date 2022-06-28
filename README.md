
# apigouvfr-ban  
  
# 📥 Usage  
  
## Scope  
  
The library give tools to call French API "[Base Adresse Nationale](https://adresse.data.gouv.fr/api-doc/adresse)".
  
## How to use

The library entry point is the class called `BanService`:

```kt
val banService = BanService()
```

API call functions are `suspend` and return `BanResult` instance.

## Proguard
Rules required for your configuration:

```
-keep class com.worldline.apigouvfr.ban.datasource.response.** {*;}
```
  
# 🔗 Links
* More information about the API itself : [https://adresse.data.gouv.fr/api-doc/adresse](https://adresse.data.gouv.fr/api-doc/adresse)

## License

    Copyright 2022 Worldline.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
