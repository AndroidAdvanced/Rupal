package Model;

/**
 * Created by rgi-38 on 14/3/18.
 */

public class SubCategory {

    private int sub_categoryid, catid_ref;
    private String sub_categoryname;


    public String getSub_categoryname() {
        return sub_categoryname;
    }

    public int getSub_categoryid() {
        return sub_categoryid;
    }

    public void setSub_categoryid(int sub_categoryid) {
        this.sub_categoryid = sub_categoryid;
    }

    public int getCatid_ref() {
        return catid_ref;
    }

    public void setCatid_ref(int catid_ref) {
        this.catid_ref = catid_ref;
    }

    public void setSub_categoryname(String sub_categoryname) {
        this.sub_categoryname = sub_categoryname;
    }
}
