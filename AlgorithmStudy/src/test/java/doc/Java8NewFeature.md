#Java SE 8 流库
##产生原因
1.针对集合进行操作，告诉java需要怎么做

2.遵循做什么而非怎么做的原则

3.流不存储其元素

4.流的操作不会修改其原数据，产生一个新的流

5.流的操作是惰性的，完成任务就会停止

##流的工作执行

1.创建一个流

2.指定将初始流转化为其他流的中间操作（惰性操作）

3.应用终止操作，产生结果

##流的使用

流的创建

1.Collection有stream方法，可以将集合转化成一个流，Array.stream(array,from,to)将数组转化成一个流

2.使用流的创建函数



##流的函数

###流的创建函数

1.Stream.of()产生一个元素为给定值的流，多用于数组

2.Stream.empty()创建一个不包含任何元素的流

3.Stream.generate(Supplier<T> s)产生一个无限流，它的值是通过反复调用函数S构建的，Supplier<T> s是一个无需参数的函数式接口，
类似于一个承载方法的容器，使用get()方法就会执行一次函数

Stream.generate(()->"lijishu");

Stream<Double> random = Stream.generate(Math::random);

4.Stream.iterate(T seed,UnaryOperator<T> f)产生一个无限流序列，它的元素包括种子以及在种子上调用f产生的值，该序列第一个元素是种子，第二个元素是f(seed),下一个元素是

Stream.iterate(BigInteger.ZERO,n->n.add(BigInteger.ONE));f(f(seed))

###流的filter，map，flatMap（在stream（）之后使用）
Stream<T>filter(Predicate<? super T> predicate) filter会产生一个新的流，它的元素与某种条件匹配,Predicate是一个可以接受参数的判断接口对象，用于判断
流元素是否满足某一条件

<R> Stream<R> map(Fuction<? super T,? extend R>mapper) 产生一个流，将当前流中的结果通过Mapper转换产生的结果。

<R> Stream<R> flatMap(Function<? super T,? extend Stream<? extend Stream<? extends R>> mapper) 产生一个流它通过将mapper应用于
当前流中的子流元素，（当前流指list），最后将许多子流拼成一个流作为结果，mapper是应用到子流的转换函数

###抽取子流和连接流

1.Stream.limit(Long N) 对无限流进行操作，返回一个含有前n元素的新流

2.Stream.skip（Long N）针对有限流，截取N到最后中的全部数据

3.Stream.concat(stream 1,stresm2) 将两个流连接到一起

###其他的流转换

Stream<T> distinct() 产生一个流，去掉当前流中重复的元素










