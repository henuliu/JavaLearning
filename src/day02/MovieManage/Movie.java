package day02.MovieManage;

import lombok.Data;

@Data
public class Movie
{
    private int id;
    private String name;
    private double price;
    private double score;
    private String director;
    private String actor;
    private String info;

    public Movie()
    {
    }
    public Movie(int id, String name, double price, double score, String director, String actor, String info)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.score = score;
        this.director = director;
        this.actor = actor;
        this.info = info;
    }
}
