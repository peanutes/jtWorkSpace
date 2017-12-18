package tedu.jt.manage.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 前台菜单对象
 *
 * @author Administrator
 * @create 2017-12-17 14:51
 */
public class ItemCatData implements Serializable {
    @JsonProperty(value = "u")
    private String url;
    @JsonProperty(value = "n")
    private String name;
    @JsonProperty(value = "i")
    private List<?> items;

    public String getUrl() {
        return url;
    }

    public ItemCatData setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemCatData setName(String name) {
        this.name = name;
        return this;
    }

    public List<?> getItems() {
        return items;
    }

    public ItemCatData setItems(List<?> items) {
        this.items = items;
        return this;
    }
}
