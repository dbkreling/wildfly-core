WildFly Core EAP
============
https://www.redhat.com/en/technologies/jboss-middleware/application-platform

This project provides the core runtime that is used by the JBoss EAP application server. This includes:

* Modular class loading.
* Unified management, including domain mode.
* Basic deployment architecture.
* CLI for management.

Building
-------------------

Prerequisites:

* JDK 11 or newer - check `java -version`
* Maven 3.6.0 or newer - check `mvn -v`
* JDK 8 or newer
* Maven 3.6.0 or newer
* Be connected to the Red Hat VPN. Necessary to download the JBoss EAP maven artifacts from the internal maven repository.
* Add the Red Hat root certificate as trusted certificate on your JDK keystore. Necessary to download the maven artifacts via HTTPS.

### How to install the Red Hat root certificate on your JDK keystore

Follow these instructions to install the Red Hat root certificate as trusted certificate on your JDK keystore:

1. Download the Red Hat root certificate:

```
wget https://password.corp.redhat.com/RH-IT-Root-CA.crt
```

2. Install it on your JDK keystore:
* For JDK 11:
   ```
   keytool -import -alias internal.redhat.com -keystore <JDK_11_Path>/lib/security/cacerts -file RH-IT-Root-CA.crt -storepass changeit
  ```

If you are using system provided JDK(rpm installed), like: java-11-openjdk-devel, using the following command also works:
```
curl https://password.corp.redhat.com/RH-IT-Root-CA.crt -o /etc/pki/ca-trust/source/anchors/RH-IT-Root-CA.crt
update-ca-trust
```

### Building

To build with your own Maven installation:

> mvn install

Alternatively, you can use the Maven Wrapper script that downloads and installs (if necessary) the required Maven version to
`~/.m2/wrapper` and runs it from there. On Linux, run

> ./mvnw install

On Windows

> mvnw install


Starting and Stopping WildFly Core EAP
------------------------------------------
Change to the bin directory after a successful build:

> $ cd core-build/target/wildfly-core-\[version\]/bin

Start the server in domain mode:

> $ ./domain.sh

Start the server in standalone mode:

> $ ./standalone.sh

To stop the server, press Ctrl + C, or use the admin console:

> $ ./jboss-cli.sh --connect command=:shutdown

Note that there is not very much that you can do with the core server, without first adding some extensions to it.
If you are trying to deploy Java EE application then you likely want the full JBoss EAP distribution, which is located
at:
https://github.com/jbossas/jboss-eap8

Contributing
------------------
* Git Setup: https://github.com/wildfly/wildfly/blob/main/docs/src/main/asciidoc/_hacking/github_setup.adoc
* Contributing: https://github.com/wildfly/wildfly/blob/main/docs/src/main/asciidoc/_hacking/contributing.adoc
* Pull request standard: https://github.com/wildfly/wildfly/blob/main/docs/src/main/asciidoc/_hacking/pullrequest_standards.adoc

Using Eclipse
-------------
1. Install the latest version of Eclipse.
2. Make sure Xmx in Eclipse.ini is at least 1280M, and it's using java 11
3. Launch Eclipse and install the m2e plugin, make sure it uses your repo configs
   (get it from: https://www.eclipse.org/m2e/
   or install "Maven Integration for Eclipse" from the Eclipse Marketplace).
4. In Eclipse preferences Java->Compiler->Errors/Warnings->Deprecated and restricted
   set forbidden reference to WARNING.
5. In Eclipse preferences Java->Code Style, import the cleanup, templates, and
   formatter configs in ide-configs/eclipse.
6. In Eclipse preferences Java->Editor->Save Actions enable "Additional Actions",
   and deselect all actions except for "Remove trailing whitespace".
7. Use import on the root pom, which will pull in all modules.
8. Wait (m2e takes awhile on initial import).

Licenses
--------
* [GNU Lesser General Public License Version 2.1](https://www.gnu.org/licenses/lgpl-2.1-standalone.html)
* [Apache License Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.html)
