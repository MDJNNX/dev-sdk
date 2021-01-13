----------------------------------------------- 第一种打包需求(配置和依赖包外置): -----------------------------------------------
1:部分配置文件外置(src/main/resources/config),部分配置文件内置(src/main/resources/static)
2:所有依赖包外置:包括maven管理的和项目里包含的本地第三方jar包;
打包结果:
dev-sdk-jar-with-dependencies.zip
    - config        (外置的配置文件)
        - images
            - ****
        - website
            - ****
    - lib
        - des-core-1.0.1.jar (这个是非maven管理的第三方本地jar包)
        - ****
    - dev-sdk.jar
        - com
            - ****
        - META-INF
        - static
            - ***** (内置的配置文件)

方式一:maven-jar-plugin + maven-assembly-plugin
详见:assembly-outter-config-lib_pom.xml + src/main/resources/assembly/assembly-outter-config-lib.xml
描述:这种方式使用:maven-jar-plugin先打包生成manifest.mf;(注意,非maven管理的第三方本地jar包,不会自动加到manifest.mf的class-path中, 需要手动处理)
                  maven-assembly-plugin;负责根据描述打包文件将配置文件和依赖包放到和jar包同级然后一起打包为zip包;但是要注意第三方非maven管理的本地jar包需要单独处理
                  这里maven-assembly-plugin打包还是依赖maven-jar-plugin, 而且mvn package的话, 始终会执行maven-jar-plugin来打包;
                  直接mvn assembly:single的话, 没试成功过;(对应pom.xml中maven-assembly-plugin的archive节点无法生成manifest.mf)
打包结果:压缩包结构和上面描述的一模一样;

方式二:maven-jar-plugin + maven-resources-plugin + maven-dependency-plugin
详见:outter-config-lib_pom.xml
描述:这种方式是maven-jar-plugin负责打包生成manifest.mf;(注意,非maven管理的第三方本地jar包,不会自动加到manifest.mf的class-path中, 需要手动处理)
               maven-resources-plugin负责将外置的资源
               maven-dependency-plugin负责将依赖jar包复制到lib包里(这里会将非maven管理的第三方管理的本地jar包也复制到lib目录里)
打包结果;这种方式所有的文件都散落在target目录中; 需要自己区别;
目录结构:target/
          - assembly
          - classes
          - config              (需要:外置的配置文件目录)
          - generated-sources
          - lib                 (需要:所有jar包目录)
          - maven-archiver
          - maven-status
          - static
          - dev-sdk.jar    (需要:可执行jar包)
交付的时候, 将这三个需要的文件打包即可;

----------------------------------------------- 第二种打包需求(配置和依赖包内置): -----------------------------------------------
方式一:maven-assembly-plugin(本地jar包+maven管理的jar包)
详见:src/main/resources/assembly/assembly-inner-config-lib.xml + assembly-innser-config-lib_pom.xml
描述:直接生成jar包, 所有配置及依赖包都打入了jar包里;(所有jar包都时解压状态,非解压的依赖包如果单独放在jar包里的lib目录下, class-path无法指定)

方式二:maven-assembly-plugin(maven管理的jar包, 第三方的本地jar包需要安装到本地仓库)
前提:如果jar包全是maven管理的, 那么直接使用assembly插件默认的descriptorRef就可以了, 不需要自己写assembly描述文件;
描述:直接生成jar包, 所有配置及依赖包都打入了jar包里;(所有jar包都时解压状态)

附:将第三方本地jar包安装到本地仓库的方法:
最好直接切到jar包所在目录, 这样-Dfile参数就可以直接指定jar包, 不需要带路径了;
D:\_intellij\dev-sdk\lib>mvn install:install-file -DgroupId=com.satan  -DartifactId=des-core -Dversion=1.0.1 -Dpackaging=jar -Dfile=des-core-1.0.1.jar
然后pom.xml里该依赖就不需要用scope=system和systemPath标签了;
