1.启动mongodb服务

    在bin目录下执行命令  --dbpath=D:\mongodb\data\db (后面这个是数据存储地址)
2.基本操作

    bin目录下执行mongo进入链接
    使用某个集合 use xxx；
    创建一个集合 db.createCollecton(name,options) 
    查看现有集合 show collections
    insert操作：db.tsh.insert({"name":"tsh","age":23}) 
        在没有对应集合的情况下会创建集合tsh
	    在没有设置{"_id":"xxx"}会生成一个uuid主键
    find操作 ：db.tsh.find()查找所有
            db.tsh.find({"":""}) 对某个指定对象检索，可以拼接多个
    update操作：db.tsh.update({"name":"tsh"},{"name":"tsh","age":21})
	    前面是条件，后面是对应的修改，必须是全部。
		多条匹配只能更新一条
	remove操作：db.tsh.remove({"name":"tsh"})
	    切记：删除时一定要带条件，不然数据就全没了
3.基本操作提升

	1.insert操作可以使用Javascript语法 
	    var single = {"name":"StrakTan","age":23,
		             "address":{"province":"四川"}}
	    db.tsh.insert(single)
	    var other = {"name":"StarkTan"}
		other.age=23
		other.address = {"province":"sichuan"}
		db.tsh.insert(other)
	    注：现在数据库只能一条条插入，不过各种语言提供批量插入操作
	2.find操作
		1.>, >=, <, <=, !=, = 查询 对应 "$gt", "$gte", "$lt", "$lte", "$ne", "没有特殊关键字"
		eg 大于查询 db.tsh.find({"age":{$gt:22}})
		2.And，OR，In，NotIn 对应  "无关键字“, "$or", "$in"，"$nin"
		eg Or : db.tsh.find({$or:[{"age":22},{"age":34}]})
		   In : db.tsh.find({"address.province":{$in:["四川","上海"]}})
		3.正则表达式
		eg db.tsh.find({"name":/^t/,"name":/h$/}) name以t开头，以h结束
	    4.$where
		eg: db.tsh.find({$where:function(){return this.name="tsh"}})
	3.update操作：
	    1.整体更新：
	    eg：db.tsh.update({"name":"tsh"},{"name":"tsh","age":21})
		2.局部更新： 修改器： $inc 和 $set。 //实践：好像只修改第一个匹配的
		eg：db.tsh.update({"name":"tsh"},{$inc:{"age":30}}) //增加30
		eg：db.tsh.update({"name":"tsh"},{$set:{"age":30}}) //直接修改
		3.upsert操作 类似saveOrUpdate 主要是第三个参数
		eg：db.tsh.update({"name":"tansihao"},{"name":"tsh","age":24},true)
		4.批量更新 第四个参数
		eg：db.tsh.update({"name":"tsh"},{$inc:{"age":30}},false,true)
	4.remove操作：

4.高级操作

	1.聚合，包括count,distinct,group,mapReduce
		1 count : db.tsh.count()   db.tsh.count({"age":24})
		2 distinct db.tsh.distinct("age")
		3 group : 参数介绍key ：分组的key，initial 分组后的数据 $reduce：分组操作，每个文档都会执行一次
			db.tsh.group({"key":{"age":true},
			              "initial":{"person":[]},
						  "$reduce":function(cur,prev){
								prev.person.push(cur.name);
							}
							})
				  参数追加： condition 过滤条件 finalize 每一组文档执行完后，都会触发这个方法
			db.tsh.group({  
							"key":{"age":true},
			                "initial":{"person":[]},
						    "$reduce":function(cur,prev){
								prev.person.push(cur.name);
							},
							"finalize":function(prev){
								prev.count = prev.person.length;
							},
							"condition":{"age":{$gte:25}}
						})
	    4 mapReduce :一种经常用于分布式的编程模式
		    map：这个称为映射函数，里面会调用emit(key,value)，集合会按照你指定的key进行映射分组。
			var map=function(){
					emit(this.age,this.name);
				}
		    reduce :reduce函数会处理每一个分组，参数也正好是我们想像分组里的key和values。
			var reduce=function(key,values){
				var ret={age:key,names:values};
				return ret;
			}
			用finally 做最终操作
			var fin=function(key,rval){
				if(key==24){
					rval.msg="right age!";
				}
				return rval
			}
			执行
			db.runCommand({
				mapreduce:"tsh",  
				map:map,
				reduce:reduce,
				finalize:fin,
				out:"result"
				}
			)
			语法

			db.runCommand(
			 { mapreduce : 字符串，集合名,
			   map : 函数
			   reduce : 函数
			   [, query : 文档，发往map函数前先给过渡文档]
			   [, sort : 文档，发往map函数前先给文档排序]
			   [, limit : 整数，发往map函数的文档数量上限]
			   [, out : 字符串，统计结果保存的集合]
			   [, keeptemp: 布尔值，链接关闭时临时结果集合是否保存]
			   [, finalize : 函数，将reduce的结果送给这个函数，做最后的处理]
			   [, scope : 文档,js代码中要用到的变量]
			   [, jsMode : 布尔值，是否减少执行过程中BSON和JS的转换，默认true] //注：false时 BSON-->JS-->map-->BSON-->JS-->reduce-->BSON,可处理非常大的mapreduce,<br>　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　//true时BSON-->js-->map-->reduce-->BSON
			   [, verbose : 布尔值，是否产生更加详细的服务器日志，默认true]
			 }
			);
	2.游标：
	    var list=db.tsh.find(); //这样申明一个查询结构，类似于懒加载
		list.forEach(function(x){
			print(x.name);
		})
		复杂一点
		var single=db.tsh.find().sort({"name":1}).skip(2).limit(2);
5.索引学习

	创建10w条数据
	for(var i=0;i<100000;i++){
		var random = parseInt(i*Math.random());
		db.tsh.insert({"name":"tsh"+random,"random":random,"number":i})
	}
	1.性能分析函数（explain）
		eg： db.tsh.find({"name":"tsh232"}).explain("executionStats")  //48ms
	2.建立索引 ensureIndex(); 查询 1为升序，-1为降序 添加{unique：true}则为唯一索引
	    eg： db.tsh.ensureIndex({"name":1},{"unique":true}) //有重复值不能是唯一索引
		     db.tsh.ensureIndex({"number":1},{"unique":true})
			 db.tsh.ensureIndex({"name":1,"random":-1}) 可以组合索引
	    正确的使用索引能优化查询速度（可以强制使用索引）hint（）
	3.查询索引 getIndexes()
		eg: db.tsh.getIndexes();
	4.删除索引 dropIndexes() 删除除id所有 dropIndex({}) 指定删除
		
6.主从复制

    D盘启动服务（主）
    mongod.exe --dbpath=D:\mongodb\data\db --master	
    E盘启动服务（从）
	mongod.exe --dbpath=E:\mongodb\data\db --port=8888 --slave --source=localhost:27017
7.副本集群

    未测试
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		