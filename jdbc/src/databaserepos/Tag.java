package databaserepos;

public class Tag {
    public Tag() {};

    public Tag(String name) {
        this.name = name;
    }

    public int tag_id;
    public String name;

    public static String sequence = "tags_tag_id_seq";

}
