package com.zxf.repository;

import com.zxf.entity.Student;
import com.zxf.util.JDBCTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentRepository {
    //查询所有数据的方法
    public List<Student> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        try {
            //调用工具类连接数据库
            connection = JDBCTools.getConnection();
            String sql = "select * from user";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            Student student = null;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double score = resultSet.getDouble("score");
                Date date = resultSet.getDate("birthday");
                student = new Student(id, name, score, date);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //调用工具类的方法来释放资源
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        //将查询到的数据库中所有数据返回
        return list;
    }

    /**
     * 向数据库中添加一数据
     * @param name
     * @param score
     */
    public void add(String name, Double score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //连接数据库
            connection = JDBCTools.getConnection();
            String sql = "insert into user(name, score, birthday) values (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, score);
            preparedStatement.setDate(3, new java.sql.Date(1));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
    }

    /**
     * 根据id删除数据库中的一条数据
     * @param id
     */
    public void deleteById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "delete from user where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
    }

    /**
     * 根据id查找数据库中的一条数据
     * @param id
     * @return student
     */
    public Student findById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "select * from user where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idNow = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double score = resultSet.getDouble("score");
                Date date = resultSet.getDate("birthday");
                student = new Student(idNow, name, score, date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return student;
    }

    /**
     * 修改数据库的一条数据
     * @param id
     * @param name
     * @param score
     */
    public void update(int id, String name, Double score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "update user set name = ?, score = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, score);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }

    }
}
