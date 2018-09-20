# rxPerfectMatch


This file requires a Markdown plugin or Visual Studio Code.
Open the file in the Visual Studio and click on the `Open Preview on the side` button, located in the top right side of the file. 

Alternatively, you can use the following url and use the browser.
https://bitbucket.eqsmut.intranatixis.com/projects/EAGTETRAFX/repos/av9--fx-hub/browse/com.natixis.etrading.fxhub.mhub.nextgen?at=mHub_nextgen 

## Project setup
---

Import the project in the in eclipse (for development java purposes) and Visual studio (for Vue development).
Path: C:\dev\git\av9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue
Make usre you are in the 'origin/mhub-nextGen'

```
com.natixis.etrading.fxhub.mhub.nextgen
├─┬ backend     → backend module with Java/Spring Boot code
│ ├── src
│ └── pom.xml   →    
├─┬ vue    → frontend module with Vue.js code
│ ├── src
│ └── pom.xml    
└── pom.xml     → Maven parent pom managing both modules
```

## First App run
---
Copy NodeJs from `"\\\cib.net\ShareParis\Salle\Services\Cmia\Tibco\ProjetForex\NodeJs`` to "C:\dev\tools\"`, as defined in the `<installDirectory>` xml element in the `"av9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue\pom.xml"`.

Add node path (`C:\dev\tools\node`) to the PATH env variable.

 * Open an command line and test the Node with `"node --version"`. The version v9.11.1 should be displayed.

 * Open an command line and test the NPM (Node Package Manager) installation with `"npm --version"`. The version 5.6.0 should be displayed

