1.����mongodb����

    ��binĿ¼��ִ������  --dbpath=D:\mongodb\data\db (������������ݴ洢��ַ)
2.��������

    binĿ¼��ִ��mongo��������
    ʹ��ĳ������ use xxx��
    ����һ������ db.createCollecton(name,options) 
    �鿴���м��� show collections
    insert������db.tsh.insert({"name":"tsh","age":23}) 
        ��û�ж�Ӧ���ϵ�����»ᴴ������tsh
	    ��û������{"_id":"xxx"}������һ��uuid����
    find���� ��db.tsh.find()��������
            db.tsh.find({"":""}) ��ĳ��ָ���������������ƴ�Ӷ��
    update������db.tsh.update({"name":"tsh"},{"name":"tsh","age":21})
	    ǰ���������������Ƕ�Ӧ���޸ģ�������ȫ����
		����ƥ��ֻ�ܸ���һ��
	remove������db.tsh.remove({"name":"tsh"})
	    �мǣ�ɾ��ʱһ��Ҫ����������Ȼ���ݾ�ȫû��
3.������������

	1.insert��������ʹ��Javascript�﷨ 
	    var single = {"name":"StrakTan","age":23,
		             "address":{"province":"�Ĵ�"}}
	    db.tsh.insert(single)
	    var other = {"name":"StarkTan"}
		other.age=23
		other.address = {"province":"sichuan"}
		db.tsh.insert(other)
	    ע���������ݿ�ֻ��һ�������룬�������������ṩ�����������
	2.find����
		1.>, >=, <, <=, !=, = ��ѯ ��Ӧ "$gt", "$gte", "$lt", "$lte", "$ne", "û������ؼ���"
		eg ���ڲ�ѯ db.tsh.find({"age":{$gt:22}})
		2.And��OR��In��NotIn ��Ӧ  "�޹ؼ��֡�, "$or", "$in"��"$nin"
		eg Or : db.tsh.find({$or:[{"age":22},{"age":34}]})
		   In : db.tsh.find({"address.province":{$in:["�Ĵ�","�Ϻ�"]}})
		3.������ʽ
		eg db.tsh.find({"name":/^t/,"name":/h$/}) name��t��ͷ����h����
	    4.$where
		eg: db.tsh.find({$where:function(){return this.name="tsh"}})
	3.update������
	    1.������£�
	    eg��db.tsh.update({"name":"tsh"},{"name":"tsh","age":21})
		2.�ֲ����£� �޸����� $inc �� $set�� //ʵ��������ֻ�޸ĵ�һ��ƥ���
		eg��db.tsh.update({"name":"tsh"},{$inc:{"age":30}}) //����30
		eg��db.tsh.update({"name":"tsh"},{$set:{"age":30}}) //ֱ���޸�
		3.upsert���� ����saveOrUpdate ��Ҫ�ǵ���������
		eg��db.tsh.update({"name":"tansihao"},{"name":"tsh","age":24},true)
		4.�������� ���ĸ�����
		eg��db.tsh.update({"name":"tsh"},{$inc:{"age":30}},false,true)
	4.remove������

4.�߼�����

	1.�ۺϣ�����count,distinct,group,mapReduce
		1 count : db.tsh.count()   db.tsh.count({"age":24})
		2 distinct db.tsh.distinct("age")
		3 group : ��������key �������key��initial ���������� $reduce�����������ÿ���ĵ�����ִ��һ��
			db.tsh.group({"key":{"age":true},
			              "initial":{"person":[]},
						  "$reduce":function(cur,prev){
								prev.person.push(cur.name);
							}
							})
				  ����׷�ӣ� condition �������� finalize ÿһ���ĵ�ִ����󣬶��ᴥ���������
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
	    4 mapReduce :һ�־������ڷֲ�ʽ�ı��ģʽ
		    map�������Ϊӳ�亯������������emit(key,value)�����ϻᰴ����ָ����key����ӳ����顣
			var map=function(){
					emit(this.age,this.name);
				}
		    reduce :reduce�����ᴦ��ÿһ�����飬����Ҳ��������������������key��values��
			var reduce=function(key,values){
				var ret={age:key,names:values};
				return ret;
			}
			��finally �����ղ���
			var fin=function(key,rval){
				if(key==24){
					rval.msg="right age!";
				}
				return rval
			}
			ִ��
			db.runCommand({
				mapreduce:"tsh",  
				map:map,
				reduce:reduce,
				finalize:fin,
				out:"result"
				}
			)
			�﷨

			db.runCommand(
			 { mapreduce : �ַ�����������,
			   map : ����
			   reduce : ����
			   [, query : �ĵ�������map����ǰ�ȸ������ĵ�]
			   [, sort : �ĵ�������map����ǰ�ȸ��ĵ�����]
			   [, limit : ����������map�������ĵ���������]
			   [, out : �ַ�����ͳ�ƽ������ļ���]
			   [, keeptemp: ����ֵ�����ӹر�ʱ��ʱ��������Ƿ񱣴�]
			   [, finalize : ��������reduce�Ľ���͸���������������Ĵ���]
			   [, scope : �ĵ�,js������Ҫ�õ��ı���]
			   [, jsMode : ����ֵ���Ƿ����ִ�й�����BSON��JS��ת����Ĭ��true] //ע��falseʱ BSON-->JS-->map-->BSON-->JS-->reduce-->BSON,�ɴ���ǳ����mapreduce,<br>������������������������������������������������������������������������//trueʱBSON-->js-->map-->reduce-->BSON
			   [, verbose : ����ֵ���Ƿ����������ϸ�ķ�������־��Ĭ��true]
			 }
			);
	2.�α꣺
	    var list=db.tsh.find(); //��������һ����ѯ�ṹ��������������
		list.forEach(function(x){
			print(x.name);
		})
		����һ��
		var single=db.tsh.find().sort({"name":1}).skip(2).limit(2);
5.����ѧϰ

	����10w������
	for(var i=0;i<100000;i++){
		var random = parseInt(i*Math.random());
		db.tsh.insert({"name":"tsh"+random,"random":random,"number":i})
	}
	1.���ܷ���������explain��
		eg�� db.tsh.find({"name":"tsh232"}).explain("executionStats")  //48ms
	2.�������� ensureIndex(); ��ѯ 1Ϊ����-1Ϊ���� ���{unique��true}��ΪΨһ����
	    eg�� db.tsh.ensureIndex({"name":1},{"unique":true}) //���ظ�ֵ������Ψһ����
		     db.tsh.ensureIndex({"number":1},{"unique":true})
			 db.tsh.ensureIndex({"name":1,"random":-1}) �����������
	    ��ȷ��ʹ���������Ż���ѯ�ٶȣ�����ǿ��ʹ��������hint����
	3.��ѯ���� getIndexes()
		eg: db.tsh.getIndexes();
	4.ɾ������ dropIndexes() ɾ����id���� dropIndex({}) ָ��ɾ��
		
6.���Ӹ���

    D��������������
    mongod.exe --dbpath=D:\mongodb\data\db --master	
    E���������񣨴ӣ�
	mongod.exe --dbpath=E:\mongodb\data\db --port=8888 --slave --source=localhost:27017
7.������Ⱥ

    δ����
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		