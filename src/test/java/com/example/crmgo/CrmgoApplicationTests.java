package com.example.crmgo;

import com.example.crmgo.register.service.IRoleManagementService;
import com.example.crmgo.register.utils.Snowflake.SnowflakeIdWorker;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
class CrmgoApplicationTests {

    @Autowired
    private IRoleManagementService roleManagement;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        List list = new ArrayList();
        list.add("张三");
        list.add("李四");
        list.add("王五");

        System.out.println(list);
        List list1 = new ArrayList();
        list1.add("jack");
        list1.add("Tom");
        list.addAll(0, list1);
        System.out.println(list);
        System.out.println(list.indexOf("王五"));
    }

    @Test
    void test1() {
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            String str = "Hello" + i;
            list.add(str);
        }
        list.add(2, "韩顺平教育");
        System.out.println(list.get(5));
        list.remove(5);
        list.set(6, "hsp");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    void test2() {
//        roleManagement.roleQuery(1).forEach(System.out::println);
        /*Iterator iterator = roleManagement.roleQuery(1).iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }*/
        int a = 1;
        int b = 1;
        String str2 = "aaaa";
        String str1 = new String("aaaa");

        System.out.println(a == b);
        System.out.println(str1 == str2);
        System.out.println(str2.equals(str1));
        List list = new ArrayList();
        list.add("cccc");
        list.add("bbbb");
        list.add("aaaa");
        list.add("dddd");
        list.add(null);
        list.add("bbbb");
        System.out.println(list);
        /*Set set = new HashSet();
        for (Object o : list){
            set.add(o);
        }
        System.out.println("================================");
        for (Object o : set){
            System.out.println(o);
        }*/
        Set treeSet = new TreeSet();
        for (Object str : list){
            if (str == null){
                continue;
            }
            treeSet.add(str);
        }
        System.out.println("=========================");
        for (Object o : treeSet){
            System.out.println(o);
        }
    }

}
