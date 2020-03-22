_#Java SE 8 流库
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

##约简

约简指将流值转化为程序中使用的非流值

Optional<T> max(Comparator<? super T> comparator) 

Optional<T> max(Comparator<? super T> comparator) 

根据比较器产生一个Optional对象的。此对象中装着这个流的最大元素和最小元素（都是对流对象的操作），流为空返回一个空的Optional对象

Optional<T> findFirst()

Optional<T> findAny()

分别产生这个流的第一个和任意一个元素，如果这个流为空，会产生一个空的Optional对象

boolean anyMatch(Predicate<? super T> predicate)

boolean allMatch(Predicate<? super T> predicate)

boolean noneMatch(Predicate<? super T> predicate)

分别在这个流中任意元素、所有元素和没有任何元素匹配给定断言时返回true

##Optional类型


Optional<T>对象是一种包装器对象,核心作用是在值不存在的时候，会产生一个可替代物。

T orElse(T other)  产生这个Optional的值，或者在该Optional为空时，产生other

T orElseGet(Supplier<? extends T>other)

