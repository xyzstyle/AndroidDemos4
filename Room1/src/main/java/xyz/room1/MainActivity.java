package xyz.room1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xyz.db.AppDatabase;
import xyz.pojo.Department;
import xyz.pojo.Employee;
import xyz.pojo.InnerJoinResult;
import xyz.pojo.User;
import xyz.db.UserDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test3();
    }

    private void test1() {
        AppDatabase db = AppDatabase.getInstance();
        UserDao userDao = db.userDao();
        User user = new User();
        user.setName("xyz");
        user.setPassword("pass");
        user.setAddress("wz");
        user.setBirthday(new Date());
        userDao.insert(user);
        List<User> users = userDao.getAll();
        Log.d("xyz", users.toString());
    }

    private void test2() {
        List<Employee> list = new ArrayList<>();
        Employee employee = new Employee("Paul", 32, "California", 20000.0,1);
        list.add(employee);
        employee = new Employee("Allen", 25, "Texas", 15000.0,2);
        list.add(employee);
        employee = new Employee("Teddy", 23, "Norway", 20000.0,3);
        list.add(employee);
        employee = new Employee("Mark", 25, "Rich-Mond", 65000.0,1);
        list.add(employee);
        employee = new Employee("David", 27, "Texas", 85000.0,2);
        list.add(employee);
        employee = new Employee("Kim", 22, "South-Hall", 45000.0,3);
        list.add(employee);
        employee = new Employee("James", 24, "Houston", 10000.0,3);
        list.add(employee);

        List<Department> departments = new ArrayList<>();
        Department department = new Department("IT Billing");
        departments.add(department);
        department = new Department("Engineer DP");
        departments.add(department);
        department = new Department("Finance DP");
        departments.add(department);

        AppDatabase db = AppDatabase.getInstance();
        db.departmentDao().insert(departments);
        db.employeeDao().insert(list);


        List<Employee> companyList = db.employeeDao().getAllCompany();
        Log.d("xyz","Company----->" + companyList.size());
        for (Employee result : companyList) {
            Log.d("xyz","result--->" + result.getName() + " " + result.getAge()+" "+result.getAddress()+" "+result.getDepId());
        }
        List<Department> departmentList = db.departmentDao().getAllDepartment();
        Log.d("xyz","Department----->" + departmentList.size());
        for (Department result : departmentList) {
            Log.d("xyz","result--->" + result.getDept() + " " + result.getDept());
        }
    }

    private void test3() {
        AppDatabase db = AppDatabase.getInstance();
        List<InnerJoinResult> companies = db.employeeDao().getDepartmentFromCompany();
        Log.d("xyz","InnerJoinResult----->" + companies.size());
        for (InnerJoinResult result : companies) {
            Log.d("xyz","result--->" + result.getName() + " " +  result.getDepId()+ " "+result.getDept() );

        }
    }
}
