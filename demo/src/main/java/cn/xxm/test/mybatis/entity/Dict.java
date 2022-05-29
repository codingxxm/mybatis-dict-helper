package cn.xxm.test.mybatis.entity;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaoming
 * @since 2022-05-29
 */
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer val;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dict{" +
            "id=" + id +
            ", val=" + val +
            ", name=" + name +
        "}";
    }
}
