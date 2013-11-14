写一个抽象类，构造方法给出表名。
定义一堆set方法，来定义属性对应的表属性名、查询关键字（=、<、>、in、between、like等）等信息
	setPrarm(paramValue,clomName,keyword);
下面两个实现类:SQLbuilder、HQLbuilder分别实现setparam方法
调用时先构造builder。然后先后调用setparam方法传递参数逐步构造出sql语句。
