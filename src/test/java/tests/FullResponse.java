package tests;

import java.util.ArrayList;

public class FullResponse {
    public ArrayList<Result1> results;
    public int offset;
    public int number;
    public int totalResults;

    public FullResponse() {
    }

    public FullResponse(ArrayList<Result1> results, int offset, int number, int totalResults) {
        this.results = results;
        this.offset = offset;
        this.number = number;
        this.totalResults = totalResults;
    }

    public ArrayList<Result1> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result1> results) {
        this.results = results;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
