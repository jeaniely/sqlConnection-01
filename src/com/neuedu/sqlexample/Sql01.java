package com.neuedu.sqlexample;

import com.neuedu.entity.Student;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sql01 {
    static String url="jdbc:mysql://127.0.0.1:3306/pms";
   static String username="root";
    static String password="123456";
    public static void main(String[] args)  {
        //update();
        try {
            List<Student> studentList=  getAll();
            for (Student s:studentList
                 ) {
                System.out.println(s.getSno()+"\t\t"+s.getSname());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  //查找实例--查找全部数据
    public static List<Student> getAll() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        List<Student> studentList=new ArrayList<>();
        conn=DriverManager.getConnection(url,username,password);

        String sql="select * from student";
        pstm=conn.prepareStatement(sql);
        rs =pstm.executeQuery();

        while(rs.next()){
           Student s=new Student();
           s.setSno(rs.getString("sno"));
           s.setSname(rs.getString("sname"));
           s.setSsex(rs.getString("ssex"));
           s.setSage(rs.getInt("sage"));
           s.setSdept(rs.getString("sdept"));
           s.setBirthday(rs.getDate("birthday"));
           studentList.add(s);

        }

        if(rs!=null){
            rs.close();
        }
        if(pstm!=null){
            pstm.close();
        }
        if(conn!=null){
            conn.close();
        }
     return studentList;
    }

    //删除的实例
    public static void update(){
        //1:加载驱动--ctrl+alt+t:
        // 反射
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2:连接数据库

        try {
            Connection conn=DriverManager.getConnection(url,username,password);
            //System.out.print(conn);
            //3:预编译sql语句
            String sql="delete from student where sno='20018035'";
            PreparedStatement pstm=conn.prepareStatement(sql);

            //执行--
            int i=pstm.executeUpdate();

            //4:处理结果
            System.out.println("影响的行数："+i);

            //5:关闭
            if(pstm!=null){
                pstm.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //3:预编译sql语句

        //4:处理结果

        //5:关闭
    }
}
