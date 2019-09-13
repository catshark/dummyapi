package data;

public class Employee {

    private String name;
    private String salary;
    private String age;
    private String profile_image;

    public Employee(String name, String salary, String age, String profile_image) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.profile_image = profile_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProfileImage() {
        return profile_image;
    }

    public void setProfileImage(String profile_image) {
        this.profile_image = profile_image;
    }
}
