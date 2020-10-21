package com.motaharinia.msutility.jstree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.ObjectUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس مدل درخت jstree میباشد
 */
public class JstreeNodeModel implements Serializable {

    /**
     * شناسه اضافه شونده
     */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_incr;


    /**
     * شناسه
     */
    private String id;


    /**
     * عنوان گره
     */
    private String text;


    /**
     * آیکن گره
     */
    private String icon;


    /**
     * وضعیت گره
     */
    private JstreeNodeStateModel state;


    /**
     * لینک گره
     */
    private JstreeNodeAAttrModel a_attr;


    /**
     * نوع گره
     */
    private String type;


    /**
     * داده اضافی گره
     */
    private HashMap<String, Object> data;


    /**
     * لیست گره های زیر مجوعه
     */
    private List<JstreeNodeModel> children;


    /**
     * لینک li گره
     */
    private HashMap<String, Object> li_attr;

    /**
     * والد گره
     */
    @JsonIgnore
    private String parent;

    /**
     * محتویات
     */
    @JsonIgnore
    private String[] nodeContents;


    /**
     * متد سازنده پیش فرض
     */
    public JstreeNodeModel() {

    }


    /**
     * متد سازنده که با دریافت مشخصات ورودی مدل درخت را میسازد
     *
     * @param id شناسه
     * @param text عنوان گره
     * @param children  لیست گره های زیر مجوعه
     * @param icon  آیکن گره
     * @param state  وضعیت گره
     * @param a_attr  لینک گره
     * @param type  نوع گره
     * @param data داده اضافی گره
     */
    public JstreeNodeModel(String id, String text, List<JstreeNodeModel> children, String icon, JstreeNodeStateModel state, JstreeNodeAAttrModel a_attr, String type, HashMap<String, Object> data) {
        this.id = id;
        this.text = text;
        this.children = children;
        this.icon = icon;
        this.state = state;
        this.a_attr = a_attr;
        this.type = type;
        this.data = data;
    }

    @Override
    public String toString() {

        String childrenString = "[]";
        if (!ObjectUtils.isEmpty(children)) {
            childrenString = "[" + System.lineSeparator() + children.stream().map(item ->"      "+ item.toString()).collect(Collectors.joining("," + System.lineSeparator())) + System.lineSeparator() + "]";
        }

        return "JstreeNode{" +
                ", id=" + id +
                ", text=" + text +
                ", children=" + childrenString +
                ", icon=" + icon +
                ", state=" + state +
                ", a_attr=" + a_attr +
                ", li_attr=" + li_attr +
                ", parent=" + parent +
                ", type=" + type +
                ", data=" + data +
                ", nodeContents=" + nodeContents +
                "id_incr=" + id_incr +
                '}';
    }


    //getter-setter:
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public JstreeNodeStateModel getState() {
        return state;
    }

    public void setState(JstreeNodeStateModel state) {
        this.state = state;
    }

    public JstreeNodeAAttrModel getA_attr() {
        return a_attr;
    }

    public void setA_attr(JstreeNodeAAttrModel a_attr) {
        this.a_attr = a_attr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public List<JstreeNodeModel> getChildren() {
        return children;
    }

    public void setChildren(List<JstreeNodeModel> children) {
        this.children = children;
    }

    public HashMap<String, Object> getLi_attr() {
        return li_attr;
    }

    public void setLi_attr(HashMap<String, Object> li_attr) {
        this.li_attr = li_attr;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String[] getNodeContents() {
        return nodeContents;
    }

    public void setNodeContents(String[] nodeContents) {
        this.nodeContents = nodeContents;
    }

    public Long getId_incr() {
        return id_incr;
    }

    public void setId_incr(Long id_incr) {
        this.id_incr = id_incr;
    }

}
