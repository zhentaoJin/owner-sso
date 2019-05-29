/**
 * Copyright (c) 2011-2016, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.cr.ots.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成器演示
 * </p>
 *
 * @author hubin
 * @date 2016-12-01
 */
public class MysqlGenerator {

    private static String AUTH = "Jinzhentao";
    // 数据库配置信息
    private static String URL = "jdbc:mysql://127.0.0.1/owner-sso?characterEncoding=utf8";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USERNAME = "root";
    private static String PWD = "root";

    // 需要生成的表
    private static String[] TABLES =
        new String[] {
            "system_user",
//            "cr_dicom_equipment",
//            "cr_dicom_instance",
//            "cr_dicom_patient",
//            "cr_dicom_series",
//            "cr_dicom_study",
//            "cr_sys_org"
    };

    // 输出路径
    private static String OUTPUTDIR = System.getProperty("user.dir") + "/ots-code-generotar/src/main/create";
    // 包路径
    private static String PACKAGEDIR = "com.jzt.sso.model";

    private static String MODULE_NAME = "user";

    // 生成类的相关信息
    // 实体 父类

    // private static String SUPERENTITYCLASS = "com.hcop.core.modular.base.common.DataEntity";

    // mapper 父类
    private static String SUPERMAPPER = "com.cr.dgp.common.mybatis.BaseMapper";
    // serviceimpl 父类
    private static String SUPERSERVICE = "com.baomidou.demo.TestService";
    // serviceimpl 父类
    private static String SUPERSERVICEIMPL = "com.cr.ots.common.mybatis.CrudService";
    // controller 父类
    private static String SUPERCONTROLLER = "com.cr.ots.common.BaseController";
    // xml 声称路径
    private static String MYBATIS_XML = System.getProperty("user.dir") + "/src/main/resources/mybatis/create/";

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        HashMap map;
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 根据本地项目路径修改
        // gc.setOutputDir(System.getProperty("user.dir")+"/src/main/create");
        // gc.setOutputDir(System.getProperty("user.dir")+"/com/crhc/core/system");
        gc.setOutputDir(OUTPUTDIR);
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor(AUTH);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        gc.setServiceImplName("%sService");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        // dsc.setTypeConvert(new MyFieldTypeConvert());
        dsc.setDriverName(DRIVER);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PWD);
        dsc.setUrl(URL);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名
        // strategy.setDbColumnUnderline(true);//全局下划线命名
        strategy.setTablePrefix(new String[] {"test_", "sys_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        // 需要生成的表
        strategy.setInclude(TABLES);
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 字段名生成策略
        // strategy.setFieldNaming(NamingStrategy.underline_to_camel);
        // 自定义实体父类
        // strategy.setSuperEntityClass(SUPERENTITYCLASS);
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(SUPERENTITYCOLUMN);
        // 自定义 mapper 父类
        strategy.setSuperMapperClass(SUPERMAPPER);
        // 自定义 service 父类
        // strategy.setSuperServiceClass(SUPERSERVICE);
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass(SUPERSERVICEIMPL);
        // 自定义 controller 父类
        strategy.setSuperControllerClass(SUPERCONTROLLER);
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(MODULE_NAME);
        // pc.setParent("com.hcop.core.modular");// 自定义包路径
        pc.setParent(PACKAGEDIR);// 自定义包路径
        // pc.setController("web");// 这里是控制器包名，默认 web
        // pc.setService("sys");
        pc.setServiceImpl("service");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                // map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // focList.add(new FileOutConfig("/template/mapper.xml.vm") {
        // @Override
        // public String outputFile(TableInfo tableInfo) {
        // // 自定义输入文件名称
        // // 根据本地项目路径修改
        // return MYBATIS_XML+ tableInfo.getEntityName() + "Mapper.xml";
        // }
        // });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/template/controller.java.vm");
        tc.setEntity("/template/entity.java.vm");
        // tc.setMapper("/template/mapper.java.vm");
        tc.setXml("/template/mapper.xml.vm");
        // tc.setService("/template/service.java.vm");
        tc.setServiceImpl("/template/serviceImpl.java.vm");

        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

        // 打印注入设置
        // System.err.println(mpg.getCfg().getMap().get("abc"));
    }

}
