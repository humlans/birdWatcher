package org.example.algorithms;

import org.example.items.Sighting;

import java.util.ArrayList;

public class MergeSort {

    //Function to split the array into smaller bits and calls the functions to sort the smaller arrays.
    public void sortAlphabetically(ArrayList<Sighting> sightings, String sortBy){
        int middle;
        ArrayList<Sighting> left = new ArrayList<>();
        ArrayList<Sighting> right = new ArrayList<>();

        if (sightings.size() > 1) {
            middle = sightings.size() / 2;

            for(int i = 0; i < middle; i++) {
                left.add(sightings.get(i));
            }
            for(int i = middle; i < sightings.size(); i++) {
                right.add(sightings.get(i));
            }
            sortAlphabetically(left, sortBy);
            sortAlphabetically(right, sortBy);

            merge(sightings, left, right, sortBy);
        }
    }

    //Function to merge the arrays after they are sorted.
    private void merge(ArrayList<Sighting> sightings, ArrayList<Sighting> left, ArrayList<Sighting> right, String sortBy) {
        ArrayList<Sighting> temporaryMergeList;

        int sightingIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (compareWithSortBy(left.get(leftIndex), right.get(rightIndex), sortBy)) {
                sightings.set(sightingIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                sightings.set(sightingIndex, right.get(rightIndex));
                rightIndex++;
            }
            sightingIndex++;
        }

        int tempIndex = 0;
        if (leftIndex >= left.size()) {
            temporaryMergeList = right;
            tempIndex = rightIndex;
        }
    else {
            temporaryMergeList = left;
            tempIndex = leftIndex;
        }

        for (int i = tempIndex; i < temporaryMergeList.size(); i++) {
            sightings.set(sightingIndex, temporaryMergeList.get(i));
            sightingIndex++;
        }

    }

    //Function to sort by either date or title.
    private boolean compareWithSortBy(Sighting leftValue, Sighting rightValue, String sortBy) {

        if (sortBy.equals("dateTime")){
            return leftValue.getDate().toLowerCase().compareTo(rightValue.getDate()) <= 0;
        }
        else {
            return leftValue.getTitle().toLowerCase().compareTo(rightValue.getTitle().toLowerCase()) <= 0;
        }
    }
}