Ensure the Natixis proxy is configured and your user and password is defined for authentication purposes. 
* Either by adding the proxy in the maven (https://www.mkyong.com/maven/how-to-enable-proxy-setting-in-maven/). This can cause some maven dependencies issues if the nonProxyHosts are not properly defined.

```
<proxies>
  <proxy>
      <id>http_natixis</id>
      <active>true</active>
      <protocol>http</protocol>
      <username>proxyuser</username>
      <password>proxypwd</password>
      <host>proxyusers.intranatixis.com</host>
      <port>8080</port>
      <nonProxyHosts>srvsvn-etrading.smt.cib.net|srvsvn-fxalgo.smt.cib.net|srvsvn-etrading</nonProxyHosts>
    </proxy>
	<proxy>
      <id>https_natixis</id>
      <active>true</active>
      <protocol>https</protocol>
      <username>proxyuser</username>
      <password>proxypwd</password>
      <host>proxyusers.intranatixis.com</host>
      <port>8080</port>
      <nonProxyHosts>srvsvn-etrading.smt.cib.net|srvsvn-fxalgo.smt.cib.net|srvsvn-etrading</nonProxyHosts>
    </proxy>
  </proxies>
```

* (optional) Alternatively go to `"av9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue\pom.xml"` and add the the following line with a proper user and password to the configuration xml element of the execution phase with the `npm install` as the id:  
```

<arguments>
install -l --proxy=http://<USER>:<PASS>@proxyusers.intranatixis.com:8080  --https-proxy=http://<USER>:<PASS>@proxyusers.intranatixis.com:8080
</arguments>
```

* (optional) All javascript dependencies can be resolved manually with the following commands
```
cd C:\<PATH_TO_GIT>\av9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue
npm install -l --proxy=http://<USER>:<PASS>@proxyusers.intranatixis.com:8080  --https-proxy=http://<USER>:<PASS>@proxyusers.intranatixis.com:8080 
```



Execute the following commands in order to build all projects:
```
> cd `"C:\<PATH_TO_GIT>\av9--fx-hub"`
> mvn clean install -Phub,master,conf
> cd `"C:\<PATH_TO_GIT>\noiua-credit-check`"
> mvn clean install -Pserver-release
> cd C:\<PATH_TO_GIT>\av9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue~
> npm config set strict-ssl false
> npm install -l --proxy=http://<USER>:<PASS>@proxyusers.intranatixis.com:8080  --https-proxy=http://<USER>:<PASS>@proxyusers.intranatixis.com:8080 
```
```
> cd  `"C:\<PATH_TO_GIT>\av9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen"`
> mvn clean install
> mvn --projects backend spring-boot:run 
```
Alternatively execute the java class `com.natixis.etrading.fxhub.admin.mhubweb.Application` in the backend project.

Now go to http://localhost:8081/ and the app should load.

All javascrip (under the folder node_mdules) and java dependencies are resolved and no additional steps is required. 
You can refer to the file `"C:\<PATH_TO_GIT>\av9--fx-hubav9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue\README.md"`, to just resolve Javascript dependencies.

## Fast feedback with webpack-dev-server
---
The webpack-dev-server, which will update and build every change through all the parts of the JavaScript build-chain, is pre-configured in Vue.js out-of-the-box! So the only thing needed to get fast feedback development-cycle is to cd into `"C:\<PATH_TO_GIT>\av9--fx-hubav9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue"` and run:

```
npm run dev
```

Thats it, the root page should be automatically open! Every change in the Vue.js files are automatically updated, if not just refresh the page. You should confirm the change in the Chrome dev tools: 

* Ctrl + Shift + l
* Click on the Sources tab
* On the left list, navigate to webpack and choose the changed file 

Notice that the port is not the same when the app is started from the backend module. The main purpose is to have independent process in order to change Js and Java files independently. 
After the required change is made, just commit it and it should be correctly updated in the next build, as the backend pom is configured to copy the content of the `${project.parent.basedir}/frontend/target/dist` directoy.

## Browser developer tools extension
---
These plugins are required to be installed by machine administrators 

Install vue-devtools Browser extension (https://chrome.google.com/webstore/detail/vuejs-devtools/nhdogjmejiglipccpnnnanhbledajbpd),  and DejaVue (https://chrome.google.com/webstore/detail/dejavue/jpigngmphmclcmikmcbcfplgnhlnefbp?hl=en) get better feedback on the Vue.js objects, 

e.g. in Chrome:

* Ctrl + Shift + l
* Click on the Vue tab
* On the left list, navigate to the required module and view the data state of the object 




## IDEs (Optionall with Visual Studio Code)
---


Install CodeMix (trial version) plugin for VueJs development. This is optional with Visual Studio Code. 
Note that with CodeMix plugin you lose the Vue Syntax Highlighting, after the trial expires´.

1. Install eclipse Java EE Photon Release (4.8.0) 

2. Add proxy configuration: 
>Window - Preferences -> Network Connections -> Proxy Bypass -> add http://proxyusers.intranatixis.com:8080  

3. Install CodeMix plugin: 

Help -> Install New Software -> http://www.genuitec.com/updates/codemix/ci/

4. Configure installed NodeJs

Window -> Preferences -> Javascript -> Tern -> Server -> Node.js -> "Node.js install" select "-- Choose your node.js install --" -> C:\dev\tools\node
 

## Maven NPM install with proxy mave settings (TODO)

---

There is an issue installing Chrome driver from maven. 
As the workaround execute the following command line, with the proper user and password, in the vue project

```
> npm config set strict-ssl false
> npm install -l --proxy=http://<USER>:<PASS>@proxyusers.intranatixis.com:8080  --https-proxy=http://<USER>:<PASS>@proxyusers.intranatixis.com:8080 
> cd ..
> mvn clean install
```

[INFO] > node install.js
[INFO]
[INFO] PhantomJS not found on PATH
[INFO] Download already available at C:\Users\MACHAD~1\AppData\Local\Temp\phantomjs\phantomjs-2.1.1-windows.zip
[INFO] Verified checksum of previously downloaded file
[INFO] Extracting zip contents
[INFO] Removing C:\dev\git\av9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue\node_modules\phantomjs-prebuilt\lib\phantom
[INFO] Copying extracted folder C:\Users\MACHAD~1\AppData\Local\Temp\phantomjs\phantomjs-2.1.1-windows.zip-extract-1536677361715\phantomjs-2.1.1-windows -> C:\dev\git\av9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue\node_modules\phantomjs-prebuilt\lib\phantom
[INFO] Writing location.js file
[INFO] Done. Phantomjs binary available at C:\dev\git\av9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue\node_modules\phantomjs-prebuilt\lib\phantom\bin\phantomjs.exe
[INFO]
[INFO] > chromedriver@2.41.0 install C:\dev\git\av9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue\node_modules\chromedriver
[INFO] > node install.js
[INFO]
[INFO] Downloading https://chromedriver.storage.googleapis.com/2.41/chromedriver_win32.zip
[INFO] Saving to C:\Users\MACHAD~1\AppData\Local\Temp\chromedriver\chromedriver_win32.zip
[ERROR] ChromeDriver installation failed Error with http(s) request: Error: tunneling socket could not be established, statusCode=407
[WARNING] npm WARN vue-routing@1.0.0 No repository field.
[WARNING] npm WARN vue-routing@1.0.0 scripts['server'] should probably be scripts['start'].
[WARNING] npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.4 (node_modules\fsevents):
[WARNING] npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.4: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
[ERROR]
[ERROR] npm ERR! code ELIFECYCLE
[ERROR] npm ERR! errno 1
[ERROR] npm ERR! chromedriver@2.41.0 install: `node install.js`
[ERROR] npm ERR! Exit status 1
[ERROR] npm ERR!
[ERROR] npm ERR! Failed at the chromedriver@2.41.0 install script.
[ERROR] npm ERR! This is probably not a problem with npm. There is likely additional logging output above.
[ERROR]
[ERROR] npm ERR! A complete log of this run can be found in:
[ERROR] npm ERR!     C:\Users\machadojo\AppData\Roaming\npm-cache\_logs\2018-09-11T14_49_28_475Z-debug.log
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO]
[INFO] fxhub.mhubweb.nextgen .............................. SUCCESS [  0.936 s]
[INFO] fxhub.mhubweb.nextgen.vue .......................... FAILURE [01:31 min]
[INFO] fxhub.mhubweb.nextgen.java ......................... SKIPPED
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 01:32 min
[INFO] Finished at: 2018-09-11T16:49:28+02:00
[INFO] Final Memory: 20M/243M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal com.github.eirslett:frontend-maven-plugin:1.6:npm (npm install) on project fxhub.mhubweb.nextgen.vue: Failed to run task: 'npm install --https-proxy=http://proxyuser:***@proxyusers.intranatixis.com:8080 --proxy=http://proxyuser:***@proxyusers.intranatixis.com:8080' failed. org.apache.commons.exec.ExecuteException: Process exited with an error: 1 (Exit value: 1) -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
[ERROR]
[ERROR] After correcting the problems, you can resume the build with the command
[ERROR]   mvn <goals> -rf :fxhub.mhubweb.nextgen.vue

## Maven Encrypt issue (TODO)
---
The following two resources are used to configure the Natixis proxy (to resolve javascript dependencies), and to encrypt the user password, but the following error happens  

https://maven.apache.org/guides/mini/guide-proxies.html
https://maven.apache.org/guides/mini/guide-encryption.html

[INFO] Writing location.js file
[INFO] Done. Phantomjs binary available at C:\dev\git\av9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue\node_modules\phantomjs-prebuilt\lib\phantom\bin\phantomjs.exe
[INFO]
[INFO] > chromedriver@2.41.0 install C:\dev\git\av9--fx-hub\com.natixis.etrading.fxhub.mhub.nextgen\vue\node_modules\chromedriver
[INFO] > node install.js
[INFO]
[INFO] Downloading https://chromedriver.storage.googleapis.com/2.41/chromedriver_win32.zip
[INFO] Saving to C:\Users\MACHAD~1\AppData\Local\Temp\chromedriver\chromedriver_win32.zip
[ERROR] ChromeDriver installation failed Error with http(s) request: Error: tunneling socket could not be established, statusCode=407




## References:
 * https://github.com/angelozerr/tern.java/wiki/Tern-Linter-ESLint
 * https://github.com/vuejs/eslint-plugin-vue (<!-- eslint-disable -->)
 * https://github.com/vuejs/vue-devtools/blob/master/shells/electron/README.md
 * https://github.com/eirslett/frontend-maven-plugin/issues/652
 * https://medium.com/skyshidigital/8-vuejs-plugins-to-speed-up-your-front-end-development-61528155f280
 * https://hackernoon.com/a-tale-of-webpack-4-and-how-to-finally-configure-it-in-the-right-way-4e94c8e7e5c1
 * https://dev.to/rhymes/what-vuejs-framework-should-i-use-4nk1
 * https://blog.bitsrc.io/11-vue-js-component-libraries-you-should-know-in-2018-3d35ad0ae37f
 * https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet


## Testing References
 * https://vuejs.org/v2/guide/unit-testing.html
 * https://vuejs.org/v2/cookbook/unit-testing-vue-components.html
 * https://vue-test-utils.vuejs.org/
  
