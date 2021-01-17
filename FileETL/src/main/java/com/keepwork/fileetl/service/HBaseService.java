package com.keepwork.fileetl.service;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HBaseService {
    private Logger log = LoggerFactory.getLogger(HBaseService.class);

    private Admin admin = null;
    private Connection connection = null;
    public HBaseService() {
        try {
            org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
            conf.addResource("E:\\javaSpace\\KeepWork\\FileETL\\src\\main\\resources\\core-site.xml");
            conf.addResource("E:\\javaSpace\\KeepWork\\FileETL\\src\\main\\resources\\hdfs-site.xml");
            conf.addResource("E:\\javaSpace\\KeepWork\\FileETL\\src\\main\\resources\\hbase-site.xml");
            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
            connection = ConnectionFactory.createConnection(conf);
            admin = connection.getAdmin();
        } catch (Exception e) {
            log.error("获取HBase连接失败!");
        }
    }
    /**
     * 创建表 create <table>, {NAME => <column family>, VERSIONS => <VERSIONS>}
     */
    public boolean creatTable(String tableName, List<String> columnFamily) {
        //2.注意，创建表的话 需要创建一个描述器
        HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(tableName));
        //3.创建列族
        for (String cf : columnFamily) {
            htd.addFamily(new HColumnDescriptor(cf));
        }
        //4.创建表
        try {
            admin.createTable(htd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("表已创建成功！");
        return true;
    }
    /**
     * 查询所有表的表名
     */
    public List<String> getAllTableNames() {
        List<String> result = new ArrayList<>();
        try {
            TableName[] tableNames = admin.listTableNames();
            for (TableName tableName : tableNames) {
                result.add(tableName.getNameAsString() + "--" +tableName.getNamespaceAsString()
                        + "--" + tableName.getNameWithNamespaceInclAsString()
                        + "--" + tableName.getQualifierAsString());
            }
        } catch (Exception e) {
            log.error("获取所有表的表名失败", e);
        } finally {
            close(admin, null, null);
        }
        return result;
    }
    /**
     * 遍历查询指定表中的所有数据
     */
    public Map<String, Map<String, String>> getResultScanner(String tableName) {
        Scan scan = new Scan();
        return this.queryData(tableName, scan);
    }
    /**
     * 通过表名及过滤条件查询数据
     */
    private Map<String, Map<String, String>> queryData(String tableName, Scan scan) {
        // <rowKey,对应的行数据>
        Map<String, Map<String, String>> result = new HashMap<>();
        ResultScanner rs = null;
        //获取表
        Table table = null;
        try {
            table = getTable(tableName);
            rs = table.getScanner(scan);
            for (Result r : rs) {
                // 每一行数据
                Map<String, String> columnMap = new HashMap<>();
                String rowKey = null;
                // 行键，列族和列限定符一起确定一个单元（Cell）
                for (Cell cell : r.listCells()) {
                    if (rowKey == null) {
                        rowKey = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                    }
                    columnMap.put(
                            //列限定符
                            Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()),
                            //列族
                            Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                }
                if (rowKey != null) {
                    result.put(rowKey, columnMap);
                }
            }
        } catch (Exception e) {
            log.error(MessageFormat.format("遍历查询指定表中的所有数据失败,tableName:{0}", tableName), e);
        } finally {
            close(null, rs, table);
        }
        return result;
    }
    /**
     * 为表添加或者更新数据
     */
    public void putData(String tableName, String rowKey, String familyName, String[] columns, String[] values) {
        Table table = null;
        try {
            table = getTable(tableName);
            putData(table, rowKey, tableName, familyName, columns, values);
        } catch (Exception e) {
            log.error(MessageFormat.format("为表添加 or 更新数据失败,tableName:{0},rowKey:{1},familyName:{2}", tableName, rowKey, familyName), e);
        } finally {
            close(null, null, table);
        }
    }
    private void putData(Table table, String rowKey, String tableName, String familyName, String[] columns, String[] values) {
        try {
            //设置rowkey
            Put put = new Put(Bytes.toBytes(rowKey));
            if (columns != null && values != null && columns.length == values.length) {
                for (int i = 0; i < columns.length; i++) {
                    if (columns[i] != null && values[i] != null) {
                        put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columns[i]), Bytes.toBytes(values[i]));
                    } else {
                        throw new NullPointerException(MessageFormat.format(
                                "列名和列数据都不能为空,column:{0},value:{1}", columns[i], values[i]));
                    }
                }
            }
            table.put(put);
            log.debug("putData add or update data Success,rowKey:" + rowKey);
            table.close();
        } catch (Exception e) {
            log.error(MessageFormat.format(
                    "为表添加 or 更新数据失败,tableName:{0},rowKey:{1},familyName:{2}",
                    tableName, rowKey, familyName), e);
        }
    }
    /**
     * 根据表名获取table
     */
    private Table getTable(String tableName) throws Exception {
        return connection.getTable(TableName.valueOf(tableName));
    }

    /**
     * 关闭流
     */
    private void close(Admin admin, ResultScanner rs, Table table) {
        if (admin != null) {
            try {
                admin.close();
            } catch (Exception e) {
                log.error("关闭Admin失败", e);
            }
            if (rs != null) {
                rs.close();
            }
            if (table != null) {
                rs.close();
            }
            if (table != null) {
                try {
                    table.close();
                } catch (Exception e) {
                    log.error("关闭Table失败", e);
                }
            }
        }
    }
}