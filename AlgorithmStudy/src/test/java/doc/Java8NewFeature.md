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

3.Stream.generate(Supplier<T>
s)产生一个无限流，它的值是通过反复调用函数S构建的，Supplier<T>
s是一个无需参数的函数式接口， 类似于一个承载方法的容器，使用get()方法就会执行一次函数
````
[Supplier用来创建]
````

Stream.generate(()->"lijishu");

Stream<Double> random = Stream.generate(Math::random);

4.Stream.iterate(T seed,UnaryOperator<T> f)产生一个无限流序列，它的元素包括种子以及在种子上调用f产生的值，该序列第一个元素是种子，第二个元素是f(seed),下一个元素是

Stream.iterate(BigInteger.ZERO,n->n.add(BigInteger.ONE));f(f(seed))

##流的filter，map，flatMap（在stream（）之后使用）
Stream<T>filter(Predicate<? super T> predicate) filter会产生一个新的流，它的元素与某种条件匹配,Predicate是一个可以接受参数的判断接口对象，用于判断
流元素是否满足某一条件

`<R> Stream<R> map(Fuction<? super T,? extend R>mapper)` 产生一个流，将当前流中的结果通过Mapper转换产生的结果。

`<R> Stream<R> flatMap(Function<? super T,? extend Stream<? extend Stream<? extends R>> mapper)` 产生一个流它通过将mapper应用于
当前流中的子流元素，（当前流指list），最后将许多子流拼成一个流作为结果，mapper是应用到子流的转换函数

##抽取子流和连接流

1.Stream.limit(Long N) 对无限流进行操作，返回一个含有前n元素的新流

2.Stream.skip（Long N）针对有限流，截取N到最后中的全部数据

3.Stream.concat(stream 1,stresm2) 将两个流连接到一起

##其他的流转换

Stream<T> distinct() 产生一个流，去掉当前流中重复的元素

Stream<T> sorted()

Stream<T> sorted(Comparator<? super T>comparator)产生一个流他的元素是当前流中的所有元素按照顺序排列的。

Stream<T> peek(Consumer<? super T>action) 产生一个流，它与当前流中元素相同，在获取每个元素时，会将其传递给action,执行一下action



##约简

约简指将流值转化为程序中使用的非流值

Optional<T> max(Comparator<? super T> comparator) 

Optional<T> min(Comparator<? super T> comparator) 

根据比较器产生一个Optional对象的。此对象中装着这个流的最大元素和最小元素（都是对流对象的操作），流为空返回一个空的Optional对象

Optional<T> findFirst()

Optional<T> find()

分别产生这个流的第一个和任意一个元素，如果这个流为空，会产生一个空的Optional对象

boolean anyMatch(Predicate<? super T> predicate)
```
[predicate 一个匹配条件]
```

boolean allMatch(Predicate<? super T> predicate)

boolean noneMatch(Predicate<? super T> predicate)

分别在这个流中任意元素、所有元素和没有任何元素匹配给定断言时返回true

##Optional类型


Optional<T>对象是一种包装器对象,核心作用是在值不存在的时候，会产生一个可替代物。

T orElse(T other)  产生这个Optional的值，或者在该Optional为空时，产生other

T orElseGet(Supplier<? extends T>other)产生这个Optional值，或者在Optional为空时，产生调用other的结果（无参有返回值操作结果）

`<X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier)` 产生这个Optional的值，或者在该Optional为空时抛出调用exceptionSupplier的结果

`void ifPresent（Consumer<? super T> consumer）`如果Optional不为空，那么就将它的值传递给consumer(有参无返回值)

`<U> Optional <U> map(Function<? super T,? extends U> mapper)`产生将该Optional的值传递给mapper后的结果，只要这个Optional不为空且结果不为null，否则产生一个空Optional

##不适合使用Optional类型的方式

T get() 产生optional的值或者返回一个异常

boolean isPresent() 判断是否存在

这个方法不推荐使用，因为没有意义，Optional核心是不存在值时自动处理


##创建Optional的值

static <T> Optional<T> of(T value)

static <T> Optional<T> ofNullable() 

产生一个具有给定值的Optional,如果value为null，第一个方法会抛出NullPointerException对象，而第二个方法会产生一个空的Optional

static <T> Optional<T> empty() 产生一个空的Optional对象

`<U> Optional <U> flatMap(Fuction<? super T,Optional<U> mapper)有一个可以产生Optional<T>` 

对象的方法f,并且目标类型T具有一个可以产生`Optional<U>`的方法g。f().g()没法执行，可以使用flatMAp()来做转换，将Optional(T)转换成T
f().flatmap(T::g),产生将mapper应用于当前Optional值所产生的结果，或者在当前Optional为空时，返回一个空的Optional

##收集结果

void forEach(Consumer<? super T> action)在流的每个元素上调用action，是一种终结操作，但是在并行流中无法保证访问元素顺序，此时可以使用
Iterator<T> iterator()这种迭代器来输出流的内容。

Object[] toArray()

`<A> A[] toArray(IntFunction<A[]> generator)`

产生一个数组对象，第二个需在参数中传入一个构造器 A[]::new ,可以返回一个A类型的数组

