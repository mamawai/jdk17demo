package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    private static final Log logger = LogFactory.getLog(Test.class);
    static int ans;
    static int value = 23123;
    static String str = String.valueOf(value);
    private static final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();


    public static void main(String[] args) throws IOException {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        DFS(0,false,0,list);
//        System.out.println(ans);
        PathMatcher pathMatcher = resolver.getPathMatcher();

        String rootDirPath = "classpath*:com/example/demo/";
        String subPattern = "**/*.class";
        Resource[] rootDirResources = resolver.getResources(rootDirPath);
        Resource rootDirResource = rootDirResources[0];
        URI uri = rootDirResource.getURI();
        Path rootPath = Path.of(uri);
        String resourcePattern = StringUtils.cleanPath(rootPath.toString())+"/" + StringUtils.cleanPath(subPattern);

        Predicate<Path> isMatchingFile = path -> (!path.equals(rootPath) &&
                resolver.getPathMatcher().match(resourcePattern, StringUtils.cleanPath(path.toString())));
        Set<Resource> result = new LinkedHashSet<>();
        try (Stream<Path> files = Files.walk(rootPath)) {
//            List<Path> collect = files.collect(Collectors.toList());
//            System.out.println(collect);
//            List<Path> collect1 = collect.stream().filter(new Predicate<Path>() {
//                @Override
//                public boolean test(Path path) {
//                    if(!path.equals(rootPath)
//                            &&
//                            resolver.getPathMatcher().match(resourcePattern, StringUtils.cleanPath(path.toString())))
//                    {
//                        return true;
//                    }
//                    else {
//                        return false;
//                    }
//                }
//            }).collect(Collectors.toList());
//            System.out.println(collect1);
            files.filter(isMatchingFile).sorted().map(FileSystemResource::new).forEach(result::add);
        }
        catch (Exception ex) {
            if (logger.isWarnEnabled()) {
                logger.warn("Failed to search in directory [%s] for files matching pattern [%s]: %s"
                        .formatted(rootPath.toAbsolutePath(), subPattern, ex));
            }
        }
        System.out.println(result);

    }
    public static boolean DFS(int index, boolean pass, int temp, List<Integer> list){
        if (index==str.length()){
            ans = temp;
            return true;
        }

        //pass为true的情况,只有最高位没有数，或者上一位数小于对应n中的值
        if(pass){
            return DFS(index+1,true,temp*10+list.get(list.size()-1),list);
        }else{
            int val = str.charAt(index)-'0';

            //找到与这一位相比 取相等的或者小于中最大的一位
            for (int i=list.size()-1;i>=0;i--) {
                //先判断等于这一位的
                if (val==list.get(i)){
                    if (DFS(index+1,false,temp*10+list.get(i),list)){
                        return true;
                    }
                }else if(val>list.get(i)){//要不就取小于这一位的最大值
                    if (DFS(index+1,true,temp*10+list.get(i),list)){
                        return true;
                    }
                }
            }

            //如果该位置都放不了且index!=0
            if (index!=0) return false;
            //如果index==0 上面for循环中这些位置都不满足,应该跳过这一位了
            return DFS(index+1,true,temp,list);
        }
    }
}