# 私服搭建

## 步骤一
maven settings.xml配置文件

    <servers>
        <server><id>nexus</id>
          <username>admin</username>
          <password>admin123</password>
        </server>
    </servers>
    
    <mirrors>
        <mirror>
            <id>nexus</id>
            <mirrorOf>*</mirrorOf>
            <url>http://127.0.0.1:8081/nexus/content/groups/public/</url>
         </mirror>
      </mirrors>
## 步骤二

    <distributionManagement>
        <repository>
            <id>nexus</id>
            <name>Nexus Release Repository</name>
            <url>http://127.0.0.1:8081/nexus/content/repositories/releases/</url>
        </repository>
        <snapshotRepository>
            <id>nexus</id>
            <name>Nexus Snapshot Repository</name>
            <url>http://127.0.0.1:8081/nexus/content/repositories/snapshots/</url>
        </snapshotRepository>
    </distributionManagement>
 
