package com.example.asyncdemo.testBro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.lang.Nullable;
import org.springframework.util.function.SingletonSupplier;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        A a2 = new A("CN","PEK");
        A a3 = new A("CN","SHA");
        A a4 = new A("JP","Tokyo");
//        B b = new B("JP","日语");

        List<A> aList1 = new ArrayList<>();
        List<A> aList2 = new ArrayList<>();
        Set<A> aList3 = new HashSet<>();


        aList2.add(a2);
        aList2.add(a3);
        aList2.add(a4);
        aList2.add(a2);
        aList2.add(a3);

//        aList3.add(b);
        aList3.addAll(aList2);

        A next = aList3.iterator().next();
        System.out.println(next);
        Predicate<String> jp = Predicate.isEqual("JP");
        Predicate<A> noJP = (A f) -> jp.test(f.getCountry());

        Stream<A> aStream = aList3.stream().filter(Predicate.not(noJP));

        List<String> collect = aStream.map(A::getCity).collect(Collectors.toList());
        System.out.println(collect);

//        List<Father> noPek = aStream.collect(Collectors.toList());
//
//
//        System.out.println(noPek);
        ExecutionInterceptor interceptor = new ExecutionInterceptor(null, null);
        interceptor.configure(SimpleAsyncTaskExecutor::new,"yes");
//        interceptor.configure2(SimpleAsyncTaskExecutor::new,"no");
        AsyncTaskExecutor e = interceptor.getE();
        System.out.println(e.getClass().getName());
    }

    public static <T> T process(BInterface<T> bInterface){
        return bInterface.doProcess();
    }

    public A returnA(A a){
        BInterface<A> bInterface = new BInterface<>() {
            @Override
            public A doProcess() {
                return a;
            }
        };
        return process(bInterface);
    }
}

@AllArgsConstructor
@Getter
@Setter
@ToString
class Father{
    String country;
}
@ToString(callSuper = true)
@Setter
@Getter
class A extends Father{
    String city;
    public A(String country , String city) {
        super(country);
        this.city = city;
    }
}
@ToString(callSuper = true)
@Setter
@Getter
class B extends Father{
    String language;
    public B(String country, String language) {
        super(country);
        this.language = language;
    }
}
// ------------------------------------
@FunctionalInterface
interface BInterface<T> {
    T doProcess();
}

class BInterfaceSupplier<T> implements BInterface<T>{
    private final BInterface<? extends T> defaultSupplier;
    private volatile T singletonInstance;
    private final BInterface<? extends T> instanceSupplier;

    BInterfaceSupplier(BInterface<? extends T> defaultSupplier) {
        this.defaultSupplier = defaultSupplier;
        instanceSupplier = null;
    }
    BInterfaceSupplier(T instance, BInterface<? extends T> defaultSupplier) {
        this.defaultSupplier = defaultSupplier;
        this.singletonInstance = instance;
        instanceSupplier = null;
    }

    BInterfaceSupplier(@Nullable BInterface<? extends T> instanceSupplier, BInterface<? extends T> defaultSupplier) {
        this.defaultSupplier = defaultSupplier;
        this.instanceSupplier = instanceSupplier;
    }

    @Override
    public T doProcess() {
        System.out.println("BInterfaceSupplier 的 doProcess 实现方法执行");
        return this.defaultSupplier.doProcess();
    }
}

abstract class ExecutionSupport{
    private BInterfaceSupplier<Executor> defaultExecutor;

    private SingletonSupplier<Executor> defaultExecutor22;

    public ExecutionSupport(@Nullable Executor defaultExecutor,String s) {
        this.defaultExecutor = new BInterfaceSupplier<>(defaultExecutor, ()-> getDefaultExecutor(s));
    }

    public void configure(@Nullable BInterface<Executor> defaultExecutor, String s) {
        this.defaultExecutor = new BInterfaceSupplier<>(defaultExecutor, new BInterface<Executor>() {
            @Override
            public Executor doProcess() {
                return getDefaultExecutor2(s);
            }
        });
    }
    public void configure2(@Nullable Supplier<Executor> defaultExecutor, String s) {
        this.defaultExecutor22 = new SingletonSupplier<>(defaultExecutor, () -> getDefaultExecutor(s));
    }

    protected Executor getDefaultExecutor(String s){
        if (s != null) {
            return new SimpleAsyncTaskExecutor();
        }
        else return null;
    }

    protected Executor getDefaultExecutor2(String s){
        if (s != null) {
            return new SyncTaskExecutor();
        }
        else return null;
    }

    protected AsyncTaskExecutor determineAsyncExecutor(){
        AsyncTaskExecutor executor = null;
        Executor targetExecutor;
        targetExecutor = this.defaultExecutor.doProcess();
        executor = (targetExecutor instanceof AsyncTaskExecutor asyncTaskExecutor ?
                asyncTaskExecutor : new TaskExecutorAdapter(targetExecutor));
        return  executor;
    }
}
class ExecutionInterceptor extends ExecutionSupport{
    public ExecutionInterceptor(Executor defaultExecutor, String s) {
        super(defaultExecutor,s);
    }
    public AsyncTaskExecutor getE(){
        return determineAsyncExecutor();
    }
}
class NMClass implements BInterface<Executor> {
    @Override
    public Executor doProcess() {
        return new SyncTaskExecutor();
    }
}