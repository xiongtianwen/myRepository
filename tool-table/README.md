# Getting Started
### 1、进入 resources/autoCreateTableConfig.properties
### 2、data.file.dir需要批量生成表的文件夹路径
### 3、data.table.pkType是主键类型(0自增 1uuid)
### 主键类型分不同的文件不要一起生成，不同主键类型的表可以分开处理
### 4、data.table.pk是主键名称
### 5、poi方式支持多sheet页创建表，sheet名称就是表名
### 6、easypoi方式不支持多sheet，excel文件名就是表名
### 7、用easypoi方式需要注释poi的包，会有冲突