<R,A>collect(Collector<? super T,A,R> collector),使用给定的收集器来收集当前流中的元素，Collectors类中有用于多种收集器的工厂方法

static <T> Collector<T,?,List<T>> toList

static <T> Collector<T,?,Set<T>> toSet

产生一个将元素收集到列表或集中的收集器

static <T,C extends Collection<T> Collector<T,?,C> toCollection(Supplier<C> collectionFactory)

产生一个将元素收集到任意集合中的收集器，可以传递一个诸如TreeSet：：new的构造器引用

static Collector<CharSequence,?,String> joining()

static Collector<CharSequence,?,String> joining(CharSequence delimiter) 

static Collector<CharSequence,?,String> joining(CharSequence delimiter,CharSequence prefix,CharSequence suffix) 

产生一个连接字符串的收集器，delimiter为拼接时的分隔符，prefix为第一个字符串之前的前缀，suffix为最后一个字符串之间的后缀

static <T> Collector<T,?,IntSummaryStatistics> summarizingInt(ToIntFunction<? super T>mapper)

static <T> Collector<T,?,LongSummaryStatistics> summarizingLong(ToIntFunction<? super T>mapper)

static <T> Collector<T,?,DoubleSummaryStatistics> summarizingDouble(ToIntFunction<? super T>mapper)

产生一个SummaryStatistics结果收集器，通过mapper应用于每个元素后所产生的结果进行统计

long getCount()

产生汇总后的元素个数

 (int|Long|double) getSum()
 
 产生汇总后的元素的总和，在没有任何元素时返回0
 
 double getAverage()
 
 产生汇总后的元素的平均值，在没有任何元素时返回0
 
 (int|Long|double) getMax()
 
 (int|Long|double) getMin()
 
 产生汇总后元素的最大值和最小值，当没有任何值时产生（Integer|Long|Double).(Min|Max)_VALUE
 
 
 ##收集到映射中
```` 
static<T,K,U,M extends Map<K,U> Collector<T,?,M> toMap(Fuction<? super T,? extends K> keyMapper,Fuction<? super T,? extends K> valueMapper,
BinaryOperator<U> mergeFunction,Supplier<M> mapSupplier)

static<T,K,U,M extends ConcurentMap<K,U> Collector<T,?,M> toConcurentMap(Fuction<? super T,? extends K> keyMapper,Fuction<? super T,? extends K> valueMapper,
BinaryOperator<U> mergeFunction,Supplier<M> mapSupplier)
````

产生一个收集器，产生一个映射表或者并发映射表，keyMapper和valueMapper函数用于将流中的元素映射成映射表中的一个键/值项，产生相同的键时会产生IllegalStateException异常,可以使用mergeFunction
函数来处理相同的键值，mapSupplier可以提供一个期望的映射表实例


##群组和分区

static<T,K> Collector<T,?,Map<K,List<T>>> groupingBy(Function<? super T,? extends K> classifier) 

static<T,K> Collector<T,?,ConcurrentMap<K,List<T>>> groupingBy(Function<? super T,? extends K> classifier) 

产生一个收集器，它会产生一个映射表或并发映射表，其键是将classifier 应用于所有收集到的元素上所产生的结果，而值是由具有相同键元素构成的一个个列表

static <T> Collector<T,?,Map<Boolean,List<T>>> partitioningBy(Predicate<? super T> predicate)
产生一个收集器，它会产生一个映射表，其键值是true\false,而值是由满足/不满足断言的元素构成的列表 


##下游收集器

static<T> Collector<T,?,Long>counting()

产生一个收集元素进行计数的收集器

static<T>Collector<T,?,Integer>summingInt(ToIntFunction<? super T> mapper)

产生一个收集器，对将mapper应用到收集的元素上之后产生的值计算总和

static<T>Collector<T,?,Optional<T>> minBy(Comparator<? super T> comparator)

产生一个收集器，使用comparator指定排序的方法，计算收集到的元素的极值

static <T,U,A,R> Collector<T,?,R> mapping(Function<? super T,? extends U>mapper,Collector<? super U,A,R>downstream)

产生一个收集器，产生一个子映射表，其键值是将mapper应用到收集到的数据上而产生的，其值是使用downstream收集器收集到的具有相同键的元素

##reduce方法

reduce方法是一种从流中计算某个值的通用机制

reduce（op（v0,v1））

 intege xxx =   stream().reduce((x,y)->x+y) = 就是流中v0+v1+v2....

当流的为空时会返回一个optional

reduce(0,(x,y)->x+y)会返回一个0值

reduce(提供者,累计器,组合器)

int result = words.reduce(0,(total,word)->total+word.length(),(total1,total2)->total1+total2)


````
函数式方法：
Function<T, R>  用于指代get方法 T为参数对象类型，R为返回值类型 accpet执行所传函数

BiConsumer<T,R>是一个函数式接口。它可以接受两个参数，没有返回值 accpet执行所传函数

BiConsumer<Integer, Integer> addTwo = (x, y) -> System.out.println(x + y);
      addTwo.accept(1, 2);    // 3  

````










