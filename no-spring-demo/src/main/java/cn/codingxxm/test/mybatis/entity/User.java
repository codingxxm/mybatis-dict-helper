package cn.codingxxm.test.mybatis.entity;

import cn.codingxxm.mybatis.dict.helper.anno.Dict;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaoming
 * @since 2022-05-29
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    @Dict(table = "dict", column = "val", text = "name", property = "genderDictText")
    private Integer gender;

    private String genderDictText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getGenderDictText() {
        return genderDictText;
    }

    public void setGenderDictText(String genderDictText) {
        this.genderDictText = genderDictText;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", genderDictText='" + genderDictText + '\'' +
                '}';
    }
}
