# ConfigurNation
#### Annotation based Java Interfaces for your SharedPreferences
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![Development](https://img.shields.io/badge/Stability-Development-green.svg)](https://shields.io/) [![Bintray](https://img.shields.io/badge/Bintray-0.5-lightgrey.svg)](https://dl.bintray.com/ifanie/izilib)
## Installation
### Gradle
```
implementation 'com.izikode.izilib:configurnation:0.5'
annotationProcessor 'com.izikode.izilib:configurnation-compiler:0.5'
```
* for kotlin
```
implementation 'com.izikode.izilib:configurnation:0.5'
kapt 'com.izikode.izilib:configurnation-compiler:0.5'
```
## Usage
### #1 SETUP your mapping interface
```kotlin
@ConfigurNation( name = "SampleAppConfig", mode = Context.MODE_PRIVATE )
interface ConfigMap { ... }
```
* Create your mapping interface and decorate with *@ConfigurNation*.
    * The *name* variable is the name of the generated Config class.
    * The *mode* variable is the mode assigned to the inner SharedPreferences object.
    * The mapping interface can be named anything.
```kotlin
@ConfigurMember
fun aBool(): Boolean
```
* Declare abstract functions inside your interface, for your Config fields.
    * Decorate the config fields with *@ConfigMember*
    * The *name* of the function is the name of the field inside the Config class.
    * The return type of the function is the type of data that will be stored.
        * Only valid SharePreferences types are supported at the moment. Those are:
            * Boolean
            * Float
            * Integer
            * Long
            * String
### #2 BUILD your project
### #3 USE
```kotlin
val config = SampleAppConfig(context)
  
/* write */
config.aBool().set(true)
  
/* read */
val value = config.aBool().get()

/* clear */
val value = config.aBool().clear()
```
## TODO
1. Use JavaPoet for code generation
2. Use serialization/deserialization for Object type support
## Licence

```
Copyright 2018 Fanie Veizis

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
