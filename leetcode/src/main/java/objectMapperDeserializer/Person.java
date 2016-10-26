package objectMapperDeserializer;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Person {
    private String id;
    private String name;
    @JsonDeserialize(using = DescDeserializer.class)
    private Description desc;
    private Map<String, String> maps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Description getDesc() {
        return desc;
    }

    public void setDesc(Description desc) {
        this.desc = desc;
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

}
