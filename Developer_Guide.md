# Technical Pre-requisites #


<font color='red'>The project maintainer strongly recommends the use of Linux as main Operating System for Ivela development. The most used distributions are:<br>
<ul><li>Debian Lenny (i386)<br>
</li><li>Ubuntu Hardy Heron (amd64 / i386)</li></ul>

For tests in other platforms the recommendation is the use of <a href='http://www.virtualbox.org/'>VirtualBox</a>. For IE testing inside Linux the recommendation is the use of <a href='http://www.tatanka.com.br/ies4linux/page/Main_Page'>IE4Linux</a> under Wine.</font>


  * Install OpenJDK 1.6 http://openjdk.java.net/
  * Install Netbeans 6.5.1 http://www.netbeans.org/downloads/index.html. From Netbeans site, download the _Java Web and EE_ bundle
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

# Workstation Setup: Maven #


  * Start Netbeans
  * Go to _Tools > Plugins_ menu
  * Go to _Available Plugins_
  * Select _Maven_ and click the _Install_ button
  * Once _Maven_ plugin is installed click the _Close_ button
  * Go to _Tools > Options_
  * Go to _Miscellaneous_ tab
  * Click on _Maven_ tab
  * At _External Maven Home_ write /opt/sws/apache-maven-2.0.9
  * Check _Use plugin registry_
  * Check _Stop at first failure_
  * Check _No Global policy_ twice
  * At _Local Repository_ write /opt/sws/localRepo
  * At _Index_ write _Once a week_
  * Click the _Ok_ button
  * Open the file _/home/${MYUSER}/.m2/settings.xml_
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

  * Start Netbeans
  * Go to _Versioning_ menu
  * At _Repository URL_ field type https://ivela.googlecode.com/svn/
  * At _User_ field type with your Gmail user (without @gmail.com)
  * At _Password_ field type your Google Code password (from http://code.google.com/hosting/settings)
  * Click _Next_ button
  * At _Repository Folder(s)_ field type **ivela, ivela-commons, ivela-ear, ivela-ejb, ivela-web**
  * Check the option _Scan for Netbeans Projects after Checkout_
  * Click the _Finish_ button
  * After all these steps, Netbeans will make a checkout from Google Code's SVN repository
  * Once checkout is finished Netbeans will ask to select projects to open, select: **ivela, ivela-commons, ivela-ear, ivela-ejb, ivela-web**

# Workstation Setup: Database #

  * Install PostgreSQL 8.3
  * Go to http://code.google.com/p/ivela/downloads/list and download _ivela\_structure.sql & ivela\_basic\_data.sql_
  * Save downloaded sql files into _/opt/sws/sql_
  * Create database, structure tables and import basic data:
```
  $ adduser ivela
  $ su postgres
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
  * Open Netbeans and go to _Services_ tab
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
  * <font color='red'>Even if your network uses http proxy, you MUST set Glassfish to ignore it: Go to <i>Services</i> tab, then go to option <i>Properties</i> then go to <i>Connection</i> tab then UNCHECK <i>Use IDE Proxy settings</i> option </font>


# Workstation Setup: Compilation #
  * Open Netbeans and go to _Projects_ tab
  * Click with the right button of mouse at _ivela (pom)_ then select option _Clean and Build_
  * After that all projects will be compiled in the right sequence (ivela-commons, ivela-ejb, ivela-war, ivela-ear)
  * The **.ear, .jar and .war** packages will be generated at _target_ folder of each project

# Workstation Setup: Webical #

  * Open Netbeans and go to _Services_ tab
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