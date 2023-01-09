import java.util.ArrayList;

public class RootRecipes {
    private ArrayList<Results> results;
    private Integer offset;
    private Integer number;
    private Integer totalResults;

    public RootRecipes(ArrayList<Results> recipes, Integer offset, Integer number, Integer totalResults) {
        this.results = recipes;
        this.offset = offset;
        this.number = number;
        this.totalResults = totalResults;
    }

    public RootRecipes() {
    }

    public ArrayList<Results> getRecipes() {
        return results;
    }

    public void setRecipes(ArrayList<Results> recipes) {
        this.results = recipes;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}