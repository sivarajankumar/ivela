# Technical Pre-requisites #


<font color='red'>The project maintainer strongly recommends the use of Linux as main Operating System for Ivela development. The most used distributions are:<br>
<ul><li>Debian Lenny (i386)<br>
</li><li>Ubuntu Hardy Heron (amd64 / i386)</li></ul>

For tests in other platforms the recommendation is the use of <a href='http://www.virtualbox.org/'>VirtualBox</a>. For IE testing inside Linux the recommendation is the use of <a href='http://www.tatanka.com.br/ies4linux/page/Main_Page'>IE4Linux</a> under Wine.</font>


  * Install OpenJDK 1.6 http://openjdk.java.net/
  * Create /opt/sws folder (Remember to grant read/write access to your user for this folder)
  * Go to http://code.google.com/p/ivela/downloads/list and download _localRepo.tar.gz_
  * Extract the content of _localRepo.tar.gz_ at _/opt/sws_ (The result must be /opt/sws/localRepo)
  * Create /opt/sws/sql folder
  * Go to http://code.google.com/p/ivela/downloads/list and download _ivela\_fileSystem.tar.gz_
  * Extract the content of _ivela\_fileSystem.tar.gz_ at _/opt/_ . The result must be /opt/ivela (Remember to grant read/write access to your user for this folder)
  * Download Maven 2.09 http://maven.apache.org/download.html
  * Extract Maven to /opt/sws (The result must be /opt/sws/apache-maven-2.0.9)
  * Install Subversion
  * Install [Firebug](https://addons.mozilla.org/en-US/firefox/addon/1843) add-on into Firefox
  * Subscribe our group http://groups.google.com.br/group/ivela/
  * Stay on in our [IRC](http://code.google.com/p/ivela/wiki/IRC)
  * Install Eclipse Galileo 3.5 (LINUX 32) http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/galileo/R/eclipse-jee-galileo-linux-gtk.tar.gz
  * Install Eclipse Galileo 3.5 (WIN 32) http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/galileo/R/eclipse-jee-galileo-win32.zip. From Eclipse site, download the Java\_EE Galileo Edition


# Workstation Setup: Env #
  * Put his lines into your .profile or .bash\_profile
```
  $ JAVA_HOME=<path of your jdk>
  $ CLASSPATH=$JAVA_HOME/lib
  $ M2_HOME=<maven-path>
  $ M2=$M2_HOME/bin
  $ PATH=$JAVA_HOME/bin:$M2:$PATH
  $ LD_LIBRARY_PATH=/usr/lib/jni 
  $ export JAVA_HOME CLASSPATH M2_HOME M2 PATH LD_LIBRARY_PATH 
```

# Workstation Setup: Glassfish #
  * Download https://glassfish.dev.java.net/downloads/v2.1-b60e.html one of the glassfish bundles to disk
  * set JAVA\_HOME to the JDK you have installed on your system.
  * Run
```
$ java -Xmx256m -jar filename.jar
```
  * This command will unbundle GlassFish and create a new directory structure rooted under a directory named 'glassfish'.
  * 
```
$ cd glassfish
```
  * If you are using a machine with an operating system that is a derivative of UNIX(tm), set the execute permission for the Ant binaries that are included with the GlassFish bundle.
```
$ chmod -R +x lib/ant/bin
$ lib/ant/bin/ant -f setup.xml 
```
  * <font color='red'>Into AIX, Debian and Ubuntu environments it is common to found problems when ANT runs, the problem is described as:</font>
```

`[exec]` CLI130 Could not create domain, domain1

BUILD FAILED
/opt/sws/glassfish/setup.xml:177: The following error occurred while executing this line:
/opt/sws/glassfish/setup.xml:607: exec returned: 1
```
  * To solve that run these lines:
```
keytool -genkey -keyalg RSA -keystore ~/.asadmintruststore -alias junk
```
  * Is imperative to use passphrase as **changeit**

# Workstation Setup: Eclipse #
  * Add into you eclipse.ini
  * -Djava.library.path=/usr/lib/jni
  * Start Eclipse
  * Go to _Help > Install new software_ menu
  * Add the following update sites and install the plugins available for each one
  * Subeclipse   http://subclipse.tigris.org/update_1.6.x
  * Maven http://m2eclipse.sonatype.org/update/
  * **Remove the ADJT package from installation, this plugin does not working in this version**
  * Glassfish server plugin
    * Open the view Server
    * Click in New Server
    * Click in download additional adapters
    * Choose glassfish and install it
    * To configure it just choose the folder where glassfish was installed.
    * <font color='red'>Some users have experienced problems in the glassfish plugin installation.</font> For these users, theses links can help: https://glassfishplugins.dev.java.net/download/index.html https://glassfishplugins.dev.java.net/issues/show_bug.cgi?id=45

# Workstation Setup: Maven #
  * Start Eclipse
  * Go to _Windows > Preferences > Maven > Installations_ menu
  * Go to _Configure maven installations_ and add you external maven distribution
  * At _Local Repository_ write /opt/sws/localRepo
  * IN Global settings put  _/home/${MYUSER}/.m2/settings.xml_
  * Fill _settings.xml_ with the following content:
```
<?xml version="1.0" encoding="UTF-8"?>
<settings>
	<mirrors>
		<mirror>
		<id>artifactory</id>
		<mirrorOf>*</mirrorOf>
		<url>http://repo1.maven.org/maven2</url>
		<name>Artifactory</name>
		</mirror>
	</mirrors>
	<localRepository>/opt/sws/localRepo</localRepository>
</settings>
```
  * <font color='red'>If your network uses http proxy, add the following configuration after <i><code>&lt;localRepository&gt;/opt/sws/localRepo&lt;/localRepository&gt;</code></i> into <i>settings.xml</i> </font>
```
<proxies>
   <proxy>
      <active>true</active>
      <protocol>http</protocol>
      <host>proxy.somewhere.com</host>
      <port>8080</port>
      <username>proxyuser</username>
      <password>somepassword</password>
      <nonProxyHosts>www.google.com|*.somewhere.com</nonProxyHosts>
    </proxy>
  </proxies>
```

# Workstation Setup: Versioning #
  * Start Eclipse
  * Go to _SVN Repositories_ view (use open view menu)
  * Add a new repostory
  * At _Repository URL_ field type https://ivela.googlecode.com/svn/
  * If system request username use your Gmail user (without @gmail.com)
  * At _Password_ field type your Google Code password (from http://code.google.com/hosting/settings)
  * Click _Finish_ button
  * At _Repository Folder(s)_ choose the projects:
  * ivela, ivela-commons, ivela-ear, ivela-ejb, ivela-web
  * and click in  _Check out as MAven projects_
  * After all these steps, open each project in your environment click with the right button in pom.xml and disable workspace dependencies

# Workstation Setup: Database #
  * Install PostgreSQL 8.3
  * Go to http://code.google.com/p/ivela/source/browse/trunk/ivela-commons/database/ivela.sql and http://code.google.com/p/ivela/source/browse/trunk/ivela-commons/database/ivela_basic_data.sql and download _ivela\_structure.sql & ivela\_basic\_data.sql_
  * Save downloaded sql files into _/opt/sws/sql_
  * Create database, structure tables and import basic data (make sure that postgres is up and running):
```
  $ useradd ivela
  $ passwd ivela
  $ su - postgres
  $ createuser -s ivela
  $ psql
  postgres=# ALTER USER ivela WITH PASSWORD 'mypasswd';
  postgres=# \q
  $ su ivela
  $ createdb ivela
  $ psql ivela
  postgres=# \q
  $ su ivela
  $ psql -f /opt/sws/sql/ivela_structure.sql
  $ psql -f /opt/sws/sql/ivela_basic_data.sql
```

# Workstation Setup: Glassfish #
  * Go to http://jdbc.postgresql.org/download.html and get JDBC type 4 .jar driver
  * Save the downloaded jar archive (postgresql-8.3-604.jdbc4.jar) to _/usr/local/glassfish-v2ur2/lib_
  * Open Eclipse and go to _Servers_ tab
  * At _Servers_ choose _Glassfish V2_
  * Right click of mouse at _Glassfish V2_
  * Select _Start_
  * After started, Right click of mouse at _Glassfish V2_ and select _View Admin Console_
  * Enter as admin (admin / adminadmin)
  * After login, go to _Resources > Connection Pools_
  * Create a new _Connection Pool_ with name _ivela.postgresql_
  * Make sure that _Datasource Classname_ is _org.postgresql.ds.PGSimpleDataSource_ and _Resource Type_ is _javax.sql.DataSource_
  * At _Additional Properties_ set the follow properties: _User = ivela, DatabaseName = ivela, Password = mypasswd, Ssl = false, ServerName = 127.0.0.1, PortNumber = 5432_
  * Go to _Resources > JDBC Resources_ and create a new resource
  * Make sure that the _JNDI Name_ is _jdbc/ivelaDataSource_ and the _Pool Name_ is _ivela.postgresql_


# Workstation Setup: Compilation #
  * Open Eclipse and go to _Project Explorer_ tab
  * Click with the right button of mouse at _ivela (pom)_ then select option _Clean and Install_
  * After that all projects will be compiled in the right sequence (ivela-commons, ivela-ejb, ivela-war, ivela-ear)
  * The **.ear, .jar and .war** packages will be generated at _target_ folder of each project

# Workstation Setup: Webical #

  * Open Eclipse and go to _Servers_ tab
  * At _Servers_ choose _Glassfish V2_
  * Right click of mouse at _Glassfish V2_
  * Select _Start_
  * After started, Right click of mouse at _Glassfish V2_ and select _View Admin Console_
  * Enter as admin (admin / adminadmin)
  * Go to _Resources > JDBC > JDBC Resources > New_
  * Set _JNDI Name_ as _jdbc/calendarDataBase_
  * Set _Pool Name_ as _ivela.postgresql_
  * Go to _Configuration > Security > Realms > New_
  * Set _Name_ as _Webical_
  * Set _Class Name_ as _com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm_
  * Set _JAAS context_ as _jdbcRealm_
  * Set _JNDI_ as _jdbc/calendarDataBase_
  * Set _User Table_ as _`_auth_user`_
  * Set _User Name Column_ as _username_
  * Set _Password Column_ as _userpass_
  * Set _Group Table_ as _`_auth_userrole`_
  * Set _Group Name Column_ as _role_
  * Set _Digest Algorithm_ as _none_
  * Go to http://code.google.com/p/ivela/downloads/list and download _webical.war_
  * Install _webical.war_ into Glassfish
  * Go to terminal and execute the following commands:
```
  $ su ivela
  $ psql ivela
  ivela=# INSERT INTO application_settings VALUES (1, NULL, '${java.io.tmpdir}', '.zip', 5000, true, 'webical', 'webical');
  ivela=# \q
```