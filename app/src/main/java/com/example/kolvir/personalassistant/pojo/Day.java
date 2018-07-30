package com.example.kolvir.personalassistant.pojo;


public class Day {
    private String title;
    private String desc;

    public Day(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }



    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day = (Day) o;

        if (!title.equals(day.title)) return false;
        return desc.equals(day.desc);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + desc.hashCode();
        return result;
    }
}
